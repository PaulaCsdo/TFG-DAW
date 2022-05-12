
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

/**
 * Controlador que recopila los métodos del usuario administrador
 * 
 * @version 1.0
 *
 */

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
	 * Método para ver los usuarios
	 * 
	 * @return json de la lista con todos los usuarios registrados
	 */
	@GetMapping("/verUsuarios")
	public List <Usuario> verUsuarios() {
		return udao.findAll();
	}
	
	/**
	 * Método para ver la lista de ingredientes
	 * @return json de la lista de los igredientes registrados
	 */
	@GetMapping("/verIngredientes")
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
	//Boton que lleva a vista con la lista de recetas: incluye ingredientes + unidad/cantidades
	@GetMapping("/verRecetas")
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
	public int registrarIngrediente(@RequestParam("descripcion") String descripcion) {
		System.out.println(descripcion);
		Ingrediente ingrediente=new Ingrediente();
		ingrediente.setDescripcion(descripcion);
		ingrediente.setIdIngrediente(3);
		if(idao.altaIngrediente(ingrediente)==1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	/*Botones que llevan a una lista de:
	 * 	- Niveles de cocina
	 * 	- Categorias de receta
	 * 	- Tipos de dieta
	 */
	/**
	 * Método para ver todos atributos de la clase NivelCocina @see NivelCocina
	 * 
	 * @return Lista de objetos NivelCocina
	 */
	@GetMapping("/verNiveles")
	public List<NivelCocina> verDificultad() {
		return nint.findAll();
	}
	
	/**
	 * Método para ver todos los atributos de la clase Categoria @see Categoria
	 * @return Lista de objetos Categoria
	 */
	@GetMapping("/verCategorias")
	public List<Categoria> verCateg() {
		return cint.verCategorias();
	}
	
	/**
	 * Método para ver los tipos de dieta
	 * @return Lista de objetos TiposDieta
	 */
	@GetMapping("/verTiposDieta")
	public List<TiposDieta> verTipos() {
		return tint.findAll();
	}
	

}

