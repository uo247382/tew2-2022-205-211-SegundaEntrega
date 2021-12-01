package com.tew.model;

import java.sql.Timestamp;

public class PisoParaVisitar {
	private Long p;
	private Long c;
	private Timestamp  fechaHoraCita;
	private int estado;

	public Long getP() {
		return p;
	}
	public void setP(Long p) {
		this.p = p;
	}
	public Long getC() {
		return c;
	}
	public void setC(Long c) {
		this.c = c;
	}

	public Timestamp  getFechaHoraCita() {
		return fechaHoraCita;
	}
	public void setFechaHoraCita(Timestamp timestamp) {
		this.fechaHoraCita = timestamp;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
}
