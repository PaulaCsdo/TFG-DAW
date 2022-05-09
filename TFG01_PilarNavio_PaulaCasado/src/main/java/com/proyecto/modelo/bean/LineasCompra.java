package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the lineas_compra database table.
 * Esta clase define objetos que contienen los atributos necesarios para crear
 * las lineas de la compra con la cantidad de ingredientes de todas las recetas agendadas.
 */
@Entity
@Table(name="lineas_compra")
@NamedQuery(name="LineasCompra.findAll", query="SELECT l FROM LineasCompra l")
public class LineasCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_LINEA")
	private int idLinea;

	private int cantidad;

	//bi-directional many-to-one association to Ingrediente
	@ManyToOne
	@JoinColumn(name="ID_INGREDIENTE")
	private Ingrediente ingrediente;

	//bi-directional many-to-one association to ListasCompra
	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="ID_LISTA_COMPRA")
	private ListasCompra listasCompra;

	public LineasCompra() {
	}

	public int getIdLinea() {
		return this.idLinea;
	}

	public void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Ingrediente getIngrediente() {
		return this.ingrediente;
	}

	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}

	public ListasCompra getListasCompra() {
		return this.listasCompra;
	}

	public void setListasCompra(ListasCompra listasCompra) {
		this.listasCompra = listasCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLinea;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LineasCompra))
			return false;
		LineasCompra other = (LineasCompra) obj;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}

}