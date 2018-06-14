package net.originmobi.pdv.repository;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.model.Receber;

public interface ReceberRepository extends JpaRepository<Receber, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into receber (observacao, valor_total, valor_recebido, valor_desconto, valor_acrescimo, valor_restante, quitado, sequencia, "
			+ "data_cadastro, data_vencimento, pessoa_codigo) values (:observacao, :valor_total, :valor_recebido, :valor_desconto, :valor_acrescimo, "
			+ ":valor_restante, :quitado, :sequencia, :data_cadastro, :data_vencimento, :pessoa_codigo)", nativeQuery = true)
	public void lancaReceber(@Param("observacao") String observacao, @Param("valor_total") Double valor_total,
			@Param("valor_recebido") Double valor_recebido, @Param("valor_desconto") Double valor_desconto,
			@Param("valor_acrescimo") Double valor_acrescimo, @Param("valor_restante") Double valor_restante,
			@Param("quitado") int quitado, @Param("sequencia") int sequencia,
			@Param("data_cadastro") Timestamp data_cadastro, @Param("data_vencimento") String data_vencimento,
			@Param("pessoa_codigo") Long codigo);

	@Transactional
	@Modifying
	@Query(value = "update receber set valor_desconto = :valor_desconto, valor_acrescimo = :valor_acrescimo, valor_recebido = :valor_recebido, "
			+ "valor_restante = :valor_restante, quitado = :quitado, data_finalizado = :data_finalizado where codigo = :codigo", nativeQuery = true)
	public void recebeTitulo(@Param("codigo") Long codigo, @Param("valor_desconto") Double vldesconto,
			@Param("valor_acrescimo") Double vlacrescomo, @Param("valor_recebido") Double vlrecebido,
			@Param("valor_restante") Double vlfechamento, @Param("quitado") int quitado,
			@Param("data_finalizado") Timestamp data_finalizado);

	public List<Receber> findByPessoaNomeContaining(String nome);

	@Query(value = "select coalesce(format(sum(p.valor_restante), 2, 'de_DE'), '0,00') from receber r, parcela p where p.receber_codigo = r.codigo "
			+ "and p.quitado = 0", nativeQuery = true)
	public String total_a_receber();
}
