package net.originmobi.pdv.singleton;

import org.springframework.security.core.context.SecurityContextHolder;

public class Aplicacao {

	private static Aplicacao aplicacao;
	private String usuarioAtual;

	public Aplicacao() {
		usuarioAtual = SecurityContextHolder.getContext().getAuthentication().getName();
	}

	public static synchronized Aplicacao getInstancia() {
		if (aplicacao == null) {
			aplicacao = new Aplicacao();
		}
		return aplicacao;
	}

	public String getUsuarioAtual() {
		return usuarioAtual;
	}
	
}
