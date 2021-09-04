package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "nome_fantasia")
	@NotNull(message = "Nome Fantasia é obrigatório")
	@Size(max = 250, message = "Tamanho máximo de 250 caracteres")
	private String nomeFantasia;

	@NotNull(message = "Nome é obrigatório")
	@Size(max = 205, message = "Tamanho máximo de 250 caracteres")
	private String nome;

	@NotNull(message = "CNPJ é obrigatório")
	@CNPJ(message = "CNPJ inválido")
	@Column(unique = true)
	@Size(max = 18)
	private String cnpj;

	@Column(name = "inscricao_estadual")
	@NotNull(message = "Inscrição Estadual é obrigatório")
	private String inscricaoEstadual;

	@NotNull
	private int ativo;

	@Size(max = 150, message = "Tamanho máximo permitido de 150 caracteres")
	private String observacao;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@OneToMany
	private List<Telefone> telefone;

	@ManyToOne
	private Endereco endereco;

	public Fornecedor() {
	}

	public Fornecedor(Long codigo, String nomeFantasia, String nome, String cnpj, String inscricaoEstadual, int ativo,
					  Date dataCadastro, Endereco endereco, List<Telefone> telefone) {
		super();
		this.codigo = codigo;
		this.nomeFantasia = nomeFantasia;
		this.nome = nome;
		this.cnpj = cnpj;
		this.inscricaoEstadual = inscricaoEstadual;
		this.ativo = ativo;
		this.dataCadastro = dataCadastro;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
