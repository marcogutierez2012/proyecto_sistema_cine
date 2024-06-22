package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_rol")
public class Rol {

	@Id
	@Column(name="id_rol")
	private int idRol;
	
	@Column(name="des_rol")
	private String desRol;

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDesRol() {
		return desRol;
	}

	public void setDesRol(String desRol) {
		this.desRol = desRol;
	}
}
