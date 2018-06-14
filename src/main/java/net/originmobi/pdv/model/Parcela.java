package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Parcela implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_total;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_acrescimo;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_desconto;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_recebido;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_restante;

	private int quitado;

	private int sequencia;

	@ManyToOne
	private Receber receber;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp data_cadastro;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_vencimento;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_pagamento;

	@Deprecated
	public Parcela() {
	}

	public Parcela(Double valor_total, Double valor_acrescimo, Double valor_desconto, Double valor_recebido,
			Double valor_restante, int quitado, int sequencia, Receber receber,
			Timestamp data_cadastro, Date data_vencimento) {
		super();
		this.valor_total = valor_total;
		this.valor_acrescimo = valor_acrescimo;
		this.valor_desconto = valor_desconto;
		this.valor_recebido = valor_recebido;
		this.valor_restante = valor_restante;
		this.quitado = quitado;
		this.sequencia = sequencia;
		this.receber = receber;
		this.data_cadastro = data_cadastro;
		this.data_vencimento = data_vencimento;
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

	public Double getValor_acrescimo() {
		return valor_acrescimo;
	}

	public void setValor_acrescimo(Double valor_acrescimo) {
		this.valor_acrescimo = valor_acrescimo;
	}

	public Double getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(Double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	public Double getValor_recebido() {
		return valor_recebido;
	}

	public void setValor_recebido(Double valor_recebido) {
		this.valor_recebido = valor_recebido;
	}

	public Double getValor_restante() {
		return valor_restante;
	}

	public void setValor_restante(Double valor_restante) {
		this.valor_restante = valor_restante;
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

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(Date data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Timestamp getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Timestamp data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

}
