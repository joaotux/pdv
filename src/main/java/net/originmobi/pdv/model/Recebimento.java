package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Recebimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NumberFormat(pattern = "#,##0.00")
	private Double valor_total;

	@NumberFormat(pattern = "#,##0.00")
	private Double valor_desconto;

	@NumberFormat(pattern = "#,##0.00")
	private Double valor_acrescimo;

	@NumberFormat(pattern = "#,##0.00")
	private Double valor_recebido;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_cadastro;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_processamento;

	@ManyToOne
	private Pessoa pessoa;

	@ManyToMany
	@JoinTable(name = "recebimento_parcelas", joinColumns = @JoinColumn(name = "recebimento_cod"), inverseJoinColumns = @JoinColumn(name = "parcela_cod"))
	private List<Parcela> parcela;

	@ManyToOne
	private Titulo titulo;

	public Recebimento() {
		super();
	}

	public Recebimento(Double valor_total, Timestamp data_cadastro, Pessoa pessoa, List<Parcela> parcela) {
		this.valor_total = valor_total;
		this.data_cadastro = data_cadastro;
		this.pessoa = pessoa;
		this.parcela = parcela;
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

	public Double getValor_recebido() {
		return valor_recebido;
	}

	public void setValor_recebido(Double valor_recebido) {
		this.valor_recebido = valor_recebido;
	}

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Timestamp getData_processamento() {
		return data_processamento;
	}

	public void setData_processamento(Timestamp data_processamento) {
		this.data_processamento = data_processamento;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Parcela> getParcela() {
		return parcela;
	}

	public void setParcela(List<Parcela> parcela) {
		this.parcela = parcela;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

}
