package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

	@NotNull
	@NumberFormat(pattern = "##,##0.00")
	private Double valor_total;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_desconto;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_acrescimo;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_pago;

	@NotNull
	@NumberFormat(pattern = "##,##0.00")
	private Double valor_restante;

	private int quitado;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_cadastro;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_vencimento;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_pagamento;

	@ManyToOne
	private Pagar pagar;

	@Deprecated
	public PagarParcela() {
		super();
	}

	public PagarParcela(Double valor_total, Double valor_restante, int quitado, Timestamp data_cadastro,
			LocalDate data_vencimento, Pagar pagar) {
		super();
		this.valor_total = valor_total;
		this.valor_restante = valor_restante;
		this.quitado = quitado;
		this.data_cadastro = data_cadastro;
		this.data_vencimento = data_vencimento;
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

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Double getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(Double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	public Double getValor_acrescimo() {
		return valor_acrescimo;
	}

	public void setValor_acrescimo(Double valor_acrescimo) {
		this.valor_acrescimo = valor_acrescimo;
	}

	public Double getValor_pago() {
		return valor_pago;
	}

	public void setValor_pago(Double valor_pago) {
		this.valor_pago = valor_pago;
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

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public LocalDate getData_vencimento() {
		return data_vencimento;
	}

	public void setData_vencimento(LocalDate data_vencimento) {
		this.data_vencimento = data_vencimento;
	}

	public Timestamp getData_pagamento() {
		return data_pagamento;
	}

	public void setData_pagamento(Timestamp data_pagamento) {
		this.data_pagamento = data_pagamento;
	}

	public Pagar getPagar() {
		return pagar;
	}

	public void setPagar(Pagar pagar) {
		this.pagar = pagar;
	}

}
