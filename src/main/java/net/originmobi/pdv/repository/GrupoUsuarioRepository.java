package net.originmobi.pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Usuario;

public interface GrupoUsuarioRepository extends JpaRepository<GrupoUsuario, Long> {

	public List<GrupoUsuario> findByUsuarioIn(Usuario usuario);

	public GrupoUsuario findByCodigoIn(Long codigo);

	@Transactional
	@Modifying
	@Query("update GrupoUsuario set nome = :nome, descricao = :descricao where codigo = :codigo")
	public void update(@Param("nome") String nome, @Param("descricao") String descricao, @Param("codigo") Long codigo);

	@Query(value = "select count(*) from usuario_grupousuario ug where ug.grupo_usuario_codigo = ?1", nativeQuery = true)
	public int grupoTemUsuaio(Long codigo);

	@Transactional
	@Modifying
	@Query(value = "insert into permissoes_grupo_usuario (grupo_usuario_codigo, permissoes_codigo) "
			+ "values (:codgrupo, :codpermissao)", nativeQuery = true)
	public void addPermissao(@Param("codgrupo") Long codgrupo, @Param("codpermissao") Long codpermissao);

	@Query(value = "select count(*) from permissoes_grupo_usuario pgu where pgu.grupo_usuario_codigo = :codgrupo "
			+ "and pgu.permissoes_codigo = :codpermissao", nativeQuery = true)
	public int grupoTemPermissao(@Param("codgrupo") Long codgrupo, @Param("codpermissao") Long codpermissao);

	@Transactional
	@Modifying
	@Query(value = "delete from permissoes_grupo_usuario where permissoes_codigo = :codigo "
			+ "and grupo_usuario_codigo = :codgrupo", nativeQuery = true)
	public void removePermissao(@Param("codigo") Long codigo, @Param("codgrupo") Long codgrupo);

}
