package com.tenisupm.torneos_tenis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.repository.JugadorRepository;

import jakarta.transaction.Transactional;
@Service
public class JugadorService {
	
	private final JugadorRepository jugadorRepository;

	@Autowired
	public JugadorService(JugadorRepository jugadorRepository) {
		this.jugadorRepository = jugadorRepository;
	}
	
	
	public List<Jugador> getJugadores(){
		return jugadorRepository.findAll();
	}
	
	public void addNuevoJugador(Jugador jugador) {
		Optional<Jugador> jugadorOptionalByUsername = jugadorRepository.findJugadorByUsername(jugador.getUsername());
		Optional<Jugador> jugadorOptionalByEmail = jugadorRepository.findJugadorByEmail(jugador.getEmail());
		if(jugadorOptionalByUsername.isPresent()) {
			throw new IllegalStateException("username ya elegido");
		}else if(jugadorOptionalByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}else {
			jugadorRepository.save(jugador);
		}
		
	}
  
	public void deleteJugador(Long jugadorId) {
		boolean existe =jugadorRepository.existsById(jugadorId);
		if(!existe) {
			throw new IllegalStateException("el jugador con id " + jugadorId + " no existe");
		}
		jugadorRepository.deleteById(jugadorId);
	}
	@Transactional
	public void updateJugador(Long jugadorId, String username, String nombre, String apellidos, String contrasena,
			String email, String telefono) {
		
		Jugador jugador = jugadorRepository.findById(jugadorId).orElseThrow(() -> new IllegalStateException(
																				"jugador con id " + jugadorId + " no existe"));
	
		if(username !=null && username.length()>0 && !jugador.getUsername().equals(username)) {
			
			Optional<Jugador> jugadorOptional = jugadorRepository.findJugadorByUsername(username);
			if(jugadorOptional.isPresent()) {
				throw new IllegalStateException("username en uso");
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
