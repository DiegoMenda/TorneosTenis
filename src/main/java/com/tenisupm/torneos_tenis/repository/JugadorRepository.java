package com.tenisupm.torneos_tenis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenisupm.torneos_tenis.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
	
	@Query("SELECT j FROM Jugador j WHERE j.username = ?1")
	Optional<Jugador> findJugadorByUsername(String username);
}
