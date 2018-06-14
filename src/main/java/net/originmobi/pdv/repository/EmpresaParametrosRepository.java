package net.originmobi.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.EmpresaParametro;

public interface EmpresaParametrosRepository extends JpaRepository<EmpresaParametro, Long> {

	@Transactional
	@Modifying
	@Query(value = "update empresa_parametros set serie_nfe = :serie, tipo_ambiente_codigo = :ambiente, p_credsn = :aliqCalcCredito", nativeQuery = true)
	void update(@Param("serie") int serie, @Param("ambiente") int ambiente,
			@Param("aliqCalcCredito") Double aliqCalcCredito);

}
