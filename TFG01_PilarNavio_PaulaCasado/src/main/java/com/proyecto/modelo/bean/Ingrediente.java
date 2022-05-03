package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Arrays;
import java.util.List;


/**
 * The persistent class for the ingredientes database table.
 * 
 */
@Entity
@Table(name="ingredientes")
@NamedQuery(name="Ingrediente.findAll", query="SELECT i FROM Ingrediente i")
public class Ingrediente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_INGREDIENTE")
	private int idIngrediente;

	private String descripcion;

	@Lob
	private byte[] imagen;

	//bi-directional many-to-one association to IngredienteEnReceta
//	@OneToMany(mappedBy="ingrediente")
//	private List<IngredienteEnReceta> ingredienteEnRecetas;

	//bi-directional many-to-one association to LineasCompra
	@OneToMany(mappedBy="ingrediente")
	private List<LineasCompra> lineasCompras;

	public Ingrediente() {
	}

	public int getIdIngrediente() {
		return this.idIngrediente;
	}

	public void setIdIngrediente(int idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

//	public List<IngredienteEnReceta> getIngredienteEnRecetas() {
//		return this.ingredienteEnRecetas;
//	}
//
//	public void setIngredienteEnRecetas(List<IngredienteEnReceta> ingredienteEnRecetas) {
//		this.ingredienteEnRecetas = ingredienteEnRecetas;
//	}

//	public IngredienteEnReceta addIngredienteEnReceta(IngredienteEnReceta ingredienteEnReceta) {
//		getIngredienteEnRecetas().add(ingredienteEnReceta);
//		ingredienteEnReceta.setIngrediente(this);
//
//		return ingredienteEnReceta;
//	}
//
//	public IngredienteEnReceta removeIngredienteEnReceta(IngredienteEnReceta ingredienteEnReceta) {
//		getIngredienteEnRecetas().remove(ingredienteEnReceta);
//		ingredienteEnReceta.setIngrediente(null);
//
//		return ingredienteEnReceta;
//	}

	public List<LineasCompra> getLineasCompras() {
		return this.lineasCompras;
	}

	public void setLineasCompras(List<LineasCompra> lineasCompras) {
		this.lineasCompras = lineasCompras;
	}

	public LineasCompra addLineasCompra(LineasCompra lineasCompra) {
		getLineasCompras().add(lineasCompra);
		lineasCompra.setIngrediente(this);

		return lineasCompra;
	}

	public LineasCompra removeLineasCompra(LineasCompra lineasCompra) {
		getLineasCompras().remove(lineasCompra);
		lineasCompra.setIngrediente(null);

		return lineasCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idIngrediente;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Ingrediente))
			return false;
		Ingrediente other = (Ingrediente) obj;
		if (idIngrediente != other.idIngrediente)
			return false;
		return true;
	}

//	@Override
//	public String toString() {
//		return "Ingrediente [idIngrediente=" + idIngrediente + ", descripcion=" + descripcion + ", imagen="
//				+ Arrays.toString(imagen) + ", ingredienteEnRecetas=" + ingredienteEnRecetas + ", lineasCompras="
//				+ lineasCompras + "]";
//	}
	
	

}