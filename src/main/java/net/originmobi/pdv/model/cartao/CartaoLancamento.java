package net.originmobi.pdv.model.cartao;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import net.originmobi.pdv.enumerado.cartao.CartaoSituacao;
import net.originmobi.pdv.enumerado.cartao.CartaoTipo;

@Entity
@Table(name = "cartao_lancamento")
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
	@JoinColumn(name = "maquina_cartao_codigo")
	private MaquinaCartao maquinaCartao;

	@Enumerated(EnumType.STRING)
	private CartaoTipo tipo;

	@Enumerated(EnumType.STRING)
	private CartaoSituacao situacao;

	@Column(name = "data_recebimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataRecebimento;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;

	public CartaoLancamento() {
		super();
	}

	public CartaoLancamento(Double vlParcela, Double taxa, Double vlTaxa, Double vlLiqParcela, Double taxaAntecipacao,
							Double vlTaxaAntecipacao, Double vlLiqAntecipacao, MaquinaCartao maquinaCartao, CartaoTipo tipo,
							CartaoSituacao situacao, Date dataRecebimento, Date dataCadastro) {
		super();
		this.vlParcela = vlParcela;
		this.taxa = taxa;
		this.vlTaxa = vlTaxa;
		this.vlLiqParcela = vlLiqParcela;
		this.taxaAntecipacao = taxaAntecipacao;
		this.vlTaxaAntecipacao = vlTaxaAntecipacao;
		this.vlLiqAntecipacao = vlLiqAntecipacao;
		this.maquinaCartao = maquinaCartao;
		this.tipo = tipo;
		this.situacao = situacao;
		this.dataRecebimento = dataRecebimento;
		this.dataCadastro = dataCadastro;
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

	public MaquinaCartao getMaquinaCartao() {
		return maquinaCartao;
	}

	public void setMaquinaCartao(MaquinaCartao maquinaCartao) {
		this.maquinaCartao = maquinaCartao;
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

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
