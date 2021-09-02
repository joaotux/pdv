package net.originmobi.pdv.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
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
import net.originmobi.pdv.model.Fornecedor;
import net.originmobi.pdv.model.Pagar;
import net.originmobi.pdv.model.PagarParcela;
import net.originmobi.pdv.model.PagarTipo;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.PagarRepository;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;

@Service
public class PagarService {

	@Autowired
	private PagarRepository pagarRepo;

	@Autowired
	private PagarParcelaService pagarParcelaServ;

	@Autowired
	private FornecedorService fornecedores;

	@Autowired
	private CaixaService caixas;

	@Autowired
	private UsuarioService usuarios;

	@Autowired
	private CaixaLancamentoService lancamentos;

	public List<Pagar> listar() {
		return pagarRepo.findAll();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String cadastrar(Long codFornecedor, Double valor, String obs, LocalDate vencimento, PagarTipo tipo) {
		LocalDate dataAtual = LocalDate.now();
		DataAtual dataTime = new DataAtual();

		obs = obs.isEmpty() ? tipo.getDescricao() : obs;

		Optional<Fornecedor> OptionaFornecedor = fornecedores.busca(codFornecedor);
		Fornecedor fornecedor = OptionaFornecedor.get();

		Pagar pagar = new Pagar(obs, valor, dataAtual, fornecedor, tipo);

		try {
			pagarRepo.save(pagar);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Erro ao lançar despesa, chame o suporte");
		}

		try {
			pagarParcelaServ.cadastrar(valor, valor, 0, dataTime.dataAtualTimeStamp(), vencimento, pagar);
		} catch (Exception e) {
			e.getStackTrace();
			throw new RuntimeException("Erro ao lançar despesa, chame o suporte");
		}

		return "Despesa lançada com sucesso";
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String quitar(Long codparcela, Double vlPago, Double vldesc, Double vlacre, Long codCaixa) {

		Optional<PagarParcela> parcela = pagarParcelaServ.busca(codparcela);

		DecimalFormat df = new DecimalFormat("#0.00");

		if (vlPago > Double.valueOf(df.format(parcela.map(PagarParcela::getValor_restante).get()).replace(",", ".")))
			throw new RuntimeException("Valor de pagamento inválido");

		Double vlquitado = (vlPago + vlacre) + parcela.map(PagarParcela::getValor_pago).get();
		Double vlRestante = (parcela.map(PagarParcela::getValor_restante).get() - (vlPago + vldesc));
		Double vlDesconto = parcela.map(PagarParcela::getValor_desconto).get() + vldesc;
		Double vlAcrescimo = parcela.map(PagarParcela::getValor_acrescimo).get() + vlacre;

		vlRestante = vlRestante < 0 ? 0.0 : vlRestante;

		int quitado = Double.valueOf(df.format(vlRestante).replace(",", ".")) <= 0 ? 1 : 0;

		DataAtual dataAtual = new DataAtual();

		parcela.get().setValor_pago(vlquitado);
		parcela.get().setValor_restante(vlRestante);
		parcela.get().setValor_desconto(vlDesconto);
		parcela.get().setValor_acrescimo(vlAcrescimo);
		parcela.get().setQuitado(quitado);
		parcela.get().setData_pagamento(dataAtual.dataAtualTimeStamp());

		try {
			pagarParcelaServ.merger(parcela.get());
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Ocorreu um erro ao realizar o pagamento, chame o suporte");
		}

		Aplicacao aplicacao = Aplicacao.getInstancia();

		Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());
		Optional<Caixa> caixa = caixas.busca(codCaixa);

		if (vlPago + vlacre > caixa.map(Caixa::getValorTotal).get())
			throw new RuntimeException("Saldo insuficiente para realizar este pagamento");

		try {
			CaixaLancamento lancamento = new CaixaLancamento("Referente a pagamento de despesas", vlPago + vlacre,
					TipoLancamento.PAGAMENTO, EstiloLancamento.SAIDA, caixa.get(), usuario);

			// vincula a parcela do pagar ao caixa_lancametno
			lancamento.setParcelaPagar(parcela.get());

			lancamentos.lancamento(lancamento);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Ocorreu um erro ao realizar o pagamento, chame o suporte");
		}

		return "Pagamento realizado com sucesso";
	}

}
