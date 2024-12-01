package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenisupm.torneos_tenis.service.JugadorService;

import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/v1")
public class CambiarDatosController {

    private final JugadorService jugadorService;

    @Autowired
    public CambiarDatosController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("/cambiar-datos")
    public String mostrarPrincipal() {
    	  System.out.println("wazaaa");
        return "cambiar-datos";  // Nombre del archivo cambiar-datos.html en src/main/resources/templates
    }

    @PostMapping("/cambiar-datos")
    public String cambiarDatos(
            @RequestParam(value = "newUsername", required = false) String newUsername,
            @RequestParam(value = "newPassword", required = false) String newPassword,
            @RequestParam(value = "newTelefono", required = false) String telefono,
            Model model) {
    	System.out.println("cambiar datos controller");
        String currentUsername = jugadorService.getJugadorActivo().getUsername();
        try {
            // Actualiza solo los campos que se proporcionan
            jugadorService.actualizarJugador(currentUsername, newUsername, newPassword, telefono);
            return "redirect:/v1/principal";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "cambiar-datos";
        }
    }
}
