package com.proyecto.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.CategoriaInt;
import com.proyecto.modelo.dao.IngredienteInt;
import com.proyecto.modelo.dao.IngredienteRecetaInt;
import com.proyecto.modelo.dao.RecetaInt;
import com.proyecto.modelo.dao.RecetaUsuarioInt;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private RecetaInt recint;
	
	@Autowired
	private CategoriaInt catint;
	
	@Autowired
	private IngredienteInt ingint;
	
	@Autowired
	private IngredienteRecetaInt irint;
	
	@Autowired
	private RecetaUsuarioInt rudao;
	
	@GetMapping("/inicio")
	public String index() {
		return "index";
	}
	
	
	@GetMapping("/verTodas")
	public String home(Model model) {
		List<Receta> recetas = recint.verRecetas();
		model.addAttribute("listaRecetas", recetas);
		return "recetas";
	}
	
	@GetMapping("/guardadas")
	public String meGustan(Model model) {
		List<RecetaEnUsuario> recetas = rudao.verRecetasGuardadas();
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
	
	
//	@GetMapping("/receta/{idReceta}")
//	public String verReceta(Model model, @PathVariable ("idReceta") int idReceta) {
//		Receta receta = recint.findById(idReceta);
//		
//		int num_ingredientes = (receta.getIngredienteEnRecetas()).size();
		
		//NO LO UTILIZO pq saco los datos con los atributos en la jsp
//		List<IngredienteEnReceta> ingredienteEnReceta = receta.getIngredienteEnRecetas();
//		for (int i = 0; i < ingredienteEnReceta.size(); i++){
//			//String nombreIngrediente= ingredienteEnReceta.get(i).getIngrediente().getDescripcion();
//			String nombreIngr = irint.buscarNombreIngrediente(ingredienteEnReceta.get(i).getIdIngredientereceta());
//			System.out.println(nombreIngr);
//		}
		
		
//		String pasos= receta.getPasos();
//		String[] pasosSegmentados= pasos.split("- ");
////		for (int i = 1; i < pasosSegmentados.length; i++){
////			System.out.println("paso de la receta segmentados: " + pasosSegmentados[i]);
////		}
//		
//		
//		model.addAttribute("pasosSegmentados", pasosSegmentados);
//		model.addAttribute("num_ingredientes", num_ingredientes);
//		model.addAttribute("receta", receta);
//		
//		return "receta";
//	}
//	
	
	
	@PostMapping("/altaReceta")
	public String procesarAltaReceta(Receta receta, RedirectAttributes attr, HttpSession session) {
		if(receta==null) {
			attr.addFlashAttribute("mensaje", "Error en el alta");
			return "redirect:/usuario/altaReceta";
		}else {
			receta.setPuntuacion(new BigDecimal(0));
			receta.setUsuario((Usuario) session.getAttribute("username"));
			recint.altaReceta(receta);
			if(recint.altaReceta(receta)==1) {
				attr.addFlashAttribute("mensaje", "La receta ha sido dada de alta");
				return "redirect:/";
			} else {
				attr.addFlashAttribute("mensaje", "La receta NO ha sido dada de alta");
				return "redirect:/usuario/altaReceta";
			}
		}
		
	}

}
