package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ui.Model;
import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.service.JugadorService;

@Controller
@RequestMapping(path = "v1/")
public class RegistroController {
	
	private final JugadorService jugadorService;
	
	@Autowired
	public RegistroController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}
	@GetMapping("registrar")
	public String mostrarFormularioRegistro() {
		return "registrar";  // Este nombre corresponde al archivo registrar.html en src/main/resources/templates
	}
	
	@PostMapping("registrar")
	public String registrarJugador(@ModelAttribute Jugador jugador, Model model) {
		try {
			jugadorService.addNuevoJugador(jugador);
			return "redirect:/v1/bienvenido";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			return "registrar";
		}
		
	}



}
