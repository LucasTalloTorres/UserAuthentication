package com.luki.auth.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luki.auth.dao.UsuarioDao;
import com.luki.auth.entities.Usuario;

@RestController
@RequestMapping(value="/user", consumes="application/json", produces="application/json")
public class UserResource {

	@Autowired
	private UsuarioDao usuarioDao;
	
	@GetMapping("/{emailUsuario}")
	String busquedaPorEmail(@PathVariable("emailUsuario") String emailUsuario) {
		return usuarioDao.findByEmail(emailUsuario).toString();
	}
	
	@GetMapping("/search/name/{nombre}")
	String busquedaPorNombre(@PathVariable("nombre") String nombreUsuario) {
		return usuarioDao.findByNombreLike(nombreUsuario).toString();
	}
	
	@PostMapping("/add")
	ResponseEntity<Void> registroUsuario(@ModelAttribute Usuario usuario) {
		usuario = usuarioDao.save(usuario);
		URI location = URI.create("/"+usuario.getEmail());
		return ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).build();
	}
	
}
