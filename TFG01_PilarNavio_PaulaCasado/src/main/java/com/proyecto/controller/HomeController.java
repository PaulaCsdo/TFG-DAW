package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.modelo.bean.Perfile;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.PerfilInt;
import com.proyecto.modelo.dao.UsuarioInt;


@Controller
public class HomeController {

	/*Cuando implementemos security, el login redirige a:
	 * 		- /usuario/index/verTodas si el perfil es USUARIO
	 * 		- /usuarioPremium si es USUARIO PREMIUM
	 * 		- /admin/index si el perfil es ADMINISTRADOR
	 */
	
	@Autowired
	private UsuarioInt usuint;
	
	@Autowired
	private PerfilInt perint;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
		
	}
	
	/*Esta vista consiste en la presentación de la aplicación. Incluye un botón
	 * que redirige a la página de login.
	 */
	
	@GetMapping ("/presentacion")
	public String verPresentacion() {
		return "PruebasPaula";
	}
	
	@GetMapping ("/login")
	public String iniciarSesion () {
		return "PruebasPaula";
	}
	
	@PostMapping ("/login")
	public String formLogin (HttpSession session, Usuario usuario, RedirectAttributes rattr) {
		Usuario usu=usuint.login(usuario.getUsername(), usuario.getPassword());
		if (usu!=null) {
			session.setAttribute("usuario", usu);
				
			return "redirect:/PruebasPaula";
		}else {
			//rattr.addFlashAttribute("mensaje", "Usuario o contraseña incorrectos");
			return "redirect:/login";
		}
	}
	
	
	@GetMapping("/alta")
	public String formRegistro() {
		return "PruebasPaula";
	}

	@PostMapping("/alta")
	public String registrarUsuario(Usuario usuario, RedirectAttributes attr) {
		Date fecha= new Date();
		if (usuario == null) {
			attr.addFlashAttribute("mensaje", "Error en el alta");
			return "redirect:/alta";

		}else {
			usuario.setEnabled(1);
			usuario.setFechaAlta(fecha);

			Perfile per = perint.findById(2);
			List<Perfile> lista = new ArrayList<Perfile>();
			lista.add(per);
			usuario.setPerfiles(lista);
			usuint.altaUsuario(usuario);
			//attr.addFlashAttribute("mensaje", "Nuevo usuario creado");
			return "redirect:/registro";
		}

	}
	
//	@Autowired
//	private PasswordEncoder pwcript;
//	
//	@GetMapping("/sesionIniciada")
//	public String inicio(HttpSession session,Authentication auth, Model model) {
//		Usuario usuario=uint.findById(auth.getName());
//		if (usuario==null) {
//			return "redirect:/login";
//		}
//		session.setAttribute("usuario", usuario);
//		
//		List<Libro> lib = lint.listarNovedad();
//		List<Usuario> usu=uint.findAll();
//		List<Perfile> per=perint.findAll();
//		List<Tema> tem=tint.findAll();
//		model.addAttribute("listaUsuarios", usu);
//		model.addAttribute("listaPerfiles", per);
//		model.addAttribute("listaTemas", tem);
//		model.addAttribute("listaNovedades", lib);
//		
//		return "MenuInicio";
//		
//	}
//	
//	@GetMapping("/encriptar/{id}") 
//	@ResponseBody 
//	public String encriptar(@PathVariable("id") String texto) { 
//	String newPassw= null; 
//	 
//	newPassw = "el texto es: " + pwcript.encode(texto); 
//	return newPassw; 
//	}
//
//	@GetMapping("/logout")
//	public String logout(HttpServletRequest request){
//		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
//		logoutHandler.logout(request,null,null);
//		return "redirect:/login";
//	}

}
