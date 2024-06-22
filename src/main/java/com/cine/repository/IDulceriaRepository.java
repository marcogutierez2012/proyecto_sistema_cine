package com.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Dulceria;

public interface IDulceriaRepository extends JpaRepository<Dulceria, Integer> {

	Dulceria findByIdDulceria(int dulceria);
	
}
