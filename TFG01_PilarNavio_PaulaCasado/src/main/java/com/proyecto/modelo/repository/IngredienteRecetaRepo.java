package com.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.IngredienteEnReceta;

public interface IngredienteRecetaRepo extends JpaRepository<IngredienteEnReceta, Integer>{
	
	@Query("select r from IngredienteEnReceta r where r.ingrediente.descripcion like %?1%") 
	public List<IngredienteEnReceta> buscarXIngrediente(String descripcion);
	
	//ERROR: La Query no es correcta porque no encuentra en BD la columna IdIngredientereceta
	
//	@Query (value = "select descripcion from Ingredientes i" + 
//			"inner join Ingrediente_en_receta ier on i.idIngrediente = ier.idIngrediente " +
//			"where ier.idIngredientereceta = ?1", nativeQuery = true) 
//	public String buscarNombreIngrediente(int IdIngredientereceta);

}
