package net.originmobi.pdv.repository.notafiscal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.originmobi.pdv.model.NotaFiscalItem;

public interface NotaFiscalItemRepository extends JpaRepository<NotaFiscalItem, Long> {

	@Query(value = "select nfi.cod_prod, prod.descricao, prod.ncm, nfii.cst, nfi.cfop, prod.unidade, nfi.qtd qtd, format(prod.valor_venda, 2, 'de_DE'), "
			+ "format(nfi.vl_total, 2 , 'de_DE') total_produto, format(nfii.v_bc, 2, 'de_DE') bc_icms, format(nfii.v_icms, 2, 'de_DE') vl_icms, "
			+ "format(nfii.p_icms, 2, 'de_DE') aliq_icms, nfi.codigo, nfi.nota_fiscal_codigo from nota_fiscal_item nfi,"
			+ "produto prod, nota_fiscal_item_imposto nfii where nfi.cod_prod = prod.codigo "
			+ "and nfii.codigo = nfi.imposto_codigo and nfi.nota_fiscal_codigo = ?1", nativeQuery = true)
	List<Object> findByNotaFiscalCodigoEquals(Long codigo);
}
