package com.proyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.bean.IngredienteEnReceta;

public interface IngredienteRecetaRepo extends JpaRepository<IngredienteEnReceta, Integer>{

}
