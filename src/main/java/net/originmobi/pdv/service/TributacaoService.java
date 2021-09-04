package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Empresa;
import net.originmobi.pdv.model.Tributacao;
import net.originmobi.pdv.repository.TributacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TributacaoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TributacaoRepository tributacoes;

    @Autowired
    private EmpresaService empresas;

    @Transactional
    public String cadastro(Tributacao tributacao) {
        LocalDate dataAtual = LocalDate.now();

        Optional<Empresa> empresa = empresas.verificaEmpresaCadastrada();

        if (!empresa.isPresent())
            return "sem empresa";

        try {
            tributacao.setEmpresa(empresa.get());
            tributacao.setDataCadastro(Date.valueOf(dataAtual));
			tributacao = tributacoes.save(tributacao);
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
        	throw new RuntimeException("Erro ao cadastrar tributação");
        }

        return tributacao.getCodigo().toString();
    }

    public List<Tributacao> lista() {
        return tributacoes.findAll();
    }

}
