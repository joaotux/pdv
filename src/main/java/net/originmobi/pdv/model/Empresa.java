package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Nome não pode ser em branco")
	@Size(min = 4, max = 255, message = "Quantidade minima de caracteres é de 4 e máxima de 255")
	private String nome;

	@Column(name = "nome_fantasia")
	@NotBlank(message = "Nome não pode ser em branco")
	@Size(min = 4, max = 255, message = "Quantidade minima de caracteres é de 4 e máxima de 255")
	private String nomeFantasia;

	@CNPJ
	private String cnpj;

	private String ie;

	@ManyToOne
	@JoinColumn(name = "regime_tributario_codigo")
	private RegimeTributario regimeTributario;

	@OneToOne
	private EmpresaParametro parametro;

	@OneToOne
	private Endereco endereco;
	
	public Empresa() {
		super();
	}

	public Empresa(String nome, String nomeFantasia, String cnpj, String ie, RegimeTributario regime,
				   Endereco endereco, EmpresaParametro parametro) {
		this.nome = nome;
		this.nomeFantasia = nomeFantasia;
		this.cnpj = cnpj;
		this.ie = ie;
		this.regimeTributario = regime;
		this.endereco = endereco;
		this.parametro = parametro;

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

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public RegimeTributario getRegimeTributario() {
		return regimeTributario;
	}

	public void setRegimeTributario(RegimeTributario regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	public EmpresaParametro getParametro() {
		return parametro;
	}

	public void setParametro(EmpresaParametro parametro) {
		this.parametro = parametro;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
