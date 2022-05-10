package com.proyecto.modelo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.repository.CategoriaRepo;

@Service
public class CategoriaImpl implements CategoriaInt{

	@Autowired
	CategoriaRepo crepo;
	
	
	@Override
	public List<Categoria> verCategorias() {
		return crepo.findAll();
	}
	
	@Override
	public Categoria findById(int idCategoria) {
		return crepo.findById(idCategoria).orElse(null);
	}

}
