package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;

	@Column(name = "pais_codigo")
	private String paisCodigo;

	public Pais() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoPais() {
		return paisCodigo;
	}

	public void setCodigoPais(String codigoPais) {
		this.paisCodigo = codigoPais;
	}

}
