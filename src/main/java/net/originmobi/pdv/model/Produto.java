package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import net.originmobi.pdv.enumerado.Ativo;
import net.originmobi.pdv.enumerado.produto.ProdutoBalanca;
import net.originmobi.pdv.enumerado.produto.ProdutoControleEstoque;
import net.originmobi.pdv.enumerado.produto.ProdutoSubstTributaria;
import net.originmobi.pdv.enumerado.produto.ProdutoVendavel;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição não pode ser vazia")
	@Size(min = 3, max = 50)
	private String descricao;

	@Column(name = "valor_custo")
	@NumberFormat(pattern = "#,##0.00")
	private Double valorCusto;

	@Column(name = "valor_venda")
	@NumberFormat(pattern = "#,##0.00")
	private Double valorVenda;

	@Column(name = "valor_balanca")
	@NumberFormat(pattern = "#,##0.00")
	@Transient
	private Double valorBalanca;

	@Column(name = "balanca")
	@Enumerated(EnumType.ORDINAL)
	private ProdutoBalanca balanca;

	@Column(name = "data_validade")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private java.util.Date dataValidade;

	@Enumerated(EnumType.STRING)
	private ProdutoVendavel vendavel;

	@Enumerated(EnumType.STRING)
	private Ativo ativo;

	@Size(max = 10, message = "Unidade de conter no máximo 10 caracteres")
	private String unidade;

	@Enumerated(EnumType.ORDINAL)
	private ProdutoSubstTributaria subtributaria;

	@Size(max = 8)
	private String ncm;

	@Size(max = 7)
	private String cest;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@Column(name = "controla_estoque")
	@Enumerated(EnumType.STRING)
	private ProdutoControleEstoque controlaEstoque;

	@ManyToOne
	private Fornecedor fornecedor;

	@ManyToOne
	private Grupo grupo;

	@ManyToOne
	private Categoria categoria;

	@ManyToOne
	private Tributacao tributacao;

	@ManyToOne
	@JoinColumn(name = "bc_icms_codigo")
	private ModBcIcms modBcIcms;

	@OneToOne(mappedBy = "produto")
	private ProdutoEstoque estoque;

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

	public Double getValorCusto() {
		return valorCusto;
	}

	public void setValorCusto(Double valorCusto) {
		this.valorCusto = valorCusto;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Double getValorBalanca() {
		return valorBalanca;
	}

	public void setValorBalanca(Double valorBalanca) {
		this.valorBalanca = valorBalanca;
	}

	public java.util.Date getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(java.util.Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public ProdutoBalanca getBalanca() {
		return balanca;
	}

	public void setBalanca(ProdutoBalanca balanca) {
		this.balanca = balanca;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public ProdutoSubstTributaria getSubtributaria() {
		return subtributaria;
	}

	public void setSubtributaria(ProdutoSubstTributaria subtributaria) {
		this.subtributaria = subtributaria;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public Tributacao getTributacao() {
		return tributacao;
	}

	public void setTributacao(Tributacao tributacao) {
		this.tributacao = tributacao;
	}

	public ModBcIcms getModBcIcms() {
		return modBcIcms;
	}

	public void setModBcIcms(ModBcIcms modBcIcms) {
		this.modBcIcms = modBcIcms;
	}

	public ProdutoVendavel getVendavel() {
		return vendavel;
	}

	public void setVendavel(ProdutoVendavel vendavel) {
		this.vendavel = vendavel;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public ProdutoEstoque getEstoque() {
		return estoque;
	}

	public void setEstoque(ProdutoEstoque estoque) {
		this.estoque = estoque;
	}

	public ProdutoControleEstoque getControlaEstoque() {
		return controlaEstoque;
	}

	public void setControlaEstoque(ProdutoControleEstoque controleEstoque) {
		this.controlaEstoque = controleEstoque;
	}

}
