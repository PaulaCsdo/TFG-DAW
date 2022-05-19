package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.dto.RecetaDTO;
import com.proyecto.modelo.repository.RecetaRepo;

@Service
public class RecetaImpl implements RecetaInt{
	@Autowired
	RecetaRepo rrepo;
	
	@Autowired
	UsuarioInt uint;
	
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
	public Receta altaReceta(RecetaDTO receta) {
		Receta rec=new Receta();
		
		String nombre=receta.getUsername();
		
		rec.setCategoria(receta.getCategoria());
		rec.setNivelCocina(receta.getNivelCocina());
		rec.setMomento(receta.getMomento());
		rec.setTitulo(receta.getTitulo());
		rec.setNovedad("S");
		rec.setKcal(receta.getKcal());
		rec.setPasos(receta.getPasos());
		rec.setTiempo(receta.getTiempo());
		rec.setNumPorciones(receta.getNumPorciones());
		rec.setUsuario(uint.findById(nombre));
		rec.setIdReceta(receta.getIdReceta());
		
		try {
			rrepo.save(rec);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return rec;
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
	public List<Receta> misRecetas(String username) {
		return rrepo.misRecetas(username);
	}




}
