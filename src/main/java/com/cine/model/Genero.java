package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_genero")
public class Genero {

	@Id
	@Column(name="id_genero")
	private int idGenero;
	
	@Column(name="des_genero")
	private String desGenero;

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getDesGenero() {
		return desGenero;
	}

	public void setDesGenero(String desGenero) {
		this.desGenero = desGenero;
	}
}
