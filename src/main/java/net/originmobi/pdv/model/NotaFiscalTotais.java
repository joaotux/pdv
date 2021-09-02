package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "nota_fiscal_totais")
public class NotaFiscalTotais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "v_bc")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vBc;

	@Column(name = "v_icms")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vIcms;

	@Column(name = "v_st")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vSt;

	@Column(name = "v_prod")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vProd;

	@Column(name = "v_frete")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vFrete;

	@Column(name = "v_seg")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vSeg;

	@Column(name = "v_desc")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vDesc;

	@Column(name = "v_ii")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vIi;

	@Column(name = "v_ipi")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vIpi;

	@Column(name = "v_pis")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vPis;

	@Column(name = "v_cofins")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vCofins;

	@Column(name = "v_outros")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vOutros;

	@Column(name = "v_nf")
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double vNf;

	public NotaFiscalTotais() {
		super();
	}

	public NotaFiscalTotais(Double vBc, Double vIcms, Double vSt, Double vProd, Double vFrete, Double vSeg,
							Double vDesc, Double vIi, Double vIpi, Double vPis, Double vCofins, Double vOutros, Double vNf) {
		super();
		this.vBc = vBc;
		this.vIcms = vIcms;
		this.vSt = vSt;
		this.vProd = vProd;
		this.vFrete = vFrete;
		this.vSeg = vSeg;
		this.vDesc = vDesc;
		this.vIi = vIi;
		this.vIpi = vIpi;
		this.vPis = vPis;
		this.vCofins = vCofins;
		this.vOutros = vOutros;
		this.vNf = vNf;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getVBc() {
		return vBc;
	}

	public void setVBc(Double vBc) {
		this.vBc = vBc;
	}

	public Double getVIcms() {
		return vIcms;
	}

	public void setVIcms(Double vIcms) {
		this.vIcms = vIcms;
	}

	public Double getVSt() {
		return vSt;
	}

	public void setVSt(Double vSt) {
		this.vSt = vSt;
	}

	public Double getVProd() {
		return vProd;
	}

	public void setVProd(Double vProd) {
		this.vProd = vProd;
	}

	public Double getVFrete() {
		return vFrete;
	}

	public void setVFrete(Double vFrete) {
		this.vFrete = vFrete;
	}

	public Double getVSeg() {
		return vSeg;
	}

	public void setVSeg(Double vSeg) {
		this.vSeg = vSeg;
	}

	public Double getVDesc() {
		return vDesc;
	}

	public void setVDesc(Double vDesc) {
		this.vDesc = vDesc;
	}

	public Double getVIi() {
		return vIi;
	}

	public void setVIi(Double vIi) {
		this.vIi = vIi;
	}

	public Double getVIpi() {
		return vIpi;
	}

	public void setVIpi(Double vIpi) {
		this.vIpi = vIpi;
	}

	public Double getVPis() {
		return vPis;
	}

	public void setVPis(Double vPis) {
		this.vPis = vPis;
	}

	public Double getVCofins() {
		return vCofins;
	}

	public void setVCofins(Double vCofins) {
		this.vCofins = vCofins;
	}

	public Double getVOutros() {
		return vOutros;
	}

	public void setVOutros(Double vOutros) {
		this.vOutros = vOutros;
	}

	public Double getVNf() {
		return vNf;
	}

	public void setVNf(Double vNf) {
		this.vNf = vNf;
	}

}
