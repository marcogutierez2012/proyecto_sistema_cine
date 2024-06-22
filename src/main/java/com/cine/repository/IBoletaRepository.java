package com.cine.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cine.model.Boleta;

public interface IBoletaRepository extends JpaRepository<Boleta, Integer> {

	Boleta findByIdBoleta(int idboleta);
	
	List<Boleta> findByUsuarioApeUsuarioContainingOrUsuarioNomUsuarioContainingOrClienteApeClienteContainingOrClienteNomClienteContaining(String apeusu, String nomusu, String apecli, String nomcli);
}
