package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the listas_compra database table.
 * Esta clase define objetos que contienen las listas de la compra con las lineas de la compra de cada usuario
 * @see LineasCompra
 */
@Entity
@Table(name="listas_compra")
@NamedQuery(name="ListasCompra.findAll", query="SELECT l FROM ListasCompra l")
public class ListasCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_LISTA_COMPRA")
	private int idListaCompra;

	//bi-directional many-to-one association to LineasCompra
	@OneToMany(mappedBy="listasCompra")
	private List<LineasCompra> lineasCompras;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private Usuario usuario;

	public ListasCompra() {
	}

	public int getIdListaCompra() {
		return this.idListaCompra;
	}

	public void setIdListaCompra(int idListaCompra) {
		this.idListaCompra = idListaCompra;
	}

	public List<LineasCompra> getLineasCompras() {
		return this.lineasCompras;
	}

	public void setLineasCompras(List<LineasCompra> lineasCompras) {
		this.lineasCompras = lineasCompras;
	}

	public LineasCompra addLineasCompra(LineasCompra lineasCompra) {
		getLineasCompras().add(lineasCompra);
		lineasCompra.setListasCompra(this);

		return lineasCompra;
	}

	public LineasCompra removeLineasCompra(LineasCompra lineasCompra) {
		getLineasCompras().remove(lineasCompra);
		lineasCompra.setListasCompra(null);

		return lineasCompra;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idListaCompra;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ListasCompra))
			return false;
		ListasCompra other = (ListasCompra) obj;
		if (idListaCompra != other.idListaCompra)
			return false;
		return true;
	}

}