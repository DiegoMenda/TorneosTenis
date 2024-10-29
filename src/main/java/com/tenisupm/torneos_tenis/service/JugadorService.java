package com.tenisupm.torneos_tenis.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository, VerificationTokenRepository verificationTokenRepository) {
		this.jugadorRepository = jugadorRepository;
		this.verificationTokenRepository = verificationTokenRepository;
	}

	public List<Jugador> getJugadores(){
		return jugadorRepository.findAll();
	}

	public void validarJugador(String username, String password) throws Exception {
		Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByUsername(username);

		// Verifica si el jugador existe
		if (jugadorOptional.isEmpty()) {
			throw new IllegalStateException("El usuario o contraseña es incorrecto");
		}

		Jugador jugador = jugadorOptional.get();

		// Verifica si la contraseña es correcta
		if (!jugador.getContrasena().equals(password)) {
			throw new IllegalStateException("El usuario o contraseña es incorrecto");
		}

		// Verifica si el jugador está validado
		if (!jugador.getsActivo()) { // Asegúrate de que el método isValidado() esté implementado en la clase Jugador
			throw new IllegalStateException("Tu cuenta no está validada. Revisa tu correo para validar.");
		}
	}


//	public Jugador validarUsuarioYContrasena(String username,String password) {
//		Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByUsername(username);
//
//		// Verifica si el jugador existe
//		if (jugadorOptional.isEmpty()) {
//			throw new IllegalStateException("El usuario o contraseña es incorrecto");
//		}
//		Jugador jugador = jugadorOptional.get();
//		// Verifica si la contraseña es correcta
//		if (!jugador.getContrasena().equals(password)) {
//			throw new IllegalStateException("El usuario o contraseña es incorrecto");
//		}
//		return jugador;
//	}
	@Transactional
	public void actualizarJugador(String currentUsername, String currentPassword,
				String username, String password, String telefono) {     
	    // Obtener el jugador actual desde la base de datos usando su ID
	    Jugador jugadorActual = jugadorRepository.findJugadorByUsernameAndPassword(currentUsername,currentPassword)
	            .orElseThrow(() -> new IllegalStateException("Usuario o contraseña incorrecto"));

	    // Solo verificar si el nuevo nombre de usuario es diferente del actual
	    if (!jugadorActual.getUsername().equals(username)) {
	        Optional<Jugador> jugadorByUsername = jugadorRepository.findJugadorByUsername(username);
	        if (jugadorByUsername.isPresent()) {
	            throw new IllegalStateException("Este usuario ya existe");
	        }
	    }
	    
	    // Actualizar los detalles del jugador
	    jugadorActual.setUsername(username);
	    jugadorActual.setContrasena(password); // Asegúrate de encriptar la contraseña
	    jugadorActual.setTelefono(telefono);
	    
	    // Guardar los cambios en la base de datos
	    jugadorRepository.save(jugadorActual);
	}


	public void addNuevoJugador(Jugador jugador) {
		Optional<Jugador> jugadorOptionalByUsername = jugadorRepository.findJugadorByUsername(jugador.getUsername());
		Optional<Jugador> jugadorOptionalByEmail = jugadorRepository.findJugadorByEmail(jugador.getEmail());

		if (jugadorOptionalByUsername.isPresent()) {
			throw new IllegalStateException("Este usuario ya existe");
		} else if (jugadorOptionalByEmail.isPresent()) {
			throw new IllegalStateException("Email ya en uso");
		} else {
			jugadorRepository.save(jugador);
		}
	}



	@Transactional
	public void confirmarToken(String token) {
		// Buscar el token de verificación
		VerificationToken verificationToken = verificationTokenRepository.findByToken(token);

		if (verificationToken == null) {
			throw new IllegalStateException("Token inválido");
		}

		// Comprobar si el token ha expirado
		Date now = new Date();
		if (verificationToken.getFechaExpiracion().before(now)) {
			throw new IllegalStateException("El token ha expirado");
		}

		// Activar la cuenta del jugador
		Jugador jugador = verificationToken.getJugador();
		jugador.setActivo(true);  // Si tienes el campo 'enabled' en Jugador
		jugadorRepository.save(jugador);

		// Eliminar el token después de activar la cuenta
		verificationTokenRepository.delete(verificationToken);
	}

	public void deleteJugador(Long jugadorId) {
		boolean existe = jugadorRepository.existsById(jugadorId);
		if (!existe) {
			throw new IllegalStateException("El jugador con id " + jugadorId + " no existe");
		}
		jugadorRepository.deleteById(jugadorId);
	}

	@Transactional
	public void updateJugador(Long jugadorId, String username, String nombre, String apellidos, String contrasena,
			String email, String telefono) {

		Jugador jugador = jugadorRepository.findById(jugadorId).orElseThrow(() ->
		new IllegalStateException("El jugador con id " + jugadorId + " no existe"));

		if (username != null && username.length() > 0 && !jugador.getUsername().equals(username)) {
			Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByUsername(username);
			if (jugadorOptional.isPresent()) {
				throw new IllegalStateException("Username en uso");
			}
			jugador.setUsername(username);
		}

		if (nombre != null && nombre.length() > 0 && !jugador.getNombre().equals(nombre)) {
			jugador.setNombre(nombre);
		}

		if (apellidos != null && apellidos.length() > 0 && !jugador.getApellidos().equals(apellidos)) {
			jugador.setApellidos(apellidos);
		}

		if (contrasena != null && contrasena.length() > 0 && !jugador.getContrasena().equals(contrasena)) {
			jugador.setContrasena(contrasena);
		}

		if (email != null && email.length() > 0 && !jugador.getEmail().equals(email)) {
			jugador.setEmail(email);
		}

		if (telefono != null && telefono.length() > 0 && !jugador.getTelefono().equals(telefono)) {
			jugador.setTelefono(telefono);
		}
	}
}