package com.cine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_detalleboleta")
public class DetalleBoleta{

	@Id
	@Column(name="id_detalle")
	private int idDetalle;
	
	@ManyToOne
	@JoinColumn(name="id_boleta")
    private Boleta boleta;

    @ManyToOne
    @JoinColumn(name = "idDulceria")
    private Dulceria dulceria;
	
	@Column(name="cantidad")
	private int cant;
	
	@Column(name="tot_dulceria")
	private double totDulceria;

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Boleta getBoleta() {
		return boleta;
	}

	public void setBoleta(Boleta boleta) {
		this.boleta = boleta;
	}

	public Dulceria getDulceria() {
		return dulceria;
	}

	public void setDulceria(Dulceria dulceria) {
		this.dulceria = dulceria;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}

	public double getTotDulceria() {
		return totDulceria;
	}

	public void setTotDulceria(double totDulceria) {
		this.totDulceria = totDulceria;
	}
}
