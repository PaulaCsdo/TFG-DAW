package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.repository.IngredienteRecetaRepo;

@Service
public class IngredienteRecetaImpl implements IngredienteRecetaInt{

	@Autowired
	private IngredienteRecetaRepo irrepo;
	
	@Override
	public List<IngredienteEnReceta> findAll() {
		return irrepo.findAll();
	}

	//Con este método, creamos una nueva receta incluyendo los ingredientes
	
	@Override
	public int nuevaReceta(List<IngredienteEnReceta> lista) {
		for(IngredienteEnReceta ele: lista) {
			irrepo.save(ele);
		}
		return 1;
	}

	@Override
	public IngredienteEnReceta findById(int idIngredientereceta) {
		return irrepo.findById(idIngredientereceta).orElse(null);
	}

	@Override
	public List<IngredienteEnReceta> buscarXIngrediente(String descripcion) {
		return irrepo.buscarXIngrediente(descripcion);
	}

	@Override
	public List<IngredienteEnReceta> buscarXReceta(String titulo) {
		return irrepo.buscarXReceta(titulo);
	}

	@Override
	public List<IngredienteEnReceta> buscarXCategoria(int idCategoria) {
		return irrepo.buscarXCategoria(idCategoria);
	}

	@Override
	public List<IngredienteEnReceta> buscarXNivel(int idNivel) {
		return irrepo.buscarXNivel(idNivel);
	}

	@Override
	public List<IngredienteEnReceta> buscarXTipo(int idTipoDieta) {
		return irrepo.buscarXTipo(idTipoDieta);
	}

	@Override
	public List<IngredienteEnReceta> misRecetas(String username) {
		return irrepo.misRecetas(username);
	}

	


}
