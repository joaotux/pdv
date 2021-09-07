package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.model.Usuario;

public class UsuarioFactory {

    public static Usuario createUserValid () {
        Usuario user = new Usuario();
        user.setCodigo(11L);
        user.setSenha("123");
        user.setUser("gerente");
        return  user;
    }
}
