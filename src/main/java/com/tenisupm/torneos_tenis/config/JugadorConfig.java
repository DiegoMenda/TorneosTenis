package com.tenisupm.torneos_tenis.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;

@Configuration
public class JugadorConfig {

	@Bean
	CommandLineRunner commandLineRunner(JugadorRepository repository) {
		return args -> {
			Jugador pepe = new Jugador("Pepe53", "1234", "José", "Gutiérrez Montoya", "644 44 55 37", "pepeputero69@hotmail.com");
			repository.saveAll(List.of(pepe));
		};
	}
}
