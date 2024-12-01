package com.tenisupm.torneos_tenis.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = Jugador.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "jugador_id")
    private Jugador jugador;

    private Date fechaExpiracion;

    
    public VerificationToken(String token, Jugador jugador, Date fechaExpiracion) {
        this.token = token;
        this.jugador = jugador;
        this.fechaExpiracion = fechaExpiracion;
    }

    public VerificationToken() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Date getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(Date fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

    // Constructor, getters y setters
    
}
