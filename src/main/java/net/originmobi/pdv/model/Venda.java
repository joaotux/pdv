package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

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

	@Column(name = "valor_produtos")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorProdutos;

	@Column(name = "valor_desconto")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorDesconto;

	@Column(name = "valor_acrescimo")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorAcrescimo;

	@Column(name = "valor_total")
	@NumberFormat(pattern = "##,##0.00")
	private Double valorTotal;

	@Enumerated(EnumType.STRING)
	private VendaSituacao situacao;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataCadastro;

	@Column(name = "data_finalizado")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataFinalizado;

	@Column(name = "data_cancelado")
	@DateTimeFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp dataCancelado;

	@ManyToOne
	private Pessoa pessoa;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	@JoinTable(name = "pagamento_tipo_venda",
			joinColumns = @JoinColumn(name = "ven_codigo"),
			inverseJoinColumns = @JoinColumn(name = "pag_tipo_codigo"))
	private PagamentoTipo pagamentotipo;

	@ManyToMany
	@JoinTable(name = "venda_produtos")
	private List<Produto> produto;

	public Venda() {
	}

	public Venda(String observacao, Double valorProdutos, Double valorDesconto, Double valorAcrescimo,
				 Double valorTotal, VendaSituacao situacao, Timestamp dataCadastro, Timestamp dataFinalizado,
				 Timestamp dataCancelado, Pessoa pessoa, Usuario usuario) {
		super();
		this.observacao = observacao;
		this.valorProdutos = valorProdutos;
		this.valorDesconto = valorDesconto;
		this.valorAcrescimo = valorAcrescimo;
		this.valorTotal = valorTotal;
		this.situacao = situacao;
		this.dataCadastro = dataCadastro;
		this.dataFinalizado = dataFinalizado;
		this.dataCancelado = dataCancelado;
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

	public Double getValorProdutos() {
		return valorProdutos;
	}

	public void setValorProdutos(Double valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorAcrescimo() {
		return valorAcrescimo;
	}

	public void setValorAcrescimo(Double valorAcrescimo) {
		this.valorAcrescimo = valorAcrescimo;
	}

	public VendaSituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(VendaSituacao situacao) {
		this.situacao = situacao;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Timestamp getDataFinalizado() {
		return dataFinalizado;
	}

	public void setDataFinalizado(Timestamp dataFinalizado) {
		this.dataFinalizado = dataFinalizado;
	}

	public Timestamp getDataCancelado() {
		return dataCancelado;
	}

	public void setDataCancelado(Timestamp dataCancelado) {
		this.dataCancelado = dataCancelado;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
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
