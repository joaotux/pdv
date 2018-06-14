package net.originmobi.pdv.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public Page<Produto> findByDescricaoContaining(String descricao, Pageable pageable);

	public Produto findByCodigoIn(Long codigo);

	@Transactional
	@Modifying
	@Query(value = "insert into produto (fornecedor_codigo, categoria_codigo, grupo_codigo, balanca, descricao, valor_custo, valor_venda, "
			+ "data_validade, controla_estoque, ativo, unidade, subtributaria, data_cadastro, ncm, cest, tributacao_codigo, bc_icms_codigo, vendavel) "
			+ "values (:codforne, :categoria, :grupo, :balanca, :descricao, :vlcusto, :vlvenda, :validade, :controleEstoque, :ativo, :unidade, "
			+ ":subtribu, :dataCadastro, :ncm, :cest, :tributacao, :modbc, :vendavel)", nativeQuery = true)
	public void insere(@Param("codforne") Long codforne, @Param("categoria") Long codcategoria,
			@Param("grupo") Long codgrupo, @Param("balanca") int balanca, @Param("descricao") String descricao,
			@Param("vlcusto") Double valorCusto, @Param("vlvenda") Double valorVenda,
			@Param("validade") Date dataValidade, @Param("controleEstoque") String controleEstoque,
			@Param("ativo") String situacao, @Param("unidade") String unitario, @Param("subtribu") int subtribu,
			@Param("dataCadastro") java.sql.Date cadastro, @Param("ncm") String ncm, @Param("cest") String cest,
			@Param("tributacao") Long tributacao, @Param("modbc") Long modbc, @Param("vendavel") String vendavel);

	@Transactional
	@Modifying
	@Query(value = "update produto set fornecedor_codigo = :fornecedor, categoria_codigo = :categoria, grupo_codigo = :grupo, balanca = :balanca, "
			+ "descricao = :descricao, valor_custo = :vlcusto, valor_venda = :vlvenda, data_validade = :validade, controla_estoque = :controleEstoque, ativo = :ativo, "
			+ "unidade = :unidade, subtributaria = :subtribu, ncm = :ncm, cest = :cest, tributacao_codigo = :tributacao, bc_icms_codigo = :modbc, vendavel = :vendavel "
			+ "where codigo = :codprod", nativeQuery = true)
	public void atualiza(@Param("codprod") Long codprod, @Param("fornecedor") Long codforne,
			@Param("categoria") Long codcategoria, @Param("grupo") Long codgrupo, @Param("balanca") int balanca,
			@Param("descricao") String descricao, @Param("vlcusto") Double valorCusto,
			@Param("vlvenda") Double valorVenda, @Param("validade") Date dataValidade,
			@Param("controleEstoque") String controleEstoque, @Param("ativo") String situacao,
			@Param("unidade") String unitario, @Param("subtribu") int subtribu, @Param("ncm") String ncm,
			@Param("cest") String cest, @Param("tributacao") Long tributacao, @Param("modbc") Long modbc,
			@Param("vendavel") String vendavel);

	@Transactional
	@Modifying
	@Query(value = "insert into estoque_movimentacao (produto_codigo, tipo, qtd, origem_operacao, data_movimentacao) "
			+ "values (:codprod, :tipo, :qtd, :origem, :data_movimentacao)", nativeQuery = true)
	public void movimentaEstoque(@Param("codprod") Long codprod, @Param("tipo") String tipo, @Param("qtd") int qtd,
			@Param("origem") String origem, @Param("data_movimentacao") java.sql.Date data_movimentacao);

	@Query(value = "select pe.qtd from produto p, produto_estoque pe where pe.produto_codigo = p.codigo and p.codigo = :codprod", nativeQuery = true)
	public int saldoEstoque(@Param("codprod") Long codprod);

	@Query("select p from Produto p where p.vendavel = 'SIM'")
	public List<Produto> produtosVendaveis();

}
