package com.tenisupm.torneos_tenis.config;


import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private JugadorService jugadorService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = "admin";
        String adminPassword = "admin"; // Cambia esta contraseña a algo seguro antes de producción

        // Verificar si el usuario admin ya existe
        if (jugadorService.findByUsername(adminUsername).isEmpty()) {
            // Si no existe, crearlo
            String encodedPassword = passwordEncoder.encode(adminPassword); // Codificar la contraseña
            Jugador admin = new Jugador(adminUsername, encodedPassword, "Admin", "Admin", "000000000", "admin@example.com");
            admin.setActivo(true);  // Configura el estado activo
            jugadorService.saveJugador(admin);
            System.out.println("Usuario administrador creado por defecto.");
        } else {
            System.out.println("Usuario administrador ya existe.");
        }
    }
}
