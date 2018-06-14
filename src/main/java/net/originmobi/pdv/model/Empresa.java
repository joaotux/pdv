package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

	@NotBlank(message = "Nome não pode ser em branco")
	@Size(min = 4, max = 255, message = "Quantidade minima de caracteres é de 4 e máxima de 255")
	private String nome_fantasia;

	@CNPJ
	private String cnpj;

	private String ie;

	@ManyToOne
	private RegimeTributario regime_tributario;

	@OneToOne
	private EmpresaParametro parametro;

	@OneToOne
	private Endereco endereco;
	
	public Empresa() {
		super();
	}

	public Empresa(String nome, String nome_fantasia, String cnpj, String ie, RegimeTributario regime,
			Endereco endereco, EmpresaParametro parametro) {
		this.nome = nome;
		this.nome_fantasia = nome_fantasia;
		this.cnpj = cnpj;
		this.ie = ie;
		this.regime_tributario = regime;
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

	public String getNome_fantasia() {
		return nome_fantasia;
	}

	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
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

	public RegimeTributario getRegime_tributario() {
		return regime_tributario;
	}

	public void setRegime_tributario(RegimeTributario regime_tributario) {
		this.regime_tributario = regime_tributario;
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
