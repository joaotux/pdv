package net.originmobi.pdv.repository.notafiscal;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.model.NotaFiscal;
import net.originmobi.pdv.model.NotaFiscalTotais;

public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

	@Transactional
	@Modifying
	@Query(value = "insert into nota_fiscal (modelo, tipo, natureza_operacao, serie, emissor_codigo, destinatario_codigo, tipo_emissao, "
			+ "verProc, frete_tipo_codigo, finalidade_codigo, totais_codigo, data_cadastro) values (:modelo, :tipo, :natureza, :serie, :emissor,"
			+ " :destinatario, :tipoEmissao, :verProc, :frete, :finalidade, :totais, :cadastro)", nativeQuery = true)
	Integer insere(@Param("modelo") int modelo, @Param("tipo") int tipo, @Param("natureza") String natureza,
			@Param("serie") int serie, @Param("emissor") Long empresa, @Param("destinatario") Long coddesti,
			@Param("tipoEmissao") int tipoEmissao, @Param("verProc") String verProc, @Param("frete") int tipoFrete,
			@Param("finalidade") int finalidade, @Param("totais") Long total_codigo, @Param("cadastro") Date cadastro);
	
	@Query("select n.totais from NotaFiscal n where n.codigo = ?1")
	NotaFiscalTotais buscaTotaisDaNota(Long codigo);

	@Query(value = "select coalesce(max(numero),0) + 1 from nota_fiscal where serie = ?1", nativeQuery = true)
	Long buscaUltimaNota(int serie);
	
	@Query(value = "select count(*) from nota_fiscal", nativeQuery = true)
	int totalNotaFiscalEmitidas();

}
