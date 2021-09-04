package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Receber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull(message = "Observação não pode ser vazia")
	@Size(max = 255)
	private String observacao;

	@Column(name = "valor_total")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorTotal;

	@ManyToOne
	private Pessoa pessoa;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataCadastro;

	@OneToOne
	private Venda venda;

	public Receber() {
	}

	public Receber(String observacao, Double valorTotal, Pessoa pessoa, Timestamp dataCadastro,
				   Venda venda) {
		this.observacao = observacao;
		this.valorTotal = valorTotal;
		this.pessoa = pessoa;
		this.dataCadastro = dataCadastro;
		this.venda = venda;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
