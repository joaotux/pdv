package net.originmobi.pdv.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@NotBlank(message = "Usuário não pode ser vazio")
	@Size(min = 3, max = 20, message = "Tamanha minimo de 4 caracteres e máximo de 20 para usuario")
	private String user;

	@NotBlank(message = "Senha não pode ser vazia")
	private String senha;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@OneToOne
	private Pessoa pessoa;

	@ManyToMany
	@JoinTable(name = "usuario_grupousuario",
			joinColumns = @JoinColumn(name = "usuario_codigo"),
			inverseJoinColumns = @JoinColumn(name = "grupo_usuario_codigo"))
	private List<GrupoUsuario> grupousuario;

	@ManyToMany(mappedBy = "usuario")
	private List<Permissoes> permissoes;

	public Usuario() {
	}

	public Usuario(Long codigo, String user, String senha, Date dataCadastro, Pessoa pessoa,
                   List<GrupoUsuario> grupousuario, List<Permissoes> permissoes) {
		this.codigo = codigo;
		this.user = user;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
		this.pessoa = pessoa;
		this.grupousuario = grupousuario;
		this.permissoes = permissoes;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<GrupoUsuario> getGrupoUsuario() {
		return grupousuario;
	}

	public void setGrupoUsuario(List<GrupoUsuario> grupousuario) {
		this.grupousuario = grupousuario;
	}

	public List<Permissoes> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissoes> permissoes) {
		this.permissoes = permissoes;
	}

}
