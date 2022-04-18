package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrador")
public class AdminController {


	@GetMapping("/index")
	public String listarCosas(Model model) {
//		listarIngredientes
//		listarRecetas
	}
	
	@GetMapping("/listaUsuarios")
	public String listarUsuarios(Model model) {
		
	}
	
	@GetMapping("/buscadorIngrediente/{}")
	public String buscarIngrediente(Model model) {
		
	}
	@GetMapping("/buscadorReceta/{}")
	public String buscarReceta(Model model) {
		
	}
	
	@GetMapping("/altaReceta")
	public String crearReceta(Model model) {
		
	}
	
	@PostMapping
	
	
	@GetMapping("/altaIngrediente")
	public String crearIngrediente(Model model) {
		
	}
	
	@PostMapping

}
