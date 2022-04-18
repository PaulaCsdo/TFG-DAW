package com.proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.dao.RecetaInt;

@Controller
@RequestMapping("/usuario/index")
public class UsuarioController {
	
	@Autowired
	private RecetaInt recint;
	
	
	@GetMapping("/verTodas")
	public String home(Model model) {
		List<Receta> recetas = recint.verRecetas();
		model.addAttribute("ListaRecetas", recetas);
		return "recetas";
	}

}
