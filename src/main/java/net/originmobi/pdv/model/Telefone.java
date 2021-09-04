package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
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

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	public Telefone() {
	}

	public Telefone(String fone, TelefoneTipo tipo, Date dataCadastro) {
		super();
		this.fone = fone;
		this.tipo = tipo;
		this.dataCadastro = dataCadastro;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public void setTipo(TelefoneTipo tipo) {
		this.tipo = tipo;
	}

	public TelefoneTipo getTipo() {
		return tipo;
	}

}
