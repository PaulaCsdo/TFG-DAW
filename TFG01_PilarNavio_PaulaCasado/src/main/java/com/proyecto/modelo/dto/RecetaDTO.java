package com.proyecto.modelo.dto;

import java.io.Serializable;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.bean.NivelCocina;


public class RecetaDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idReceta;
	private String username;
	private int kcal;
	private String momento;
	private int numPorciones;
	private String pasos;
	private int tiempo;
	private String titulo;
	private Categoria categoria;
	private NivelCocina nivelCocina;
	
	public RecetaDTO() {
		super();
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public String getMomento() {
		return momento;
	}

	public void setMomento(String momento) {
		this.momento = momento;
	}

	public int getNumPorciones() {
		return numPorciones;
	}

	public void setNumPorciones(int numPorciones) {
		this.numPorciones = numPorciones;
	}

	public String getPasos() {
		return pasos;
	}

	public void setPasos(String pasos) {
		this.pasos = pasos;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public NivelCocina getNivelCocina() {
		return nivelCocina;
	}

	public void setNivelCocina(NivelCocina nivelCocina) {
		this.nivelCocina = nivelCocina;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdReceta() {
		return idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	

}
