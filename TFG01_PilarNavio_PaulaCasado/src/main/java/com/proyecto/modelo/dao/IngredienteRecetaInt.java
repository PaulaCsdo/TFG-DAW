package com.proyecto.modelo.dao;
import java.util.List;

import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.dto.IngredienteEnRecetaDTO;

public interface IngredienteRecetaInt {
	List <IngredienteEnReceta> findAll();
	int nuevaReceta (IngredienteEnReceta nuevaReceta);
	IngredienteEnReceta findById(int idIngredientereceta);
	List<IngredienteEnReceta> buscarXIngrediente(String descripcion);
	List<IngredienteEnReceta> buscarXReceta(String titulo);
	List<IngredienteEnReceta> buscarXCategoria(int idCategoria);
	List<IngredienteEnReceta> buscarXNivel(int idNivel);
	List<IngredienteEnReceta> buscarXTipo(int idTipoDieta);
	List<IngredienteEnReceta> misRecetas(String username);

}
