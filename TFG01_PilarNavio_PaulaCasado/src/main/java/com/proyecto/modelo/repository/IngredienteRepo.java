package com.proyecto.modelo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.modelo.bean.Ingrediente;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer>{
	
	@Query("select i from Ingrediente i where i.descripcion like %?1%")
	public List<Ingrediente> buscarPorDescripcion(String descripcion);

}
