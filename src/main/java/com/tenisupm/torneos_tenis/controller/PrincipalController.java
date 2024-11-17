package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenisupm.torneos_tenis.service.JugadorService;

@Controller
@RequestMapping(path = "v1/")
public class PrincipalController {
	
	@Autowired
	private JugadorService jugadorService;
	
	@GetMapping("principal")
	  public String mostrarPaginaPrincipal(Model model) {
        // Verificar si el jugador activo es el administrador
        boolean esAdminActivo = jugadorService.esAdminActivo();
        model.addAttribute("esAdminActivo", esAdminActivo);
        
        return "principal";  // Corresponde a principal.html en src/main/resources/templates
    }
	
	
}
