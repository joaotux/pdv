package net.originmobi.pdv.filter;

import net.originmobi.pdv.enumerado.cartao.CartaoSituacao;
import net.originmobi.pdv.enumerado.cartao.CartaoTipo;

public class CartaoFilter {
	private CartaoTipo tipo;
	private CartaoSituacao situacao;
	private String data_recebimento;

	public CartaoTipo getTipo() {
		return tipo;
	}

	public void setTipo(CartaoTipo tipo) {
		this.tipo = tipo;
	}

	public CartaoSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(CartaoSituacao situacao) {
		this.situacao = situacao;
	}

	public String getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(String data_recebimento) {
		this.data_recebimento = data_recebimento;
	}

}
