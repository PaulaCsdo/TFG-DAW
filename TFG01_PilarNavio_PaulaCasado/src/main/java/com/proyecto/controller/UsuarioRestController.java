package com.proyecto.controller;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.bean.Ingrediente;
import com.proyecto.modelo.bean.IngredienteEnReceta;
import com.proyecto.modelo.bean.NivelCocina;
import com.proyecto.modelo.bean.Receta;
import com.proyecto.modelo.bean.RecetaEnUsuario;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.CategoriaInt;
import com.proyecto.modelo.dao.IngredienteInt;
import com.proyecto.modelo.dao.IngredienteRecetaInt;
import com.proyecto.modelo.dao.NivelCocinaInt;
import com.proyecto.modelo.dao.RecetaInt;
import com.proyecto.modelo.dao.RecetaUsuarioInt;
import com.proyecto.modelo.dto.IngredienteEnRecetaDTO;
import com.proyecto.modelo.dto.RecetaDTO;
import com.proyecto.modelo.repository.IngredienteRecetaRepo;

/**
 * Controller con los métodos que ejecutan los usuarios cuando navegn por la aplicacion
 * @version 1.0
 *
 */
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
	
	@Autowired
	private CategoriaInt ctint;
	
	@Autowired
	private NivelCocinaInt nint;
	
	/**
	 * Invalida los atributos de sesion asociados al objeto session,
	 * y devuelve el String "Logout"
	 * 
	 * @param session 
	 * @return String "Logout"
	 */
	@GetMapping ("/logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "Logout";
	}
	
	/**
	 * Método que muestra los atributos que definen una receta concreta
	 * pasándole el id de la receta como argumento de Path Variable
	 * 
	 * @param idReceta Atributo de tipo int que identifica una receta.
	 * @return Objeto de tipo Receta @see Receta
	 */
	@GetMapping("/verUna/{idReceta}")
	public Receta verReceta(@PathVariable ("idReceta") int idReceta) {
		return rdao.findById(idReceta);
	}
	
	
	//Buscar receta completa por NOMBRE	
	/**
	 * Método que busca el listado de recetas cuyo título coincide con
	 * el parámetro que viaja por path variable.
	 * 
	 * @param titulo Atributo de tipo String con el nombre descriptivo de la receta
	 * @return Lista de objetos IngredienteEnReceta de aquellas recetas cuyo atributo
	 * título contenga la cadena titulo del path variable
	 */
	@GetMapping("/buscadorReceta/{titulo}")
	public List<IngredienteEnReceta> buscarIngredientesEnReceta(@PathVariable ("titulo") String titulo) {
		 return irdao.buscarXReceta(titulo);
	}
	
	
	//Buscar receta por categoría
	/**
	 * Método que busca el listado de recetas cuya categoría coincide con
	 * el parámetro que viaja por path variable.
	 * 
	 * @param idCategoria Atributo de tipo int que identifica la categoría de la receta
	 * @return Lista de objetos IngredienteEnReceta de aquellas recetas cuyo atributo
	 * idCategoria sea igual que el que viaja por path variable
	 */
	@GetMapping("/buscadorCategoria/{idCategoria}")
	public List<IngredienteEnReceta> buscarRecetaCategoria(@PathVariable ("idCategoria") int idCategoria) {
		 return irdao.buscarXCategoria(idCategoria);
	}
	
	
	//Buscar receta por nivel de cocina
	/**
	 * Método que busca las recetas filtradas por un nivel de dificultad
	 * 
	 * @param idNivel Atributo de tipo int que identifica el nivel de dificultad de la receta
	 * @return Lista de objetos IngredienteEnReceta de aquellas recetas cuyo atributo idNivel
	 * coincide con el que viaja por path variable.
	 */
	@GetMapping("/buscadorNivel/{idNivel}")
	public List<IngredienteEnReceta> buscarRecetaNivel(@PathVariable ("idNivel") int idNivel) {
		 return irdao.buscarXNivel(idNivel);
	}
	
	//Buscar por tipo de dieta
	/**
	 * Método que busca las recetas que corresponden a un determinado tipo de dieta
	 * 
	 * @param idTipoDieta Atributo de tipo int que identifica el tipo de dieta de la receta
	 * @return Lista de objetos IngredienteEnReceta de aquellas recetas cuyo atributo idTipoDieta
	 * coincide con el que viaja por path variable.
	 */
	@GetMapping("/buscadorTipo/{idTipoDieta}")
	public List<IngredienteEnReceta> buscarRecetaTipo(@PathVariable ("idTipoDieta") int idTipoDieta) {
		 return irdao.buscarXTipo(idTipoDieta);
	}
	
	
	//Ver todas las recetas (vista principal)
	/**
	 * Método que busca el listado de todas las recetas registradas
	 * @return Lista de objetos de tipo Receta
	 */
	@GetMapping("/verRecetas")
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
	}
	
	
	//Guardar una receta
	/**
	 * Método para que el usuario pueda guardar una receta como favorita
	 * 
	 * @param idReceta Atributo de tipo int que identifica una receta
	 * @param session Objeto que se almacena en la sesion.
	 * @return 1 si se ha almacenado, 0 si el cambio no ha persistido en BD 
	 */
	@GetMapping("/guardarReceta/{idReceta}")
	public int guardaReceta (@PathVariable ("idReceta") int idReceta, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Receta receta=rdao.findById(idReceta);
		
		RecetaEnUsuario recusuario=new RecetaEnUsuario();
		recusuario.setReceta(receta);
		recusuario.setUsuario(usuario);
		
		if (rudao.guardarReceta(recusuario)==1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	//Ver recetas guardadas por el usuario que ha iniciado sesión
	/**
	 * Método para ver las recetas que el usuario almacenado en sesion
	 * ha guardado como favoritas.
	 * 
	 * @param session Objeto que se almacena en la sesion.
	 * @return Lista de Recetas guardadas como favoritas
	 */
	@GetMapping("/verGuardadas")
	public List<RecetaEnUsuario> meGustan(HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return rudao.verRecetasGuardadas(usuario.getUsername());
	}
	
	
	//Ver las recetas creadas por el usuario
	/**
	 * Método para ver las recetas que el usuario almacenado en sesion ha creado él mismo,
	 * es decir, las que el usuario ha dado de alta introduciendo los atributos.
	 * 
	 * @param session Objeto que se almacena en la sesion.
	 * @return Lista de Recetas
	 */
	@GetMapping("/verMisRecetas")
	public List <IngredienteEnReceta> verCreadas(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return irdao.misRecetas(usuario.getUsername());
	}
	
	/*Buscar ingredientes: necesario para dar de alta una nueva receta.
	 * El rol de usuario puede buscar recetas por ingrediente, pero NO buscar ingredientes.
	 * El método es necesario para que pueda buscar los ingredientes en el formulario al
	 * crear una nueva receta.
	 */
	/**
	 * El método para buscar ingredientes es necesario para dar de alta una nueva receta.
	 * El rol de usuario puede buscar recetas por ingrediente, pero NO buscar ingredientes.
	 * El método es necesario para que pueda buscar los ingredientes en el formulario al
	 * crear una nueva receta.
	 * 
	 * @param descripcion Atributo de tipo String que corresponde con el nombre del ingrediente
	 * @return Objeto de tipo Ingrediente
	 */
	@GetMapping("/buscadorIngrediente/{descripcion}")
	public List <Ingrediente> buscarIngrediente(@PathVariable("descripcion") String descripcion) {
		return idao.buscarPorDescripcion(descripcion);
	}
	
	
	//ALTA RECETA
	/**
	 * Método para dar de alta una receta desde un formulario.
	 * 
	 * @param receta Objeto de tipo RecetaDTO
	 * @param session Objeto que se almacena en la sesion
	 * @return 1 si el alta se ha realizado o 0 si no  persiste en base de datos
	 */
	@PostMapping("/altaReceta")
	public String altaReceta(@RequestBody RecetaDTO receta, HttpSession session) {
		
		session.setAttribute("receta", null);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		receta.setUsuario(usuario);
		receta.setIdReceta(56);
		
		//Crea un objeto receta y la guarda en sesion
		Receta recetaSesion=rdao.recuperarSesion(receta);
		session.setAttribute("receta", recetaSesion);
		return(rdao.altaReceta(receta)==1)?"Si":"No";
	}
	
	/**
	 * Método para buscar un ingrediente por su id
	 * @param idIngrediente Atributo de tipo int que identifica un ingrediente
	 * @return Objeto de tipo Ingrediente
	 */
	@GetMapping("/busca/{idIngrediente}")
	public Ingrediente aBuscar(@PathVariable("idIngrediente")int idIngrediente) {
		return idao.findById(idIngrediente);
	}
	
	/**
	 * Método para añadir ingredientes a partir de un formulario
	 * 
	 * @param nuevaReceta DTO de IngredienteEnReceta en el que se almacenan todos los atributos de la receta
	 * @param session Objeto que se almacena en la sesion
	 * @return String informativo sobre si el ala se ha realizado correcta o incorrectamente.
	 */
	@PostMapping("/añadirIngrediente")
	public String altaIngredientes(@RequestBody IngredienteEnRecetaDTO nuevaReceta, HttpSession session) {
		
		IngredienteEnReceta recetaCreada=new IngredienteEnReceta();
		
		Ingrediente ingSeleccionado=idao.findById(nuevaReceta.getIdIngrediente());

		Receta buscada=(Receta)session.getAttribute("receta");

		recetaCreada.setCantidad(nuevaReceta.getCantidad());
		recetaCreada.setUnidad(nuevaReceta.getUnidad());
		recetaCreada.setIngrediente(ingSeleccionado);
		recetaCreada.setReceta(buscada);
		System.out.println(recetaCreada);
		
		return (irdao.nuevaReceta(recetaCreada)==1)?"Alta realizada":"Alta no realizada";
	}

	

}
