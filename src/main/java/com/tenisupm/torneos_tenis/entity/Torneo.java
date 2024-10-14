package com.tenisupm.torneos_tenis.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Torneo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String temporada;
	private List<Partido> listaPartidos;
	private List<Jugador> listaInscritos;
	private List<Jugador> listaJugadoresFinales;
	private Date fechaLimiteInscripción;
	public Torneo(Long id, String temporada, List<Partido> listaPartidos, List<Jugador> listaInscritos,
			List<Jugador> listaJugadoresFinales, Date fechaLimiteInscripción) {
		super();
		this.id = id;
		this.temporada = temporada;
		this.listaPartidos = listaPartidos;
		this.listaInscritos = listaInscritos;
		this.listaJugadoresFinales = listaJugadoresFinales;
		this.fechaLimiteInscripción = fechaLimiteInscripción;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public List<Partido> getListaPartidos() {
		return listaPartidos;
	}
	public void setListaPartidos(List<Partido> listaPartidos) {
		this.listaPartidos = listaPartidos;
	}
	public List<Jugador> getListaInscritos() {
		return listaInscritos;
	}
	public void setListaInscritos(List<Jugador> listaInscritos) {
		this.listaInscritos = listaInscritos;
	}
	public List<Jugador> getListaJugadoresFinales() {
		return listaJugadoresFinales;
	}
	public void setListaJugadoresFinales(List<Jugador> listaJugadoresFinales) {
		this.listaJugadoresFinales = listaJugadoresFinales;
	}
	public Date getFechaLimiteInscripción() {
		return fechaLimiteInscripción;
	}
	public void setFechaLimiteInscripción(Date fechaLimiteInscripción) {
		this.fechaLimiteInscripción = fechaLimiteInscripción;
	}
	@Override
	public String toString() {
		return "Torneo [id=" + id + ", temporada=" + temporada + ", listaPartidos=" + listaPartidos
				+ ", listaInscritos=" + listaInscritos + ", listaJugadoresFinales=" + listaJugadoresFinales
				+ ", fechaLimiteInscripción=" + fechaLimiteInscripción + "]";
	}
	
	
	
	
}
