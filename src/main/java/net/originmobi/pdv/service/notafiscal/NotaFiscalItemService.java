package net.originmobi.pdv.service.notafiscal;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.enumerado.notafiscal.NotaFiscalTipo;
import net.originmobi.pdv.enumerado.produto.ProdutoSubstTributaria;
import net.originmobi.pdv.model.NotaFiscal;
import net.originmobi.pdv.model.NotaFiscalItem;
import net.originmobi.pdv.model.NotaFiscalItemImposto;
import net.originmobi.pdv.model.NotaFiscalTotais;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.model.Tributacao;
import net.originmobi.pdv.model.TributacaoRegra;
import net.originmobi.pdv.repository.notafiscal.NotaFiscalItemRepository;
import net.originmobi.pdv.service.ProdutoService;

@Service
public class NotaFiscalItemService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private NotaFiscalItemRepository itemServer;

	@Autowired
	private NotaFiscalItemImpostoService impostos;

	@Autowired
	private NotaFiscalTotaisServer totais;

	@Autowired
	private ProdutoService produtos;

	@Autowired
	private NotaFiscalService notas;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String insere(Long prod, Long codnota, int qtd, NotaFiscalTipo tipo) {
		Optional<Produto> produtoOptional = produtos.buscaProduto(prod);
		Optional<NotaFiscal> notaFiscalOptional = notas.busca(codnota);

		Produto produto = produtoOptional.orElseThrow(() ->
				new RuntimeException("Nenhum produto encontrado, favor verifique"));
		verificaRegraDeTributacao(tipo, produto);

		Tributacao tributacao = produto.getTributacao();

		Long codImposto = null;
		Long codNotaItem = null;

		NotaFiscal notaFiscal = notaFiscalOptional.orElseThrow(() -> new RuntimeException("Nota fiscal não encontrada"));
		// verifica se já tem o item
		for (int i = 0; i < notaFiscal.getItens().size(); i++) {
			if (notaFiscal.getItens().get(i).getCodProd().equals(prod)) {

				qtd = qtd + notaFiscal.getItens().get(i).getQtd();
				codImposto = notaFiscal.getItens().get(i).getImpostos().getCodigo();
				codNotaItem = notaFiscal.getItens().get(i).getCodigo();
			}
		}

		char origin = tributacao.getRegra().get(0).getCstCsosn().getCodCstCsosn().charAt(0);
		Double vlTotal = produto.getValorVenda() * qtd;
		String uniTribu = produto.getUnidade();
		int modBcIcms = produto.getModBcIcms().getTipo();
		Double vlUnidade = produto.getValorVenda();

		// pega uf do destinatário
		String ufDestinatario = notaFiscal.getDestinatario().getEndereco().getCidade().getEstado()
				.getSigla();

		TributacaoRegra regra = null;

		// pega a regra da tributação do produto que é da mesma uf do destinatário e do
		// mesmo
		// estilo da nota
		for (int i = 0; i < produto.getTributacao().getRegra().size(); i++) {
			String ufRegra = produto.getTributacao().getRegra().get(i).getUf().getSigla();
			String tipoRegra = produto.getTributacao().getRegra().get(i).getTipo().toString();
			String tipoNota = notaFiscal.getTipo().toString();

			if (ufRegra.equals(ufDestinatario) && tipoRegra.equals(tipoNota)) {
				regra = produto.getTributacao().getRegra().get(i);
			}
		}

		if (regra == null)
			throw new RuntimeException("Nenhuma regra de tributação cadastrada para a UF do destinatário");

		String cfop = regra.getCfop().getCodCfop();

		// calcula impostos da nota
		NotaFiscalItemImposto imposto = impostos.calcula(codImposto, vlTotal, regra, origin, modBcIcms);

		// cria item da nota com imposto vinculado
		NotaFiscalItem item = new NotaFiscalItem(prod, qtd, vlTotal, uniTribu, qtd, vlUnidade, notaFiscal,
				imposto, cfop);

		// se for diferente de null, se trata de uma atualização
		if (codNotaItem != null)
			item.setCodigo(codNotaItem);

		try {
			itemServer.save(item);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Erro ao salvar item na nota, chame o suporte");
		}

		// atualiza totais da nota
		NotaFiscalTotais total = notaFiscal.getTotais();
		Long codNota = notaFiscal.getCodigo();
		totais.atualiza(codNota, total);

		return "ok";
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void remove(Long notaitem, Long codnota) {
		
		try {
			itemServer.deleteById(notaitem);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Erro ao tentar remover o item da nota, chame o suporte");
		}
		
		Optional<NotaFiscal> notaFiscal = notas.busca(codnota);
		if (notaFiscal.isPresent()) {
			NotaFiscalTotais total = notaFiscal.get().getTotais();
			totais.atualiza(codnota, total);
		}
	}

	private void verificaRegraDeTributacao(NotaFiscalTipo tipo, Produto produto) {
		if (produto.getTributacao() == null)
			throw new RuntimeException("Produto sem tributação, favor verifique");

		if (produto.getNcm().isEmpty())
			throw new RuntimeException("Produto sem código NCM, favor verifique");

		if (produto.getSubtributaria().ordinal() == ProdutoSubstTributaria.SIM.ordinal()
				&& produto.getCest().isEmpty())
			throw new RuntimeException("Produto de substituição tributária sem código CEST, favor verifique");

		if (produto.getUnidade().isEmpty())
			throw new RuntimeException("Produto sem unidade, favor verifique");

		// verifica se a tributação do produto possue regra para o estilo de nota
		// selecionado
		if (tipo.equals(NotaFiscalTipo.SAIDA)) {
			verificaRegraTributacaoTipo(produto, EntradaSaida.SAIDA, "Tributação sem regra de saída, verifique");
		} else {
			verificaRegraTributacaoTipo(produto, EntradaSaida.ENTRADA, "Tributação sem regra de entrada, verifique");
		}
	}

	private void verificaRegraTributacaoTipo(Produto produto, EntradaSaida saida, String resposta) {
		int aux = 0;
		for (int i = 0; i < produto.getTributacao().getRegra().size(); i++) {
			if (produto.getTributacao().getRegra().get(i).getTipo().equals(saida))
				aux = aux + 1;
		}

		if (aux == 0)
			throw new RuntimeException(resposta);
	}

	public List<Object> buscaItensNota(Long codigo) {
		return itemServer.findByNotaFiscalCodigoEquals(codigo);
	}

}
