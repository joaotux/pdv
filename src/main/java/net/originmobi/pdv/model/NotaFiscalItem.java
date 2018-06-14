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
	private String unidade_tribu;
	private int qtd_tribu;
	private String cfop;

	@Column(name = "vl_uni_tribu")
	private Double v_uniTribu;

	@ManyToOne
	@JoinColumn(name = "nota_fiscal_codigo")
	private NotaFiscal notaFiscal;

	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "imposto_codigo")
	private NotaFiscalItemImposto impostos;

	public NotaFiscalItem() {
		super();
	}

	public NotaFiscalItem(Long prod, int qtd, Double vlTotal, String uniTribu, int qtd_tribu, Double vlUnidade,
			NotaFiscal notaFiscal, NotaFiscalItemImposto imposto, String cfop) {
		this.codProd = prod;
		this.qtd = qtd;
		this.vlTotal = vlTotal;
		this.unidade_tribu = uniTribu;
		this.qtd_tribu = qtd_tribu;
		this.v_uniTribu = vlUnidade;
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

	public String getUnidade_tribu() {
		return unidade_tribu;
	}

	public void setUnidade_tribu(String unidade_tribu) {
		this.unidade_tribu = unidade_tribu;
	}

	public int getQtd_tribu() {
		return qtd_tribu;
	}

	public void setQtd_tribu(int qtd_tribu) {
		this.qtd_tribu = qtd_tribu;
	}

	public Double getV_uniTribu() {
		return v_uniTribu;
	}

	public void setV_uniTribu(Double v_uniTribu) {
		this.v_uniTribu = v_uniTribu;
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
