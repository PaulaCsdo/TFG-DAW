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

/**
 * Controller con los métodos que ejecutan los usuarios cuando navegn por la aplicacion
 * @version 1.0
 *
 */
@CrossOrigin(origins = "*")
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
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.OK)
	public List<IngredienteEnReceta> buscarRecetaTipo(@PathVariable ("idTipoDieta") int idTipoDieta) {
		 return irdao.buscarXTipo(idTipoDieta);
	}
	
	
	/**
	 * Método que busca el listado de todas las recetas registradas
	 * @return Lista de objetos de tipo Receta
	 */
	@GetMapping("/verRecetas")
	@ResponseStatus(HttpStatus.OK)
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
	}
	
	/**
	 * Método para ver todos atributos de la clase NivelCocina @see NivelCocina
	 * 
	 * @return Lista de objetos NivelCocina
	 */
	@GetMapping("/verNiveles")
	@ResponseStatus(HttpStatus.OK)
	public List<NivelCocina> verDificultad() {
		return nint.findAll();
	}
	
	/**
	 * Método para ver todos los atributos de la clase Categoria @see Categoria
	 * @return Lista de objetos Categoria
	 */
	@GetMapping("/verCategorias")
	@ResponseStatus(HttpStatus.OK)
	public List<Categoria> verCateg() {
		return ctint.verCategorias();
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
	/**
	 * Método para ver las recetas que el usuario almacenado en sesion
	 * ha guardado como favoritas.
	 * 
	 * @param session Objeto que se almacena en la sesion.
	 * @return Lista de Recetas guardadas como favoritas
	 */
	@GetMapping("/verGuardadas")
	@ResponseStatus(HttpStatus.OK)
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
	@ResponseStatus(HttpStatus.OK)
	public List <IngredienteEnReceta> verCreadas(HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return irdao.misRecetas(usuario.getUsername());
	}
	

	/**
	 * Método para dar de alta una receta desde un formulario.
	 * 
	 * @param receta Objeto de tipo RecetaDTO
	 * @param session Objeto que se almacena en la sesion
	 * @return 1 si el alta se ha realizado o 0 si no  persiste en base de datos
	 */
//	@PostMapping("/altaReceta")
//	public ResponseEntity<Receta> altaReceta(@RequestBody  RecetaDTO receta, HttpSession session) {
//		
//		session.setAttribute("receta", null);
//		Usuario usuario = (Usuario) session.getAttribute("usuario");
//		
//		receta.setUsuario(usuario);
//		int id = ThreadLocalRandom.current().nextInt(10, 200) + 10;
//		receta.setIdReceta(id);
//		
//		//Crea un objeto receta y la guarda en sesion
//		Receta recetaSesion=rdao.recuperarSesion(receta);
//		session.setAttribute("receta", recetaSesion);
//		if(rdao.altaReceta(receta)==1) {
//			session.setAttribute("receta", recetaSesion);
//			return new ResponseEntity<Receta>(recetaSesion, HttpStatus.CREATED);
//		}else {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//	}
	
	@PostMapping("/altaReceta")
	public ResponseEntity<Receta> altaReceta(@RequestBody RecetaDTO receta, HttpSession session) {
		
		session.setAttribute("receta", null);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		Categoria cat=ctint.findById(receta.getCategoria().getIdCategoria());
		receta.setCategoria(cat);
		
		NivelCocina nivelco=nint.findById(receta.getNivelCocina().getIdNivel());
		receta.setNivelCocina(nivelco);
		
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
	 * Método para buscar un ingrediente por su id
	 * @param idIngrediente Atributo de tipo int que identifica un ingrediente
	 * @return Objeto de tipo Ingrediente
	 */
	@GetMapping("/busca/{idIngrediente}")
	public Ingrediente aBuscar(@PathVariable("idIngrediente")int idIngrediente) {
		return idao.findById(idIngrediente);
	}
	
		
	/**
	 * Método para buscar la lista de ingredientes que contengan la cadena que se introduce en el parámetro
	 * El método para buscar ingredientes es necesario para dar de alta una nueva receta.
	 *
	 * El rol de usuario puede buscar recetas por ingrediente, pero NO buscar ingredientes.
	 * El método es necesario para que pueda buscar los ingredientes en el formulario al
	 * crear una nueva receta.
	 * 
	 * @param descripcion Cadena de caracteres que contiene total o parcialmente la descripción del ingrediente.
	 * @return Lista de ingredientes en la que coincide el nombre del ingrediente con la cadena del parametro.
	 */

	@GetMapping("/buscadorIngrediente/{descripcion}")
	@ResponseStatus(HttpStatus.OK)
	public List <Ingrediente> buscarIngrediente(@PathVariable("descripcion") String descripcion) {
		return idao.buscarPorDescripcion(descripcion);
	}
	
	/**
	 * Método para añadir ingredientes a partir de un formulario
	 * 
	 * @param nuevaReceta DTO de IngredienteEnReceta en el que se almacenan todos los atributos de la receta
	 * @param session Objeto que se almacena en la sesion
	 * @return String informativo sobre si el ala se ha realizado correcta o incorrectamente.
	 */

	@PostMapping("/añadirIngrediente")
	public ResponseEntity<IngredienteEnReceta> altaIngredientes(@RequestBody IngredienteEnRecetaDTO ingredienteAñadido, HttpSession session) {
		
		IngredienteEnReceta recetaCreada=new IngredienteEnReceta();
		
		Ingrediente ingSeleccionado=idao.findById(ingredienteAñadido.getIdIngrediente());

		Receta buscada=(Receta)session.getAttribute("receta");

		recetaCreada.setCantidad(ingredienteAñadido.getCantidad());
		recetaCreada.setUnidad(ingredienteAñadido.getUnidad());
		recetaCreada.setIngrediente(ingSeleccionado);
		recetaCreada.setReceta(buscada);
		
		if(irdao.nuevaReceta(recetaCreada)==1) {
			return new ResponseEntity<IngredienteEnReceta>(recetaCreada, HttpStatus.CREATED);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
	

}
