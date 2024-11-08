package com.tenisupm.torneos_tenis.repository;

import com.tenisupm.torneos_tenis.entity.Torneo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Long> {
}
