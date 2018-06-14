package net.originmobi.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Endereco findByCodigoIn(Long codigo);

	@Transactional
	@Modifying
	@Query(value = "update endereco set cidade_codigo = :cidade, rua = :rua, bairro = :bairro, numero = :numero, cep = :cep, "
			+ "referencia = :referencia where codigo = :codigo", nativeQuery = true)
	public void update(@Param("codigo") Long codigo, @Param("cidade") Long codcidade, @Param("rua") String rua, @Param("bairro") String bairro,
			@Param("numero") String numero, @Param("cep") String cep, @Param("referencia") String referencia);

}
