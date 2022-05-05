package com.proyecto.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.modelo.bean.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, String>{
	

}
