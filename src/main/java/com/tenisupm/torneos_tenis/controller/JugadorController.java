package com.tenisupm.torneos_tenis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.service.JugadorService;

@RestController
@RequestMapping(path = "api/v1/jugador")
public class JugadorController {

	private final JugadorService jugadorService;
	
	@Autowired
	public JugadorController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}

	@GetMapping
	public List<Jugador> getJugadores(){
		return jugadorService.getJugadores();
}
}
