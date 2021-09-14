package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioFactory {

    public static Usuario createUserValid () {
        Usuario user = new Usuario();
        user.setCodigo(11L);
        user.setSenha("123");
        user.setUser("gerente");
        user.setGrupoUsuario(GrupoUsuarioFactory.createListGrupoUsuariosValid());

        return  user;
    }


    public  static List<Usuario> createListUserValid(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(createUserValid());
        return usuarios;
    }

    public static Usuario createUserValidWithPeopleAndGroup () {
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(11L);

        Usuario user = createUserValid();
        user.setPessoa(pessoa);
        user.setGrupoUsuario(GrupoUsuarioFactory.createListGrupoUsuariosValid2());
        return  user;
    }

    public static Usuario createUserValidToInsert () {
        Usuario user = createUserValid();
        user.setCodigo(null);
        return  user;
    }

    public static Usuario createUserInvalidToInsert () {
        Usuario user = null;
        return  user;
    }
}
