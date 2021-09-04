package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
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

	@Column(name = "subs_tributaria")
	private Boolean subsTributaria;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

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

	public Boolean getSubsTributaria() {
		return subsTributaria;
	}

	public void setSubsTributaria(Boolean subsTributaria) {
		this.subsTributaria = subsTributaria;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
