package net.originmobi.pdv.service;

import net.originmobi.pdv.model.*;
import net.originmobi.pdv.repository.EmpresaParametrosRepository;
import net.originmobi.pdv.repository.EmpresaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class EmpresaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EmpresaRepository empresas;

    @Autowired
    private EmpresaParametrosRepository parametros;

    @Autowired
    private RegimeTributarioService regimes;

    @Autowired
    private CidadeService cidades;

    @Autowired
    private EnderecoService enderecos;

    public void cadastro(Empresa empresa) {

        try {
            empresas.save(empresa);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Optional<Empresa> verificaEmpresaCadastrada() {

        return empresas.buscaEmpresaCadastrada();

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String merger(Long codigo, String nome, String nomeFantasia, String cnpj, String ie, int serie,
                         int ambiente, Long codRegime, Long codendereco, Long codcidade, String rua, String bairro, String numero,
                         String cep, String referencia, Double aliqCalcCredito) {

        if (codigo != null) {
            try {
                empresas.update(codigo, nome, nomeFantasia, cnpj, ie, codRegime);
                parametros.update(serie, ambiente, aliqCalcCredito);
                enderecos.update(codendereco, codcidade, rua, bairro, numero, cep, referencia);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro ao salvar dados da empresa, chame o suporte";
            }
        } else {
            EmpresaParametro parametro = new EmpresaParametro();

            try {
                parametro.setAmbiente(ambiente);
                parametro.setSerieNfe(serie);
                parametro.setpCredSN(aliqCalcCredito);
                parametros.save(parametro);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro ao salvar dados da empresa, chame o suporte";
            }

            Optional<RegimeTributario> tributario = regimes.busca(codRegime);
            Optional<Cidade> cidade = cidades.busca(codcidade);

            LocalDate dataAtual = LocalDate.now();
            Endereco endereco = new Endereco(rua, bairro, numero, cep, referencia, Date.valueOf(dataAtual),
                    cidade.orElse(null));

            try {
                enderecos.cadastrar(endereco);
                Empresa empresa = new Empresa(nome, nomeFantasia, cnpj, ie, tributario.orElse(null), endereco, parametro);
                empresas.save(empresa);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro ao salvar dados da empresa, chame o suporte";
            }
        }

        return "Empresa salva com sucesso";
    }

}
