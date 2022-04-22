package com.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.proyecto.modelo.bean.IngredienteEnReceta;

public interface IngredienteRecetaRepo extends JpaRepository<IngredienteEnReceta, Integer>{
	


}
