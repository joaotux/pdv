package net.originmobi.pdv.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

public class UsuarioSistema extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    public UsuarioSistema(String nome, String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.nome = nome;
    }

    @Override
    public boolean equals(Object rhs) {
        return super.equals(rhs) && nome.equals(((UsuarioSistema) rhs).getNome());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
