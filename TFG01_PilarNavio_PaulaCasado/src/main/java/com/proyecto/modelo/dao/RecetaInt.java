package com.proyecto.modelo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.proyecto.modelo.bean.Receta;

public interface RecetaInt {
	List<Receta> verRecetas();
	int eliminarReceta (int idReceta);
	int altaReceta (Receta receta);
	Receta findById (int idReceta);
	//List<Receta> buscarXIngrediente(int idIngrediente);
	List<Receta> buscarXNombre(String titulo);
	List<Receta> buscarXCategoria(int idCategoria);
	List<Receta> buscarXMomento(String momento);
	List<Receta> verMisRecetas(String username);
	List<Receta> verRecetasGuardadas();
	int guardarReceta(Receta receta);
	int valorarReceta(Receta receta, BigDecimal valoracion);
}
