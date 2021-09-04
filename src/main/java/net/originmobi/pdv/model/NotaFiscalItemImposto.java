package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "nota_fiscal_item_imposto")
public class NotaFiscalItemImposto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private int orig;

	@Column(name = "mod_bc")
	private int modBc;

	private int cst;

	@Column(name = "v_bc")
	private Double vBc;

	@Column(name = "p_icms")
	private Double pIcms;

	@Column(name = "v_icms")
	private Double vIcms;

	@Column(name = "cst_pis")
	private int cstPis;

	@Column(name = "vbc_pis")
	private Double vbcPis;

	@Column(name = "p_pis")
	private Double pPis;

	@Column(name = "v_pis")
	private Double vPis;

	@Column(name = "cst_cofins")
	private int cstCofins;

	@Column(name = "vbc_cofins")
	private Double vbcCofins;

	@Column(name = "p_cofins")
	private Double pCofins;

	@Column(name = "v_cofins")
	private Double vCofins;

	@Column(name = "cst_ipi")
	private int cstIpi;

	@Column(name = "vbc_ipi")
	private Double vbcIpi;

	@Column(name = "p_ipi")
	private Double pIpi;

	@Column(name = "v_ipi")
	private Double vIpi;

	public NotaFiscalItemImposto() {
		super();
	}

	public NotaFiscalItemImposto(Integer orig, int vlCstCofins, int modBcIcms, Double bcIcms, Double aliqIcms,
								 Double vlIcms, int vlCstPis, Double bcPis, Double pis, Double vlPis, Double bcCofins,
								 Double aliqCofins, Double vlCofins, int cstCsosn, int cstIpi, Double vbcIpi,
								 Double pIpi, Double vIpi) {
		this.orig = orig;
		this.cstCofins = vlCstCofins;
		this.modBc = modBcIcms;
		this.vBc = bcIcms;
		this.pIcms = aliqIcms;
		this.vIcms = vlIcms;
		this.cstPis = vlCstPis;
		this.vbcPis = bcPis;
		this.pPis = pis;
		this.vPis = vlPis;
		this.vbcCofins = bcCofins;
		this.pCofins = aliqCofins;
		this.vCofins = vlCofins;
		this.cst = cstCsosn;
		this.cstIpi = cstIpi;
		this.vbcIpi = vbcIpi;
		this.pIpi = pIpi;
		this.vIpi = vIpi;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public int getOrig() {
		return orig;
	}

	public void setOrig(int orig) {
		this.orig = orig;
	}

	public int getCst() {
		return cst;
	}

	public void setCst(int cst) {
		this.cst = cst;
	}

	public int getModBc() {
		return modBc;
	}

	public void setModBc(int modBc) {
		this.modBc = modBc;
	}

	public Double getVBc() {
		return vBc;
	}

	public void setVBc(Double vBc) {
		this.vBc = vBc;
	}

	public Double getPIcms() {
		return pIcms;
	}

	public void setPIcms(Double pIcms) {
		this.pIcms = pIcms;
	}

	public Double getVIcms() {
		return vIcms;
	}

	public void setVIcms(Double vIcms) {
		this.vIcms = vIcms;
	}

	public int getCstPis() {
		return cstPis;
	}

	public void setCstPis(int cstPis) {
		this.cstPis = cstPis;
	}

	public Double getVbcPis() {
		return vbcPis;
	}

	public void setVbcPis(Double vbcPis) {
		this.vbcPis = vbcPis;
	}

	public Double getPPis() {
		return pPis;
	}

	public void setPPis(Double pPis) {
		this.pPis = pPis;
	}

	public Double getVPis() {
		return vPis;
	}

	public void setVPis(Double vPis) {
		this.vPis = vPis;
	}

	public int getCstCofins() {
		return cstCofins;
	}

	public void setCstCofins(int cstCofins) {
		this.cstCofins = cstCofins;
	}

	public Double getVbcCofins() {
		return vbcCofins;
	}

	public void setVbcCofins(Double vbcCofins) {
		this.vbcCofins = vbcCofins;
	}

	public Double getPCofins() {
		return pCofins;
	}

	public void setPCofins(Double pCofins) {
		this.pCofins = pCofins;
	}

	public Double getVCofins() {
		return vCofins;
	}

	public void setVCofins(Double vCofins) {
		this.vCofins = vCofins;
	}

	public int getCstIpi() {
		return cstIpi;
	}

	public void setCstIpi(int cstIpi) {
		this.cstIpi = cstIpi;
	}

	public Double getVbcIpi() {
		return vbcIpi;
	}

	public void setVbcIpi(Double vbcIpi) {
		this.vbcIpi = vbcIpi;
	}

	public Double getPIpi() {
		return pIpi;
	}

	public void setPIpi(Double pIpi) {
		this.pIpi = pIpi;
	}

	public Double getVIpi() {
		return vIpi;
	}

	public void setVIpi(Double vIpi) {
		this.vIpi = vIpi;
	}

}
