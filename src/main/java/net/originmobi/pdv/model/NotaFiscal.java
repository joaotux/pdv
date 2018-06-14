package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import net.originmobi.pdv.enumerado.notafiscal.NotaFiscalTipo;

@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private Long numero;
	private int modelo;
	private int serie;

	@Enumerated(EnumType.ORDINAL)
	private NotaFiscalTipo tipo;

	private String chave_acesso;
	private String natureza_operacao;
	private String situacao;
	private int tipo_emissao;
	private int tipo_impressao;

	@Column(name = "cdv")
	private Long dv;

	// valor 0 - emissão de nfe com aplicativo do contribuinte
	@Column(name = "procemi")
	private int procEmis;

	@Column(name = "verproc")
	private String verProc;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_emissao;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_saida;

	@DateTimeFormat(pattern = "hh:mm:ss")
	private Time hora_saida;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cadastro;

	@ManyToOne
	@JoinColumn(name = "emissor_codigo")
	private Empresa emissor;

	@ManyToOne
	@JoinColumn(name = "destinatario_codigo")
	private Pessoa destinatario;

	@ManyToOne
	@JoinColumn(name = "frete_tipo_codigo")
	private FreteTipo freteTipo;

	@ManyToOne
	@JoinColumn(name = "finalidade_codigo")
	private NotaFiscalFinalidade finalidade;

	@OneToOne
	@JoinColumn(name = "totais_codigo")
	private NotaFiscalTotais totais;

	@OneToMany(mappedBy = "notaFiscal")
	private List<NotaFiscalItem> itens;

	private int tipo_ambiente;

	public NotaFiscal() {
		super();
	}

	public NotaFiscal(Long numeroNota, int modelo, NotaFiscalTipo tipoNota, String natureza, int serie, Empresa emissor,
			Pessoa destinatario, int tipoEmissao, String verProc, FreteTipo tipoFrete, NotaFiscalFinalidade finalidade,
			NotaFiscalTotais totais, int tipo_ambiente, Date cadastro) {
		this.numero = numeroNota;
		this.modelo = modelo;
		this.tipo = tipoNota;
		this.natureza_operacao = natureza;
		this.serie = serie;
		this.emissor = emissor;
		this.destinatario = destinatario;
		this.tipo_emissao = tipoEmissao;
		this.verProc = verProc;
		this.freteTipo = tipoFrete;
		this.finalidade = finalidade;
		this.totais = totais;
		this.tipo_ambiente = tipo_ambiente;
		this.data_cadastro = cadastro;

	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public int getModelo() {
		return modelo;
	}

	public void setModelo(int modelo) {
		this.modelo = modelo;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public NotaFiscalTipo getTipo() {
		return tipo;
	}

	public void setTipo(NotaFiscalTipo tipo) {
		this.tipo = tipo;
	}

	public String getChave_acesso() {
		return chave_acesso;
	}

	public void setChave_acesso(String chave_acesso) {
		this.chave_acesso = chave_acesso;
	}

	public String getNatureza_operacao() {
		return natureza_operacao;
	}

	public void setNatureza_operacao(String natureza_operacao) {
		this.natureza_operacao = natureza_operacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getTipo_emissao() {
		return tipo_emissao;
	}

	public void setTipo_emissao(int tipo_emissao) {
		this.tipo_emissao = tipo_emissao;
	}

	public int getTipo_impressao() {
		return tipo_impressao;
	}

	public void setTipo_impressao(int tipo_impressao) {
		this.tipo_impressao = tipo_impressao;
	}

	public Long getDv() {
		return dv;
	}

	public void setDv(Long dv) {
		this.dv = dv;
	}

	public int getProcEmis() {
		return procEmis;
	}

	public void setProcEmis(int procEmis) {
		this.procEmis = procEmis;
	}

	public String getVerProc() {
		return verProc;
	}

	public void setVerProc(String verProc) {
		this.verProc = verProc;
	}

	public Date getData_emissao() {
		return data_emissao;
	}

	public void setData_emissao(Date data_emissao) {
		this.data_emissao = data_emissao;
	}

	public Date getData_saida() {
		return data_saida;
	}

	public void setData_saida(Date data_saida) {
		this.data_saida = data_saida;
	}

	public Time getHora_saida() {
		return hora_saida;
	}

	public void setHora_saida(Time hora_saida) {
		this.hora_saida = hora_saida;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Empresa getEmissor() {
		return emissor;
	}

	public void setEmissor(Empresa emissor) {
		this.emissor = emissor;
	}

	public Pessoa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Pessoa destinatario) {
		this.destinatario = destinatario;
	}

	public FreteTipo getFreteTipo() {
		return freteTipo;
	}

	public void setFreteTipo(FreteTipo freteTipo) {
		this.freteTipo = freteTipo;
	}

	public NotaFiscalFinalidade getFinalidade() {
		return finalidade;
	}

	public void setFinalidade(NotaFiscalFinalidade finalidade) {
		this.finalidade = finalidade;
	}

	public NotaFiscalTotais getTotais() {
		return totais;
	}

	public void setTotais(NotaFiscalTotais totais) {
		this.totais = totais;
	}

	public List<NotaFiscalItem> getItens() {
		return itens;
	}

	public void setItens(List<NotaFiscalItem> itens) {
		this.itens = itens;
	}

	public int getTipo_ambiente() {
		return tipo_ambiente;
	}

	public void setTipo_ambiente(int tipo_ambiente) {
		this.tipo_ambiente = tipo_ambiente;
	}

}
