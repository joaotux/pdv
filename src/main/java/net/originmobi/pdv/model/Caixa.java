package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;

@Entity
public class Caixa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "Descricao")
	@Size(max = 250, message = "Valor máximo para descrição é de 250 caracteres")
	private String descricao;

	@Column(name = "valor_abertura")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorAbertura;

	@Column(name = "valor_total")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorTotal;

	@Column(name = "valor_fechamento")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorFechamento;

	@Column(name = "valor_entrada")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorEntrada;

	@Column(name = "valor_saida")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorSaida;

	@Enumerated(EnumType.STRING)
	private CaixaTipo tipo;

	private String agencia;
	private String conta;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;

	@Column(name = "data_fechamento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp dataFechamento;

	@ManyToOne
	@JsonIgnore
	private Usuario usuario;

	public Caixa() {
	}

	public Caixa(String descricao, CaixaTipo tipo, Double valorAbertura, Double valorTotal, Double valorFechamento,
				 Date dataCadastro, Timestamp dataFechamento, Usuario usuario) {
		this.descricao = descricao;
		this.tipo = tipo;
		this.valorAbertura = valorAbertura;
		this.valorTotal = valorTotal;
		this.valorFechamento = valorFechamento;
		this.dataCadastro = dataCadastro;
		this.dataFechamento = dataFechamento;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Caixa [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

	public Boolean isCofre() {
		return tipo.equals(CaixaTipo.COFRE);
	}
	
	public boolean isBanco() {
		return tipo.equals(CaixaTipo.BANCO);
	}

	public boolean isAberto() {
		return null == dataFechamento;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValorAbertura() {
		return valorAbertura;
	}

	public void setValorAbertura(Double valorAbertura) {
		this.valorAbertura = valorAbertura;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Double getValorFechamento() {
		return valorFechamento;
	}

	public void setValorFechamento(Double valorFechamento) {
		this.valorFechamento = valorFechamento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(Timestamp dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValorEntrada() {
		return valorEntrada;
	}

	public void setValorEntrada(Double valorEntrada) {
		this.valorEntrada = valorEntrada;
	}

	public Double getValorSaida() {
		return valorSaida;
	}

	public void setValorSaida(Double valorSaida) {
		this.valorSaida = valorSaida;
	}

	public CaixaTipo getTipo() {
		return tipo;
	}

	public void setTipo(CaixaTipo tipo) {
		this.tipo = tipo;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

}
