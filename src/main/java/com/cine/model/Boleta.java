package com.cine.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tb_boleta")
public class Boleta {

	@Id
	@Column(name="id_boleta")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBoleta;
	
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idFuncion")
	private Funcion funcion;
	
	@Column(name="cant_asientos")
	private int cantAsientos;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fec_boleta")
	private Date fecBoleta;
	
	@Column(name="tot_boleta")
	private double totBoleta;

	public int getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

	public int getCantAsientos() {
		return cantAsientos;
	}

	public void setCantAsientos(int cantAsientos) {
		this.cantAsientos = cantAsientos;
	}

	public Date getFecBoleta() {
		return fecBoleta;
	}

	public void setFecBoleta(Date fecBoleta) {
		this.fecBoleta = fecBoleta;
	}

	public double getTotBoleta() {
		return totBoleta;
	}

	public void setTotBoleta(double totBoleta) {
		this.totBoleta = totBoleta;
	}
}
