package com.proyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.bean.RecetaEnUsuario;

public interface RecetaUsuarioRepo extends JpaRepository<RecetaEnUsuario, Integer>{

}
