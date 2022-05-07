package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String apellido;

	private String email;

	private int enabled;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ALTA")
	private Date fechaAlta;

	private String nombre;

	private String password;

	//bi-directional many-to-one association to ListasCompra
	@OneToMany(mappedBy="usuario")
	private List<ListasCompra> listasCompras;

	//bi-directional many-to-one association to RecetaEnUsuario
	@OneToMany(mappedBy="usuario")
	@JsonIgnoreProperties(value="usuarios")
	private List<RecetaEnUsuario> recetaEnUsuarios;

	//bi-directional many-to-one association to Receta
	@OneToMany(mappedBy="usuario")
	@JsonIgnoreProperties(value="recetaEnUsuarios")
	private List<Receta> recetas;

	//bi-directional many-to-many association to Perfile
	@ManyToMany
	@JoinTable(
		name="usuario_perfiles"
		, joinColumns={
			@JoinColumn(name="USERNAME")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PERFIL")
			}
		)
	private List<Perfile> perfiles;

	//bi-directional many-to-one association to NivelCocina
	@ManyToOne
	@JoinColumn(name="ID_NIVEL")
	@JsonIgnoreProperties(value={"usuarios","recetas"})
	private NivelCocina nivelCocina;

	//bi-directional many-to-one association to TiposDieta
	@ManyToOne
	@JoinColumn(name="ID_TIPO_DIETA")
	@JsonIgnoreProperties(value={"usuarios", "recetas"})
	private TiposDieta tiposDieta;

	public Usuario() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEnabled() {
		return this.enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ListasCompra> getListasCompras() {
		return this.listasCompras;
	}

	public void setListasCompras(List<ListasCompra> listasCompras) {
		this.listasCompras = listasCompras;
	}

	public ListasCompra addListasCompra(ListasCompra listasCompra) {
		getListasCompras().add(listasCompra);
		listasCompra.setUsuario(this);

		return listasCompra;
	}

	public ListasCompra removeListasCompra(ListasCompra listasCompra) {
		getListasCompras().remove(listasCompra);
		listasCompra.setUsuario(null);

		return listasCompra;
	}

	public List<RecetaEnUsuario> getRecetaEnUsuarios() {
		return this.recetaEnUsuarios;
	}

	public void setRecetaEnUsuarios(List<RecetaEnUsuario> recetaEnUsuarios) {
		this.recetaEnUsuarios = recetaEnUsuarios;
	}

	public RecetaEnUsuario addRecetaEnUsuario(RecetaEnUsuario recetaEnUsuario) {
		getRecetaEnUsuarios().add(recetaEnUsuario);
		recetaEnUsuario.setUsuario(this);

		return recetaEnUsuario;
	}

	public RecetaEnUsuario removeRecetaEnUsuario(RecetaEnUsuario recetaEnUsuario) {
		getRecetaEnUsuarios().remove(recetaEnUsuario);
		recetaEnUsuario.setUsuario(null);

		return recetaEnUsuario;
	}

	public List<Receta> getRecetas() {
		return this.recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public Receta addReceta(Receta receta) {
		getRecetas().add(receta);
		receta.setUsuario(this);

		return receta;
	}

	public Receta removeReceta(Receta receta) {
		getRecetas().remove(receta);
		receta.setUsuario(null);

		return receta;
	}

	public List<Perfile> getPerfiles() {
		return this.perfiles;
	}

	public void setPerfiles(List<Perfile> perfiles) {
		this.perfiles = perfiles;
	}

	public NivelCocina getNivelCocina() {
		return this.nivelCocina;
	}

	public void setNivelCocina(NivelCocina nivelCocina) {
		this.nivelCocina = nivelCocina;
	}

	public TiposDieta getTiposDieta() {
		return this.tiposDieta;
	}

	public void setTiposDieta(TiposDieta tiposDieta) {
		this.tiposDieta = tiposDieta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}