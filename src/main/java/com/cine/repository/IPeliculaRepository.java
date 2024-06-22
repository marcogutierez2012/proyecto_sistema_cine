package com.cine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Integer> {

	Pelicula findByIdPelicula(int idPelicula);
	
	Pelicula findByTitPelicula(String titPelicula);
	
	List<Pelicula> findByTitPeliculaContainingOrDirPeliculaContainingOrGeneroDesGeneroContaining(String tit, String dir, String gen);
}
