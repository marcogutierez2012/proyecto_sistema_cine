package com.cine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tb_funcion")
public class Funcion {

	@Id
	@Column(name="id_funcion")
	private int idFuncion;
	
	@ManyToOne
	@JoinColumn(name="idPelicula")
	private Pelicula pelicula;
	
	@ManyToOne
	@JoinColumn(name="idSala")
	private Sala sala;
	
	@Column(name="pre_asiento")
	private double preAsiento;
	
	@Column(name="nro_asientos")
	private int nroAsientos;
	
	@ManyToOne
	@JoinColumn(name="idTipoFuncion")
	private TipoFuncion tipoFuncion;
	
	@Column(name="dia_funcion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date diaFuncion;
	
	@Column(name="hora_funcion")
	private String horaFuncion;

	public int getIdFuncion() {
		return idFuncion;
	}

	public void setIdFuncion(int idFuncion) {
		this.idFuncion = idFuncion;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public double getPreAsiento() {
		return preAsiento;
	}

	public void setPreAsiento(double preAsiento) {
		this.preAsiento = preAsiento;
	}

	public int getNroAsientos() {
		return nroAsientos;
	}

	public void setNroAsientos(int nroAsientos) {
		this.nroAsientos = nroAsientos;
	}

	public TipoFuncion getTipoFuncion() {
		return tipoFuncion;
	}

	public void setTipoFuncion(TipoFuncion tipoFuncion) {
		this.tipoFuncion = tipoFuncion;
	}

	public Date getDiaFuncion() {
		return diaFuncion;
	}

	public void setDiaFuncion(Date diaFuncion) {
		this.diaFuncion = diaFuncion;
	}

	public String getHoraFuncion() {
		return horaFuncion;
	}

	public void setHoraFuncion(String horaFuncion) {
		this.horaFuncion = horaFuncion;
	}
}
