package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.NumberFormat;

@Entity
public class NotaFiscalTotais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_bc;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_icms;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_st;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_prod;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_frete;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_seg;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_desc;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_ii;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_ipi;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_pis;

	@NumberFormat(pattern = "R$ #,##0.00")
	@Column(name = "v_cofins")
	private Double v_cofins;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_outros;
	
	@NumberFormat(pattern = "R$ #,##0.00")
	private Double v_nf;

	@Deprecated
	public NotaFiscalTotais() {
		super();
	}

	public NotaFiscalTotais(Double v_bc, Double v_icms, Double v_st, Double v_prod, Double v_frete, Double v_seg,
			Double v_desc, Double v_ii, Double v_ipi, Double v_pis, Double v_cofins, Double v_outros, Double v_nf) {
		super();
		this.v_bc = v_bc;
		this.v_icms = v_icms;
		this.v_st = v_st;
		this.v_prod = v_prod;
		this.v_frete = v_frete;
		this.v_seg = v_seg;
		this.v_desc = v_desc;
		this.v_ii = v_ii;
		this.v_ipi = v_ipi;
		this.v_pis = v_pis;
		this.v_cofins = v_cofins;
		this.v_outros = v_outros;
		this.v_nf = v_nf;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getV_bc() {
		return v_bc;
	}

	public void setV_bc(Double v_bc) {
		this.v_bc = v_bc;
	}

	public Double getV_icms() {
		return v_icms;
	}

	public void setV_icms(Double v_icms) {
		this.v_icms = v_icms;
	}

	public Double getV_st() {
		return v_st;
	}

	public void setV_st(Double v_st) {
		this.v_st = v_st;
	}

	public Double getV_prod() {
		return v_prod;
	}

	public void setV_prod(Double v_prod) {
		this.v_prod = v_prod;
	}

	public Double getV_frete() {
		return v_frete;
	}

	public void setV_frete(Double v_frete) {
		this.v_frete = v_frete;
	}

	public Double getV_seg() {
		return v_seg;
	}

	public void setV_seg(Double v_seg) {
		this.v_seg = v_seg;
	}

	public Double getV_desc() {
		return v_desc;
	}

	public void setV_desc(Double v_desc) {
		this.v_desc = v_desc;
	}

	public Double getV_ii() {
		return v_ii;
	}

	public void setV_ii(Double v_ii) {
		this.v_ii = v_ii;
	}

	public Double getV_ipi() {
		return v_ipi;
	}

	public void setV_ipi(Double v_ipi) {
		this.v_ipi = v_ipi;
	}

	public Double getV_pis() {
		return v_pis;
	}

	public void setV_pis(Double v_pis) {
		this.v_pis = v_pis;
	}

	public Double getV_cofins() {
		return v_cofins;
	}

	public void setV_cofins(Double v_cofins) {
		this.v_cofins = v_cofins;
	}

	public Double getV_outros() {
		return v_outros;
	}

	public void setV_outros(Double v_outros) {
		this.v_outros = v_outros;
	}

	public Double getV_nf() {
		return v_nf;
	}

	public void setV_nf(Double v_nf) {
		this.v_nf = v_nf;
	}

}
