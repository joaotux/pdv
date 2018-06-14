package net.originmobi.pdv.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.Receber;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into parcela (valor_total, valor_desconto, valor_acrescimo, valor_recebido, valor_restante, receber_codigo, quitado, sequencia,"
			+ "data_cadastro, data_vencimento) values (:valor_total, :valor_desconto, :valor_acrescimo, :valor_recebido, :valor_restante, "
			+ ":receber_codigo, :quitado, :sequencia, :data_cadastro, :data_vencimento)", nativeQuery = true)
	public void gerarparcela(@Param("valor_total") Double total, @Param("valor_desconto") Double desconto,
			@Param("valor_acrescimo") Double acrescimo, @Param("valor_recebido") Double recebido,
			@Param("valor_restante") Double restante, @Param("receber_codigo") Receber receber,
			@Param("quitado") int quitado, @Param("sequencia") int sequencia,
			@Param("data_cadastro") Timestamp cadastro, @Param("data_vencimento") Date vencimento);

	@Transactional
	@Modifying
	@Query(value = "update parcela set valor_desconto = :vldesconto, valor_acrescimo = :vlacrescimo, valor_recebido = :vlrecebido, "
			+ "valor_restante = :vlrestante, quitado = :quitado, data_pagamento = :data_pagamento where codigo = :codigo", nativeQuery = true)
	public void receber(@Param("vldesconto") Double vldesconto, @Param("vlacrescimo") Double vlacrescimo,
			@Param("vlrecebido") Double vlrecebido, @Param("vlrestante") Double vlrestante,
			@Param("quitado") int quitado, @Param("data_pagamento") Timestamp data_pagamento,
			@Param("codigo") Long codParcela);

	@Query(value = "select * from parcela p, receber r, pessoa pes where r.codigo = p.receber_codigo and  pes.codigo = r.pessoa_codigo "
			+ "and pes.codigo = :codigo and p.quitado = :quitado order by p.codigo", nativeQuery = true)
	public List<Parcela> buscaReceberDaPessoaCodigo(@Param("codigo") Long codpessoa, @Param("quitado") Boolean quitado);
	
	@Query(value = "select coalesce(format(sum(p.valor_restante), 2, 'de_DE'), format(0, 2, 'de_DE')) from parcela p, receber r, pessoa pes where r.codigo = p.receber_codigo and  pes.codigo = r.pessoa_codigo "
			+ "and pes.codigo = :codigo and p.quitado = :quitado order by p.codigo", nativeQuery = true)
	public String somaTotaAReceberPessoaCodigo(@Param("codigo") Long codpessoa, @Param("quitado") Boolean quitado);

	@Query("select p from Parcela p where p.quitado = ?1")
	public Page<Parcela> findByParcelasOrdenadas(int quitado, Pageable pageable);

	Parcela findByCodigoEquals(Long codigo);
}
