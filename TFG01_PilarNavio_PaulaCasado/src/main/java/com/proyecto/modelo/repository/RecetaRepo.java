package com.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.modelo.bean.Receta;

public interface RecetaRepo extends JpaRepository<Receta, Integer>{
	
	@Query("select r from Receta r where r.titulo like %?1%")
	public List<Receta> buscarXNombre(String titulo);
	
	@Query("select r from Receta r where r.momento=?1")
	public List<Receta> buscarXMomento(String momento);
	

}
