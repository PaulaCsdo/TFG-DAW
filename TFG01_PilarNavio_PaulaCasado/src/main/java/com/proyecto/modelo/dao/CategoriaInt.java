package com.proyecto.modelo.dao;

import java.util.List;

import com.proyecto.modelo.bean.Categoria;

public interface CategoriaInt {
	List<Categoria> verCategorias();
	Categoria findById (int idCategoria); 
}
