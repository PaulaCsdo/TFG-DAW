package com.proyecto.controller;


import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.IngredienteInt;
import com.proyecto.modelo.dao.IngredienteRecetaInt;
import com.proyecto.modelo.dao.RecetaInt;
import com.proyecto.modelo.dao.RecetaUsuarioInt;
import com.proyecto.modelo.dto.IngredienteEnRecetaDTO;
import com.proyecto.modelo.dto.RecetaDTO;

@CrossOrigin(origins = "http://localhost:8088")
@RestController
@RequestMapping("rest/usuario")
public class UsuarioRestController {
	
	@Autowired
	private RecetaInt rdao;
	
	@Autowired
	private IngredienteRecetaInt irdao;
	
	@Autowired
	private IngredienteInt idao;
	
	@Autowired
	private RecetaUsuarioInt rudao;
	
	@GetMapping ("/logout")
	@ResponseStatus(HttpStatus.OK)
	public String logout (HttpSession session) {
		session.invalidate();
		return "Logout";
	}
	
	@GetMapping("/verUna/{idReceta}")
	@ResponseStatus(HttpStatus.OK)
	public Receta verReceta(@PathVariable ("idReceta") int idReceta) {
		return rdao.findById(idReceta);
	}
	
	//Buscar receta completa por NOMBRE	
	@GetMapping("/buscadorReceta/{titulo}")
	@ResponseStatus(HttpStatus.OK)
	public List<IngredienteEnReceta> buscarIngredientesEnReceta(@PathVariable ("titulo") String titulo) {
		 return irdao.buscarXReceta(titulo);
	}
	
	//Buscar receta por categoría
	@GetMapping("/buscadorCategoria/{idCategoria}")
	@ResponseStatus(HttpStatus.OK)
	public List<IngredienteEnReceta> buscarRecetaCategoria(@PathVariable ("idCategoria") int idCategoria) {
		 return irdao.buscarXCategoria(idCategoria);
	}
	
	//Buscar receta por nivel de cocina
	@GetMapping("/buscadorNivel/{idNivel}")
	@ResponseStatus(HttpStatus.OK)
	public List<IngredienteEnReceta> buscarRecetaNivel(@PathVariable ("idNivel") int idNivel) {
		 return irdao.buscarXNivel(idNivel);
	}
	
	//Buscar por tipo de dieta
	@GetMapping("/buscadorTipo/{idTipoDieta}")
	@ResponseStatus(HttpStatus.OK)
	public List<IngredienteEnReceta> buscarRecetaTipo(@PathVariable ("idTipoDieta") int idTipoDieta) {
		 return irdao.buscarXTipo(idTipoDieta);
	}
	
	
	//Ver todas las recetas (vista principal)
	@GetMapping("/verRecetas")
	@ResponseStatus(HttpStatus.OK)
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
	}
	
	//Guardar una receta
	@GetMapping("/guardarReceta/{idReceta}")
	public ResponseEntity<String> guardaReceta (@PathVariable ("idReceta") int idReceta, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Receta receta=rdao.findById(idReceta);
		
		RecetaEnUsuario recusuario=new RecetaEnUsuario();
		recusuario.setReceta(receta);
		recusuario.setUsuario(usuario);
		
		if (rudao.guardarReceta(recusuario)==1) {
			return new ResponseEntity<String>("Receta guardada", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Receta NO guardada", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Ver recetas guardadas por el usuario que ha iniciado sesión
	@GetMapping("/verGuardadas")
	@ResponseStatus(HttpStatus.OK)
	public List<RecetaEnUsuario> meGustan(HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return rudao.verRecetasGuardadas(usuario.getUsername());
	}
	
	//Ver las recetas creadas por el usuario
	@GetMapping("/verMisRecetas")
	@ResponseStatus(HttpStatus.OK)
	public List <IngredienteEnReceta> verCreadas(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return irdao.misRecetas(usuario.getUsername());
	}
	
	/*Buscar ingredientes: necesario para dar de alta una nueva receta.
	 * El rol de usuario puede buscar recetas por ingrediente, pero NO buscar ingredientes.
	 * El método es necesario para que pueda buscar los ingredientes en el formulario al
	 * crear una nueva receta.
	 */
	
	//ALTA RECETA
	
	@PostMapping("/altaReceta")
	public ResponseEntity<Receta> altaReceta(@RequestBody RecetaDTO receta, HttpSession session) {
		
		session.setAttribute("receta", null);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		receta.setUsuario(usuario);
		int id = ThreadLocalRandom.current().nextInt(10, 200) + 10;
		receta.setIdReceta(id);
		
		//Crea un objeto receta y la guarda en sesion
		Receta recetaSesion=rdao.recuperarSesion(receta);
		session.setAttribute("receta", recetaSesion);
		if(rdao.altaReceta(receta)==1) {
			session.setAttribute("receta", recetaSesion);
			return new ResponseEntity<Receta>(recetaSesion, HttpStatus.CREATED);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	/**
	 * Método para buscar la lista de ingredientes que contengan la cadena que se introduce en el parámetro
	 * 
	 * @param descripcion Cadena de caracteres que contiene total o parcialmente la descripción del ingrediente.
	 * @return Lista de ingredientes en la que coincide el nombre del ingrediente con la cadena del parametro.
	 */
	@GetMapping("/buscadorIngrediente/{descripcion}")
	@ResponseStatus(HttpStatus.OK)
	public List <Ingrediente> buscarIngrediente(@PathVariable("descripcion") String descripcion) {
		return idao.buscarPorDescripcion(descripcion);
	}
	
	@PostMapping("/añadirIngrediente")
	public ResponseEntity<IngredienteEnReceta> altaIngredientes(@RequestBody IngredienteEnRecetaDTO nuevaReceta, HttpSession session) {
		
		IngredienteEnReceta recetaCreada=new IngredienteEnReceta();
		
		Ingrediente ingSeleccionado=idao.findById(nuevaReceta.getIdIngrediente());

		Receta buscada=(Receta)session.getAttribute("receta");

		recetaCreada.setCantidad(nuevaReceta.getCantidad());
		recetaCreada.setUnidad(nuevaReceta.getUnidad());
		recetaCreada.setIngrediente(ingSeleccionado);
		recetaCreada.setReceta(buscada);
		
		if(irdao.nuevaReceta(recetaCreada)==1) {
			return new ResponseEntity<IngredienteEnReceta>(recetaCreada, HttpStatus.CREATED);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}

	

}
