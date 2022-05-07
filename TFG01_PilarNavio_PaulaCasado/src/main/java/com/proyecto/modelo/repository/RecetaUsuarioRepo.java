package com.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.modelo.bean.RecetaEnUsuario;

public interface RecetaUsuarioRepo extends JpaRepository<RecetaEnUsuario, Integer>{
	@Query("select ru from RecetaEnUsuario ru where ru.guardada ='G' AND ru.usuario.username like %?1%")
	public List<RecetaEnUsuario> verRecetasGuardadas(String username);
	
	
}
