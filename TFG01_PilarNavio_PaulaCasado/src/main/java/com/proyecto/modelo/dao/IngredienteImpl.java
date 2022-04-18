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
		// TODO Auto-generated method stub
		return irepo.findAll();
	}

	@Override
	public int altaIngrediente(Ingrediente ingrediente) {
		if(findById(ingrediente.getIdIngrediente())==null) {
			irepo.save(ingrediente);
			return 1;
		}
		else {
			return 0;
		}
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
