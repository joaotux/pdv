package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;
import net.originmobi.pdv.filter.AjusteFilter;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.repository.AjusteRepository;
import net.originmobi.pdv.singleton.Aplicacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

/*Classe Alterada para os testes de unidade*/
@Service
public class AjusteService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    LocalDate dataAtual;
    @Autowired
    private AjusteRepository ajustes;
    @Autowired
    private ProdutoService produtos;

    public Page<Ajuste> lista(Pageable pageable, AjusteFilter filter) {
        if (filter.getCodigo() != null)
            return ajustes.lista(filter.getCodigo(), pageable);

        return ajustes.lista(pageable);
    }

    public Optional<Ajuste> busca(Long codigo) {
        return ajustes.findById(codigo);
    }

    public Long novo() {
        dataAtual = LocalDate.now();


        Aplicacao aplicacao = Aplicacao.getInstancia();
        String usuarioAtual = aplicacao.getUsuarioAtual();


        Ajuste ajuste = new Ajuste(AjusteStatus.APROCESSAR, usuarioAtual, Date.valueOf(dataAtual));
        return ajustes.save(ajuste).getCodigo();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processar(Long codajuste, String obs) {
        dataAtual = LocalDate.now();
        Optional<Ajuste> ajusteOptional = ajustes.findById(codajuste);

        if (!ajusteOptional.isPresent()) {
            throw new RuntimeException("Ajuste não encontrado");
        }

        Ajuste ajuste = ajusteOptional.get();

        if (ajuste.getStatus().equals(AjusteStatus.PROCESSADO))
            throw new RuntimeException("Ajuste já processado");

        for (int i = 0; i < ajuste.getProdutos().size(); i++) {
            Long codprod = ajuste.getProdutos().get(i).getProduto().getCodigo();
            int qtdAlteracao = ajuste.getProdutos().get(i).getQtdAlteracao();

            EntradaSaida tipo = qtdAlteracao > 0 ? EntradaSaida.ENTRADA : EntradaSaida.SAIDA;
            String origemOperacao = "Referente ao ajuste de estoque " + codajuste;

            try {
                produtos.ajusteEstoque(codprod, qtdAlteracao, tipo, origemOperacao, Date.valueOf(dataAtual));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("Erro ao tentar processar o ajuste, chame o suporte");
            }
        }

        ajuste.setStatus(AjusteStatus.PROCESSADO);
        ajuste.setObservacao(obs);
        ajuste.setDataProcessamento(Date.valueOf(dataAtual));
        try {
            ajustes.save(ajuste);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar processar o ajuste, chame o suporte");
        }

        return "Ajuste realizado com sucesso";
    }

    public void remover(Ajuste ajuste) {

        if (ajuste.getStatus().equals(AjusteStatus.PROCESSADO)) {
            throw new RuntimeException("O ajuste já esta processado");
        }

        try {
            ajustes.deleteById(ajuste.getCodigo());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar cancelar o ajuste");
        }
    }

}
