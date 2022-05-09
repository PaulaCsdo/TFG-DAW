package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the categorias database table.
 * Esta clase define objetos que contienen los atributos que definen una categoria de receta

 */
@Entity
@Table(name="categorias")
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_CATEGORIA")
	private int idCategoria;

	private String descripcion;

	//bi-directional many-to-one association to Receta
	@OneToMany(mappedBy="categoria")
	@JsonIgnoreProperties(value="recetaEnUsuarios")
	private List<Receta> recetas;

	public Categoria() {
	}

	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Receta> getRecetas() {
		return this.recetas;
	}

	public void setRecetas(List<Receta> recetas) {
		this.recetas = recetas;
	}

	public Receta addReceta(Receta receta) {
		getRecetas().add(receta);
		receta.setCategoria(this);

		return receta;
	}

	public Receta removeReceta(Receta receta) {
		getRecetas().remove(receta);
		receta.setCategoria(null);

		return receta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCategoria;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Categoria))
			return false;
		Categoria other = (Categoria) obj;
		if (idCategoria != other.idCategoria)
			return false;
		return true;
	}

}