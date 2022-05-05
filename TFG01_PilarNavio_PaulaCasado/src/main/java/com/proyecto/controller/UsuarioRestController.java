package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.dao.IngredienteRecetaInt;
import com.proyecto.modelo.dao.RecetaInt;
import com.proyecto.modelo.dao.RecetaUsuarioInt;

@RestController
@RequestMapping("rest/usuario")
public class UsuarioRestController {
	
	@Autowired
	private RecetaInt rdao;
	
	@Autowired
	private IngredienteRecetaInt irdao;
	
	@Autowired
	private RecetaUsuarioInt rudao;
	
	@GetMapping("/verUna/{idReceta}")
	public Receta verReceta(@PathVariable ("idReceta") int idReceta) {
		return rdao.findById(idReceta);
	}
	
	
	@GetMapping("/buscarIngredientesEnReceta/{idReceta}")
	public List<IngredienteEnReceta> buscarIngredientesEnReceta(@PathVariable ("idReceta") int idReceta) {
		 return irdao.buscarXReceta(idReceta);
	}
	
	@GetMapping("/verRecetas")
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
	}
	
	//DUDA: ¿COMO PUEDO PROBARLO EN POSTMAN?
//	@GetMapping("/guardadas")
//	public List<RecetaEnUsuario> meGustan(){
//		return rudao.verRecetasGuardadas();
//	}
	
	//ME RESPONDE CON ALTA NO REALIZADA PORQUE NO PERSISTE
	@PostMapping("/altaReceta")
	public String procesarAlta (@RequestBody Receta receta) {
		return (rdao.altaReceta(receta)==1)?"Alta realizada":"Alta no realizada";
	}
	
}
