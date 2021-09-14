package net.originmobi.pdv.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.model.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {

	@Query("select c from Caixa c order by 1 desc")
	public List<Caixa> findByCodigoOrdenado();

	public Caixa findByCodigoIn(Long codigo);

	@Query("select c from Caixa c where c.tipo = 'CAIXA' and c.dataFechamento is null")
	public Optional<Caixa> caixaAberto();

	@Query("select c from Caixa c where c.dataFechamento is null")
	public List<Caixa> caixasAbertos();

	@Query("select c from Caixa c where c.usuario.codigo = ?1 and c.tipo = 'CAIXA' and c.dataFechamento is null")
	public Caixa findByCaixaAbertoUsuario(Long codigoUsu);
	
	@Query("select c from Caixa c where c.tipo in ('CAIXA', 'COFRE') and c.dataCadastro = ?1 order by c.codigo desc")
	public List<Caixa> buscaCaixasPorDataAbertura(Date dataCadastro);
	
	@Query("select c from Caixa c where c.tipo in ('CAIXA', 'COFRE')  order by c.tipo, case when c.dataFechamento is null then 1 else 0 end desc")
	public List<Caixa> listaCaixas();
	
	@Query(value = "select b.codigo from caixa b where b.tipo = 'BANCO' and b.data_fechamento is null", nativeQuery = true)
	public List<Caixa> buscaBancos(CaixaTipo tipo);
	
	@Query("select c from Caixa c where c.tipo = ?1 and c.dataFechamento is null")
	public List<Caixa> buscaCaixaTipo(CaixaTipo tipo);

	@Query("select c from Caixa c where c.tipo = :tipo and c.dataCadastro = :data_cadastro order by c.codigo desc")
	public List<Caixa> buscaCaixaTipoData(@Param("tipo") CaixaTipo tipo, @Param("data_cadastro") Date dataCadastro);

}
