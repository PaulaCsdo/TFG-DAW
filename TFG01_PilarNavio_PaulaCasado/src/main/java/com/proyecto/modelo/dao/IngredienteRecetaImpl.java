package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.repository.IngredienteRecetaRepo;

@Service
public class IngredienteRecetaImpl implements IngredienteRecetaInt{

	@Autowired
	private IngredienteRecetaRepo irepo;
	
	@Override
	public List<IngredienteEnReceta> findAll() {
		return irepo.findAll();
	}

	//Con este método, creamos una nueva receta incluyendo los ingredientes
	
	@Override
	public int nuevaReceta(IngredienteEnReceta inrec) {
		irepo.save(inrec);
		return 1;
	}

	@Override
	public IngredienteEnReceta findById(int idIngredientereceta) {
		return irepo.findById(idIngredientereceta).orElse(null);
	}

	@Override
	public List<IngredienteEnReceta> buscarXIngrediente(String descripcion) {
		return irepo.buscarXIngrediente(descripcion);
	}

	@Override
	public String buscarNombreIngrediente(int IdIngredientereceta) {
		return irepo.buscarNombreIngrediente(IdIngredientereceta);
	}


	



}
