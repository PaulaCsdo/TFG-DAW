package com.proyecto.modelo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.proyecto.modelo.bean.Usuario;

public interface UsuarioRepo extends JpaRepository<Usuario, String>{

}
