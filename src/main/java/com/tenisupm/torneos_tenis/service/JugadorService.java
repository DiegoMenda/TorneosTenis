package com.tenisupm.torneos_tenis.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.entity.VerificationToken;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;
import com.tenisupm.torneos_tenis.repository.VerificationTokenRepository;

import jakarta.transaction.Transactional;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final BCryptPasswordEncoder passwordEncoder; // Añadir BCryptPasswordEncoder

    @Autowired
    public JugadorService(JugadorRepository jugadorRepository, VerificationTokenRepository verificationTokenRepository, PasswordEncoder passwordEncoder) {
        this.jugadorRepository = jugadorRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.passwordEncoder = (BCryptPasswordEncoder) passwordEncoder; // Asegúrate de que sea el tipo correcto
    }

    // Método para obtener todos los jugadores
    public List<Jugador> getJugadores() {
        return jugadorRepository.findAll();
    }

    // Método para validar un jugador por su nombre de usuario y contraseña
    public void validarJugador(String username, String password) throws Exception {
        Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByUsername(username);

        if (jugadorOptional.isEmpty()) {
            throw new IllegalStateException("El usuario o contraseña es incorrecto");
        }

        Jugador jugador = jugadorOptional.get();

        // Comparar la contraseña codificada
        if (!passwordEncoder.matches(password, jugador.getContrasena())) {
            throw new IllegalStateException("El usuario o contraseña es incorrecto");
        }

        if (!jugador.getsActivo()) {
            throw new IllegalStateException("Tu cuenta no está validada. Revisa tu correo para validar.");
        }
    }

    // Método para obtener el jugador autenticado actualmente
    public Jugador getJugadorActivo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        // Verifica que el username no sea nulo ni vacío
        if (username == null || username.isEmpty()) {
            throw new RuntimeException("No hay usuario autenticado");
        }
        
        // Busca el jugador por su nombre de usuario
        return jugadorRepository.findJugadorByUsername(username)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    // Método para actualizar la información de un jugador
    @Transactional
    public void actualizarJugador(String currentUsername, String username, String password, String telefono) {
    	System.out.println("Método actualizarJugador llamado");
        Jugador jugadorActual = jugadorRepository.findJugadorByUsername(currentUsername)
                .orElseThrow(() -> new IllegalStateException("Usuario o contraseña incorrecto"));

        // Actualiza el nombre de usuario si se proporciona y es diferente al actual
        if (username != null && !username.trim().isEmpty() && !jugadorActual.getUsername().equals(username)) {
            Optional<Jugador> jugadorByUsername = jugadorRepository.findJugadorByUsername(username);
            if (jugadorByUsername.isPresent()) {
                throw new IllegalStateException("Este usuario ya existe");
            }
            jugadorActual.setUsername(username);
        }

        // Actualiza la contraseña si se proporciona
        if (password != null && !password.trim().isEmpty()) {
            jugadorActual.setContrasena(passwordEncoder.encode(password));
        }

        // Actualiza el teléfono si se proporciona y es diferente al actual
        if (telefono != null && !telefono.trim().isEmpty() && !jugadorActual.getTelefono().equals(telefono)) {
            jugadorActual.setTelefono(telefono);
        }
        System.out.println(jugadorActual.toString());
        jugadorRepository.save(jugadorActual);
    }


    // Método para añadir un nuevo jugador
    public void addNuevoJugador(Jugador jugador) {
        Optional<Jugador> jugadorOptionalByUsername = jugadorRepository.findJugadorByUsername(jugador.getUsername());
        Optional<Jugador> jugadorOptionalByEmail = jugadorRepository.findJugadorByEmail(jugador.getEmail());

        if (jugadorOptionalByUsername.isPresent()) {
            throw new IllegalStateException("Este usuario ya existe");
        } else if (jugadorOptionalByEmail.isPresent()) {
            throw new IllegalStateException("Email ya en uso");
        } else {
            // Cifrar la contraseña antes de guardarla
            String encodedPassword = passwordEncoder.encode(jugador.getContrasena());
            jugador.setContrasena(encodedPassword);
            jugadorRepository.save(jugador);
        }
    }

    // Método para confirmar un token de verificación
    @Transactional
    public void confirmarToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken == null) {
            throw new IllegalStateException("Token inválido");
        }

        Date now = new Date();
        if (verificationToken.getFechaExpiracion().before(now)) {
            throw new IllegalStateException("El token ha expirado");
        }

        Jugador jugador = verificationToken.getJugador();
        jugador.setActivo(true); // Activa la cuenta del jugador
        jugadorRepository.save(jugador);

        verificationTokenRepository.delete(verificationToken);
    }

    // Método para eliminar un jugador por su ID
    public void deleteJugador(Long jugadorId) {
        boolean existe = jugadorRepository.existsById(jugadorId);
        if (!existe) {
            throw new IllegalStateException("El jugador con id " + jugadorId + " no existe");
        }
        jugadorRepository.deleteById(jugadorId);
    }

    // Método para actualizar los detalles de un jugador
//    @Transactional
//    public void updateJugador(Long jugadorId, String username, String nombre, String apellidos, String contrasena,
//                              String email, String telefono) {
//
//        Jugador jugador = jugadorRepository.findById(jugadorId)
//                .orElseThrow(() -> new IllegalStateException("El jugador con id " + jugadorId + " no existe"));
//
//        if (username != null && username.length() > 0 && !jugador.getUsername().equals(username)) {
//            Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByUsername(username);
//            if (jugadorOptional.isPresent()) {
//                throw new IllegalStateException("Username en uso");
//            }
//            jugador.setUsername(username);
//        }
//
//        if (nombre != null && nombre.length() > 0 && !jugador.getNombre().equals(nombre)) {
//            jugador.setNombre(nombre);
//        }
//
//        if (apellidos != null && apellidos.length() > 0 && !jugador.getApellidos().equals(apellidos)) {
//            jugador.setApellidos(apellidos);
//        }
//
//        // Cifrar la nueva contraseña si es proporcionada
//        if (contrasena != null && contrasena.length() > 0) {
//            jugador.setContrasena(passwordEncoder.encode(contrasena));
//        }
//
//        if (email != null && email.length() > 0 && !jugador.getEmail().equals(email)) {
//            jugador.setEmail(email);
//        }
//
//        if (telefono != null && telefono.length() > 0 && !jugador.getTelefono().equals(telefono)) {
//            jugador.setTelefono(telefono);
//        }
//        
//        jugadorRepository.save(jugador);
//    }
}
