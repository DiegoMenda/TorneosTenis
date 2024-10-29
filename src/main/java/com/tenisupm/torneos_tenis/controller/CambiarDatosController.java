package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;
import com.tenisupm.torneos_tenis.service.JugadorService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "v1/")
public class CambiarDatosController {
	
	private final JugadorService jugadorService;
	
	@Autowired
	public CambiarDatosController(JugadorService jugadorService) {
		super();
		this.jugadorService = jugadorService;
	}

	@GetMapping("cambiar-datos")
	public String mostrarPrincipal() {
		return "cambiar-datos";  // Este nombre corresponde al archivo login.html en src/main/resources/templates
	}
	
	@PostMapping("cambiar-datos")
	public String cambiarDatos(@RequestParam("currentUsername")String currentUsername,
			@RequestParam("currentPassword") String currentPassword,
			@RequestParam("newUsername") String newUsername,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("newTelefono") String telefono, Model model) {
		try {
	        jugadorService.actualizarJugador(currentUsername,currentPassword,
	        		newUsername,newPassword,telefono);
			return "redirect:/v1/principal";
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
			return "cambiar-datos";
		}
	}
}
