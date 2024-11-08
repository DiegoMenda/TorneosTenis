package com.tenisupm.torneos_tenis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.entity.Torneo;
import com.tenisupm.torneos_tenis.service.JugadorService;
import com.tenisupm.torneos_tenis.service.TorneoService;

import java.util.List;

@Controller
@RequestMapping("/v1/torneos-inscritos")
public class TorneoInscritoController {

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public String verTorneosInscritos(Model model) {
        Jugador jugador = jugadorService.getJugadorActivo();
        if (jugador == null) {
            model.addAttribute("mensaje", "No hay un jugador activo.");
            return "error"; // O una vista de error adecuada
        }

        List<Torneo> torneosInscritos = torneoService.obtenerTorneosPorJugador(jugador);
        model.addAttribute("torneosInscritos", torneosInscritos);
        return "verTorneosInscritos"; // Vista que muestra los torneos inscritos
    }


}
