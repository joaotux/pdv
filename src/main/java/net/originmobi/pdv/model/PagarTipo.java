package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "pagartipo")
public class PagarTipo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotNull(message = "Descrição não pode ser vazia")
	@Size(min = 4, max = 200, message = "Favor, informe no minimo 4 caracteres")
	private String descricao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp data_cadastro;

	public PagarTipo() {
		super();
	}

	public PagarTipo(String descricao, Timestamp data_cadastro) {
		super();
		this.descricao = descricao;
		this.data_cadastro = data_cadastro;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

}
