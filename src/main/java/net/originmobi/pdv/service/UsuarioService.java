package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarios;

	@Autowired
	private GrupoUsuarioService grupos;

	LocalDate dataAtual = LocalDate.now();

	/*
	 * Cadastra o usuário, caso o mesmo já não existe e a pessoa não esteja
	 * vinculado a outro usuário.
	 */
	public String cadastrar(Usuario usuario) {
		usuario.setData_cadastro(Date.valueOf(dataAtual));
		String mensagem = "";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		
		if (usuario.getCodigo() == null) {

			Usuario usuarioExiste = usuarios.findByUserEquals(usuario.getUser());
			Usuario pessoaUsuario = usuarios.findByPessoaCodigoEquals(usuario.getPessoa().getCodigo());

			if (usuarioExiste != null) {
				return mensagem = "Usuário já existe";
			} else if (pessoaUsuario != null) {
				return mensagem = "Pessoa já vinculada a outro usuário";
			} else {
				usuarios.save(usuario);
				mensagem = "Usuário salvo com sucesso";
			}
		} else {
			try {
				usuarios.save(usuario);
				mensagem = "Usuário atualizado com sucesso";
			} catch (Exception e) {
				mensagem = e.getMessage();
				e.getStackTrace();
			}
		}

		return mensagem;
	}

	public List<Usuario> lista() {
		return usuarios.findAll();
	}

	public String addGrupo(Long codUsu, Long codGru) {
		Usuario usuario = usuarios.findByCodigoIn(codUsu);
		GrupoUsuario gruposUsu = grupos.buscaGrupo(codGru);

		List<GrupoUsuario> listaGrupo = new ArrayList<>();
		listaGrupo.add(gruposUsu);

		if (!usuario.getGrupoUsuario().contains(gruposUsu)) {
			usuario.getGrupoUsuario().add(gruposUsu);
		} else {
			return "ja existe";
		}

		usuarios.save(usuario);

		return "ok";
	}

	public String removeGrupo(Long codUsu, Long codGru) {
		Usuario usuario = usuarios.findByCodigoIn(codUsu);
		GrupoUsuario gruposUsu = grupos.buscaGrupo(codGru);

		List<GrupoUsuario> todosGrupos = new ArrayList<>();
		todosGrupos = grupos.buscaGrupos(usuario);

		for (int i = 0; i < todosGrupos.size(); i++) {
			if (todosGrupos.get(i).getCodigo() == gruposUsu.getCodigo()) {
				todosGrupos.remove(i);
			}
		}

		try {
			usuario.setGrupoUsuario(todosGrupos);
			usuarios.save(usuario);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return "ok";
	}

	public Usuario buscaUsuario(String username) {
		return usuarios.findByUserEquals(username);
	}
	
}
