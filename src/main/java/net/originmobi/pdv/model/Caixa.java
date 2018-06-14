package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

	@Size(max = 250, message = "Valor máximo para descrição é de 250 caracteres")
	private String Descricao;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_abertura;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_total;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_fechamento;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_entrada;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_saida;

	@Enumerated(EnumType.STRING)
	private CaixaTipo tipo;

	private String agencia;
	private String conta;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cadastro;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp data_fechamento;

	@ManyToOne
	@JsonIgnore
	private Usuario usuario;

	public Caixa() {
	}

	public Caixa(String descricao, CaixaTipo tipo, Double valor_abertura, Double valor_total, Double valor_fechamento,
			Date data_cadastro, Timestamp data_fechamento, Usuario usuario) {
		this.Descricao = descricao;
		this.tipo = tipo;
		this.valor_abertura = valor_abertura;
		this.valor_total = valor_total;
		this.valor_fechamento = valor_fechamento;
		this.data_cadastro = data_cadastro;
		this.data_fechamento = data_fechamento;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Caixa [codigo=" + codigo + ", descricao=" + Descricao + "]";
	}

	public Boolean isCofre() {
		return tipo.equals(CaixaTipo.COFRE);
	}
	
	public boolean isBanco() {
		return tipo.equals(CaixaTipo.BANCO);
	}

	public boolean isAberto() {
		return null == data_fechamento;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public Double getValor_abertura() {
		return valor_abertura;
	}

	public void setValor_abertura(Double valor_abertura) {
		this.valor_abertura = valor_abertura;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public Double getValor_fechamento() {
		return valor_fechamento;
	}

	public void setValor_fechamento(Double valor_fechamento) {
		this.valor_fechamento = valor_fechamento;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Timestamp getData_fechamento() {
		return data_fechamento;
	}

	public void setData_fechamento(Timestamp data_fechamento) {
		this.data_fechamento = data_fechamento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValor_entrada() {
		return valor_entrada;
	}

	public void setValor_entrada(Double valor_entrada) {
		this.valor_entrada = valor_entrada;
	}

	public Double getValor_saida() {
		return valor_saida;
	}

	public void setValor_saida(Double valor_saida) {
		this.valor_saida = valor_saida;
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
