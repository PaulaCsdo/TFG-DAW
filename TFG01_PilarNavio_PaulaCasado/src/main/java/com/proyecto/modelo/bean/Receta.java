package com.proyecto.modelo.bean;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the recetas database table.
 * Esta clase define objetos que contienen los atributos que definen cada receta.
 * @see IngredienteEnRecetas
 * @see NivelCocina
 * @see Categoria
 * @see Usuario
 * @see TiposDietas
 * @see RecetaEnUsuario
 * 
 */
@Entity
@Table(name="recetas")
@NamedQuery(name="Receta.findAll", query="SELECT r FROM Receta r")
public class Receta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_RECETA")
	private int idReceta;

	private String imagen;

	private int kcal;

	private String momento;

	private String novedad;

	@Column(name="NUM_PORCIONES")
	private int numPorciones;

	@Lob
	private String pasos;

	@JsonIgnore
	private BigDecimal puntuacion;

	private int tiempo;

	private String titulo;

	//bi-directional many-to-one association to IngredienteEnReceta
	@OneToMany(mappedBy="receta")
	@JsonIgnoreProperties(value="receta")
	private List<IngredienteEnReceta> ingredienteEnRecetas;

	//bi-directional many-to-one association to RecetaEnUsuario
	@OneToMany(mappedBy="receta")
	private List<RecetaEnUsuario> recetaEnUsuarios;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIA")
	@JsonIgnoreProperties(value="recetas")
	private Categoria categoria;

	//bi-directional many-to-one association to NivelCocina
	@ManyToOne
	@JoinColumn(name="ID_NIVEL")
	@JsonIgnoreProperties(value={"usuarios","recetas"})
	private NivelCocina nivelCocina;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="Autor")
	@JsonIgnoreProperties(value={"listasCompras","recetas", "recetaEnUsuarios", "perfiles", "nivelCocina",
			"tiposDieta"})
	private Usuario usuario;

	//bi-directional many-to-many association to TiposDieta
	@ManyToMany(mappedBy="recetas")
	@JsonIgnoreProperties(value={"usuarios","recetas"})
	private List<TiposDieta> tiposDietas;

	public Receta() {
	}

	public int getIdReceta() {
		return this.idReceta;
	}

	public void setIdReceta(int idReceta) {
		this.idReceta = idReceta;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getKcal() {
		return this.kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public String getMomento() {
		return this.momento;
	}

	public void setMomento(String momento) {
		this.momento = momento;
	}

	public String getNovedad() {
		return this.novedad;
	}

	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}

	public int getNumPorciones() {
		return this.numPorciones;
	}

	public void setNumPorciones(int numPorciones) {
		this.numPorciones = numPorciones;
	}

	public String getPasos() {
		return this.pasos;
	}

	public void setPasos(String pasos) {
		this.pasos = pasos;
	}

	public BigDecimal getPuntuacion() {
		return this.puntuacion;
	}

	public void setPuntuacion(BigDecimal puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getTiempo() {
		return this.tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<IngredienteEnReceta> getIngredienteEnRecetas() {
		return this.ingredienteEnRecetas;
	}

	public void setIngredienteEnRecetas(List<IngredienteEnReceta> ingredienteEnRecetas) {
		this.ingredienteEnRecetas = ingredienteEnRecetas;
	}

	public IngredienteEnReceta addIngredienteEnReceta(IngredienteEnReceta ingredienteEnReceta) {
		getIngredienteEnRecetas().add(ingredienteEnReceta);
		ingredienteEnReceta.setReceta(this);

		return ingredienteEnReceta;
	}

	public IngredienteEnReceta removeIngredienteEnReceta(IngredienteEnReceta ingredienteEnReceta) {
		getIngredienteEnRecetas().remove(ingredienteEnReceta);
		ingredienteEnReceta.setReceta(null);

		return ingredienteEnReceta;
	}

	public List<RecetaEnUsuario> getRecetaEnUsuarios() {
		return this.recetaEnUsuarios;
	}

	public void setRecetaEnUsuarios(List<RecetaEnUsuario> recetaEnUsuarios) {
		this.recetaEnUsuarios = recetaEnUsuarios;
	}

	public RecetaEnUsuario addRecetaEnUsuario(RecetaEnUsuario recetaEnUsuario) {
		getRecetaEnUsuarios().add(recetaEnUsuario);
		recetaEnUsuario.setReceta(this);

		return recetaEnUsuario;
	}

	public RecetaEnUsuario removeRecetaEnUsuario(RecetaEnUsuario recetaEnUsuario) {
		getRecetaEnUsuarios().remove(recetaEnUsuario);
		recetaEnUsuario.setReceta(null);

		return recetaEnUsuario;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public NivelCocina getNivelCocina() {
		return this.nivelCocina;
	}

	public void setNivelCocina(NivelCocina nivelCocina) {
		this.nivelCocina = nivelCocina;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<TiposDieta> getTiposDietas() {
		return this.tiposDietas;
	}

	public void setTiposDietas(List<TiposDieta> tiposDietas) {
		this.tiposDietas = tiposDietas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idReceta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Receta))
			return false;
		Receta other = (Receta) obj;
		if (idReceta != other.idReceta)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Receta [idReceta=" + idReceta + ", imagen=" + imagen + ", kcal=" + kcal + ", momento=" + momento
				+ ", novedad=" + novedad + ", numPorciones=" + numPorciones + ", pasos=" + pasos + ", puntuacion="
				+ puntuacion + ", tiempo=" + tiempo + ", titulo=" + titulo + ", ingredienteEnRecetas="
				+ ingredienteEnRecetas + ", recetaEnUsuarios=" + recetaEnUsuarios + ", categoria=" + categoria
				+ ", nivelCocina=" + nivelCocina + ", usuario=" + usuario + ", tiposDietas=" + tiposDietas + "]";
	}

}