package com.luki.auth.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.luki.auth.entities.Usuario;

@Repository
public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

	Usuario findByEmail(String email);

	List<Usuario> findByNombreLike(String nombreUsuario);
	
}
