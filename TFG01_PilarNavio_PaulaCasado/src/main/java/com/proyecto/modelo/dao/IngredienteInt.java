package com.proyecto.modelo.dao;

import java.util.List;

import com.proyecto.modelo.bean.Ingrediente;

public interface IngredienteInt {
	List<Ingrediente> findAll();
	int altaIngrediente (Ingrediente ingrediente);
	Ingrediente findById(int idIngrediente);
	List<Ingrediente> buscarPorDescripcion(String descripcion);
}
