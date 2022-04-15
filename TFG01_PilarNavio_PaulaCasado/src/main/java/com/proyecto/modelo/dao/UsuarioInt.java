package com.proyecto.modelo.dao;

import java.util.List;

import com.proyecto.modelo.bean.Usuario;

public interface UsuarioInt {
	
	List<Usuario> findAll();
	int altaUsuario (Usuario usuario);
	Usuario login(String username, String password);
	Usuario findById(String username);

}
