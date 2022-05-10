package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the nivel_cocina database table.
 * Esta clase define objetos que contienen los atributos que definen el nivel de dificultad
 * de una receta
 */
@Entity
@Table(name="nivel_cocina")
@NamedQuery(name="NivelCocina.findAll", query="SELECT n FROM NivelCocina n")
public class NivelCocina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_NIVEL")
	private int idNivel;

	private String descripcion;

	private String nivel;

	//bi-directional many-to-one association to Receta
	@OneToMany(mappedBy="nivelCocina")
	@JsonIgnoreProperties(value="recetaEnUsuarios")
	private List<Receta> recetas;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="nivelCocina")
	@JsonIgnore
	private List<Usuario> usuarios;

	public NivelCocina() {
	}

	public int getIdNivel() {
		return this.idNivel;
	}

	public void setIdNivel(int idNivel) {
		this.idNivel = idNivel;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public List<Receta> getRecetas() {
		return this.recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public Receta addReceta(Receta receta) {
		getRecetas().add(receta);
		receta.setNivelCocina(this);

		return receta;
	}

	public Receta removeReceta(Receta receta) {
		getRecetas().remove(receta);
		receta.setNivelCocina(null);

		return receta;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setNivelCocina(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setNivelCocina(null);

		return usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idNivel;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof NivelCocina))
			return false;
		NivelCocina other = (NivelCocina) obj;
		if (idNivel != other.idNivel)
			return false;
		return true;
	}

}