package com.proyecto.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Perfile;
import com.proyecto.modelo.repository.PerfilRepo;

@Service
public class PerfilImpl implements PerfilInt{
	
	@Autowired
	private PerfilRepo prepo;
	@Override
	public Perfile findById(int idPerfil) {
		return prepo.findById(idPerfil).orElse(null);
	}

}
