package net.originmobi.pdv.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Permissoes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Nome não pode ser vazio")
	@Size(max = 45, message = "Tamanho máximo de 45 caracteres")
	private String nome;

	@NotBlank(message = "Descrição não pode ser vazio")
	@Size(max = 45, message = "Tamanho máximo de 45 caracteres")
	private String descricao;

	@ManyToMany(mappedBy = "permissoes")
	private List<GrupoUsuario> grupoUsuario;

	@ManyToMany
	@JoinTable(name = "usuario_permissoes")
	private List<Usuario> usuario;

	public Permissoes() {
	}

	public Permissoes(String nome, String descricao, List<GrupoUsuario> grupoUsuario, List<Usuario> usuario) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.grupoUsuario = grupoUsuario;
		this.usuario = usuario;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<GrupoUsuario> getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(List<GrupoUsuario> grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

}
