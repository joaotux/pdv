package net.originmobi.pdv.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.model.TributacaoRegra;

public interface TributacaoRegraRepository extends JpaRepository<TributacaoRegra, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into tributacao_regra (tributacao_codigo, tipo, uf, cfop_codigo, cst_csosn_codigo, cst_pis, cst_cofins, pis, cofins, "
			+ "aliq_ipi, aliq_icms, cst_ipi_codigo, data_cadastro) values (:codtribu, :tipo, :uf, :cfop, :cst_csosn, :cstpis, :cst_cofins, :pis, :cofins, :aliq_ipi, "
			+ ":aliq_icms, :cst_ipi, :data_cadastro)", nativeQuery = true)
	void cadastrar(@Param("codtribu") Long codtribu, @Param("tipo") String tipo, @Param("uf") String uf,
			@Param("cfop") String cfop, @Param("cst_csosn") String cstCsosn, @Param("cstpis") String cstpis,
			@Param("cst_cofins") String cstcofins, @Param("pis") String pis, @Param("cofins") String cofins,
			@Param("aliq_ipi") String aliqIpi, @Param("aliq_icms") String aliqIcms, @Param("cst_ipi") String cstIpi,
			@Param("data_cadastro") Date dataCadastro);

	List<TributacaoRegra> findByTributacaoCodigoAndTipoEquals(Long codtributacao, EntradaSaida tipo);

	@Transactional
	@Modifying
	@Query(value = "update tributacao_regra set tributacao_codigo = :tributacao_codigo, tipo = :tipo, uf = :uf, cfop_codigo = :cfop, "
			+ "cst_csosn_codigo = :cstcsosn, cst_pis = :cstpis, "
			+ "cst_cofins = :cstcofins, pis = :pis, cofins = :cofins, aliq_ipi = :aliq_ipi, aliq_icms = :aliq_icms, cst_ipi_codigo = :cst_ipi where codigo = :codigo", nativeQuery = true)
	void update(@Param("codigo") Long codregra, @Param("tributacao_codigo") Long codtribu, @Param("tipo") String tipo,
			@Param("uf") String uf, @Param("cfop") String cfop, @Param("cstcsosn") String cstCsosn,
			@Param("cstpis") String cstpis, @Param("cstcofins") String cstcofins, @Param("pis") String pis,
			@Param("cofins") String cofins, @Param("aliq_ipi") String aliqIpi, @Param("aliq_icms") String aliqIcms,
			@Param("cst_ipi") String cstIpi);

}
