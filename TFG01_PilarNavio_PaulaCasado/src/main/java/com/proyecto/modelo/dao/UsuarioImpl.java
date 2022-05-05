package com.proyecto.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.repository.UsuarioRepo;

@Service
public class UsuarioImpl implements UsuarioInt{

	@Autowired
	private UsuarioRepo urepo;
	
	@Override
	public List<Usuario> findAll() {
		return urepo.findAll();
	}

	@Override
	public int altaUsuario (Usuario usuario) {
		if(findById(usuario.getUsername())==null) {
			urepo.save(usuario);
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public Usuario login(String username, String password) {
		Usuario usuario= new Usuario();
		boolean flag=false;
		
		for (Usuario ele: findAll()) {
			if ((ele.getUsername().equals(username))&& ele.getPassword().equals(password)){
				flag=true;
				usuario=ele;
			}
		}
		if (flag!=true){
			return null;
		}
		return usuario;
	}

	@Override
	public Usuario findById(String username) {
		return urepo.findById(username).orElse(null);
	}


}
