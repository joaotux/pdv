package net.originmobi.pdv.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;
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

	@Column(name = "valor_total")
	@NotNull
	@NumberFormat(pattern = "##,##0.00")
	private Double valorTotal;

	@Column(name = "data_cadastro")
	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;

	@ManyToOne
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name="pagartipo_codigo")
	private PagarTipo tipo;

	public Pagar() {
		super();
	}

	public Pagar(String observacao, Double valorTotal, LocalDate dataCadastro, Fornecedor fornecedor,
			PagarTipo tipo) {
		super();
		this.observacao = observacao;
		this.valorTotal = valorTotal;
		this.dataCadastro = dataCadastro;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
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
