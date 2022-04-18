package com.proyecto.modelo.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.repository.RecetaRepo;

@Service
public class RecetaImpl implements RecetaInt{
	@Autowired
	RecetaRepo rrepo;
	
	//int num_valoraciones = 0; //DUDA: ¿donde iniciamos esta variable que debe guardarse como atr de aplicacion?

	
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

	@Override
	public List<Receta> buscarXMomento(String momento) {
		return rrepo.buscarXMomento(momento);
	}

	@Override
	public List<Receta> verMisRecetas(String username) {
		return rrepo.verMisRecetas(username);
	}

	@Override
	public List<Receta> verRecetasGuardadas() {
		return rrepo.verRecetasGuardadas();
	}

	@Override
	public int guardarReceta(Receta receta) {
		if(findById(receta.getIdReceta())!=null) {
			List<Receta> guardadas = rrepo.verRecetasGuardadas();
			guardadas.add(receta);
			return 1;
		}
		return 0;
	}

	//DUDA: ¿Creamos una tabla renacida con las puntuaciones de cada receta para recuperar el arraylist?
	@Override
	public int valorarReceta(Receta receta, BigDecimal valoracion) {
		if(rrepo.findById(receta.getIdReceta()) != null) {
			//Leer el ArrayList con todas las valoraciones
			
			//Si el tamaño del ArrayList es 0 --> crear arraylist valoraciones;
			
			//Si el tamaño del ArrayList es >0 --> valoraciones.add(valoracion);
			//int suma=0;
			//int valoracion =0;
			//for (int i=0; i<valoraciones.length; i++){
			//	suma += valoraciones[i+]
			//}
			//BigDecimal puntuacion = suma/valoraciones.length();
			//receta.setPuntuacion(puntuacion);
			//return 1;
		}
		return 0;
	}

}
