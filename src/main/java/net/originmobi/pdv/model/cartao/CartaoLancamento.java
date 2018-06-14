package net.originmobi.pdv.model.cartao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import net.originmobi.pdv.enumerado.cartao.CartaoSituacao;
import net.originmobi.pdv.enumerado.cartao.CartaoTipo;

@Entity
public class CartaoLancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_parcela")
	private Double vlParcela;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "taxa")
	private Double taxa;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_taxa")
	private Double vlTaxa;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_liq_parcela")
	private Double vlLiqParcela;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "taxa_antecipacao")
	private Double taxaAntecipacao;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_taxa_antecipacao")
	private Double vlTaxaAntecipacao;

	@NumberFormat(pattern = "#,##0.00")
	@Column(name = "vl_liq_antecipacao")
	private Double vlLiqAntecipacao;

	@ManyToOne
	private MaquinaCartao maquina_cartao;

	@Enumerated(EnumType.STRING)
	private CartaoTipo tipo;

	@Enumerated(EnumType.STRING)
	private CartaoSituacao situacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_recebimento;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cadastro;

	@Deprecated
	public CartaoLancamento() {
		super();
	}

	public CartaoLancamento(Double vlParcela, Double taxa, Double vlTaxa, Double vlLiqParcela, Double taxaAntecipacao,
			Double vlTaxaAntecipacao, Double vlLiqAntecipacao, MaquinaCartao maquina_cartao, CartaoTipo tipo,
			CartaoSituacao situacao, Date data_recebimento, Date data_cadastro) {
		super();
		this.vlParcela = vlParcela;
		this.taxa = taxa;
		this.vlTaxa = vlTaxa;
		this.vlLiqParcela = vlLiqParcela;
		this.taxaAntecipacao = taxaAntecipacao;
		this.vlTaxaAntecipacao = vlTaxaAntecipacao;
		this.vlLiqAntecipacao = vlLiqAntecipacao;
		this.maquina_cartao = maquina_cartao;
		this.tipo = tipo;
		this.situacao = situacao;
		this.data_recebimento = data_recebimento;
		this.data_cadastro = data_cadastro;
	}

	public boolean isProcessado() {
		return situacao.equals(CartaoSituacao.PROCESSADO);
	}

	public boolean isAntecipado() {
		return situacao.equals(CartaoSituacao.ANTECIPADO);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getVlParcela() {
		return vlParcela;
	}

	public void setVlParcela(Double vlParcela) {
		this.vlParcela = vlParcela;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}

	public Double getVlTaxa() {
		return vlTaxa;
	}

	public void setVlTaxa(Double vlTaxa) {
		this.vlTaxa = vlTaxa;
	}

	public Double getVlLiqParcela() {
		return vlLiqParcela;
	}

	public void setVlLiqParcela(Double vlLiqParcela) {
		this.vlLiqParcela = vlLiqParcela;
	}

	public Double getTaxaAntecipacao() {
		return taxaAntecipacao;
	}

	public void setTaxaAntecipacao(Double taxaAntecipacao) {
		this.taxaAntecipacao = taxaAntecipacao;
	}

	public Double getVlTaxaAntecipacao() {
		return vlTaxaAntecipacao;
	}

	public void setVlTaxaAntecipacao(Double vlTaxaAntecipacao) {
		this.vlTaxaAntecipacao = vlTaxaAntecipacao;
	}

	public Double getVlLiqAntecipacao() {
		return vlLiqAntecipacao;
	}

	public void setVlLiqAntecipacao(Double vlLiqAntecipacao) {
		this.vlLiqAntecipacao = vlLiqAntecipacao;
	}

	public MaquinaCartao getMaquina_cartao() {
		return maquina_cartao;
	}

	public void setMaquina_cartao(MaquinaCartao maquina_cartao) {
		this.maquina_cartao = maquina_cartao;
	}

	public CartaoTipo getTipo() {
		return tipo;
	}

	public void setTipo(CartaoTipo tipo) {
		this.tipo = tipo;
	}

	public CartaoSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(CartaoSituacao situacao) {
		this.situacao = situacao;
	}

	public Date getData_recebimento() {
		return data_recebimento;
	}

	public void setData_recebimento(Date data_recebimento) {
		this.data_recebimento = data_recebimento;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

}
