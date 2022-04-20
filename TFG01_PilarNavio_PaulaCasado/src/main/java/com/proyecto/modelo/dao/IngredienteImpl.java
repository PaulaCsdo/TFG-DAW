package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.repository.IngredienteRepo;

@Service
public class IngredienteImpl implements IngredienteInt{
	@Autowired
	private IngredienteRepo irepo;
	
	@Override
	public List<Ingrediente> findAll() {
		return irepo.findAll();
	}

	@Override
	public int altaIngrediente(Ingrediente ingrediente) {
		int filas = 0;
		try {
			irepo.save(ingrediente);
			filas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}


	@Override
	public Ingrediente findById(int idIngrediente) {
		return irepo.findById(idIngrediente).orElse(null);
	}

	@Override
	public List<Ingrediente> buscarPorDescripcion(String descripcion) {
		return irepo.buscarPorDescripcion(descripcion);
	}

}
