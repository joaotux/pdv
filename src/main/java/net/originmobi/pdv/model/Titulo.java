package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import net.originmobi.pdv.model.cartao.MaquinaCartao;

@Entity
@Table(name = "titulo")
public class Titulo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição é obrigatória")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "tipo_codigo")
	private TituloTipo tipo;

	@ManyToOne
	@JoinColumn(name="maquina_cartao_codigo")
	private MaquinaCartao maquina;

	public Titulo() {
		super();
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

	public TituloTipo getTipo() {
		return tipo;
	}

	public void setTipo(TituloTipo tipo) {
		this.tipo = tipo;
	}

	public MaquinaCartao getMaquina() {
		return maquina;
	}

	public void setMaquina(MaquinaCartao maquina) {
		this.maquina = maquina;
	}

}
