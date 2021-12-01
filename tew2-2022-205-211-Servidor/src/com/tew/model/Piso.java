package com.tew.model;

public class Piso {
	private Long id;
	private Long idAgente;
	private double precio; 
	private String direccion;
	private String ciudad;
	private int ano;
	private int estado;
	private boolean visitado = false;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public Long getIDAgente() {
		return idAgente;
	}
	public void setIDAgente(Long iDAgente) {
		this.idAgente = iDAgente;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public boolean getVisitado() {
		return visitado;
	}
	public void setVisitado(boolean Visitado) {
		visitado = Visitado;
	}
}
