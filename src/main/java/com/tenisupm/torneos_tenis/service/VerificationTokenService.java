package com.tenisupm.torneos_tenis.service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.entity.VerificationToken;
import com.tenisupm.torneos_tenis.repository.VerificationTokenRepository;
@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void enviarTokenConfirmacion(Jugador jugador) {
        // Verificar si el jugador ya tiene un token y actualizarlo en lugar de generar uno nuevo
        VerificationToken existingToken = verificationTokenRepository.findByJugador(jugador);
        
        String token;
        VerificationToken verificationToken;
        
        if (existingToken != null) {
            token = UUID.randomUUID().toString();
            existingToken.setToken(token);
            existingToken.setFechaExpiracion(calcularFechaExpiracion());
            verificationToken = verificationTokenRepository.save(existingToken);
        } else {
            token = UUID.randomUUID().toString();
            verificationToken = new VerificationToken(token, jugador, calcularFechaExpiracion());
            verificationTokenRepository.save(verificationToken);
        }

        // Construir el enlace de confirmación
        String url = "http://localhost:8080/v1/confirmar?token=" + token;

        // Enviar el correo
        enviarCorreo(jugador.getEmail(), url);
    }

    private void enviarCorreo(String email, String url) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Confirmación de registro");
        message.setText("Para activar tu cuenta, por favor haz clic en el siguiente enlace: " + url);

        mailSender.send(message);
    }

    private Date calcularFechaExpiracion() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 1);  // Expiración de 24 horas
        return new Date(cal.getTime().getTime());
    }
}
