package com.tenisupm.torneos_tenis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;
@Service
public class JugadorService {
	
	private final JugadorRepository jugadorRepository;

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}
	
	
	public List<Jugador> getJugadores(){
		return jugadorRepository.findAll();
	}
	
	public void addNuevoJugador(Jugador jugador) {
		Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByEmail(jugador.getEmail());
		if(jugadorOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}else {
			jugadorRepository.save(jugador);
		}
	}
}
