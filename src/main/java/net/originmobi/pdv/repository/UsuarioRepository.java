package net.originmobi.pdv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.originmobi.pdv.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByPessoaCodigoEquals(Long codigo);

	public Usuario findByUserEquals(String user);

	public Usuario findByUserContaining(String usuario);

	public Usuario findByCodigoIn(Long codigo);
	
}
