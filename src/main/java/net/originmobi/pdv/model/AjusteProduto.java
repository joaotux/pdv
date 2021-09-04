package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.*;

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

	@Column(name = "estoque_atual")
	private int estoqueAtual;

	@Column(name = "qtd_alteracao")
	private int qtdAlteracao;

	@Column(name = "qtd_nova")
	private int qtdNova;

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

	public int getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(int estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public int getQtdAlteracao() {
		return qtdAlteracao;
	}

	public void setQtdAlteracao(int qtdAlteracao) {
		this.qtdAlteracao = qtdAlteracao;
	}

	public int getQtdNova() {
		return qtdNova;
	}

	public void setQtdNova(int qtdNova) {
		this.qtdNova = qtdNova;
	}

}
