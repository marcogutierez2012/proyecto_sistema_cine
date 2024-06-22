package com.cine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

	Usuario findByIdUsuario(int idUsuario);
	
	Usuario findByDniUsuarioAndPassword(String dni, String password);
	
	Usuario findByDniUsuario(String dni);
	
	Usuario findByEmail(String correo);
	
	Usuario findByTelefono(String telefono);
	
	List<Usuario> findByDniUsuarioContainingOrNomUsuarioContainingOrApeUsuarioContaining(String dni, String nom, String ape);
}
