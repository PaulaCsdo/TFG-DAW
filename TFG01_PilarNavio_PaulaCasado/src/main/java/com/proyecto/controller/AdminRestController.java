
package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	/**
	 * Invalida los atributos de sesion asociados al objeto session,
	 * y redirige a la vista llamada "Logout"
	 * 
	 * @param session 
	 * @return DUDA: ¿Nombre de la vista a la que se dirige el método?
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
		System.out.println(descripcion);
		Ingrediente ingrediente=new Ingrediente();
		ingrediente.setDescripcion(descripcion);
		ingrediente.setIdIngrediente(3);
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
		return cint.verCategorias();
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
	

}

