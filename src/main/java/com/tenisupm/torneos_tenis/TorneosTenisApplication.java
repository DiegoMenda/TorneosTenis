package com.tenisupm.torneos_tenis;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tenisupm.torneos_tenis.entity.Jugador;

@SpringBootApplication
@RestController
public class TorneosTenisApplication {

	public static void main(String[] args) {
		SpringApplication.run(TorneosTenisApplication.class, args);
	}

}
