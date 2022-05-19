
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
import com.proyecto.modelo.dto.IngredienteEnRecetaDTO;
import com.proyecto.modelo.dto.RecetaDTO;

/**
 * Controlador que recopila los métodos del usuario administrador
 * 
 * @version 1.0
 *
 */

@CrossOrigin(origins = "*")
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
	private CategoriaInt ctint;
	
	@Autowired
	private NivelCocinaInt nint;
	
	@Autowired
	private TipoDietaInt tint;
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
	 * Método para ver los usuarios
	 * 
	 * @return json de la lista con todos los usuarios registrados
	 */
	@GetMapping("/verUsuarios")
	@ResponseStatus(HttpStatus.OK)
	public List <Usuario> verUsuarios() {
		return udao.findAll();
	}
	
	/**
	 * Método para ver la lista de ingredientes
	 * @return json de la lista de los igredientes registrados
	 */
	@GetMapping("/verIngredientes")
	@ResponseStatus(HttpStatus.OK)
	public List<Ingrediente> listarIngredientes(){
		return idao.findAll();
	}	
	
	/**
	 * Método para ver la lista de todas las recetas con los atributos:
	 * idReceta, imagen, kcal, momento, novedad, numero de porciones, pasos,
	 * puntuacion, tiempo, titulo, receta en usuario, ingredientes de la receta,
	 * categoria, nivel de cocina, usuario propietario, tipos de dietas.
	 * 
	 * @return json de la lista de recetas
	 */
	
	@GetMapping("/verRecetas")
	@ResponseStatus(HttpStatus.OK)
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
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
	
	/**
	 * Método para buscar las recetas cuyo título contenga la cadena introducida en el parámetro
	 * 
	 * @param titulo Cadena de caracteres que contiene total o parcialmente el título de la receta.
	 * @return Lista de recetas en la que coincide el título de la receta con la cadena del parametro.
	 */
	@GetMapping("/buscadorReceta/{titulo}")
	@ResponseStatus(HttpStatus.OK)
	public List<IngredienteEnReceta> buscarIngredientesEnReceta(@RequestParam ("titulo") String titulo) {
		 return irdao.buscarXReceta(titulo);
	}
	
	/**
	 * Método para dar de alta un nuevo ingrediente procedente de un formulario.
	 * Se deben incluir todos los atributos de la clase @see Ingrediente
	 * 
	 * @param ingrediente 
	 * @return 1 si se ha dado de alta correctamente, o 0 si no.
	 */
	@PostMapping("/altaIngrediente")
	public ResponseEntity<Ingrediente> registrarIngrediente(@RequestParam("descripcion") String descripcion) {
		Ingrediente ingrediente=new Ingrediente();
		ingrediente.setDescripcion(descripcion);
		int id = ThreadLocalRandom.current().nextInt(10, 900) + 10;
		ingrediente.setIdIngrediente(id);
		if(idao.altaIngrediente(ingrediente)==1) {
			return new ResponseEntity<Ingrediente>(ingrediente, HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
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
	
	/**
	 * Método para ver los tipos de dieta
	 * @return Lista de objetos TiposDieta
	 */
	@GetMapping("/verTiposDieta")
	@ResponseStatus(HttpStatus.OK)
	public List<TiposDieta> verTipos() {
		return tint.findAll();
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
	public ResponseEntity<Receta> altaReceta(@RequestBody RecetaDTO receta) {
	
		Categoria cat=ctint.findById(receta.getCategoria().getIdCategoria());
		receta.setCategoria(cat);
		
		NivelCocina nivelco=nint.findById(receta.getNivelCocina().getIdNivel());
		receta.setNivelCocina(nivelco);
		
		int id = ThreadLocalRandom.current().nextInt(10, 200) + 10;
		receta.setIdReceta(id);
		
		Receta alta=rdao.altaReceta(receta);
		if(alta!=null) {
			return new ResponseEntity<Receta>(alta, HttpStatus.CREATED);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	
	/**
	 * Método para añadir ingredientes a partir de un formulario
	 * 
	 * @param nuevaReceta DTO de IngredienteEnReceta en el que se almacenan todos los atributos de la receta
	 * @param session Objeto que se almacena en la sesion
	 * @return String informativo sobre si el ala se ha realizado correcta o incorrectamente.
	 */

	@PostMapping("/anadirIngrediente")
	public ResponseEntity<IngredienteEnReceta> altaIngredientes(@RequestBody IngredienteEnRecetaDTO ingredienteAñadido) {
		
		IngredienteEnReceta recetaCreada=new IngredienteEnReceta(); 
		
		Ingrediente ingSeleccionado=idao.findById(ingredienteAñadido.getIdIngrediente());

		Receta recnueva=rdao.findById(ingredienteAñadido.getIdReceta());

		recetaCreada.setCantidad(ingredienteAñadido.getCantidad());
		recetaCreada.setUnidad(ingredienteAñadido.getUnidad());
		recetaCreada.setIngrediente(ingSeleccionado);
		recetaCreada.setReceta(recnueva);
		
		if(irdao.nuevaReceta(recetaCreada)==1) {
			return new ResponseEntity<IngredienteEnReceta>(recetaCreada, HttpStatus.CREATED);
			}else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
	

}

