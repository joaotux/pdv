package net.originmobi.pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.RecebimentoParcela;

public interface RecebimentoParcelaRepository extends JpaRepository<RecebimentoParcela, Long> {

	@Query("select p from RecebimentoParcela rp, Parcela p where p.codigo = rp.parcela and rp.recebimento = ?1")
	List<Parcela> parcelasDoRecebimento(Long recebimento);
}
