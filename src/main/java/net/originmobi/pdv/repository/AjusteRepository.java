package net.originmobi.pdv.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.model.Ajuste;

public interface AjusteRepository extends JpaRepository<Ajuste, Long> {

	@Query("select a from Ajuste a")
	Page<Ajuste> lista(Pageable pageable);

	@Query("select a from Ajuste a where codigo = :codigo")
	Page<Ajuste> lista(@Param("codigo") Long codigo, Pageable pageable);
	
}
