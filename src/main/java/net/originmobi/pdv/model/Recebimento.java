package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Recebimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "valor_total")
	@NumberFormat(pattern = "#,##0.00")
	private Double valorTotal;

	@Column(name = "valor_desconto")
	@NumberFormat(pattern = "#,##0.00")
	private Double valorDesconto;

	@Column(name = "valor_acrescimo")
	@NumberFormat(pattern = "#,##0.00")
	private Double valorAcrescimo;

	@Column(name = "valor_recebido")
	@NumberFormat(pattern = "#,##0.00")
	private Double valorRecebido;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataCadastro;

	@Column(name = "data_processamento")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataProcessamento;

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

	public Recebimento(Double valorTotal, Timestamp dataCadastro, Pessoa pessoa, List<Parcela> parcela) {
		this.valorTotal = valorTotal;
		this.dataCadastro = dataCadastro;
		this.pessoa = pessoa;
		this.parcela = parcela;
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

	public Double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataProcessamento() {
		return dataProcessamento;
	}

	public void setDataProcessamento(Timestamp dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
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
