package com.proyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.bean.Receta;

public interface RecetaRepo extends JpaRepository<Receta, Integer>{

}
