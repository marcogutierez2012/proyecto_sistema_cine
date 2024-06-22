package com.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Sala;

public interface ISalaRepository extends JpaRepository<Sala, Integer> {

}
