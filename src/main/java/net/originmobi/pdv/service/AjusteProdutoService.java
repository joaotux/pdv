package net.originmobi.pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.model.AjusteProduto;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.repository.AjusteProdutoRepository;

@Service
public class AjusteProdutoService {

	@Autowired
	private AjusteProdutoRepository ajusteprodutos;

	@Autowired
	private ProdutoService produtos;

	@Autowired
	private AjusteService ajustes;

	public List<AjusteProduto> listaProdutosAjuste(Long codAjuste) {
		return ajusteprodutos.findByAjusteCodigoEquals(codAjuste);
	}

	public int buscaProdAjust(Long codAjuste, Long codProd) {
		return ajusteprodutos.buscaProdAjuste(codAjuste, codProd);
	}

	public String addProduto(Long codajuste, Long codprod, int qtd_alteracao) {
		Optional<Ajuste> ajuste = ajustes.busca(codajuste);

		if (ajuste.map(Ajuste::getStatus).get().equals(AjusteStatus.PROCESSADO))
			throw new RuntimeException("Ajuste já esta processado");

		Produto produto = produtos.busca(codprod);
		int estoque_aqual = produto.getEstoque().getQtd();

		int tem = buscaProdAjust(codajuste, codprod);

		if (tem > 0)
			throw new RuntimeException("Este produto já existe neste ajuste");

		if (qtd_alteracao == 0)
			throw new RuntimeException("Quantidade inválido");

		if (ajuste.map(Ajuste::getStatus).get().equals(AjusteStatus.PROCESSADO))
			throw new RuntimeException("Ajuste já processado");

		int novo_estoque = estoque_aqual + qtd_alteracao;

		try {
			ajusteprodutos.insereProduto(codajuste, codprod, estoque_aqual, qtd_alteracao, novo_estoque);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao tentar inserir produto no ajuste, chame o suporte");
		}
		
		return "Ajuste processado com sucesso";
	}

	public String removeProduto(Long codajuste, Long coditem) {
		Optional<Ajuste> ajuste = ajustes.busca(codajuste);
		
		if(ajuste.map(Ajuste::getStatus).get().equals(AjusteStatus.PROCESSADO))
			throw new RuntimeException("Ajuste já esta processado");
		
		try {
			ajusteprodutos.removeProduto(codajuste, coditem);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao tentar remover produto do ajuste, chame o suporte");
		}
		
		return "Produto removido com sucesso";
	}

}
