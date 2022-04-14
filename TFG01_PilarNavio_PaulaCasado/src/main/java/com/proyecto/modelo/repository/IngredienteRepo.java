package com.proyecto.modelo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.bean.Ingrediente;

public interface IngredienteRepo extends JpaRepository<Ingrediente, Integer>{

}
