package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "pagamento_tipo")
public class PagamentoTipo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição não pode ser em branco")
	private String descricao;

	@Column(name = "forma_pagamento")
	private String formaPagamento;

	private int qtd_parcelas;

	private Date data_cadastro;

	public PagamentoTipo() {
	}

	public PagamentoTipo(String descricao, String formaPagamento, Date data_cadastro) {
		this.descricao = descricao;
		this.formaPagamento = formaPagamento;
		this.data_cadastro = data_cadastro;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public int getQtd_parcelas() {
		return qtd_parcelas;
	}

	public void setQtd_parcelas(int qtd_parcelas) {
		this.qtd_parcelas = qtd_parcelas;
	}

}
