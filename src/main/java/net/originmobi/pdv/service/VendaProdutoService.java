package net.originmobi.pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.model.Venda;
import net.originmobi.pdv.model.VendaProduto;
import net.originmobi.pdv.repository.VendaProdutosRepository;

@Service
public class VendaProdutoService {

	@Autowired
	private VendaProdutosRepository vendaProdutos;

	public void salvar(VendaProduto vendaProduto) {
		vendaProdutos.save(vendaProduto);
	}

	public List<Object> listaProdutosVenda(Venda venda) {
		return vendaProdutos.findByProdutosDaVenda(venda.getCodigo());
	}

	public List<Produto> listaVendaProduto(Venda venda) {
		return vendaProdutos.findByVendaIn(venda);
	}

	public List<VendaProduto> listaVendaProdutos(Long codigoVen) {
		return vendaProdutos.findByVendaEquals(codigoVen);
	}

	public VendaProduto busca(Long codigo) {
		return vendaProdutos.findByCodigoIn(codigo);
	}

	public void remove(VendaProduto vendaProduto) {
		vendaProdutos.delete(vendaProduto);
	}

	public void removeProduto(Long posicaoProd) {
		vendaProdutos.removeProduto(posicaoProd);
	}

	public List<Object[]> buscaQtdProduto(Long codvenda) {
		return vendaProdutos.buscaQtdProduto(codvenda);
	}

}
