package com.proyecto.modelo.dao;

import java.util.List;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.dto.RecetaDTO;

public interface RecetaInt {
	List<Receta> verRecetas();
	int eliminarReceta (int idReceta);
	Receta altaReceta (RecetaDTO receta);
	Receta findById (int idReceta);
	List<Receta> misRecetas(String username);
	List<Receta> buscarXNombre(String titulo);
	List<Receta> buscarXMomento(String momento);
}
