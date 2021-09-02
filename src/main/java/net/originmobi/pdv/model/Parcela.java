package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Parcela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "valor_total")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorTotal;

	@Column(name = "valor_acrescimo")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorAcrescimo;

	@Column(name = "valor_desconto")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorDesconto;

	@Column(name = "valor_recebido")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorRecebido;

	@Column(name = "valor_restante")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorRestante;

	private int quitado;

	private int sequencia;

	@ManyToOne
	private Receber receber;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp dataCadastro;

	@Column(name = "data_vencimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimento;

	@Column(name = "data_pagamento")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataPagamento;

	public Parcela() {
	}

	public Parcela(Double valorTotal, Double valorAcrescimo, Double valorDesconto, Double valorRecebido,
				   Double valorRestante, int quitado, int sequencia, Receber receber,
				   Timestamp dataCadastro, Date dataVencimento) {
		super();
		this.valorTotal = valorTotal;
		this.valorAcrescimo = valorAcrescimo;
		this.valorDesconto = valorDesconto;
		this.valorRecebido = valorRecebido;
		this.valorRestante = valorRestante;
		this.quitado = quitado;
		this.sequencia = sequencia;
		this.receber = receber;
		this.dataCadastro = dataCadastro;
		this.dataVencimento = dataVencimento;
	}
	
	public Boolean isQuitado() {
		return quitado == 1;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getValorAcrescimo() {
		return valorAcrescimo;
	}

	public void setValorAcrescimo(Double valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public Double getValorRestante() {
		return valorRestante;
	}

	public void setValorRestante(Double valorRestante) {
		this.valorRestante = valorRestante;
	}

	public int getQuitado() {
		return quitado;
	}

	public void setQuitado(int quitado) {
		this.quitado = quitado;
	}

	public Receber getReceber() {
		return receber;
	}

	public void setReceber(Receber receber) {
		this.receber = receber;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Timestamp dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

}
