package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.TiposDieta;
import com.proyecto.modelo.repository.TipoDietaRepo;

@Service
public class TipoDietaImpl implements TipoDietaInt{

	@Autowired
	private TipoDietaRepo trepo;
	@Override
	public List<TiposDieta> findAll() {
		return trepo.findAll();
	}

}
