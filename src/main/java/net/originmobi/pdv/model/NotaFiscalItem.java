package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "nota_fiscal_item")
public class NotaFiscalItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private Long codProd;

	private int qtd;

	private Double vlTotal;

	private String ceantrib;

	@Column(name = "unidade_tribu")
	private String unidadeTribu;

	@Column(name = "qtd_tribu")
	private int qtdTribu;

	private String cfop;

	@Column(name = "vl_uni_tribu")
	private Double vUniTribu;

	@ManyToOne
	@JoinColumn(name = "nota_fiscal_codigo")
	private NotaFiscal notaFiscal;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "imposto_codigo")
	private NotaFiscalItemImposto impostos;

	public NotaFiscalItem() {
		super();
	}

	public NotaFiscalItem(Long prod, int qtd, Double vlTotal, String uniTribu, int qtdTribu, Double vlUnidade,
						  NotaFiscal notaFiscal, NotaFiscalItemImposto imposto, String cfop) {
		this.codProd = prod;
		this.qtd = qtd;
		this.vlTotal = vlTotal;
		this.unidadeTribu = uniTribu;
		this.qtdTribu = qtdTribu;
		this.vUniTribu = vlUnidade;
		this.notaFiscal = notaFiscal;
		this.impostos = imposto;
		this.cfop = cfop;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodProd() {
		return codProd;
	}

	public void setCodProd(Long codProd) {
		this.codProd = codProd;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Double getVlTotal() {
		return vlTotal;
	}

	public void setVlTotal(Double vlTotal) {
		this.vlTotal = vlTotal;
	}

	public String getCeantrib() {
		return ceantrib;
	}

	public void setCeantrib(String ceantrib) {
		this.ceantrib = ceantrib;
	}

	public String getUnidadeTribu() {
		return unidadeTribu;
	}

	public void setUnidadeTribu(String unidadeTribu) {
		this.unidadeTribu = unidadeTribu;
	}

	public int getQtdTribu() {
		return qtdTribu;
	}

	public void setQtdTribu(int qtdTribu) {
		this.qtdTribu = qtdTribu;
	}

	public Double getVUniTribu() {
		return vUniTribu;
	}

	public void setVUniTribu(Double vUniTribu) {
		this.vUniTribu = vUniTribu;
	}

	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public NotaFiscalItemImposto getImpostos() {
		return impostos;
	}

	public void setImpostos(NotaFiscalItemImposto impostos) {
		this.impostos = impostos;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

}
