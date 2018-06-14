package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.originmobi.pdv.enumerado.EntradaSaida;

@Entity
public class TributacaoRegra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "cst_csosn_codigo")
	private CstCsosn cst_csosn;

	private Double pis;
	private Double cofins;

	private Double aliq_ipi;

	private Double aliq_icms;

	@Enumerated(EnumType.STRING)
	private EntradaSaida tipo;

	@ManyToOne
	@JsonIgnore
	private Tributacao tributacao;

	@ManyToOne
	@JoinColumn(name = "uf")
	private Estado uf;

	@ManyToOne
	@JoinColumn(name = "cfop_codigo")
	private CFOP cfop;

	@ManyToOne
	@JoinColumn(name = "cst_pis")
	private Cst cst_pis;

	@ManyToOne
	@JoinColumn(name = "cst_cofins")
	private Cst cst_cofins;

	@ManyToOne
	@JoinColumn(name = "cst_ipi_codigo")
	private CstIPI cst_ipi;

	private Date data_cadastro;

	public TributacaoRegra() {
		super();
	}

	@Override
	public String toString() {
		return "{regra: [codigo=" + codigo + ", cst_csosn=" + cst_csosn.getCodigo() + ", pis=" + pis + ", cofins=" + cofins + ", aliq_ipi="
				+ aliq_ipi + ", aliq_icms=" + aliq_icms + ", tipo=" + tipo + ", tributacao=" + tributacao.getCodigo()
				+ ", uf=" + uf.getCodigo() + ", cfop=" + cfop.getCodigo() + ", cst_pis=" + cst_pis.getCodigo()
			+ ", cst_cofins=" + cst_cofins.getCodigo() + ", cst_ipi=" + cst_ipi.getCodigo() + "]}";
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getPis() {
		return pis;
	}

	public void setPis(Double pis) {
		this.pis = pis;
	}

	public Double getCofins() {
		return cofins;
	}

	public void setCofins(Double cofins) {
		this.cofins = cofins;
	}

	public Double getAliq_ipi() {
		return aliq_ipi;
	}

	public void setAliq_ipi(Double aliq_ipi) {
		this.aliq_ipi = aliq_ipi;
	}

	public Double getAliq_icms() {
		return aliq_icms;
	}

	public void setAliq_icms(Double aliq_icms) {
		this.aliq_icms = aliq_icms;
	}

	public EntradaSaida getTipo() {
		return tipo;
	}

	public void setTipo(EntradaSaida tipo) {
		this.tipo = tipo;
	}

	public Tributacao getTributacao() {
		return tributacao;
	}

	public void setTributacao(Tributacao tributacao) {
		this.tributacao = tributacao;
	}

	public Estado getUf() {
		return uf;
	}

	public void setUf(Estado uf) {
		this.uf = uf;
	}

	public CFOP getCfop() {
		return cfop;
	}

	public void setCfop(CFOP cfop) {
		this.cfop = cfop;
	}

	public Cst getCst_pis() {
		return cst_pis;
	}

	public void setCst_pis(Cst cst_pis) {
		this.cst_pis = cst_pis;
	}

	public Cst getCst_cofins() {
		return cst_cofins;
	}

	public void setCst_cofins(Cst cst_cofins) {
		this.cst_cofins = cst_cofins;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public CstCsosn getCst_csosn() {
		return cst_csosn;
	}

	public void setCst_csosn(CstCsosn cst_csosn) {
		this.cst_csosn = cst_csosn;
	}

	public CstIPI getCst_ipi() {
		return cst_ipi;
	}

	public void setCst_ipi(CstIPI cst_ipi) {
		this.cst_ipi = cst_ipi;
	}
}
