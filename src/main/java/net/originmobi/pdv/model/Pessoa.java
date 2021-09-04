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

	@Column(name = "data_nascimento")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataNascimento;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@ManyToOne
	@JoinColumn(name = "endereco_codigo")
	private Endereco endereco;

	@OneToMany
	private List<Telefone> telefone;

	public Pessoa() {
	}

	public Pessoa(String nome, String apelido, String cpfcnpj, String observacao, Date dataCadastro,
				  Date dataNascimento, Endereco endereco, List<Telefone> telefone) {
		this.nome = nome;
		this.apelido = apelido;
		this.cpfcnpj = cpfcnpj;
		this.observacao = observacao;
		this.dataCadastro = dataCadastro;
		this.dataNascimento = dataNascimento;
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

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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
