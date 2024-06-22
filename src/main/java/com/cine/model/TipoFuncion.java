package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_tipofuncion")
public class TipoFuncion {

	@Id
	@Column(name="id_tipo_funcion")
	private int idTipoFuncion;
	
	@Column(name="des_tipo_funcion")
	private String desTipoFuncion;

	public int getIdTipoFuncion() {
		return idTipoFuncion;
	}

	public void setIdTipoFuncion(int idTipoFuncion) {
		this.idTipoFuncion = idTipoFuncion;
	}

	public String getDesTipoFuncion() {
		return desTipoFuncion;
	}

	public void setDesTipoFuncion(String desTipoFuncion) {
		this.desTipoFuncion = desTipoFuncion;
	}
}
