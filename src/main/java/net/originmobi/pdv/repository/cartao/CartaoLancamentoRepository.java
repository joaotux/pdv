package net.originmobi.pdv.repository.cartao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.model.cartao.CartaoLancamento;

public interface CartaoLancamentoRepository extends JpaRepository<CartaoLancamento, Long> {
	
	@Query(value = "select cl.* from cartao_lancamento cl where cl.situacao like :situacao and cl.tipo like :tipo and cl.data_recebimento like :data_recebimento", nativeQuery = true)
	List<CartaoLancamento> buscaLancamentos(@Param("situacao") String situacao, @Param("tipo") String tipo, @Param("data_recebimento") String data_recebimento);
}
