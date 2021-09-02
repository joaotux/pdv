package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
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

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp dataCadastro;

	public PagarTipo() {
		super();
	}

	public PagarTipo(String descricao, Timestamp dataCadastro) {
		super();
		this.descricao = descricao;
		this.dataCadastro = dataCadastro;
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

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
