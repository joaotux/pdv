package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_total;

	@ManyToOne
	private Pessoa pessoa;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_cadastro;

	@OneToOne
	private Venda venda;

	@Deprecated
	public Receber() {
	}

	public Receber(String observacao, Double valor_total, Pessoa pessoa, Timestamp data_cadastro,
			Venda venda) {
		this.observacao = observacao;
		this.valor_total = valor_total;
		this.pessoa = pessoa;
		this.data_cadastro = data_cadastro;
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

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
