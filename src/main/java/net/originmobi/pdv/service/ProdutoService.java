package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.enumerado.produto.ProdutoControleEstoque;
import net.originmobi.pdv.enumerado.produto.ProdutoSubstTributaria;
import net.originmobi.pdv.filter.ProdutoFilter;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtos;

	@Autowired
	private VendaProdutoService vendaProdutos;

	private LocalDate dataAtual = LocalDate.now();

	public List<Produto> listar() {
		return produtos.findAll();
	}
	
	public List<Produto> listaProdutosVendaveis() {
		return produtos.produtosVendaveis();
	}

	public Produto busca(Long codigoProduto) {
		return produtos.findByCodigoIn(codigoProduto);
	}

	public Optional<Produto> buscaProduto(Long codigo) {
		return produtos.findById(codigo);
	}

	public Page<Produto> filter(ProdutoFilter filter, Pageable pageable) {
		String descricao = filter.getDescricao() == null ? "%" : filter.getDescricao();
		return produtos.findByDescricaoContaining(descricao, pageable);
	}

	public String merger(Long codprod, Long codforne, Long codcategoria, Long codgrupo, int balanca, String descricao,
			Double valorCusto, Double valorVenda, java.util.Date dataValidade, String controleEstoque, String situacao,
			String unitario, ProdutoSubstTributaria subtribu, String ncm, String cest, Long tributacao, Long modbc, String vendavel) {

		if (codprod == 0) {
			try {
				produtos.insere(codforne, codcategoria, codgrupo, balanca, descricao, valorCusto, valorVenda,
						dataValidade, controleEstoque, situacao, unitario, subtribu.ordinal(), Date.valueOf(dataAtual),
						ncm, cest, tributacao, modbc, vendavel);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "Erro a cadastrar produto, chame o suporte";
			}
		} else {

			try {
				produtos.atualiza(codprod, codforne, codcategoria, codgrupo, balanca, descricao, valorCusto, valorVenda,
						dataValidade, controleEstoque, situacao, unitario, subtribu.ordinal(), ncm, cest, tributacao,
						modbc, vendavel);

				return "Produto atualizado com sucesso";
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return "Erro a atualizar produto, chame o suporte";
			}

		}

		return "Produdo cadastrado com sucesso";
	}

	@SuppressWarnings("static-access")
	public void movimentaEstoque(Long codvenda, EntradaSaida tipo) {
		List<Object[]> resultado = vendaProdutos.buscaQtdProduto(codvenda);

		for (int i = 0; i < resultado.size(); i++) {
			Long codprod = Long.decode(resultado.get(i)[0].toString());
			int qtd = Integer.parseInt(resultado.get(i)[1].toString());

			Produto produto = produtos.findByCodigoIn(codprod);

			if (produto.getControla_estoque().equals(ProdutoControleEstoque.SIM)) {

				// estoque atual do produto
				int qtd_estoque = produtos.saldoEstoque(codprod);
				String origem_operacao = "Venda " + codvenda.toString();

				if (qtd <= qtd_estoque) {
					produtos.movimentaEstoque(codprod, tipo.SAIDA.toString(), qtd, origem_operacao,
							Date.valueOf(dataAtual));
				} else {
					throw new RuntimeException(
							"O produto de código " + codprod + " não tem estoque suficiente, verifique");
				}
			} else {
				System.out.println("Produto não controla estoque");
			}
		}

	}
	
	public void ajusteEstoque(Long codprod, int qtd, EntradaSaida tipo, String origem_operacao, Date data_movimentacao) {
		Produto produto = produtos.findByCodigoIn(codprod);
		
		if (produto.getControla_estoque().equals(ProdutoControleEstoque.NAO))
			throw new RuntimeException("O produto de código " + codprod + " não controla estoque, verifique");
		
		produtos.movimentaEstoque(codprod, tipo.toString(), qtd, origem_operacao, data_movimentacao);
		
	}

}
