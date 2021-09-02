package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.repository.CaixaLancamentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class CaixaLancamentoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UsuarioService usuarios;
    @Autowired
    private CaixaLancamentoRepository caixaLancamento;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String lancamento(CaixaLancamento lancamento) {

        try {
            lancamento.setDataCadastro(new Timestamp(System.currentTimeMillis()));

            verificaAberturaCaixa(lancamento);

            if (!temSaldoSuficienteParaLancamento(lancamento)) {
                throw new RuntimeException("Saldo insuficiente para realizar esta operação");
            }

            // se for do tipo SAIDA, converte o valor que vier para negativo
            if (lancamento.getEstilo().equals(EstiloLancamento.SAIDA) && lancamento.getValor() > 0) {
                Double valorNegativo = (lancamento.getValor() * -1);
                lancamento.setValor(valorNegativo);
            }

            // verifica o tipo de lançamento e de acordo com ele coloca uma
            // observação no lançamento caso a mesma venha vazia.
            if (lancamento.getObservacao().isEmpty()) {
                String observacao = "";
                if (lancamento.getTipo().equals(TipoLancamento.SANGRIA))
                    observacao = lancamento.getObservacao().isEmpty() ? "Sangria de caixa" : lancamento.getObservacao();
                else if (lancamento.getTipo().equals(TipoLancamento.SUPRIMENTO))
                    observacao = lancamento.getObservacao().isEmpty() ? "Suprimento de caixa"
                            : lancamento.getObservacao();

                lancamento.setObservacao(observacao);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao realizar lançamento, chame o suporte");
        }

        try {
            caixaLancamento.save(lancamento);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao realizar lançamento, chame o suporte");
        }

        return "Lançamento realizado com sucesso";
    }

    private boolean temSaldoSuficienteParaLancamento(CaixaLancamento lancamento) {
        // se for realizar uma saida de caixa, verifica se tem saldo
        // suficiente para isso
        if (lancamento.getEstilo().equals(EstiloLancamento.SAIDA)) {
            Optional<Double> vlTotalCaixaOptional = lancamento.getCaixa().map(Caixa::getValorTotal);

            return vlTotalCaixaOptional.isPresent() && lancamento.getValor() <= vlTotalCaixaOptional.get();
        }
        return true;
    }

    private void verificaAberturaCaixa(CaixaLancamento lancamento) {
        if (!lancamento.getCaixa().isPresent()
                && lancamento.getCaixa().map(Caixa::getDataFechamento).isPresent()) {
            throw new RuntimeException("Nenhum caixa aberto");
        }
    }

    public List<CaixaLancamento> lancamentosDoCaixa(Caixa caixa) {
        return caixaLancamento.findByCaixaEquals(caixa);
    }
}
