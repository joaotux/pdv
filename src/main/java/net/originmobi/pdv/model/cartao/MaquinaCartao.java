package net.originmobi.pdv.model.cartao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import net.originmobi.pdv.model.Caixa;

@Entity
public class MaquinaCartao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição não pode ser vazia")
	@Size(min = 4, message = "Descrição deve ter no minimo 4 caracteres")
	private String descricao;

	@NumberFormat(pattern = "#,##0.00")
	private Double taxa_debito;

	@NumberFormat(pattern = "#,##0.00")
	private Double taxa_credito;
	private int dias_debito;
	private int dias_credito;

	@NumberFormat(pattern = "#,##0.00")
	private Double taxa_antecipacao;

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

	public Double getTaxa_debito() {
		return taxa_debito;
	}

	public void setTaxa_debito(Double taxa_debito) {
		this.taxa_debito = taxa_debito;
	}

	public Double getTaxa_credito() {
		return taxa_credito;
	}

	public void setTaxa_credito(Double taxa_credito) {
		this.taxa_credito = taxa_credito;
	}

	public int getDias_debito() {
		return dias_debito;
	}

	public void setDias_debito(int dias_debito) {
		this.dias_debito = dias_debito;
	}

	public int getDias_credito() {
		return dias_credito;
	}

	public void setDias_credito(int dias_credito) {
		this.dias_credito = dias_credito;
	}

	public Caixa getBanco() {
		return banco;
	}

	public void setBanco(Caixa banco) {
		this.banco = banco;
	}

	public Double getTaxa_antecipacao() {
		return taxa_antecipacao;
	}

	public void setTaxa_antecipacao(Double taxa_antecipacao) {
		this.taxa_antecipacao = taxa_antecipacao;
	}

}
