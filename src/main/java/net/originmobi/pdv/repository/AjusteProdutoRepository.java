package net.originmobi.pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.AjusteProduto;

public interface AjusteProdutoRepository extends JpaRepository<AjusteProduto, Long> {

	List<AjusteProduto> findByAjusteCodigoEquals(Long codAjuste);

	@Transactional
	@Modifying
	@Query(value = "insert into ajuste_produtos (ajuste_codigo, produto_codigo, estoque_atual, qtd_alteracao, qtd_nova) "
			+ "values (:codajuste, :codprod, :estoque_atual, :qtd_alteracao, :qtd_nova)", nativeQuery = true)
	void insereProduto(@Param("codajuste") Long codajuste, @Param("codprod") Long codprod,
			@Param("estoque_atual") int estoque_aqual, @Param("qtd_alteracao") int qtd_alteracao,
			@Param("qtd_nova") int qtd_nova);

	@Query(value = "select count(*) from ajuste_produtos where ajuste_codigo = :codAjuste and produto_codigo = :codprod", nativeQuery = true)
	int buscaProdAjuste(@Param("codAjuste") Long codAjuste, @Param("codprod") Long codProd);

	@Transactional
	@Modifying
	@Query(value = "delete from ajuste_produtos where ajuste_codigo = :ajuste and codigo = :codigo", nativeQuery = true)
	void removeProduto(@Param("ajuste") Long codajuste, @Param("codigo") Long coditem);
}
