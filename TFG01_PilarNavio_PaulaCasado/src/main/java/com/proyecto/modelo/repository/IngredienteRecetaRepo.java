package com.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.modelo.bean.IngredienteEnReceta;

public interface IngredienteRecetaRepo extends JpaRepository<IngredienteEnReceta, Integer>{
	
	@Query("select r from IngredienteEnReceta r where r.ingrediente.descripcion like %?1%") 
	public List<IngredienteEnReceta> buscarXIngrediente(String descripcion);

	@Query("select ir from IngredienteEnReceta ir where ir.receta.titulo like %?1%") 
	public List<IngredienteEnReceta> buscarXReceta(String titulo);
	
	@Query("select ir from IngredienteEnReceta ir where ir.receta.categoria.idCategoria=?1")
	public List<IngredienteEnReceta> buscarXCategoria(int idCategoria);
	
	@Query("select ir from IngredienteEnReceta ir where ir.receta.nivelCocina.idNivel=?1")
	public List<IngredienteEnReceta> buscarXNivel(int idNivel);
	
	@Query("select ir from IngredienteEnReceta ir where ir.receta.usuario.username=?1")
	public List<IngredienteEnReceta> misRecetas(String username);
	
	@Query (value = "select * from ingrediente_en_receta ir " + 
			"inner join tipo_dieta_receta tdr on ir.id_receta = tdr.id_receta " +
			"inner join tipos_dietas td on tdr.id_tipo_dieta = td.id_tipo_dieta " +
			"where td.id_tipo_dieta = ?1", nativeQuery = true)
	public List<IngredienteEnReceta> buscarXTipo(int idTipoDieta);
	
}
