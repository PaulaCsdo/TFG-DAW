package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.repository.RecetaUsuarioRepo;

@Service
public class RecetaUsuarioImpl implements RecetaUsuarioInt{

	@Autowired
	private RecetaUsuarioRepo rrepo;
	@Override
	public List<RecetaEnUsuario> findAll() {
		return rrepo.findAll();
	}
	@Override
	public RecetaEnUsuario findById(int idRecetausuario) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
