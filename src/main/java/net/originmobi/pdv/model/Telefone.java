package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.originmobi.pdv.enumerado.TelefoneTipo;

@Entity
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull(message = "Favor, informe o telefone")
	@Size(max = 15, message = "Tamanho máximo de 15 caracteres")
	private String fone;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TelefoneTipo tipo;
	private Date data_cadastro;

	public Telefone() {
	}

	public Telefone(String fone, TelefoneTipo tipo, Date data_cadastro) {
		super();
		this.fone = fone;
		this.tipo = tipo;
		this.data_cadastro = data_cadastro;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public void setTipo(TelefoneTipo tipo) {
		this.tipo = tipo;
	}

	public TelefoneTipo getTipo() {
		return tipo;
	}

}
