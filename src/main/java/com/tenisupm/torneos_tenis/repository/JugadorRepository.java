package com.tenisupm.torneos_tenis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tenisupm.torneos_tenis.entity.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

}
