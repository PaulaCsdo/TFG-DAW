package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.CategoriaInt;
import com.proyecto.modelo.dao.RecetaInt;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private RecetaInt recint;
	
	@Autowired
	private CategoriaInt catint;
	
	
	@GetMapping("/verTodas")
	public String home(Model model) {
		List<Receta> recetas = recint.verRecetas();
		model.addAttribute("listaRecetas", recetas);
		return "recetas";
	}
	
	@GetMapping("/guardadas")
	public String meGustan(Model model) {
		List<Receta> recetas = recint.verRecetasGuardadas();
		model.addAttribute("listaRecetas", recetas);
		return "recetas";
	}
	
	@GetMapping("/misRecetas")
	public String misRecetas(Model model, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		List<Receta> recetas = recint.verMisRecetas(usuario.getUsername());
		model.addAttribute("listaRecetas", recetas);
		return "recetas";
	}
	
	@GetMapping("/altaReceta")
	public String altaReceta(HttpSession session) {
		List<Categoria> listaCategorias= catint.verCategorias();
		session.setAttribute("listaCategorias", listaCategorias);
		return "formAltaReceta";
	}
	
	@PostMapping("/altaReceta")
	public String procesarAltaReceta(Receta receta, RedirectAttributes attr) {
		if(recint.altaReceta(receta)==1) {
			attr.addFlashAttribute("mensaje", "La receta ha sido dada de alta");
			return "redirect:/";
		} else {
			attr.addFlashAttribute("mensaje", "La receta NO ha sido dada de alta");
			return "";
		}
	}

}
