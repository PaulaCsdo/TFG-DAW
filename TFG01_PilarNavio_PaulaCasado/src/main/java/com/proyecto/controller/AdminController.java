
package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.IngredienteInt;
import com.proyecto.modelo.dao.RecetaInt;
import com.proyecto.modelo.dao.UsuarioInt;

@Controller
@RequestMapping("/administrador")
public class AdminController {

	@Autowired
	private UsuarioInt udao;
	
	@Autowired
	private RecetaInt rdao;
	
	@Autowired
	private IngredienteInt idao;

	@GetMapping("/index")
	public String inicioAdmin(Model model) {
		return "PruebasPaula";
	}
	
	//Boton que lleva a vista con la lista de usuarios
	@GetMapping("/verUsuarios")
	public String listarUsuarios(Model model) {
		List <Usuario> lista=udao.findAll();
		model.addAttribute("listaUsuarios", lista);
		return "PruebasPaula";
	}
	
	//Boton que lleva a vista con la lista de ingredientes
	@GetMapping("/verIngredientes")
	public String listarIngredientes(Model model) {
		List<Ingrediente> listaIngredientes=idao.findAll();
		model.addAttribute("listaIngredientes", listaIngredientes);
		return "PruebasPaula";
	}	
	
	//Boton que lleva a vista con la lista de recetas
	@GetMapping("/verRecetas")
	public String listarRecetas(Model model) {
		List<Receta> listaRecetas=rdao.verRecetas();
		model.addAttribute("listarecetas", listaRecetas);
		return "PruebasPaula";
	}
	
	//Boton que lleva a vista con la lista de recetas completas: incluye ingredientes + cantidades
	@GetMapping("/verRecetasCompletas")
	public String listarRecetasCompletas(Model model) {
		List<Receta> listaRecetas=rdao.verRecetas();
		model.addAttribute("listarecetas", listaRecetas);
		return "PruebasPaula";
	}	
	
	/*Barra de navegación para buscar: ingredientes, recetas
	 * El resultado de la búsqueda aparecerá en la misma vista (index)
	 */
	@PostMapping("/buscadorIngrediente")
	public String buscarIngrediente(@RequestParam("descripcion") String descripcion, RedirectAttributes attr) {
		List <Ingrediente> ingredienteBuscado=idao.buscarPorDescripcion(descripcion);
		attr.addFlashAttribute("ingredienteBuscado", ingredienteBuscado);
		return "redirect:/administrador/index";
	}
	
	@PostMapping("/buscadorReceta")
	public String buscarReceta(@RequestParam("titulo") String titulo, RedirectAttributes attr ) {
		List <Receta> recetaBuscada=rdao.buscarXNombre(titulo);
		attr.addFlashAttribute("recetaBuscada", recetaBuscada);
		return "redirect:/administrador/index";
	}
	
	/*Boton que lleva a un formulario para dar de alta una receta COMPLETA (Ingrediente_Receta),
	 * incluyendo ingredientes, cantidades, etc...
	 */
	@GetMapping("/altaRecetaCompleta")
	public String crearRecetaCompleta() {
		return "PruebasPaula";
	}
	
//	@PostMapping
//	public String formRecetaCompleta()
	
	/*Boton que lleva a un formulario para dar de alta un ingrediente.
	 *Solo puede dar de alta un nuevo ingrediente el rol de administrador.
	 */
	@GetMapping("/altaIngrediente")
	public String crearIngrediente() {
		return "PruebasPaula";
	}
	
	@PostMapping
	public String formIngrediente (Ingrediente ingrediente, RedirectAttributes attr, HttpSession session) {
		if (ingrediente == null) {
			attr.addFlashAttribute("mensaje", "Error en el alta");
			return "redirect:/altaIngrediente ";

		}else {
			idao.altaIngrediente(ingrediente);
			attr.addFlashAttribute("mensaje", "Nuevo usuario creado");
			return "redirect:/administrador/verIngredientes";
		}
	}
	

}

