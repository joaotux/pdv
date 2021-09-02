package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "parcela_pagar")
public class PagarParcela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "valor_total")
	@NotNull
	@NumberFormat(pattern = "##,##0.00")
	private Double valorTotal;

	@Column(name = "valor_desconto")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorDesconto;

	@Column(name = "valor_acrescimo")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorAcrescimo;

	@Column(name = "valor_pago")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorPago;

	@Column(name = "valor_restante")
	@NotNull
	@NumberFormat(pattern = "##,##0.00")
	private Double valorRestante;

	private int quitado;

	@Column(name = "data_cadastro")
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataCadastro;

	@Column(name = "data_vencimento")
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;

	@Column(name = "data_pagamento")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataPagamento;

	@ManyToOne
	private Pagar pagar;

	public PagarParcela() {
		super();
	}

	public PagarParcela(Double valorTotal, Double valorRestante, int quitado, Timestamp dataCadastro,
						LocalDate dataVencimento, Pagar pagar) {
		super();
		this.valorTotal = valorTotal;
		this.valorRestante = valorRestante;
		this.quitado = quitado;
		this.dataCadastro = dataCadastro;
		this.dataVencimento = dataVencimento;
		this.pagar = pagar;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorAcrescimo() {
		return valorAcrescimo;
	}

	public void setValorAcrescimo(Double valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}

	public Double getValorPago() {
		return valorPago;
	}

	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
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

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Timestamp getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Timestamp dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Pagar getPagar() {
		return pagar;
	}

	public void setPagar(Pagar pagar) {
		this.pagar = pagar;
	}

}
