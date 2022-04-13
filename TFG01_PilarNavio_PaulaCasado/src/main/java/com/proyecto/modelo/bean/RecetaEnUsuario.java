package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the receta_en_usuario database table.
 * 
 */
@Entity
@Table(name="receta_en_usuario")
@NamedQuery(name="RecetaEnUsuario.findAll", query="SELECT r FROM RecetaEnUsuario r")
public class RecetaEnUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_RECETAUSUARIO")
	private int idRecetausuario;

	private String agendada;

	private String guardada;

	//bi-directional many-to-one association to Receta
	@ManyToOne
	@JoinColumn(name="ID_RECETA")
	private Receta receta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="USERNAME")
	private Usuario usuario;

	public RecetaEnUsuario() {
	}

	public int getIdRecetausuario() {
		return this.idRecetausuario;
	}

	public void setIdRecetausuario(int idRecetausuario) {
		this.idRecetausuario = idRecetausuario;
	}

	public String getAgendada() {
		return this.agendada;
	}

	public void setAgendada(String agendada) {
		this.agendada = agendada;
	}

	public String getGuardada() {
		return this.guardada;
	}

	public void setGuardada(String guardada) {
		this.guardada = guardada;
	}

	public Receta getReceta() {
		return this.receta;
	}

	public void setReceta(Receta receta) {
		this.receta = receta;
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
		result = prime * result + idRecetausuario;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RecetaEnUsuario))
			return false;
		RecetaEnUsuario other = (RecetaEnUsuario) obj;
		if (idRecetausuario != other.idRecetausuario)
			return false;
		return true;
	}

}