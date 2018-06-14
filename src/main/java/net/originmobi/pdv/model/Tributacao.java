package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Tributacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição não pode ser em branco")
	@Size(min = 4, max = 255, message = "Quantidade minima de caracteres é de 4 e máxima de 100")
	private String descricao;

	private Boolean subs_tributaria;
	private Date data_cadastro;

	@ManyToOne
	private Empresa empresa;

	@OneToMany(mappedBy = "tributacao")
	private List<TributacaoRegra> regra;

	public Tributacao() {
		super();
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

	public Boolean getSubs_tributaria() {
		return subs_tributaria;
	}

	public void setSubs_tributaria(Boolean subs_tributaria) {
		this.subs_tributaria = subs_tributaria;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<TributacaoRegra> getRegra() {
		return regra;
	}

	public void setRegra(List<TributacaoRegra> regra) {
		this.regra = regra;
	}

}
