package com.tenisupm.torneos_tenis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.entity.Torneo;
import com.tenisupm.torneos_tenis.service.JugadorService;
import com.tenisupm.torneos_tenis.service.TorneoService;

@Controller
@RequestMapping(path = "v1/")
public class TorneoGestionController {

    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private TorneoService torneoService;

    @GetMapping("gestion-torneos")
    public String gestionarTorneos(Model model) {
        boolean esAdminActivo = jugadorService.getJugadorActivo().getUsername().equals("admin");

        if (esAdminActivo) {
            List<Torneo> torneos = torneoService.obtenerTodosLosTorneos(); // Método en tu servicio de torneos
            model.addAttribute("torneos", torneos);
            return "gestionarTorneos";
        } else {
            return "accesoDenegado";
        }
    }
}
