package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CstCsosn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String cst_csosn;
	private int simples_nacional;

	public CstCsosn() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCst_csosn() {
		return cst_csosn;
	}

	public void setCst_csosn(String cst_csosn) {
		this.cst_csosn = cst_csosn;
	}

	public int getSimples_nacional() {
		return simples_nacional;
	}

	public void setSimples_nacional(int simples_nacial) {
		this.simples_nacional = simples_nacial;
	}

}
