package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "cst_csosn")
public class CstCsosn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "cst_csosn")
	private String codCstCsosn;

	@Column(name = "simples_nacional")
	private int simplesNacional;

	public CstCsosn() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodCstCsosn() {
		return codCstCsosn;
	}

	public void setCodCstCsosn(String codCstCsosn) {
		this.codCstCsosn = codCstCsosn;
	}

	public int getSimplesNacional() {
		return simplesNacional;
	}

	public void setSimplesNacional(int simplesNacional) {
		this.simplesNacional = simplesNacional;
	}

}
