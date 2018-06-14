package net.originmobi.pdv.repository;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.Pagar;
import net.originmobi.pdv.model.PagarParcela;

public interface PagarParcelaRespository extends JpaRepository<PagarParcela, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into parcela_pagar (valor_total, valor_restante, valor_desconto, valor_acrescimo, valor_pago, quitado, data_cadastro, "
			+ "data_vencimento, pagar_codigo) values (:total, :restante, :vldesc, :vlacre, :vlpago, :quitado, :cadastro, :vencimento, "
			+ ":pagar)", nativeQuery = true)
	void geraParcela(@Param("total") Double vltotal, @Param("restante") Double vlrestante,
			@Param("vldesc") Double vldesc, @Param("vlacre") Double vlacre, @Param("vlpago") Double vlpago,
			@Param("quitado") int quitado, @Param("cadastro") Timestamp cadastro,
			@Param("vencimento") LocalDate vencimento, @Param("pagar") Pagar pagar);

	@Query("select p from PagarParcela p where p.quitado = 0 order by p.data_vencimento")
	Page<PagarParcela> listaOrdenada(Pageable pageable);

	@Query(value = "select * from parcela_pagar pp, pagar p, fornecedor f where p.codigo = pp.pagar_codigo and f.codigo = p.fornecedor_codigo "
			+ "and f.nome like %:nome% order by pp.quitado", nativeQuery = true)
	Page<PagarParcela> listaOrdenada(@Param("nome") String nome, Pageable pageable);

	@Query(value = "select coalesce(format(sum(pp.valor_restante), 2, 'de_DE'), '0,00') from pagar p, parcela_pagar pp where pp.pagar_codigo = p.codigo "
			+ "and pp.quitado = 0", nativeQuery = true)
	String valorDespesasAbertas();
}
