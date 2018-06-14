package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "nome não pode ser vazio")
	@Size(min = 5, max = 250)
	private String nome;

	@Size(max = 250)
	private String apelido;

	@Column(unique = true)
	private String cpfcnpj;

	private String observacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_nascimento;
	private Date data_cadastro;

	@ManyToOne
	@JoinColumn(name = "endereco_codigo")
	private Endereco endereco;

	@OneToMany
	private List<Telefone> telefone;

	public Pessoa() {
	}

	public Pessoa(String nome, String apelido, String cpfcnpj, String observacao, Date data_cadastro,
			Date data_nascimento, Endereco endereco, List<Telefone> telefone) {
		this.nome = nome;
		this.apelido = apelido;
		this.cpfcnpj = cpfcnpj;
		this.observacao = observacao;
		this.data_cadastro = data_cadastro;
		this.data_nascimento = data_nascimento;
		this.endereco = endereco;
		this.telefone = telefone;
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

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

}
