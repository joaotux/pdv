package net.originmobi.pdv.service.notafiscal;

import net.originmobi.pdv.enumerado.notafiscal.NotaFiscalTipo;
import net.originmobi.pdv.model.*;
import net.originmobi.pdv.repository.notafiscal.NotaFiscalRepository;
import net.originmobi.pdv.service.EmpresaService;
import net.originmobi.pdv.service.PessoaService;
import net.originmobi.pdv.xml.nfe.GeraXmlNfe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalService {

    private static final String CAMINHO_XML = "/src/main/resources/xmlNfe/";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private NotaFiscalRepository notasFiscais;
    @Autowired
    private EmpresaService empresas;
    @Autowired
    private NotaFiscalTotaisServer notaTotais;
    @Autowired
    private PessoaService pessoas;

    public List<NotaFiscal> lista() {
        return notasFiscais.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String cadastrar(Long coddesti, String natureza, NotaFiscalTipo tipo) {
        Optional<Empresa> empresaOptional = empresas.verificaEmpresaCadastrada();
        Optional<Pessoa> pessoaOptional = pessoas.buscaPessoa(coddesti);

        if (!empresaOptional.isPresent())
            throw new RuntimeException("Nenhuma empresa cadastrada, verifique");

        if (!pessoaOptional.isPresent())
            throw new RuntimeException("Favor, selecione o destinatário");

        Empresa empresa = empresaOptional.get();
        Pessoa pessoa = pessoaOptional.get();

        // prepara informações iniciais da nota fiscal
        FreteTipo frete = new FreteTipo();
        frete.setCodigo(4L);
        NotaFiscalFinalidade finalidade = new NotaFiscalFinalidade();
        finalidade.setCodigo(1L);
        int modelo = 55;
        int serie = empresa.getParametro().getSerieNfe();

        if (empresa.getParametro().getSerieNfe() == 0)
            throw new RuntimeException("Não existe série cadastrada para o modelo 55, verifique");

        // opção 1 é emissão normal, as outras opções (2, 3, 4, 5) são para contigência
        int tipoEmissao = 1;
        LocalDate dataAtual = LocalDate.now();
        Date cadastro = Date.valueOf(dataAtual);
        String verProc = "0.0.1-beta";
        int tipoAmbiente = empresa.getParametro().getAmbiente();

        // cadastra os totais iniciais da nota fiscal
        NotaFiscalTotais totais = new NotaFiscalTotais(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        try {
            notaTotais.cadastro(totais);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao cadastrar a nota, chame o suporte");
        }

        // cadastra a nota fiscal
        NotaFiscal nota = null;
        try {
            // pega ultima nota cadastradas + 1
            Long numeroNota = notasFiscais.buscaUltimaNota(serie);

            NotaFiscal notaFiscal = new NotaFiscal(numeroNota, modelo, tipo, natureza, serie, empresa,
                    pessoa, tipoEmissao, verProc, frete, finalidade, totais, tipoAmbiente, cadastro);

            nota = notasFiscais.save(notaFiscal);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao cadastrar a nota, chame o suporte");
        }

        return nota.getCodigo().toString();
    }

    public Integer geraDV(String codigo) {
        try {
            int total = 0;
            int peso = 2;

            for (int i = 0; i < codigo.length(); i++) {
                total += (codigo.charAt((codigo.length() - 1) - i) - '0') * peso;
                peso++;
                if (peso == 10) {
                    peso = 2;
                }
            }
            int resto = total % 11;
            return (resto == 0 || resto == 1) ? 0 : (11 - resto);
        } catch (Exception e) {
            return 0;
        }
    }

    public void salvaXML(String xml, String chaveNfe) {
        Path diretorio;
        String contexto = "";

        try {
            contexto = new File(".").getCanonicalPath();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("Erro ao pegar o contexto {0}", e);
        }

        diretorio = Paths.get(contexto + CAMINHO_XML);

        try {
            PrintWriter out = new PrintWriter(new FileWriter(diretorio.toString() + "/" + chaveNfe + ".xml"));
            out.write(xml);
            out.close();
            logger.info("Arquivo gravado com sucesso em {}", diretorio);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /*
     * responsável por remover o xml quando o mesmo já existe na nota que foi
     * regerada
     */
    public void removeXml(String chaveAcesso) {
        String contexto = "";

        try {
            contexto = new File(".").getCanonicalPath();
        } catch (Exception e) {
            logger.error("Erro ao pegar o contexto {0}", e);
        }

        try {
            File file = new File(contexto + CAMINHO_XML + chaveAcesso + ".xml");
            logger.info("XML para deletar {}", file);
            if (file.exists())
                Files.delete(Paths.get(file.getPath()));
        } catch (Exception e) {
            logger.error("Erro ao deletar XML {0}", e);
        }
    }

    public Optional<NotaFiscal> busca(Long codnota) {
        return notasFiscais.findById(codnota);
    }

    public void emitir(NotaFiscal notaFiscal) {
        GeraXmlNfe geraXmlNfe = new GeraXmlNfe();

        // gera o xml e pega a chave de acesso do mesmo
        String chaveNfe = geraXmlNfe.gerarXML(notaFiscal);

        // seta a chave de acesso na nota fiscal para gravala no banco
        notaFiscal.setChaveAcesso(chaveNfe);

        notasFiscais.save(notaFiscal);
    }

    public int totalNotaFiscalEmitidas() {
        return notasFiscais.totalNotaFiscalEmitidas();
    }

}
