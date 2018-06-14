package net.originmobi.pdv.enumerado.cartao;

public enum CartaoSituacao {
	APROCESSAR("A Processar"), PROCESSADO("Processado"), ANTECIPADO("Antecipado");

	private String descricao;

	private CartaoSituacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
