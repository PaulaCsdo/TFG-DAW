package com.proyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.bean.Categoria;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer>{

}
