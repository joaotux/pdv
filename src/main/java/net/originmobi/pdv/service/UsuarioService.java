package net.originmobi.pdv.service;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    LocalDate dataAtual = LocalDate.now();

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private GrupoUsuarioService grupos;

    /*
     * Cadastra o usuário, caso o mesmo já não existe e a pessoa não esteja
     * vinculado a outro usuário.
     */
    @Transactional
    public String cadastrar(Usuario usuario) {
        usuario.setDataCadastro(Date.valueOf(dataAtual));
        String mensagem = "";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(usuario.getSenha()));

        if (usuario.getCodigo() == null) {

            Usuario usuarioExiste = usuarios.findByUserEquals(usuario.getUser());
            Usuario pessoaUsuario = usuarios.findByPessoaCodigoEquals(usuario.getPessoa().getCodigo());

            if (usuarioExiste != null) {
                return "Usuário já existe";
            } else if (pessoaUsuario != null) {
                return "Pessoa já vinculada a outro usuário";
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

        List<GrupoUsuario> gruposUsuario = usuario.getGrupoUsuario();
        if (!gruposUsuario.contains(gruposUsu)) {
            gruposUsuario.add(gruposUsu);
        } else {
            return "ja existe";
        }

        usuarios.save(usuario);

        return "ok";
    }

    public String removeGrupo(Long codUsu, Long codGru) {
        Usuario usuario = usuarios.findByCodigoIn(codUsu);
        GrupoUsuario gruposUsu = grupos.buscaGrupo(codGru);

        List<GrupoUsuario> todosGrupos = grupos.buscaGrupos(usuario);

        List<GrupoUsuario> gruposParaRemover = todosGrupos.stream()
                .filter(grupo -> grupo.getCodigo().equals(gruposUsu.getCodigo()))
                .collect(Collectors.toList());

        todosGrupos.removeAll(gruposParaRemover);

        try {
            usuario.setGrupoUsuario(todosGrupos);
            usuarios.save(usuario);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return "ok";
    }

    public Usuario buscaUsuario(String username) {
        return usuarios.findByUserEquals(username);
    }

}
