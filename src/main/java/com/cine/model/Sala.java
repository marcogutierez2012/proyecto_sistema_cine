package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_sala")
public class Sala {

	@Id
	@Column(name="id_sala")
	private int idSala;
	
	@Column(name="des_sala")
	private String desSala;

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getDesSala() {
		return desSala;
	}

	public void setDesSala(String desSala) {
		this.desSala = desSala;
	}
}
