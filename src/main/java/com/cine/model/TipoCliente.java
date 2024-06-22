package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_tipocliente")
public class TipoCliente {

	 @Id
	 @Column(name="id_tipo_cliente")
	 private int idTipo;
	 
	 @Column(name="des_tipo_cliente")
	 private String desTipo;

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getDesTipo() {
		return desTipo;
	}

	public void setDesTipo(String desTipo) {
		this.desTipo = desTipo;
	}
}
