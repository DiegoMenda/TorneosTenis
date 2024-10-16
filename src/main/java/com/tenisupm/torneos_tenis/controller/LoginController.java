package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.service.JugadorService;

@Controller
@RequestMapping(path = "v1/")
public class LoginController {

	private final JugadorService jugadorService;
	
	@Autowired
	public LoginController(JugadorService jugadorService) {
		this.jugadorService = jugadorService;
	}

	@GetMapping("login")
	public String mostrarFormularioLogin() {
		return "login";  // Este nombre corresponde al archivo login.html en src/main/resources/templates
	}

	@PostMapping("login")
	public String procesarLogin(@RequestParam("username") String username, 
			@RequestParam("password") String password, 
			Model model) {
		try {
			jugadorService.validarJugador(username, password);
			return "redirect:/v1/bienvenido";  // Aquí puedes poner la URI de la siguiente página
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "login";
		}
	}
}
