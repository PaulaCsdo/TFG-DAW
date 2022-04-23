package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.repository.RecetaUsuarioRepo;

@Service
public class RecetaUsuarioImpl implements RecetaUsuarioInt{

	@Autowired
	private RecetaUsuarioRepo rrepo;
	@Override
	public List<RecetaEnUsuario> findAll() {
		return rrepo.findAll();
	}
	@Override
	public RecetaEnUsuario findById(int idRecetausuario) {
		return rrepo.findById(idRecetausuario).orElse(null);
	}
	
	/*Para guardar una receta, en el Controller: 
	 * 	- setUsuario(session.getUsername)
	 * 	- setReceta
	 */
	@Override
	public int guardarReceta(RecetaEnUsuario recusu) {
		int filas = 0;
		try {
			recusu.setGuardada("G");
			rrepo.save(recusu);
			filas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}
	
	@Override
	public List<RecetaEnUsuario> verRecetasGuardadas() {
		return rrepo.verRecetasGuardadas();
	}
	

}
