package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class CFOP implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@Column(name = "cfop")
	@Size(max = 4, message = "Tamanho máximo do CFOP é de 4 digitos")
	private String codCfop;

	@Size(max = 250, message = "Tamanho máximo da descrição é de 250 caracteres")
	private String descricao;

	@Size(max = 400, message = "Tamanho máximo da descrição é de 250 caracteres")
	private String aplicacao;

	public CFOP() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCodCfop() {
		return codCfop;
	}

	public void setCodCfop(String cfop) {
		this.codCfop = cfop;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

}
