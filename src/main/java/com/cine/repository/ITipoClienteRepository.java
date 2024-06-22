package com.cine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.TipoCliente;

public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Integer> {

}
