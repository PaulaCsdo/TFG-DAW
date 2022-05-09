package com.proyecto.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping ("/logout")
	public String logout (HttpSession session) {
		session.invalidate();
		return "Logout";
	}
	
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
	@GetMapping("/verGuardadas")
	public List<RecetaEnUsuario> meGustan(HttpSession session){
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		return rudao.verRecetasGuardadas(usuario.getUsername());
	}
	
	//Ver las recetas creadas por el usuario
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
	
	@GetMapping("/buscadorIngrediente/{descripcion}")
	public List <Ingrediente> buscarIngrediente(@PathVariable("descripcion") String descripcion) {
		return idao.buscarPorDescripcion(descripcion);
	}
	
	
//	//Alta receta. Botón submit: dar de alta
//	@PostMapping("/altaReceta")
//	public String altaReceta(Receta receta, HttpSession session) {
//		Usuario usuario = (Usuario) session.getAttribute("usuario");
//		receta.setUsuario(usuario);
//		session.setAttribute("receta", receta);
//		return (rdao.altaReceta(receta)==1)?"Alta realizada":"Alta no realizada";
//	}
//	
//	//Este método es un botón que lleva al siguiente paso: incluir los ingredientes
//	@GetMapping("/recuperarReceta")
//	public Receta recuperarReceta (HttpSession session) {
//		Receta receta = (Receta) session.getAttribute("receta");
//		return receta;
//	}
	
	@PostMapping("/altaReceta")
	public String altaIngredienteReceta(Receta receta, List<IngredienteEnReceta> listaIngredientes, 
			HttpSession session) {
		session.setAttribute("receta", null);
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		receta.setUsuario(usuario);
		session.setAttribute("receta", receta);
		rdao.altaReceta(receta);
		
		Receta recetaCreada=(Receta) session.getAttribute("receta");
		for(IngredienteEnReceta ele: listaIngredientes) {
			ele.setReceta(recetaCreada);
		}

		return (irdao.nuevaReceta(listaIngredientes)==1)?"Alta realizada":"Alta NOOOO realizada";
	}
	
	
	//ME RESPONDE CON ALTA NO REALIZADA PORQUE NO PERSISTE
	
//	@PostMapping("/altaReceta")
//	public String procesarAlta (@RequestBody Receta receta,
//			List<IngredienteEnReceta> listaIngredientesEnReceta, HttpSession session) {
//		try {
//			Receta receta2 = new Receta();
//			Usuario usuario = (Usuario) session.getAttribute("usuario");
//			
//			receta2.setImagen(receta.getImagen());
//			receta2.setKcal(receta.getKcal());
//			receta2.setMomento(receta.getMomento());
//			receta2.setNovedad(receta.getNovedad());
//			receta2.setNumPorciones(receta.getNumPorciones());
//			receta2.setPasos(receta.getPasos());
//			receta2.setPuntuacion(receta.getPuntuacion());
//			receta2.setTiempo(receta.getTiempo());
//			receta2.setTitulo(receta.getTitulo());
//			receta2.setCategoria(receta.getCategoria());
//			receta2.setNivelCocina(receta.getNivelCocina());
//			receta2.setUsuario(usuario);
//			
//			for(IngredienteEnReceta ele: listaIngredientesEnReceta) {
//				ele.setIngrediente(receta.getIngredienteEnRecetas());
//				System.out.println(ele.getIngrediente());
//				receta2.setIngredienteEnRecetas(ele.getIngrediente());
//
//			}
//
//			RecetaEnUsuario reu = new RecetaEnUsuario();
//			List<RecetaEnUsuario> listaRecetaEnUsuario = rudao.findAll();
//			reu.setGuardada("G");
//			reu.setUsuario(usuario);
//			reu.setAgendada(null);
//			reu.setReceta(receta2);
//			receta2.setRecetaEnUsuarios(null);
//			
//			System.out.println(listaRecetaEnUsuario);
//			System.out.println(reu);
//			
//			listaRecetaEnUsuario.add(reu);
//			
			
//			return (rdao.altaReceta(receta2)==1)?"Alta realizada":"Alta no realizada";
//		}
//		catch (Exception e) {
//			return e.getMessage();
//		}
//		
//	}
//	
}
