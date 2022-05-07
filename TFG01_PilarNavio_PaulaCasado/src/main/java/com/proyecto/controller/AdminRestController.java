
package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.bean.NivelCocina;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.TiposDieta;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.CategoriaInt;
import com.proyecto.modelo.dao.IngredienteInt;
import com.proyecto.modelo.dao.IngredienteRecetaInt;
import com.proyecto.modelo.dao.NivelCocinaInt;
import com.proyecto.modelo.dao.RecetaInt;
import com.proyecto.modelo.dao.TipoDietaInt;
import com.proyecto.modelo.dao.UsuarioInt;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("/administrador")
public class AdminRestController {

	@Autowired
	private UsuarioInt udao;
	
	@Autowired
	private RecetaInt rdao;
	
	@Autowired
	private IngredienteRecetaInt irdao;
	
	@Autowired
	private IngredienteInt idao;
	
	@Autowired
	private CategoriaInt cint;
	
	@Autowired
	private NivelCocinaInt nint;
	
	@Autowired
	private TipoDietaInt tint;
	
	@GetMapping ("/logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "Logout";
	}
	
	//Boton que lleva a vista con la lista de usuarios
	@GetMapping("/verUsuarios")
	public List <Usuario> verUsuarios() {
		return udao.findAll();
	}
	
	
	//Boton que lleva a vista con la lista de ingredientes
	@GetMapping("/verIngredientes")
	public List<Ingrediente> listarIngredientes(){
		return idao.findAll();
	}	
	
	//Boton que lleva a vista con la lista de recetas: incluye ingredientes + unidad/cantidades
	@GetMapping("/verRecetas")
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
	}
	
	
	/* Input para buscar: ingredientes, recetas
	 * El resultado de la búsqueda aparecerá en la misma vista (index)
	 */
	@GetMapping("/buscadorIngrediente/{descripcion}")
	public List <Ingrediente> buscarIngrediente(@PathVariable("descripcion") String descripcion) {
		return idao.buscarPorDescripcion(descripcion);
	}
	
	@GetMapping("/buscadorReceta/{titulo}")
	public List<IngredienteEnReceta> buscarIngredientesEnReceta(@RequestParam ("titulo") String titulo) {
		 return irdao.buscarXReceta(titulo);
	}
	
	//Formulario para dar de alta un ingrediente
	@PostMapping("/altaIngrediente")
	public String registrarIngrediente(Ingrediente ingrediente) {
			return (idao.altaIngrediente(ingrediente)==1)?"Alta realizada":"ERROR en alta";
	}
	
	/*Botones que llevan a una lista de:
	 * 	- Niveles de cocina
	 * 	- Categorias de receta
	 * 	- Tipos de dieta
	 */
	@GetMapping("/verNiveles")
	public List<NivelCocina> verDificultad() {
		return nint.findAll();
	}
	
	@GetMapping("/verCategorias")
	public List<Categoria> verCateg() {
		return cint.verCategorias();
	}
	
	@GetMapping("/verTiposDieta")
	public List<TiposDieta> verTipos() {
		return tint.findAll();
	}
	
//	@PostMapping("/altaRecetaCompleta")
//	public String formRecetaCompleta(@RequestBody Receta receta, HttpSession session) {
//			Receta rec= inreceta.getReceta();
//			Usuario usu=(Usuario)session.getAttribute("usuario");
//			rec.setUsuario(usu);
//			rdao.altaReceta(rec);
//		
//			irdao.nuevaReceta(inreceta);
//			return "redirect:/administrador/verRecetasCompletas";
//	}
	

}

