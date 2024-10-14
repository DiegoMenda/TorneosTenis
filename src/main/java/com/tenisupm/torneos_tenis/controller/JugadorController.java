package com.tenisupm.torneos_tenis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

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
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void registrarNuevoJugador(@RequestBody Jugador jugador) {
		jugadorService.addNuevoJugador(jugador);
	}
	
	@DeleteMapping(path= "{jugadorId}")
	public void deleteJugador(@PathVariable("jugadorId") Long jugadorId) {
		jugadorService.deleteJugador(jugadorId);
	}
	
	@PutMapping(path = "{jugadorId}")
	public void updateJugador(@PathVariable("jugadorId") Long jugadorId,
							  @RequestParam(required = false) String nombre,
							  @RequestParam(required = false) String username,
							  @RequestParam(required = false) String contrasena,
							  @RequestParam(required = false) String apellidos,
							  @RequestParam(required = false) String email,
							  @RequestParam(required = false) String telefono){
		
		jugadorService.updateJugador(jugadorId, username, nombre, apellidos, contrasena, email, telefono);
	}
	
}
