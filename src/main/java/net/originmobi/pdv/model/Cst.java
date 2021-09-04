package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Cst implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "cst")
	private String codCst;

	private String descricao;

	public Cst() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodCst() {
		return codCst;
	}

	public void setCodCst(String cst) {
		this.codCst = cst;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
