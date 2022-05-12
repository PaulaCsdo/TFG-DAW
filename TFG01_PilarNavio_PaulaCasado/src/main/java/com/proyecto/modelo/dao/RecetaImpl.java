package com.proyecto.modelo.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.dto.RecetaDTO;
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
	public int altaReceta(RecetaDTO receta) {
		
		Receta rec=new Receta();
		
		rec.setCategoria(receta.getCategoria());
		rec.setNivelCocina(receta.getNivelCocina());
		rec.setMomento(receta.getMomento());
		rec.setTitulo(receta.getTitulo());
		rec.setNovedad("S");
		rec.setKcal(receta.getKcal());
		rec.setPasos(receta.getPasos());
		rec.setTiempo(receta.getTiempo());
		rec.setNumPorciones(receta.getNumPorciones());
		rec.setUsuario(receta.getUsuario());
		rec.setIdReceta(receta.getIdReceta());
		
		int filas = 0;
		try {
			rrepo.save(rec);
			filas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	

	@Override
	public Receta findById(int idReceta) {
		return rrepo.findById(idReceta).orElse(null);
	}


	@Override
	public List<Receta> buscarXNombre(String titulo) {
		return rrepo.buscarXNombre(titulo);
	}

	@Override
	public List<Receta> buscarXMomento(String momento) {
		return rrepo.buscarXMomento(momento);
	}

	@Override
	public Receta recuperarSesion(RecetaDTO receta) {
		
		Receta recetaSesion=new Receta();
		
		recetaSesion.setCategoria(receta.getCategoria());
		recetaSesion.setIdReceta(receta.getIdReceta());
		recetaSesion.setKcal(receta.getKcal());
		recetaSesion.setMomento(receta.getMomento());
		recetaSesion.setNivelCocina(receta.getNivelCocina());
		recetaSesion.setNovedad("S");
		recetaSesion.setNumPorciones(receta.getNumPorciones());
		recetaSesion.setPasos(receta.getPasos());
		recetaSesion.setTiempo(receta.getTiempo());
		recetaSesion.setUsuario(receta.getUsuario());
		recetaSesion.setTitulo(receta.getTitulo());
		
		return recetaSesion;
	}



}
