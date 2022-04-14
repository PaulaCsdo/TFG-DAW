package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.repository.RecetaRepo;

@Service
public class RecetaImpl implements RecetaInt{
	@Autowired
	RecetaRepo rrepo;
	
	@Override
	public List<Receta> verRecetas() {
		return rrepo.findAll();
	}

}
