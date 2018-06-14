package net.originmobi.pdv.repository.notafiscal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.originmobi.pdv.model.NotaFiscalTotais;

public interface NotaFiscalTotaisRepository extends JpaRepository<NotaFiscalTotais, Long> {

	@Query(value = "select coalesce(sum(nfi.vl_total), 0) total, coalesce(sum(nfii.v_icms), 0) icms, coalesce(sum(nfii.v_pis), 0) pis, "
			+ "coalesce(sum(nfii.v_cofins), 0) cofins, coalesce(sum(nfii.v_ipi), 0) ipi "
			+ "from	nota_fiscal_item nfi, nota_fiscal_item_imposto nfii "
			+ "where nfii.codigo = nfi.imposto_codigo "
			+ "and nfi.nota_fiscal_codigo = ?1", nativeQuery = true)
	String calcula(Long codNota);

}
