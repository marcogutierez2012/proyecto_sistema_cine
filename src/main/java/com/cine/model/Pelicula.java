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
@Table(name="tb_pelicula")
public class Pelicula {

	@Id
	@Column(name="id_pelicula")
	private int idPelicula;
	
	@Column(name="tit_pelicula")
	private String titPelicula;
	
	@Column(name="dir_pelicula")
	private String dirPelicula;
	
	@Column(name="dur_pelicula")
	private int durPelicula;
	
	@Column(name="sin_pelicula")
	private String sinPelicula;
	
	@Column(name="fecest_pelicula")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecestPelicula;
	
	@ManyToOne
	@JoinColumn(name="idGenero")
	private Genero genero;

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitPelicula() {
		return titPelicula;
	}

	public void setTitPelicula(String titPelicula) {
		this.titPelicula = titPelicula;
	}

	public String getDirPelicula() {
		return dirPelicula;
	}

	public void setDirPelicula(String dirPelicula) {
		this.dirPelicula = dirPelicula;
	}

	public int getDurPelicula() {
		return durPelicula;
	}

	public void setDurPelicula(int durPelicula) {
		this.durPelicula = durPelicula;
	}

	public String getSinPelicula() {
		return sinPelicula;
	}

	public void setSinPelicula(String sinPelicula) {
		this.sinPelicula = sinPelicula;
	}

	public Date getFecestPelicula() {
		return fecestPelicula;
	}

	public void setFecestPelicula(Date fecestPelicula) {
		this.fecestPelicula = fecestPelicula;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}
}
