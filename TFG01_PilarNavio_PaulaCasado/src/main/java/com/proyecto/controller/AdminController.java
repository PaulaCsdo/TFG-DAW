
package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public class AdminController {

	@Autowired
	private UsuarioInt udao;
	
	@Autowired
	private RecetaInt rdao;
	
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
	
	@PostMapping("/buscadorCat/{idCategoria}")
	public List <Receta> buscarcat(@PathVariable("idCategoria") int idCategoria) {
		return rdao.buscarXCategoria(idCategoria);
	}
	
	
	/*Barra de navegación para buscar: ingredientes, recetas
	 * El resultado de la búsqueda aparecerá en la misma vista (index)
	 */
	@PostMapping("/buscadorIngrediente/{descripcion}")
	public List <Ingrediente> buscarIngrediente(@PathVariable("descripcion") String descripcion) {
		return idao.buscarPorDescripcion(descripcion);
	}
	
	@PostMapping("/buscadorReceta/{titulo}")
	public List <Receta> buscarReceta(@PathVariable("titulo") String titulo) {
		return rdao.buscarXNombre(titulo);
	}
	
	/*Boton que lleva a un formulario para dar de alta un ingrediente.
	 *Solo puede dar de alta un nuevo ingrediente el rol de administrador.
	 */
	@PostMapping("/altaIngrediente")
	public String registrarIngrediente(@RequestBody Ingrediente ingrediente) {
			return (idao.altaIngrediente(ingrediente)==1)?"Alta realizada":"ERROR en alta";
	}
	
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

