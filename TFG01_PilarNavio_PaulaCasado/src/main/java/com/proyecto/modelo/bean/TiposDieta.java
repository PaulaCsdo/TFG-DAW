package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_dietas database table.
 * 
 */
@Entity
@Table(name="tipos_dietas")
@NamedQuery(name="TiposDieta.findAll", query="SELECT t FROM TiposDieta t")
public class TiposDieta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TIPO_DIETA")
	private int idTipoDieta;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-many association to Receta
	@ManyToMany
	@JoinTable(
		name="tipo_dieta_receta"
		, joinColumns={
			@JoinColumn(name="ID_TIPO_DIETA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_RECETA")
			}
		)
	private List<Receta> recetas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tiposDieta")
	private List<Usuario> usuarios;

	public TiposDieta() {
	}

	public int getIdTipoDieta() {
		return this.idTipoDieta;
	}

	public void setIdTipoDieta(int idTipoDieta) {
		this.idTipoDieta = idTipoDieta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Receta> getRecetas() {
		return this.recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTiposDieta(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTiposDieta(null);

		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idTipoDieta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TiposDieta))
			return false;
		TiposDieta other = (TiposDieta) obj;
		if (idTipoDieta != other.idTipoDieta)
			return false;
		return true;
	}

}