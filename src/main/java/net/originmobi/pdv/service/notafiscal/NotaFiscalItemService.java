package net.originmobi.pdv.service.notafiscal;

import java.util.List;
import java.util.Optional;

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
		Optional<Produto> produto = produtos.buscaProduto(prod);
		Optional<NotaFiscal> notaFiscal = notas.busca(codnota);

		verificaRegraDeTributacao(tipo, produto);

		Tributacao tributacao = produto.map(Produto::getTributacao).get();

		Long codImposto = null;
		Long codNotaItem = null;

		// verifica se já tem o item
		for (int i = 0; i < notaFiscal.map(NotaFiscal::getItens).get().size(); i++) {
			if (notaFiscal.map(NotaFiscal::getItens).get().get(i).getCodProd().equals(prod)) {

				qtd = qtd + notaFiscal.map(NotaFiscal::getItens).get().get(i).getQtd();
				codImposto = notaFiscal.map(NotaFiscal::getItens).get().get(i).getImpostos().getCodigo();
				codNotaItem = notaFiscal.map(NotaFiscal::getItens).get().get(i).getCodigo();
			}
		}

		char origin = tributacao.getRegra().get(0).getCst_csosn().getCst_csosn().toString().charAt(0);
		Double vlTotal = produto.map(Produto::getValor_venda).get() * qtd;
		String uniTribu = produto.map(Produto::getUnidade).get();
		int modBcIcms = produto.map(Produto::getModBcIcms).get().getTipo();
		Double vlUnidade = produto.map(Produto::getValor_venda).get();

		// pega uf do destinatário
		String ufDestinatario = notaFiscal.map(NotaFiscal::getDestinatario).get().getEndereco().getCidade().getEstado()
				.getSigla();

		TributacaoRegra regra = null;

		// pega a regra da tributação do produto que é da mesma uf do destinatário e do
		// mesmo
		// estilo da nota
		for (int i = 0; i < produto.map(Produto::getTributacao).get().getRegra().size(); i++) {
			String ufRegra = produto.map(Produto::getTributacao).get().getRegra().get(i).getUf().getSigla();
			String tipoRegra = produto.map(Produto::getTributacao).get().getRegra().get(i).getTipo().toString();
			String tipoNota = notaFiscal.map(NotaFiscal::getTipo).get().toString();

			if (ufRegra.equals(ufDestinatario)) {
				if (tipoRegra.equals(tipoNota)) {
					regra = produto.map(Produto::getTributacao).get().getRegra().get(i);
				}
			}
		}

		if (regra == null)
			throw new RuntimeException("Nenhuma regra de tributação cadastrada para a UF do destinatário");

		String cfop = regra.getCfop().getCfop();

		// calcula impostos da nota
		NotaFiscalItemImposto imposto = impostos.calcula(codImposto, vlTotal, regra, origin, modBcIcms);

		// cria item da nota com imposto vinculado
		NotaFiscalItem item = new NotaFiscalItem(prod, qtd, vlTotal, uniTribu, qtd, vlUnidade, notaFiscal.get(),
				imposto, cfop);

		// se for diferente de null, se trata de uma atualização
		if (codNotaItem != null)
			item.setCodigo(codNotaItem);

		try {
			itemServer.save(item);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao salvar item na nota, chame o suporte");
		}

		// atualiza totais da nota
		NotaFiscalTotais total = notaFiscal.get().getTotais();
		Long codNota = notaFiscal.get().getCodigo();
		totais.atualiza(codNota, total);

		return "ok";
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void remove(Long notaitem, Long codnota) {
		
		try {
			itemServer.deleteById(notaitem);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao tentar remover o item da nota, chame o suporte");
		}
		
		Optional<NotaFiscal> notaFiscal = notas.busca(codnota);
		NotaFiscalTotais total = notaFiscal.get().getTotais();
		totais.atualiza(codnota, total);
	}

	private void verificaRegraDeTributacao(NotaFiscalTipo tipo, Optional<Produto> produto) {
		if (!produto.isPresent())
			throw new RuntimeException("Nenhum produto encontrado, favor verifique");

		if (!produto.map(Produto::getTributacao).isPresent())
			throw new RuntimeException("Produto sem tributação, favor verifique");

		if (produto.map(Produto::getNcm).get().isEmpty())
			throw new RuntimeException("Produto sem código NCM, favor verifique");

		if (produto.map(Produto::getSubtributaria).get().ordinal() == ProdutoSubstTributaria.SIM.ordinal()
				&& produto.map(Produto::getCest).get().isEmpty())
			throw new RuntimeException("Produto de substituição tributária sem código CEST, favor verifique");

		if (produto.map(Produto::getUnidade).get().isEmpty())
			throw new RuntimeException("Produto sem unidade, favor verifique");

		// verifica se a tributação do produto possue regra para o estilo de nota
		// selecionado
		if (tipo.equals(NotaFiscalTipo.SAIDA)) {
			int aux = 0;
			for (int i = 0; i < produto.map(Produto::getTributacao).get().getRegra().size(); i++) {
				if (produto.map(Produto::getTributacao).get().getRegra().get(i).getTipo().equals(EntradaSaida.SAIDA))
					aux = aux + 1;
			}

			if (aux == 0)
				throw new RuntimeException("Tributação sem regra de saída, verifique");
		} else {
			int aux = 0;
			for (int i = 0; i < produto.map(Produto::getTributacao).get().getRegra().size(); i++) {
				if (produto.map(Produto::getTributacao).get().getRegra().get(i).getTipo().equals(EntradaSaida.ENTRADA))
					aux = aux + 1;
			}

			if (aux == 0)
				throw new RuntimeException("Tributação sem regra de entrada, verifique");
		}
	}

	public List<Object> buscaItensNota(Long codigo) {
		return itemServer.findByNotaFiscalCodigoEquals(codigo);
	}

}
