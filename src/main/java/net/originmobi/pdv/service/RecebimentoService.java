package net.originmobi.pdv.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.icu.text.DecimalFormat;

import net.originmobi.pdv.controller.TituloService;
import net.originmobi.pdv.enumerado.TituloTipo;
import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Recebimento;
import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.RecebimentoRepository;
import net.originmobi.pdv.service.cartao.CartaoLancamentoService;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;

@Service
public class RecebimentoService {

	@Autowired
	private RecebimentoRepository recebimentos;

	@Autowired
	private PessoaService pessoas;

	@Autowired
	private RecebimentoParcelaService receParcelas;

	@Autowired
	private ParcelaService parcelas;

	@Autowired
	private CaixaService caixas;

	@Autowired
	private UsuarioService usuarios;

	@Autowired
	private CaixaLancamentoService lancamentos;

	@Autowired
	private TituloService titulos;

	@Autowired
	private CartaoLancamentoService cartaoLancamentos;

	public String abrirRecebimento(Long codpes, String[] arrayParcelas) {
		List<Parcela> lista = new ArrayList<>();

		DataAtual dataAtual = new DataAtual();
		Double vlTotal = 0.0;

		for (int i = 0; i < arrayParcelas.length; i++) {
			Parcela parcela = parcelas.busca(Long.decode(arrayParcelas[i]));

			if (parcela.getQuitado() == 1)
				throw new RuntimeException("Parcela " + parcela.getCodigo() + " já esta quitada, verifique.");

			if (parcela.getReceber().getPessoa().getCodigo() != codpes)
				throw new RuntimeException("A parcela " + parcela.getCodigo() + " não pertence ao cliente selecionado");

			try {
				lista.add(parcela);

				vlTotal = vlTotal + parcela.getValor_restante();

			} catch (Exception e) {
				e.getMessage();
				throw new RuntimeException("Erro ao receber, chame o suporte");
			}
		}

		Optional<Pessoa> pessoa = pessoas.buscaPessoa(codpes);

		if (!pessoa.isPresent())
			throw new RuntimeException("Cliente não encontrado");

		Recebimento recebimento = new Recebimento(vlTotal, dataAtual.dataAtualTimeStamp(), pessoa.get(), lista);

		try {
			recebimentos.save(recebimento);
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Erro ao receber, chame o suporte");
		}

		return recebimento.getCodigo().toString();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String receber(Long codreceber, Double vlrecebido, Double vlacrescimo, Double vldesconto, Long codtitulo) {
		Optional<Recebimento> recebimento = recebimentos.findById(codreceber);
		Optional<Titulo> titulo = titulos.busca(codtitulo);

		if (codtitulo == 0 || codtitulo == null)
			throw new RuntimeException("Selecione um título para realizar o recebimento");

		if (recebimento.map(Recebimento::getData_processamento).isPresent())
			throw new RuntimeException("Recebimento já esta fechado");

		// vincula o titulo ao recebimento
		recebimento.get().setTitulo(titulo.get());

		DecimalFormat formata = new DecimalFormat("0.00");
		Double vlrecebimento = Double
				.valueOf(formata.format(recebimento.map(Recebimento::getValor_total).get()).replaceAll(",", "."));

		if (vlrecebido > vlrecebimento)
			throw new RuntimeException("Valor de recebimento é superior aos títulos");

		List<Parcela> listParcelas = receParcelas.parcelasDoReceber(codreceber);

		if (listParcelas.isEmpty())
			throw new RuntimeException("Recebimento não possue parcelas");

		if (vlrecebido <= 0.0)
			throw new RuntimeException("Valor de recebimento inválido");

		// guarda o valor do lançamento de caixa
		Double vllancamento = vlrecebido;

		// verifica cada parcela que veio e realiza o seu recebimento individual
		for (int i = 0; i < listParcelas.size(); i++) {

			if (vlrecebido > 0) {
				Double vlsobra = vlrecebido - listParcelas.get(i).getValor_restante();
				vlsobra = vlsobra < 0 ? 0 : vlsobra;

				Double vlquitado = vlsobra - vlrecebido;
				vlquitado = vlquitado < 0 ? vlquitado * -1 : vlquitado;

				vlrecebido = vlsobra;

				Long parcela = listParcelas.get(i).getCodigo();

				try {
					parcelas.receber(parcela, vlquitado, 0.00, 0.00);
				} catch (Exception e) {
					e.getMessage();
					throw new RuntimeException("Ocorreu um erro ao realizar o recebimento, chame o suporte");
				}
			}

		}

		Aplicacao aplicacao = new Aplicacao();
		Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

		// pega a sigla do titulo
		String sigla = titulo.map(Titulo::getTipo).get().getSigla();

		// verifica se é um lançamento do tipo cartão para lançar o cartao_lancamento
		if (sigla.equals(TituloTipo.CARTDEB.toString()) || sigla.equals(TituloTipo.CARTCRED.toString())) {
			cartaoLancamentos.lancamento(vllancamento, titulo);

		} else {
			Optional<Caixa> caixa = caixas.caixaAberto();
			CaixaLancamento lancamento = new CaixaLancamento("Referente ao recebimento " + codreceber, vllancamento,
					TipoLancamento.RECEBIMENTO, EstiloLancamento.ENTRADA, caixa.get(), usuario);

			// vincula o recebimento ao caixa_lancamento
			lancamento.setRecebimento(recebimento.get());

			// Faz um caixa lançamento no valor do receber;
			try {
				lancamentos.lancamento(lancamento);
			} catch (Exception e) {
				e.getMessage();
				throw new RuntimeException("Ocorreu um erro ao realizar o recebimento, chame o suporte");
			}
		}

		try {
			DataAtual dataAtual = new DataAtual();

			recebimento.get().setValor_recebido(vllancamento);
			recebimento.get().setValor_acrescimo(vlacrescimo);
			recebimento.get().setValor_desconto(vldesconto);
			recebimento.get().setData_processamento(dataAtual.dataAtualTimeStamp());

			recebimentos.save(recebimento.get());
		} catch (Exception e) {
			e.getMessage();
			throw new RuntimeException("Ocorreu um erro ao realizar o recebimento, chame o suporte");
		}

		return "Recebimento realizado com sucesso";
	}

	public String remover(Long codigo) {
		Optional<Recebimento> recebimento = recebimentos.findById(codigo);

		if (recebimento.map(Recebimento::getData_processamento).isPresent())
			throw new RuntimeException("Esse recebimento não pode ser removido, pois ele já esta processado");

		try {
			recebimentos.deleteById(codigo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException("Erro ao remover orçamento, chame o suporte");
		}

		return "removido com sucesso";
	}

}
