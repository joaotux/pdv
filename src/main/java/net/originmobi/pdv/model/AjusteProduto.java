package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ajuste_produtos")
public class AjusteProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne
	private Ajuste ajuste;

	@ManyToOne
	private Produto produto;

	private int estoque_atual;
	private int qtd_alteracao;
	private int qtd_nova;

	public AjusteProduto() {
		super();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Ajuste getAjuste() {
		return ajuste;
	}

	public void setAjuste(Ajuste ajuste) {
		this.ajuste = ajuste;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getEstoque_atual() {
		return estoque_atual;
	}

	public void setEstoque_atual(int estoque_atual) {
		this.estoque_atual = estoque_atual;
	}

	public int getQtd_alteracao() {
		return qtd_alteracao;
	}

	public void setQtd_alteracao(int qtd_alteracao) {
		this.qtd_alteracao = qtd_alteracao;
	}

	public int getQtd_nova() {
		return qtd_nova;
	}

	public void setQtd_nova(int qtd_nova) {
		this.qtd_nova = qtd_nova;
	}

}
