package com.proyecto.modelo.dao;

import java.util.List;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.dto.RecetaDTO;

public interface RecetaInt {
	List<Receta> verRecetas();
	int eliminarReceta (int idReceta);
	int altaReceta (RecetaDTO receta);
	Receta findById (int idReceta);
	Receta recuperarSesion(RecetaDTO receta);
	List<Receta> buscarXNombre(String titulo);
	List<Receta> buscarXMomento(String momento);
}
