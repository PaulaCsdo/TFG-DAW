package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.NivelCocina;
import com.proyecto.modelo.repository.NivelCocinaRepo;

@Service
public class NivelCocinaImpl implements NivelCocinaInt{
	
	@Autowired
	private NivelCocinaRepo nrepo;

	@Override
	public List<NivelCocina> findAll() {
		return nrepo.findAll();
	}

}
