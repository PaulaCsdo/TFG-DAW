package com.proyecto.modelo.dao;

import java.util.List;

import com.proyecto.modelo.bean.Ingrediente;

public interface IngredienteInt {
	List<Ingrediente> findAll();
	int altaIngrediente (Ingrediente ingrediente);
	int eliminar (int idIngrediente);
	Ingrediente findById(int idIngrediente);
}
