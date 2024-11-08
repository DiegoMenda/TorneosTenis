package com.tenisupm.torneos_tenis.controller;

import com.tenisupm.torneos_tenis.entity.Torneo;
import com.tenisupm.torneos_tenis.service.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;

    // Endpoint para obtener todos los torneos
    @GetMapping
    public List<Torneo> obtenerTodosLosTorneos() {
        return torneoService.obtenerTodosLosTorneos();
    }

    // Endpoint para agregar un nuevo torneo
    @PostMapping
    public Torneo agregarTorneo(@RequestBody Torneo torneo) {
        return torneoService.guardarTorneo(torneo);
    }

    // Endpoint para obtener un torneo por ID
    @GetMapping("/{id}")
    public Torneo obtenerTorneoPorId(@PathVariable Long id) {
        return torneoService.obtenerTorneoPorId(id);
    }
}