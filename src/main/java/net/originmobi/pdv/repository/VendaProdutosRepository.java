package net.originmobi.pdv.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.model.Venda;
import net.originmobi.pdv.model.VendaProduto;

public interface VendaProdutosRepository extends JpaRepository<VendaProduto, Long> {

	/*
	 * Esta query define os dados de apresentação dos produtos da tela de venda,
	 * ao mexer na ordem do dados, tem que alterar na tela tabProdutos.html da
	 * venda
	 */
	@Query(value = "select p.codigo as cod_prod, p.descricao, p.valor_venda, vp.codigo, p.balanca, vp.valor_balanca from produto p, venda_produtos vp "
			+ "where p.codigo = vp.produto_codigo and vp.venda_codigo = ?1", nativeQuery = true)
	public List<Object> findByProdutosDaVenda(Long codigoVen);

	public List<Produto> findByVendaIn(Venda venda);

	public List<VendaProduto> findByVendaEquals(Long codigoVen);

	public VendaProduto findByCodigoIn(Long codigo);

	@Transactional
	@Modifying
	@Query("delete from VendaProduto where codigo = :codigo")
	public void removeProduto(@Param("codigo") Long codigo);

	@Query(value = "select produto_codigo, count(produto_codigo) from venda_produtos where venda_codigo = ?1 "
			+ "group by produto_codigo", nativeQuery = true)
	public List<Object[]> buscaQtdProduto(Long codvenda);

}
