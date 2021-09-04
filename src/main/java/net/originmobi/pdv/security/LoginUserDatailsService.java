package net.originmobi.pdv.security;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Permissoes;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.GrupoUsuarioRepository;
import net.originmobi.pdv.repository.PermissoesRespository;
import net.originmobi.pdv.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class LoginUserDatailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarios;

	@Autowired
	private GrupoUsuarioRepository grupos;

	@Autowired
	private PermissoesRespository permissoes;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarios.findByUserContaining(username);

		if (usuario == null)
			throw new UsernameNotFoundException("Usuário não encontrado!");

		return new UsuarioSistema(usuario.getPessoa().getNome(), usuario.getUser(), usuario.getSenha(),
				authorities(usuario));
	}

	public Collection<GrantedAuthority> authorities(Usuario usuario) {
		return authorities(grupos.findByUsuarioIn(usuario));
	}

	public Collection<GrantedAuthority> authorities(List<GrupoUsuario> grupos) {
		Collection<GrantedAuthority> auths = new ArrayList<>();

		for (GrupoUsuario grupo : grupos) {
			List<Permissoes> lista = permissoes.findByGrupoUsuarioIn(grupo);

			for (Permissoes permissao : lista) {
				auths.add(new SimpleGrantedAuthority("ROLE_" + permissao.getNome()));
			}
		}
		return auths;
	}

}
