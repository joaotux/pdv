package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;

@Entity
public class Ajuste implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String observacao;
	private AjusteStatus status;
	private String usuario;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_processamento;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cadastro;

	@OneToMany(mappedBy = "ajuste", cascade = CascadeType.REMOVE)
	private List<AjusteProduto> produtos;

	public Ajuste() {
		super();
	}

	public Ajuste(AjusteStatus status, String usuario, Date data_cadastro) {
		super();
		this.status = status;
		this.usuario = usuario;
		this.data_cadastro = data_cadastro;
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

	public AjusteStatus getStatus() {
		return status;
	}

	public void setStatus(AjusteStatus status) {
		this.status = status;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getData_processamento() {
		return data_processamento;
	}

	public void setData_processamento(Date data_processamento) {
		this.data_processamento = data_processamento;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public List<AjusteProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<AjusteProduto> produtos) {
		this.produtos = produtos;
	}

}
