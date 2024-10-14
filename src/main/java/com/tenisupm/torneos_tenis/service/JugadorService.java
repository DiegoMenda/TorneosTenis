package com.tenisupm.torneos_tenis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;
@Service
public class JugadorService {
	
	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}
	
	private final JugadorRepository jugadorRepository;
	
	public List<Jugador> getJugadores(){
		return jugadorRepository.findAll();
	}
	public void addNuevoJugador(Jugador jugador) {
		 jugadorRepository.save(jugador);
		
	}
}
