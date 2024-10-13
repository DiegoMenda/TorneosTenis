package com.tenisupm.torneos_tenis.entity;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Partido {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long idJugador1;
	private Long idJugador2;
	private Long idTorneo;
	private List<Set> listaSets;
	private int ronda;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRonda() {
		return ronda;
	}
	public void setRonda(int ronda) {
		this.ronda = ronda;
	}
	public Long getIdJugador1() {
		return idJugador1;
	}
	public void setIdJugador1(Long idJugador1) {
		this.idJugador1 = idJugador1;
	}
	public Long getIdJugador2() {
		return idJugador2;
	}
	public void setIdJugador2(Long idJugador2) {
		this.idJugador2 = idJugador2;
	}
	public Long getIdTorneo() {
		return idTorneo;
	}
	public void setIdTorneo(Long idTorneo) {
		this.idTorneo = idTorneo;
	}
	public List<Set> getListaSets() {
		return listaSets;
	}
	public void setListaSets(List<Set> listaSets) {
		this.listaSets = listaSets;
	}
	public Partido(Long id, int ronda, Long idJugador1, Long idJugador2, Long idTorneo, List<Set> listaSets) {
		super();
		this.id = id;
		this.ronda = ronda;
		this.idJugador1 = idJugador1;
		this.idJugador2 = idJugador2;
		this.idTorneo = idTorneo;
		this.listaSets = listaSets;
	}
	@Override
	public String toString() {
		return "Partido [id=" + id + ", idJugador1=" + idJugador1 + ", idJugador2=" + idJugador2 + ", idTorneo="
				+ idTorneo + ", listaSets=" + listaSets + ", ronda=" + ronda + "]";
	}

}
