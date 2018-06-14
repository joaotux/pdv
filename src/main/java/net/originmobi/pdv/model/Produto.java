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

	@NumberFormat(pattern = "#,##0.00")
	private Double valor_custo;

	@NumberFormat(pattern = "#,##0.00")
	private Double valor_venda;

	@NumberFormat(pattern = "#,##0.00")
	@Transient
	private Double valor_balanca;

	@Column(name = "balanca")
	@Enumerated(EnumType.ORDINAL)
	private ProdutoBalanca balanca;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private java.util.Date data_validade;

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

	private Date data_cadastro;

	@Enumerated(EnumType.STRING)
	private ProdutoControleEstoque controla_estoque;

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

	public Produto() {
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

	public Double getValor_custo() {
		return valor_custo;
	}

	public void setValor_custo(Double valor_custo) {
		this.valor_custo = valor_custo;
	}

	public Double getValor_venda() {
		return valor_venda;
	}

	public void setValor_venda(Double valor_venda) {
		this.valor_venda = valor_venda;
	}

	public Double getValor_balanca() {
		return valor_balanca;
	}

	public void setValor_balanca(Double valor_balanca) {
		this.valor_balanca = valor_balanca;
	}

	public java.util.Date getData_validade() {
		return data_validade;
	}

	public void setData_validade(java.util.Date data_validade) {
		this.data_validade = data_validade;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
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

	public ProdutoControleEstoque getControla_estoque() {
		return controla_estoque;
	}

	public void setControla_estoque(ProdutoControleEstoque controla_estoque) {
		this.controla_estoque = controla_estoque;
	}

}
