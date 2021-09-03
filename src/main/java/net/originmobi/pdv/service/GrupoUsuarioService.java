package net.originmobi.pdv.service;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.GrupoUsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class GrupoUsuarioService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private GrupoUsuarioRepository grupousuarios;

    public List<GrupoUsuario> buscaGrupos(Usuario usuario) {
        return grupousuarios.findByUsuarioIn(usuario);
    }

    public List<GrupoUsuario> lista() {
        return grupousuarios.findAll();
    }

    public GrupoUsuario buscaGrupo(Long codigoGru) {
        return grupousuarios.findByCodigoIn(codigoGru);
    }

    public void merge(GrupoUsuario grupoUsuario, RedirectAttributes attributes) {

        if (grupoUsuario.getCodigo() == null) {
            try {
                attributes.addFlashAttribute("mensagem", "Grupo adicionado com sucesso");
                grupousuarios.save(grupoUsuario);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

        } else {
            attributes.addFlashAttribute("mensagem", "Grupo atualizado com sucesso");
            grupousuarios.update(grupoUsuario.getNome(), grupoUsuario.getDescricao(), grupoUsuario.getCodigo());
        }

    }

    public String remove(Long codigo, RedirectAttributes attributes) {
        // verifico se o grupo esta vinculado a algum usuário
        int aux = grupousuarios.grupoTemUsuaio(codigo);

        if (aux > 0) {
            attributes.addFlashAttribute("mensagemErro", "Este grupo possue usuários vinculados a ele, verifique");
            return "redirect:/grupousuario/" + codigo;
        }

        try {
            grupousuarios.deleteById(codigo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return "redirect:/grupousuario";
    }

    public String addPermissao(Long codgrupo, Long codpermissao) {

        int aux = grupousuarios.grupoTemPermissao(codgrupo, codpermissao);

        if (aux > 0)
            throw new RuntimeException("Esta permissão já esta adicionada a este grupo");

        try {
            grupousuarios.addPermissao(codgrupo, codpermissao);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar adicionar permissão, chame o suporte");
        }

        return "Permissao adicionada com sucesso";
    }

    public String removePermissao(Long codigo, Long codgrupo) {
        try {
            grupousuarios.removePermissao(codigo, codgrupo);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar remover permissão, chame o suporte");
        }
        return "Permissão removida com sucesso";
    }

}
