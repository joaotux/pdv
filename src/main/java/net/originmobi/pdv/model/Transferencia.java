package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NumberFormat(pattern = "##,##0.00")
	private Double valor;

	@NotNull(message = "Observação, não pode ser vazia")
	@Size(max = 255, message = "Limite de 255 caracteres para observação")
	private String observacao;

	@Column(name = "data_transferencia")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Timestamp dataTransferencia;

	@ManyToOne
	private Caixa origem;

	@ManyToOne
	private Caixa destino;

	@ManyToOne
	private Usuario usuario;

	public Transferencia() {
		super();
	}

	public Transferencia(Double valor, Timestamp dataTransferencia, Caixa origem, Caixa destino, Usuario usuario,
						 String observaocao) {
		this.valor = valor;
		this.dataTransferencia = dataTransferencia;
		this.origem = origem;
		this.destino = destino;
		this.usuario = usuario;
		this.observacao = observaocao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Timestamp getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(Timestamp dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public Caixa getOrigem() {
		return origem;
	}

	public void setOrigem(Caixa origem) {
		this.origem = origem;
	}

	public Caixa getDestino() {
		return destino;
	}

	public void setDestino(Caixa destino) {
		this.destino = destino;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
