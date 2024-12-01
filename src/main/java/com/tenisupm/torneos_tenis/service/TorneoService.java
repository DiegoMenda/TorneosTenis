package com.tenisupm.torneos_tenis.service;

import com.tenisupm.torneos_tenis.entity.Inscripcion;
import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.entity.Torneo;
import com.tenisupm.torneos_tenis.repository.InscripcionRepository;
import com.tenisupm.torneos_tenis.repository.TorneoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneoService {

    @Autowired
    private TorneoRepository torneoRepository;

    // Obtener todos los torneos
    public List<Torneo> obtenerTodosLosTorneos() {
        return torneoRepository.findAll();
    }

    // Guardar un torneo
    public Torneo guardarTorneo(Torneo torneo) {
        return torneoRepository.save(torneo);
    }

    // Obtener un torneo por ID
    public Torneo obtenerTorneoPorId(Long id) {
        return torneoRepository.findById(id).orElse(null);
    }
    
    
 

    @Autowired
    private InscripcionRepository inscripcionRepository; // Repositorio para manejar las inscripciones

    public void inscribirJugadorEnTorneo(Long torneoId, Jugador jugador) throws Exception {
        // Obtener el torneo desde la base de datos
        Torneo torneo = torneoRepository.findById(torneoId)
                .orElseThrow(() -> new Exception("Torneo no encontrado"));

        // Verificar si el jugador ya está inscrito
        if (inscripcionRepository.existsByTorneoAndJugador(torneo, jugador)) {
            throw new Exception("El jugador ya está inscrito en este torneo");
        }

        // Crear una nueva inscripción y guardarla en la base de datos
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setTorneo(torneo);
        inscripcion.setJugador(jugador);
        inscripcionRepository.save(inscripcion);
    }
    
    
    
    
    
   

    public List<Torneo> obtenerTorneosPorJugador(Jugador jugador) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByJugador(jugador);
        return inscripciones.stream()
            .map(Inscripcion::getTorneo)
            .toList();
    }
}