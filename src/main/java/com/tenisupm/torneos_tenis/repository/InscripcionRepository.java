package com.tenisupm.torneos_tenis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Importa tus entidades
import com.tenisupm.torneos_tenis.entity.Inscripcion;
import com.tenisupm.torneos_tenis.entity.Torneo;
import com.tenisupm.torneos_tenis.entity.Jugador;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // Método personalizado para verificar si un jugador ya está inscrito en un torneo
    boolean existsByTorneoAndJugador(Torneo torneo, Jugador jugador);

    // Opcional: Método para obtener una inscripción específica
    Optional<Inscripcion> findByTorneoAndJugador(Torneo torneo, Jugador jugador);
    
    
 // Método para buscar inscripciones por jugador
    List<Inscripcion> findByJugador(Jugador jugador);


}

