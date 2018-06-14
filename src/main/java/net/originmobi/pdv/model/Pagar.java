package net.originmobi.pdv.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Pagar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Size(max = 255, message = "O tamanho da observação deve conter apenas 255 caracteres")
	private String observacao;

	@NotNull
	@NumberFormat(pattern = "##,##0.00")
	private Double valor_total;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_cadastro;

	@ManyToOne
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name="pagartipo_codigo")
	private PagarTipo tipo;

	@Deprecated
	public Pagar() {
		super();
	}

	public Pagar(String observacao, Double valor_total, LocalDate data_cadastro, Fornecedor fornecedor,
			PagarTipo tipo) {
		super();
		this.observacao = observacao;
		this.valor_total = valor_total;
		this.data_cadastro = data_cadastro;
		this.fornecedor = fornecedor;
		this.tipo = tipo;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public LocalDate getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(LocalDate data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public PagarTipo getTipo() {
		return tipo;
	}

	public void setTipo(PagarTipo tipo) {
		this.tipo = tipo;
	}

}
