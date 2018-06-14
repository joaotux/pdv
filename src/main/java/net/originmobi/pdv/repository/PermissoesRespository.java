package net.originmobi.pdv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Permissoes;

public interface PermissoesRespository extends JpaRepository<Permissoes, Long> {

	public List<Permissoes> findByGrupoUsuarioIn(GrupoUsuario grupoUsuario);

	@Query(value = "select * from permissoes p, permissoes_grupo_usuario pgu where pgu.permissoes_codigo = p.codigo "
			+ "and pgu.grupo_usuario_codigo = ?1 order by pgu.permissoes_codigo", nativeQuery = true)
	List<Permissoes> listaPermissoesDoGrupo(Long codigo);
}
