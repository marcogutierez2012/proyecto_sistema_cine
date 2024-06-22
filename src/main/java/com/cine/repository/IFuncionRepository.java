package com.cine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Funcion;

public interface IFuncionRepository extends JpaRepository<Funcion, Integer> {

	Funcion findByIdFuncion(int idFuncion);
	
	List<Funcion> findByPeliculaTitPeliculaContainingOrSalaDesSalaContainingOrTipoFuncionDesTipoFuncionContaining(String tit, String sala, String tip);
}
