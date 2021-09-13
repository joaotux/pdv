package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.TelefoneTipo;
import net.originmobi.pdv.filter.PessoaFilter;
import net.originmobi.pdv.model.Cidade;
import net.originmobi.pdv.model.Endereco;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Telefone;
import net.originmobi.pdv.repository.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LocalDate dataAtual = LocalDate.now();

    @Autowired
    private PessoaRepository pessoas;

    @Autowired
    private CidadeService cidades;

    @Autowired
    private EnderecoService enderecos;

    @Autowired
    private TelefoneService telefones;

    public List<Pessoa> lista() {
        return pessoas.findAll();
    }

    public Pessoa busca(Long codigo) {
        return pessoas.findByCodigoIn(codigo);
    }

    public Optional<Pessoa> buscaPessoa(Long codigo) {
        return pessoas.findById(codigo);
    }

    public List<Pessoa> filter(PessoaFilter filter) {
        String nome = filter.getNome() == null ? "%" : filter.getNome();
        return pessoas.findByNomeContaining(nome);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String cadastrar(Long codpessoa, String nome, String apelido, String cpfcnpj, String dataNascimentoStr,
                            String observacao, Long codendereco, Long codcidade, String rua, String bairro, String numero, String cep,
                            String referencia, Long codfone, String fone, String tipo, RedirectAttributes attributes)
            throws ParseException {

        if (codpessoa == 0 && pessoas.findByCpfcnpjContaining(cpfcnpj) != null) {
            throw new RuntimeException("Já existe uma pessoa cadastrada com este CPF/CNPJ, verifique");
        }

        // Endereço
        Optional<Cidade> cidadeOptional = cidades.busca(codcidade);

        if (!cidadeOptional.isPresent()) {
            throw new RuntimeException("Cidade não encontrada");
        }
        if(fone.length() != 10 && fone.length() != 11){
            throw new RuntimeException("Número de telefone inválido");
        }

        Cidade cidade = cidadeOptional.get();

        Endereco endereco = new Endereco();
        if (codendereco != 0)
            endereco.setCodigo(codendereco);

        endereco.setCidade(cidade);
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setNumero(numero);
        endereco.setCep(cep);
        endereco.setReferencia(referencia);

        Endereco ende = enderecos.cadastrar(endereco);

        // Telefone
        Telefone telefone = new Telefone();
        if (codfone != 0)

            telefone.setCodigo(codfone);
        telefone.setFone(fone);

        TelefoneTipo tipoFone = tipo.equals(TelefoneTipo.FIXO.toString()) ? TelefoneTipo.FIXO : TelefoneTipo.CELULAR;
        telefone.setTipo(tipoFone);

        List<Telefone> fones = new ArrayList<>();
        fones.add(telefones.cadastrar(telefone));

        // Pessoa
        Pessoa pessoa = new Pessoa();
        if (codpessoa != 0)
            pessoa.setCodigo(codpessoa);

        pessoa.setNome(nome);
        pessoa.setApelido(apelido);
        pessoa.setCpfcnpj(cpfcnpj);

        SimpleDateFormat formata = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimento = new Date(Calendar.getInstance().getTimeInMillis());
        if(!dataNascimentoStr.isEmpty()){
            dataNascimento = new Date(formata.parse(dataNascimentoStr).getTime());
        }
        pessoa.setDataNascimento(dataNascimento);
        pessoa.setObservacao(observacao);
        pessoa.setDataCadastro(Date.valueOf(dataAtual));

        // Vincula endereço
        pessoa.setEndereco(ende);

        // Vincula telefone
        pessoa.setTelefone(fones);

        try {
            pessoas.save(pessoa);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar cadastrar pessoa, chame o suporte");
        }

        return "Pessoa salva com sucesso";
    }

}
