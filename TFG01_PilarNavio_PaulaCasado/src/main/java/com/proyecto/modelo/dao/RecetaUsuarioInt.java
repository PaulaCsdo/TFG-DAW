package com.proyecto.modelo.dao;
import java.util.List;

import com.proyecto.modelo.bean.RecetaEnUsuario;

public interface RecetaUsuarioInt {

	List<RecetaEnUsuario> findAll();
	RecetaEnUsuario findById(int idRecetausuario);
	int guardarReceta(RecetaEnUsuario recusu);
	List<RecetaEnUsuario> verRecetasGuardadas(String username);
}
