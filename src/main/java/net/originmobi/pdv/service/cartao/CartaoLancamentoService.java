package net.originmobi.pdv.service.cartao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.enumerado.TituloTipo;
import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.enumerado.cartao.CartaoSituacao;
import net.originmobi.pdv.enumerado.cartao.CartaoTipo;
import net.originmobi.pdv.filter.CartaoFilter;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.model.cartao.CartaoLancamento;
import net.originmobi.pdv.model.cartao.MaquinaCartao;
import net.originmobi.pdv.repository.cartao.CartaoLancamentoRepository;
import net.originmobi.pdv.service.CaixaLancamentoService;
import net.originmobi.pdv.service.UsuarioService;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;

@Service
public class CartaoLancamentoService {

	@Autowired
	private CartaoLancamentoRepository repository;

	@Autowired
	private CaixaLancamentoService caixaLancamentos;

	@Autowired
	private UsuarioService usuarios;

	private LocalDate dataAtual;

	public void lancamento(Double vl_parcela, Optional<Titulo> titulo) {
		Double taxa = 0.0;
		Double vl_taxa = 0.0;
		Double vl_liq_parcela = 0.0;

		Double taxa_ante = 0.0;
		Double vl_taxa_ante = 0.0;
		Double vl_liq_ant = 0.0;

		CartaoTipo tipo = null;
		int dias = 0;

		// verifica se é debito ou crédito e pega os valores corretos do titulo
		if (titulo.get().getTipo().getSigla().equals(TituloTipo.CARTDEB.toString())) {
			taxa = titulo.get().getMaquina().getTaxa_debito();
			dias = titulo.get().getMaquina().getDias_debito();
			tipo = CartaoTipo.DEBITO;

		} else if (titulo.get().getTipo().getSigla().equals(TituloTipo.CARTCRED.toString())) {
			taxa = titulo.get().getMaquina().getTaxa_credito();
			dias = titulo.get().getMaquina().getDias_credito();
			tipo = CartaoTipo.CREDITO;
		}

		vl_taxa = (vl_parcela * taxa) / 100;
		vl_liq_parcela = vl_parcela - vl_taxa;

		taxa_ante = titulo.get().getMaquina().getTaxa_antecipacao();
		vl_taxa_ante = (vl_parcela * taxa_ante) / 100;
		vl_liq_ant = vl_parcela - vl_taxa_ante;

		MaquinaCartao maquinaCartao = titulo.get().getMaquina();

		DataAtual data = new DataAtual();
		dataAtual = LocalDate.now();
		String data_recebimento = data.DataAtualIncrementa(dias);

		CartaoLancamento lancamento = new CartaoLancamento(vl_parcela, taxa, vl_taxa, vl_liq_parcela, taxa_ante,
				vl_taxa_ante, vl_liq_ant, maquinaCartao, tipo, CartaoSituacao.APROCESSAR,
				Date.valueOf(data_recebimento), Date.valueOf(dataAtual));

		try {
			repository.save(lancamento);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<CartaoLancamento> listar(CartaoFilter filter) {
		String situacao = filter.getSituacao() == null ? "%" : filter.getSituacao().toString();
		String tipo = filter.getTipo() == null ? "%" : filter.getTipo().toString();
		String data_recebimento = filter.getData_recebimento() == null || filter.getData_recebimento().isEmpty() ? "%"
				: filter.getData_recebimento().toString().replace("/", "-");
		return repository.buscaLancamentos(situacao, tipo, data_recebimento);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String processar(CartaoLancamento cartaoLancamento) {

		if (cartaoLancamento.getSituacao().equals(CartaoSituacao.PROCESSADO))
			throw new RuntimeException("Registro já processado");

		if (cartaoLancamento.getSituacao().equals(CartaoSituacao.ANTECIPADO))
			throw new RuntimeException("Registro já foi antecipado");

		Double valor = cartaoLancamento.getVlLiqParcela();
		TipoLancamento tipo = TipoLancamento.RECEBIMENTO;
		EstiloLancamento estilo = EstiloLancamento.ENTRADA;
		Caixa banco = cartaoLancamento.getMaquina_cartao().getBanco();

		Aplicacao aplicacao = Aplicacao.getInstancia();
		Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

		CaixaLancamento lancamento = new CaixaLancamento("Referênte a processamento de cartão", valor, tipo, estilo,
				banco, usuario);

		try {
			caixaLancamentos.lancamento(lancamento);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar realizar o processamento, chame o suporte");
		}

		try {
			cartaoLancamento.setSituacao(CartaoSituacao.PROCESSADO);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar realizar o processamento, chame o suporte");
		}

		return "Processamento realizado com sucesso";
	}

	public String antecipar(CartaoLancamento cartaoLancamento) {
		if (cartaoLancamento.getSituacao().equals(CartaoSituacao.PROCESSADO))
			throw new RuntimeException("Registro já processado");

		if (cartaoLancamento.getSituacao().equals(CartaoSituacao.ANTECIPADO))
			throw new RuntimeException("Registro já foi antecipado");

		Double valor = cartaoLancamento.getVlLiqAntecipacao();
		TipoLancamento tipo = TipoLancamento.RECEBIMENTO;
		EstiloLancamento estilo = EstiloLancamento.ENTRADA;
		Caixa banco = cartaoLancamento.getMaquina_cartao().getBanco();

		Aplicacao aplicacao = Aplicacao.getInstancia();
		Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

		CaixaLancamento lancamento = new CaixaLancamento(
				"Referênte a antecipação de cartão código " + cartaoLancamento.getCodigo(), valor, tipo, estilo, banco,
				usuario);

		try {
			caixaLancamentos.lancamento(lancamento);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar realizar a antecipação, chame o suporte");
		}

		try {
			cartaoLancamento.setSituacao(CartaoSituacao.ANTECIPADO);
			repository.save(cartaoLancamento);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar realizar a antecipação, chame o suporte");
		}

		return "Antecipação realizada com sucesso";
	}

}
