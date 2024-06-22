package com.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.DetalleBoleta;

public interface IDetalleBoletaRepository extends JpaRepository<DetalleBoleta, Integer> {

}
