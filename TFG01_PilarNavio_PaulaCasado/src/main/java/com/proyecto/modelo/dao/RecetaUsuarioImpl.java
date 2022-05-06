package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.repository.RecetaUsuarioRepo;

@Service
public class RecetaUsuarioImpl implements RecetaUsuarioInt{

	@Autowired
	private RecetaUsuarioRepo rurepo;
	
	@Override
	public List<RecetaEnUsuario> findAll() {
		return rurepo.findAll();
	}
	
	@Override
	public RecetaEnUsuario findById(int idRecetausuario) {
		return rurepo.findById(idRecetausuario).orElse(null);
	}
	
	@Override
	public int guardarReceta(RecetaEnUsuario recusu) {
		int filas = 0;
		try {
			recusu.setGuardada("G");
			rurepo.save(recusu);
			filas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	@Override
	public List<RecetaEnUsuario> verRecetasGuardadas(String username) {
		return rurepo.verRecetasGuardadas(username);
	}
	

}
