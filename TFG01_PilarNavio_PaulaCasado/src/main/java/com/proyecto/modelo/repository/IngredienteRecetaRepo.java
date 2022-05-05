package com.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.modelo.bean.IngredienteEnReceta;

public interface IngredienteRecetaRepo extends JpaRepository<IngredienteEnReceta, Integer>{
	
	@Query("select r from IngredienteEnReceta r where r.ingrediente.descripcion like %?1%") 
	public List<IngredienteEnReceta> buscarXIngrediente(String descripcion);

	
//	@Query("select ir.receta.idReceta from IngredienteEnReceta ir where ir.receta.idReceta=?1") 
//	public List<IngredienteEnReceta> buscarXReceta(int idReceta);
	
	@Query("select ir.receta.idReceta from IngredienteEnReceta ir where ir.receta.idReceta=?1") 
	public List<Integer> buscarXReceta(int idReceta);
	
}
