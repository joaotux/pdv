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

	@Column(name = "chave_acesso")
	private String chaveAcesso;

	@Column(name = "natureza_operacao")
	private String naturezaOperacao;

	private String situacao;

	@Column(name = "tipo_emissao")
	private int tipoEmissao;

	@Column(name = "tipo_impressao")
	private int tipoImpressao;

	@Column(name = "cdv")
	private Long dv;

	// valor 0 - emissão de nfe com aplicativo do contribuinte
	@Column(name = "procemi")
	private int procEmis;

	@Column(name = "verproc")
	private String verProc;

	@Column(name = "data_emissao")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataEmissao;

	@Column(name = "data_saida")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataSaida;

	@Column(name = "hora_saida")
	@DateTimeFormat(pattern = "hh:mm:ss")
	private Time horaSaida;

	@Column(name = "data_cadastro")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataCadastro;

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

	@Column(name = "tipo_ambiente")
	private int tipoAmbiente;

	public NotaFiscal() {
		super();
	}

	public NotaFiscal(Long numeroNota, int modelo, NotaFiscalTipo tipoNota, String natureza, int serie, Empresa emissor,
					  Pessoa destinatario, int tipoEmissao, String verProc, FreteTipo tipoFrete, NotaFiscalFinalidade finalidade,
					  NotaFiscalTotais totais, int tipoAmbiente, Date cadastro) {
		this.numero = numeroNota;
		this.modelo = modelo;
		this.tipo = tipoNota;
		this.naturezaOperacao = natureza;
		this.serie = serie;
		this.emissor = emissor;
		this.destinatario = destinatario;
		this.tipoEmissao = tipoEmissao;
		this.verProc = verProc;
		this.freteTipo = tipoFrete;
		this.finalidade = finalidade;
		this.totais = totais;
		this.tipoAmbiente = tipoAmbiente;
		this.dataCadastro = cadastro;

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

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getNaturezaOperacao() {
		return naturezaOperacao;
	}

	public void setNaturezaOperacao(String naturezaOperacao) {
		this.naturezaOperacao = naturezaOperacao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public int getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(int tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}

	public int getTipoImpressao() {
		return tipoImpressao;
	}

	public void setTipoImpressao(int tipoImpressao) {
		this.tipoImpressao = tipoImpressao;
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

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Date dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Time getHoraSaida() {
		return horaSaida;
	}

	public void setHoraSaida(Time horaSaida) {
		this.horaSaida = horaSaida;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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

	public int getTipoAmbiente() {
		return tipoAmbiente;
	}

	public void setTipoAmbiente(int tipoAmbiente) {
		this.tipoAmbiente = tipoAmbiente;
	}

}
