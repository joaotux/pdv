package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.originmobi.pdv.enumerado.EntradaSaida;

@Entity
@Table(name = "tributacao_regra")
public class TributacaoRegra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	@JoinColumn(name = "cst_csosn_codigo")
	private CstCsosn cstCsosn;

	private Double pis;
	private Double cofins;

	@Column(name = "aliq_ipi")
	private Double aliqIpi;

	@Column(name = "aliq_icms")
	private Double aliqIcms;

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
	private Cst cstPis;

	@ManyToOne
	@JoinColumn(name = "cst_cofins")
	private Cst cstCofins;

	@ManyToOne
	@JoinColumn(name = "cst_ipi_codigo")
	private CstIPI cstIpi;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	public TributacaoRegra() {
		super();
	}

	@Override
	public String toString() {
		return "{regra: [codigo=" + codigo + ", cst_csosn=" + cstCsosn.getCodigo() + ", pis=" + pis + ", cofins=" + cofins + ", aliq_ipi="
				+ aliqIpi + ", aliq_icms=" + aliqIcms + ", tipo=" + tipo + ", tributacao=" + tributacao.getCodigo()
				+ ", uf=" + uf.getCodigo() + ", cfop=" + cfop.getCodigo() + ", cst_pis=" + cstPis.getCodigo()
			+ ", cst_cofins=" + cstCofins.getCodigo() + ", cst_ipi=" + cstIpi.getCodigo() + "]}";
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

	public Double getAliqIpi() {
		return aliqIpi;
	}

	public void setAliqIpi(Double aliqIpi) {
		this.aliqIpi = aliqIpi;
	}

	public Double getAliqIcms() {
		return aliqIcms;
	}

	public void setAliqIcms(Double aliqIcms) {
		this.aliqIcms = aliqIcms;
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

	public Cst getCstPis() {
		return cstPis;
	}

	public void setCstPis(Cst cstPis) {
		this.cstPis = cstPis;
	}

	public Cst getCstCofins() {
		return cstCofins;
	}

	public void setCstCofins(Cst cstCofins) {
		this.cstCofins = cstCofins;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public CstCsosn getCstCsosn() {
		return cstCsosn;
	}

	public void setCstCsosn(CstCsosn cstCsosn) {
		this.cstCsosn = cstCsosn;
	}

	public CstIPI getCstIpi() {
		return cstIpi;
	}

	public void setCstIpi(CstIPI cstIPI) {
		this.cstIpi = cstIPI;
	}
}
