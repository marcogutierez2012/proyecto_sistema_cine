package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_dulceria")
public class Dulceria {

	@Id
	@Column(name="id_dulceria")
	private int idDulceria;
	
	@Column(name="des_dulceria")
	private String desDulceria;
	
	@Column(name="cant_dulceria")
	private int cantDulceria;
	
	@Column(name="pre_dulceria")
	private double preDulceria;
	
	@ManyToOne
	@JoinColumn(name="idTipoDulceria")
	private TipoDulceria tipoDulceria;

	public int getIdDulceria() {
		return idDulceria;
	}

	public void setIdDulceria(int idDulceria) {
		this.idDulceria = idDulceria;
	}

	public String getDesDulceria() {
		return desDulceria;
	}

	public void setDesDulceria(String desDulceria) {
		this.desDulceria = desDulceria;
	}

	public int getCantDulceria() {
		return cantDulceria;
	}

	public void setCantDulceria(int cantDulceria) {
		this.cantDulceria = cantDulceria;
	}

	public double getPreDulceria() {
		return preDulceria;
	}

	public void setPreDulceria(double preDulceria) {
		this.preDulceria = preDulceria;
	}

	public TipoDulceria getTipoDulceria() {
		return tipoDulceria;
	}

	public void setTipoDulceria(TipoDulceria tipoDulceria) {
		this.tipoDulceria = tipoDulceria;
	}
}
