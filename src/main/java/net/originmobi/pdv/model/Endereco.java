package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Favor, informe a rua")
	@Size(max = 150, message = "Tamanho máximo para rua é de 150 caracteres")
	private String rua;

	@NotBlank(message = "Favor, informe o bairro")
	@Size(max = 50, message = "Tamanho máximo para bairro é de 50 caracteres")
	private String bairro;

	@NotNull(message = "Favor, informe o numero")
	private String numero;

	@Size(max = 25, message = "Tamanho máximo para CEP é de 25 caracteres")
	private String cep;

	@Size(max = 150, message = "Tamanho máximo para referência é de 150 caracteres")
	private String referencia;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@ManyToOne
	private Cidade cidade;

	public Endereco() {
		super();
	}

	public Endereco(String rua, String bairro, String numero, String cep, String referencia, Date dataCadastro,
			Cidade cidade) {
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.referencia = referencia;
		this.dataCadastro = dataCadastro;
		this.cidade = cidade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
