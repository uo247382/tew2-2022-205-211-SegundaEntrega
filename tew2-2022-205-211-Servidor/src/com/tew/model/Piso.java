package com.tew.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "piso")
public class Piso implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1341578017015776587L;
	private Long id;
	private Long idAgente;
	private double precio;
	private String direccion;
	private int estado;
	private String ciudad;
	private int ano;
	private String foto;
	public Piso() {
	}
	
	public Piso(Long id, Long idAgente, double precio, String direccion, int estado, String ciudad, int ano, String foto) {
		super();
		this.id = id;
		this.idAgente = idAgente;
		this.precio = precio;
		this.direccion = direccion;
		this.estado = estado;
		this.ciudad = ciudad;
		this.ano = ano;
		this.foto = foto;
	}

	@XmlElement
	public String getFoto() {
		return foto;
	}
	public void setFoto(String s) {
		this.foto = s;
	}
	
	@XmlElement
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	@XmlElement
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@XmlElement
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlElement
	public Long getIdAgente() {
		return idAgente;
	}
	public void setIdAgente(Long idAgente) {
		this.idAgente = idAgente;
	}
	@XmlElement
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	@XmlElement
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	@XmlElement
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	
}
