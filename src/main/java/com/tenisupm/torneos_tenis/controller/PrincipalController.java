package com.tenisupm.torneos_tenis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "v1/")
public class PrincipalController {
	
	@GetMapping("principal")
	public String mostrarFormularioLogin() {
		return "principal";  // Este nombre corresponde al archivo login.html en src/main/resources/templates
	}
	
	
}
