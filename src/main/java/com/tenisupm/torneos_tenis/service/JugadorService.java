package com.tenisupm.torneos_tenis.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.tenisupm.torneos_tenis.entity.Jugador;
@Service
public class JugadorService {
	public List<Jugador> getJugadores(){
		return List.of(new Jugador(1L, "Pepe53", "1234", "José", "Gutiérrez Montoya", "644 44 55 37", "pepeputero69@hotmail.com"));
	}
}
