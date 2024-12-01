package com.tenisupm.torneos_tenis.service;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final JugadorRepository jugadorRepository;

    public CustomUserDetailsService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar el jugador en la base de datos por su nombre de usuario
        Jugador jugador = jugadorRepository.findJugadorByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Jugador no encontrado: " + username));

        // Asegúrate de que la contraseña esté codificada en la base de datos
        // Si es necesario, puedes lanzar una excepción si la contraseña no está codificada
        return User.builder()
                .username(jugador.getUsername())
                .password(jugador.getContrasena()) // Asegúrate de que esta contraseña esté codificada
                .roles("USER") // Puedes personalizar esto si tienes roles adicionales
                .accountLocked(!jugador.getsActivo()) // Bloquea la cuenta si el jugador no está activo
                .accountExpired(false) // Opcional: puedes implementar esta lógica si lo necesitas
                .credentialsExpired(false) // Opcional: si deseas implementar expiración de credenciales
                .disabled(false) // Si quieres manejar la desactivación de cuentas
                .build();
    }
}
