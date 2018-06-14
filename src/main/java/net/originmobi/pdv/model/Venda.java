package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import net.originmobi.pdv.enumerado.VendaSituacao;

@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String observacao;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_produtos;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_desconto;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_acrescimo;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor_total;

	@Enumerated(EnumType.STRING)
	private VendaSituacao situacao;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_cadastro;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_finalizado;

	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp data_cancelado;

	@ManyToOne
	private Pessoa pessoa;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	@JoinTable(name = "pagamento_tipo_venda", joinColumns = @JoinColumn(name = "ven_codigo"), inverseJoinColumns = @JoinColumn(name = "pag_tipo_codigo"))
	private PagamentoTipo pagamentotipo;

	@ManyToMany
	@JoinTable(name = "venda_produtos")
	private List<Produto> produto;

	public Venda() {
	}

	public Venda(String observacao, Double valor_produtos, Double valor_desconto, Double valor_acrescimo,
			Double valor_total, VendaSituacao situacao, Timestamp data_cadastro, Timestamp data_finalizado,
			Timestamp data_cancelado, Pessoa pessoa, Usuario usuario) {
		super();
		this.observacao = observacao;
		this.valor_produtos = valor_produtos;
		this.valor_desconto = valor_desconto;
		this.valor_acrescimo = valor_acrescimo;
		this.valor_total = valor_total;
		this.situacao = situacao;
		this.data_cadastro = data_cadastro;
		this.data_finalizado = data_finalizado;
		this.data_cancelado = data_cancelado;
		this.pessoa = pessoa;
		this.usuario = usuario;
	}

	public boolean isAberta() {
		return situacao.equals(VendaSituacao.ABERTA);
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Double getValor_produtos() {
		return valor_produtos;
	}

	public void setValor_produtos(Double valor_produtos) {
		this.valor_produtos = valor_produtos;
	}

	public Double getValor_desconto() {
		return valor_desconto;
	}

	public void setValor_desconto(Double valor_desconto) {
		this.valor_desconto = valor_desconto;
	}

	public Double getValor_acrescimo() {
		return valor_acrescimo;
	}

	public void setValor_acrescimo(Double valor_acrescimo) {
		this.valor_acrescimo = valor_acrescimo;
	}

	public VendaSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(VendaSituacao situacao) {
		this.situacao = situacao;
	}

	public Timestamp getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Timestamp data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Timestamp getData_finalizado() {
		return data_finalizado;
	}

	public void setData_finalizado(Timestamp data_finalizado) {
		this.data_finalizado = data_finalizado;
	}

	public Timestamp getData_cancelado() {
		return data_cancelado;
	}

	public void setData_cancelado(Timestamp data_cancelado) {
		this.data_cancelado = data_cancelado;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Double getValor_total() {
		return valor_total;
	}

	public void setValor_total(Double valor_total) {
		this.valor_total = valor_total;
	}

	public PagamentoTipo getPagamentotipo() {
		return pagamentotipo;
	}

	public void setPagamentotipo(PagamentoTipo pagamentotipo) {
		this.pagamentotipo = pagamentotipo;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

}
