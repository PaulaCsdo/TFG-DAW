package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.modelo.bean.Perfile;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.PerfilInt;
import com.proyecto.modelo.dao.UsuarioInt;


@RestController
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
	
	/*
	 * Para el alta es necesario: username, password, nombre, apellidos, email, nivel cocina, tipo dieta
	 */
	@PostMapping("/alta")
	public String registrarUsuario(@RequestBody Usuario usuario) {
		Date fecha= new Date();
		usuario.setEnabled(1);
		usuario.setFechaAlta(fecha);

		Perfile per = perint.findById(2);
		List<Perfile> lista = new ArrayList<Perfile>();
		lista.add(per);
		usuario.setPerfiles(lista);
			
		return (usuint.altaUsuario(usuario)==1)?"Alta realizada":"ERROR en alta";

	}
	

}
