package com.proyecto.modelo.dao;
import java.util.List;

import com.proyecto.modelo.bean.IngredienteEnReceta;

public interface IngredienteRecetaInt {
	List <IngredienteEnReceta> findAll();
	int nuevaReceta (IngredienteEnReceta inrec);
	IngredienteEnReceta findById(int idIngredientereceta);
	List<IngredienteEnReceta> buscarXIngrediente(String descripcion);
	List<IngredienteEnReceta> buscarXReceta(int idReceta);


}
