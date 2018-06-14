package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nota_fiscal_item_imposto")
public class NotaFiscalItemImposto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private int orig;
	private int mod_bc;

	private int cst;
	private Double v_bc;
	private Double p_icms;
	private Double v_icms;

	private int cst_pis;
	private Double vbc_pis;
	private Double p_pis;
	private Double v_pis;

	private int cst_cofins;
	private Double vbc_cofins;
	private Double p_cofins;
	private Double v_cofins;

	private int cst_ipi;
	private Double vbc_ipi;
	private Double p_ipi;
	private Double v_ipi;

	public NotaFiscalItemImposto() {
		super();
	}

	public NotaFiscalItemImposto(Integer orig, int vlCst_cofins, int modBcIcms, Double bc_icms, Double aliq_icms,
			Double vlIcms, int vlCst_pis, Double bc_pis, Double pis, Double vlPis, Double bc_cofins, Double aliqCofins,
			Double vlCofins, int cst_csosn, int cst_ipi, Double vbc_ipi, Double p_ipi, Double v_ipi) {
		this.orig = orig;
		this.cst_cofins = vlCst_cofins;
		this.mod_bc = modBcIcms;
		this.v_bc = bc_icms;
		this.p_icms = aliq_icms;
		this.v_icms = vlIcms;
		this.cst_pis = vlCst_pis;
		this.vbc_pis = bc_pis;
		this.p_pis = pis;
		this.v_pis = vlPis;
		this.vbc_cofins = bc_cofins;
		this.p_cofins = aliqCofins;
		this.v_cofins = vlCofins;
		this.cst = cst_csosn;
		this.cst_ipi = cst_ipi;
		this.vbc_ipi = vbc_ipi;
		this.p_ipi = p_ipi;
		this.v_ipi = v_ipi;
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

	public int getMod_bc() {
		return mod_bc;
	}

	public void setMod_bc(int mod_bc) {
		this.mod_bc = mod_bc;
	}

	public Double getV_bc() {
		return v_bc;
	}

	public void setV_bc(Double v_bc) {
		this.v_bc = v_bc;
	}

	public Double getP_icms() {
		return p_icms;
	}

	public void setP_icms(Double p_icms) {
		this.p_icms = p_icms;
	}

	public Double getV_icms() {
		return v_icms;
	}

	public void setV_icms(Double v_icms) {
		this.v_icms = v_icms;
	}

	public int getCst_pis() {
		return cst_pis;
	}

	public void setCst_pis(int cst_pis) {
		this.cst_pis = cst_pis;
	}

	public Double getVbc_pis() {
		return vbc_pis;
	}

	public void setVbc_pis(Double vbc_pis) {
		this.vbc_pis = vbc_pis;
	}

	public Double getP_pis() {
		return p_pis;
	}

	public void setP_pis(Double p_pis) {
		this.p_pis = p_pis;
	}

	public Double getV_pis() {
		return v_pis;
	}

	public void setV_pis(Double v_pis) {
		this.v_pis = v_pis;
	}

	public int getCst_cofins() {
		return cst_cofins;
	}

	public void setCst_cofins(int cst_cofins) {
		this.cst_cofins = cst_cofins;
	}

	public Double getVbc_cofins() {
		return vbc_cofins;
	}

	public void setVbc_cofins(Double vbc_cofins) {
		this.vbc_cofins = vbc_cofins;
	}

	public Double getP_cofins() {
		return p_cofins;
	}

	public void setP_cofins(Double p_cofins) {
		this.p_cofins = p_cofins;
	}

	public Double getV_cofins() {
		return v_cofins;
	}

	public void setV_cofins(Double v_cofins) {
		this.v_cofins = v_cofins;
	}

	public int getCst_ipi() {
		return cst_ipi;
	}

	public void setCst_ipi(int cst_ipi) {
		this.cst_ipi = cst_ipi;
	}

	public Double getVbc_ipi() {
		return vbc_ipi;
	}

	public void setVbc_ipi(Double vbc_ipi) {
		this.vbc_ipi = vbc_ipi;
	}

	public Double getP_ipi() {
		return p_ipi;
	}

	public void setP_ipi(Double p_ipi) {
		this.p_ipi = p_ipi;
	}

	public Double getV_ipi() {
		return v_ipi;
	}

	public void setV_ipi(Double v_ipi) {
		this.v_ipi = v_ipi;
	}

}
