package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tenisupm.torneos_tenis.entity.Inscripcion;
import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.entity.Torneo;
import com.tenisupm.torneos_tenis.repository.InscripcionRepository;
import com.tenisupm.torneos_tenis.service.JugadorService;
import com.tenisupm.torneos_tenis.service.TorneoService;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/v1/torneos")
public class InscribirseTorneoController {

	@Autowired
    private TorneoService torneoService;

    @Autowired
    private JugadorService jugadorService;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    // Método para mostrar la lista de torneos con opción de inscripción
    @GetMapping
    public String listarTorneos(Model model) {
    	System.out.println("listo torneos");
        model.addAttribute("torneos", torneoService.obtenerTodosLosTorneos());
        return "inscribirse"; // Vista que muestra la lista de torneos
    }

    // Método para inscribir al jugador en un torneo
    @PostMapping("/inscribir/{torneoId}")
    public ResponseEntity<?> inscribirJugador(@PathVariable Long torneoId) {
        System.out.println("intento inscribir");
        Torneo torneo = torneoService.obtenerTorneoPorId(torneoId);
        if (torneo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El torneo no existe.");
        }

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Convertir la fecha límite de inscripción (de java.util.Date o java.sql.Date) a LocalDate
        java.sql.Date fechaLimiteInscripcion = (java.sql.Date) torneo.getFechaLimiteInscripcion();
        LocalDate fechaLimiteLocalDate = fechaLimiteInscripcion.toLocalDate();


        // Comparar las fechas
        if (fechaActual.isAfter(fechaLimiteLocalDate)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("La fecha límite de inscripción ha pasado y no puedes inscribirte en este torneo.");
        }

        Jugador jugador = jugadorService.getJugadorActivo();
        if (jugador == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay un jugador activo.");
        }

        if (inscripcionRepository.existsByTorneoAndJugador(torneo, jugador)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya estás inscrito en este torneo.");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setTorneo(torneo);
        inscripcion.setJugador(jugador);
        inscripcionRepository.save(inscripcion);

        return ResponseEntity.ok("Te has inscrito correctamente en el torneo.");
    }

}