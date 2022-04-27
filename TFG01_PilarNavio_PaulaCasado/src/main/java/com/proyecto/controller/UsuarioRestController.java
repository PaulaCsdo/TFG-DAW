package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.dao.IngredienteRecetaInt;
import com.proyecto.modelo.dao.RecetaInt;

@RestController
@RequestMapping("/usuario")
public class UsuarioRestController {
	
	@Autowired
	private RecetaInt rdao;
	
	@Autowired
	private IngredienteRecetaInt irdao;
	
	@GetMapping("/verUna/{idReceta}")
	public Receta verReceta(@PathVariable ("idReceta") int idReceta) {
		//¿VER INGREDIENTE EN RECETA?
		//List<IngredienteEnReceta> ingredientesEnRecetas = irdao.buscarXIdReceta(idReceta);
		
		return rdao.findById(idReceta);
	}
	
	//Los ingredientes en receta no salen bien, solo sale el primero y repetidas veces
	@GetMapping("/verIngredientesEnReceta/{idReceta}")
	public List<IngredienteEnReceta> verIngredientesEnReceta(@PathVariable ("idReceta") int idReceta) {
		 return irdao.buscarXIdReceta(idReceta);
	}
}
