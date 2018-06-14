package net.originmobi.pdv.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class RegimeTributario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Descrição não pode ser em branco")
	@Size(min = 4, max = 255, message = "Quantidade minima de caracteres é de 4 e máxima de 100")
	private String descricao;

	@Column(name = "tipo_regime")
	private int tipoRegime;

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

	public int getTipoRegime() {
		return tipoRegime;
	}

	public void setTipoRegime(int tipoRegime) {
		this.tipoRegime = tipoRegime;
	}

}
