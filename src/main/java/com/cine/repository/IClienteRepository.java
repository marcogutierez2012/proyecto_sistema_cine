package com.cine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Cliente;


public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByIdCliente(int idCliente);

	Cliente findByDniCliente(String dni);
	
	Cliente findByTelefono(String telefono);
	
	Cliente findByCorreo(String correo);
	
	List<Cliente> findByDniClienteContainingOrNomClienteContainingOrApeClienteContaining(String dni, String nom, String ape);
}
