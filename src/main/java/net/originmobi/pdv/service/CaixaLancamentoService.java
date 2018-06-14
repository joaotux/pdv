package net.originmobi.pdv.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.repository.CaixaLancamentoRepository;

@Service
public class CaixaLancamentoService {

	@Autowired
	private CaixaLancamentoRepository caixaLancamento;

	@Autowired
	UsuarioService usuarios;

	private Timestamp dataHoraAtual;

	public CaixaLancamentoService() {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String lancamento(CaixaLancamento lancamento) {
		dataHoraAtual = new Timestamp(System.currentTimeMillis());

		try {
			lancamento.setData_cadastro(dataHoraAtual);

			if (!lancamento.getCaixa().isPresent()
					&& lancamento.getCaixa().map(Caixa::getData_fechamento).isPresent()) {
				throw new RuntimeException("Nenhum caixa aberto");
			}

			// se for realizar uma saida de caixa, verifica se tem saldo
			// suficiente para isso
			if (lancamento.getEstilo().equals(EstiloLancamento.SAIDA)) {
				Optional<Double> vlTotalCaixa = lancamento.getCaixa().map(Caixa::getValor_total);
				
				if (lancamento.getValor() > vlTotalCaixa.get()) {
					return "Saldo insuficiente para realizar esta operação";
				}
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
			e.getStackTrace();
			throw new RuntimeException();
		}

		try {
			caixaLancamento.save(lancamento);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Erro ao realizar lançamento, chame o suporte");
		}

		return "Lançamento realizado com sucesso";
	}

	public List<CaixaLancamento> lancamentosDoCaixa(Caixa caixa) {
		return caixaLancamento.findByCaixaEquals(caixa);
	}
}
