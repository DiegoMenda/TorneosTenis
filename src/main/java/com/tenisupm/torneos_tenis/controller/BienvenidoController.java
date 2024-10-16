package com.tenisupm.torneos_tenis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "v1/")
public class BienvenidoController {
	@GetMapping("bienvenido")
	public String mostrarFormularioLogin() {
		return "bienvenido";  // Este nombre corresponde al archivo login.html en src/main/resources/templates
	}
}
