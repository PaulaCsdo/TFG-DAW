package com.proyecto.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.modelo.bean.Categoria;
import com.proyecto.modelo.bean.NivelCocina;
import com.proyecto.modelo.bean.Perfile;
import com.proyecto.modelo.bean.TiposDieta;
import com.proyecto.modelo.bean.Usuario;
import com.proyecto.modelo.dao.CategoriaInt;
import com.proyecto.modelo.dao.NivelCocinaInt;
import com.proyecto.modelo.dao.PerfilInt;
import com.proyecto.modelo.dao.TipoDietaInt;
import com.proyecto.modelo.dao.UsuarioInt;
/**
 * Controlador que recopila los métodos relacionados con el login/logout de la aplicacion
 * y registro de usuarios en la aplicacion
 * 
 * @version 1.0
 *
 */
@CrossOrigin(origins = "http://localhost:8088")
@RestController
public class HomeRestController {
	
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
	
	/**
	 * Método para hacer login en la aplicacion. El usuario se guarda como atributo de sesion del objeto session.
	 * 
	 * @param session Objeto que se almacena en la sesion del navegador.
	 * @param usuario Atributo en el que se va a almacenar un objeto de tipo Usuario @see Usuario
	 * Se va a almacenar en el objeto session.
	 * @return Si existe el usuario devuelve el objeto usu de la clase Usuario. Si no, se devuelve un null.
	 */
	@PostMapping ("/login")
	public ResponseEntity<Usuario> formLogin (HttpSession session, Usuario usuario) {
		Usuario usu=usuint.login(usuario.getUsername(), usuario.getPassword());
		if (usu!=null) {
			session.setAttribute("usuario", usu);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return new ResponseEntity<Usuario>(usu, HttpStatus.OK);
	}
	
	
	
	/**
	 * Método para dar de alta un usuario. Se registra la fecha, se le da perfil de usuario y se añade a la lista de usuarios
	 * @param usuario @see Usuario
	 * @return Si se da de alta correctamente, devuelve un String que lo indica. En caso contrario, devuelve otro String con un error.
	 */
	@PostMapping("/alta")
	public ResponseEntity<Usuario> registrarUsuario(Usuario usuario) {
		Date fecha= new Date();
		usuario.setEnabled(1);
		usuario.setFechaAlta(fecha);

		Perfile per = perint.findById(2);
		List<Perfile> lista = new ArrayList<Perfile>();
		lista.add(per);
		usuario.setPerfiles(lista);
		if(usuint.altaUsuario(usuario)==1) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

	}
	

}
