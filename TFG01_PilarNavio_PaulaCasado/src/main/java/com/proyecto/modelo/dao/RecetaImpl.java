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

	@Override
	public int eliminarReceta(int idReceta) {
		int filas=0;
		try {
			rrepo.deleteById(idReceta);
			filas=1;
		}catch (Exception e) {
			e.printStackTrace();
	
		}
		return filas;
	}

	@Override
	public int altaReceta(Receta receta) {
		if(findById(receta.getIdReceta())==null) {
			rrepo.save(receta);
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public Receta findById(int idReceta) {
		return rrepo.findById(idReceta).orElse(null);
	}

	@Override
	public List<Receta> buscarXIngrediente(int idIngrediente) {
		return rrepo.buscarXIngrediente(idIngrediente);
	}

	@Override
	public List<Receta> buscarXNombre(String titulo) {
		return rrepo.buscarXNombre(titulo);
	}

	@Override
	public List<Receta> buscarXCategoria(int idCategoria) {
		return rrepo.buscarXCategoria(idCategoria);
	}

}
