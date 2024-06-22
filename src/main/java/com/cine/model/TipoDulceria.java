package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_tipodulceria")
public class TipoDulceria {

	@Id
	@Column(name="id_tipo_dulceria")
	private int idTipoDulceria;
	
	@Column(name="des_tipo_dulceria")
	private String desTipoDulceria;

	public int getIdTipoDulceria() {
		return idTipoDulceria;
	}

	public void setIdTipoDulceria(int idTipoDulceria) {
		this.idTipoDulceria = idTipoDulceria;
	}

	public String getDesTipoDulceria() {
		return desTipoDulceria;
	}

	public void setDesTipoDulceria(String desTipoDulceria) {
		this.desTipoDulceria = desTipoDulceria;
	}
}
