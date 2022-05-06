package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@GetMapping("/verUna/{idReceta}")
	public Receta verReceta(@PathVariable ("idReceta") int idReceta) {
		return rdao.findById(idReceta);
	}
	
	//Buscar receta completa por NOMBRE	
	@GetMapping("/buscadorReceta/{titulo}")
	public List<IngredienteEnReceta> buscarIngredientesEnReceta(@PathVariable ("titulo") String titulo) {
		 return irdao.buscarXReceta(titulo);
	}
	
	//Buscar receta por categoría
	@GetMapping("/buscadorCategoria/{idCategoria}")
	public List<IngredienteEnReceta> buscarRecetaCategoria(@PathVariable ("idCategoria") int idCategoria) {
		 return irdao.buscarXCategoria(idCategoria);
	}
	
	//Buscar receta por nivel de cocina
	@GetMapping("/buscadorNivel/{idNivel}")
	public List<IngredienteEnReceta> buscarRecetaNivel(@PathVariable ("idNivel") int idNivel) {
		 return irdao.buscarXNivel(idNivel);
	}
	
	//Buscar por tipo de dieta
	@GetMapping("/buscadorTipo/{idTipoDieta}")
	public List<IngredienteEnReceta> buscarRecetaTipo(@PathVariable ("idTipoDieta") int idTipoDieta) {
		 return irdao.buscarXTipo(idTipoDieta);
	}
	
	//Ver todas las recetas (vista principal)
	@GetMapping("/verRecetas")
	public List<Receta> listarRecetas(){
		return rdao.verRecetas();
	}
	
	//Guardar una receta
	@GetMapping("/guardarReceta/{idReceta}")
	public String guardaReceta (@PathVariable ("idReceta") int idReceta, HttpSession session) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		Receta receta=rdao.findById(idReceta);
		
		RecetaEnUsuario recusuario=new RecetaEnUsuario();
		recusuario.setReceta(receta);
		recusuario.setUsuario(usuario);
		
		return (rudao.guardarReceta(recusuario)==1)?"Receta guardada":"Receta NOOOOO guardada";
	}
	
	//Ver recetas guardadas por el usuario que ha iniciado sesión
	@GetMapping("/verGuardadas")
	public List<RecetaEnUsuario> meGustan(HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return rudao.verRecetasGuardadas(usuario.getUsername());
	}

	
	//ME RESPONDE CON ALTA NO REALIZADA PORQUE NO PERSISTE
	
	/*
	 * Creo que le tienes que quitar el @RequestBody, y meter simplemente un objeto Receta
	 * (como hemos hecho normalmente). Asi no necesitas meter TODOS los atributos de receta.
	 * Igualmente, habria que crear otro metodo RecetaCompleta, para poder recibir ingredientes,
	 * cantidad, etc
	 * 
	 * Recibe una lista de Ingredientes + un objeto receta. Para crear los registros en la tabla, 
	 * hay que hacer un bucle for: por cada listaIngrediente(i), crear un objeto Ingrediente en receta,
	 * y .setIngrediente, .setReceta recibida.
	 * Finalmente: IngredRecetaDAO.altareceta(objeto ingredienteReceta creado)
	 */
	@PostMapping("/altaReceta")
	public String procesarAlta (@RequestBody Receta receta) {
		return (rdao.altaReceta(receta)==1)?"Alta realizada":"Alta no realizada";
	}
	
}
