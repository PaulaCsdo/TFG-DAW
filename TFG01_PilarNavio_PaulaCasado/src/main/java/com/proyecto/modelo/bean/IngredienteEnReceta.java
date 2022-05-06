package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the ingrediente_en_receta database table.
 * 
 */
@Entity
@Table(name="ingrediente_en_receta")
@NamedQuery(name="IngredienteEnReceta.findAll", query="SELECT i FROM IngredienteEnReceta i")
public class IngredienteEnReceta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INGREDIENTERECETA")
	private int idIngredientereceta;

	private float cantidad;

	private String unidad;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="ID_INGREDIENTE")
	@JsonIgnoreProperties(value={"lineasCompras", "ingredienteEnRecetas"})
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="ID_RECETA")
	@JsonIgnoreProperties(value={"recetaEnUsuarios", "ingredienteEnRecetas"})
	private Receta receta;

	public IngredienteEnReceta() {
	}

	public int getIdIngredientereceta() {
		return this.idIngredientereceta;
	}

	public void setIdIngredientereceta(int idIngredientereceta) {
		this.idIngredientereceta = idIngredientereceta;
	}

	public float getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidad() {
		return this.unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idIngredientereceta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof IngredienteEnReceta))
			return false;
		IngredienteEnReceta other = (IngredienteEnReceta) obj;
		if (idIngredientereceta != other.idIngredientereceta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IngredienteEnReceta [idIngredientereceta=" + idIngredientereceta + ", cantidad=" + cantidad
				+ ", unidad=" + unidad + ", ingrediente=" + ingrediente + ", receta=" + receta + "]";
	}




	
	

}