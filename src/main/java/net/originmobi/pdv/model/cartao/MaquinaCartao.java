package net.originmobi.pdv.model.cartao;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import net.originmobi.pdv.model.Caixa;

@Entity
@Table(name = "maquina_cartao")
public class MaquinaCartao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição não pode ser vazia")
	@Size(min = 4, message = "Descrição deve ter no minimo 4 caracteres")
	private String descricao;

	@Column(name = "taxa_debito")
	@NumberFormat(pattern = "#,##0.00")
	private Double taxaDebito;

	@Column(name = "taxa_credito")
	@NumberFormat(pattern = "#,##0.00")
	private Double taxaCredito;

	@Column(name = "dias_debito")
	private int diasDebito;

	@Column(name = "dias_credito")
	private int diasCredito;

	@Column(name = "taxa_antecipacao")
	@NumberFormat(pattern = "#,##0.00")
	private Double taxaAntecipacao;

	@ManyToOne
	private Caixa banco;

	public MaquinaCartao() {
		super();
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

	public Double getTaxaDebito() {
		return taxaDebito;
	}

	public void setTaxaDebito(Double taxaDebito) {
		this.taxaDebito = taxaDebito;
	}

	public Double getTaxaCredito() {
		return taxaCredito;
	}

	public void setTaxaCredito(Double taxaCredito) {
		this.taxaCredito = taxaCredito;
	}

	public int getDiasDebito() {
		return diasDebito;
	}

	public void setDiasDebito(int diasDebito) {
		this.diasDebito = diasDebito;
	}

	public int getDiasCredito() {
		return diasCredito;
	}

	public void setDiasCredito(int diasCredito) {
		this.diasCredito = diasCredito;
	}

	public Caixa getBanco() {
		return banco;
	}

	public void setBanco(Caixa banco) {
		this.banco = banco;
	}

	public Double getTaxaAntecipacao() {
		return taxaAntecipacao;
	}

	public void setTaxaAntecipacao(Double taxaAntecipacao) {
		this.taxaAntecipacao = taxaAntecipacao;
	}

}
