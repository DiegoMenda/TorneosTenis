package com.tenisupm.torneos_tenis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenisupm.torneos_tenis.entity.Jugador;
import com.tenisupm.torneos_tenis.service.JugadorService;
import com.tenisupm.torneos_tenis.service.VerificationTokenService;

@Controller
@RequestMapping(path = "/v1")
public class RegistroController {

    private final JugadorService jugadorService;
    private final VerificationTokenService verificationTokenService;  // Agregar el servicio

    @Autowired
    public RegistroController(JugadorService jugadorService, VerificationTokenService verificationTokenService) {
        this.jugadorService = jugadorService;
        this.verificationTokenService = verificationTokenService;
    }

    // Mostrar el formulario de registro
    @GetMapping("/registrar")
    public String mostrarFormularioRegistro() {
        return "registrar";  // Nombre del archivo registrar.html en src/main/resources/templates
    }

    // Manejar la creación de un nuevo jugador
    @PostMapping("/registrar")
    public String registrarJugador(@ModelAttribute Jugador jugador, Model model) {
        try {
            // Registrar al jugador
            jugadorService.addNuevoJugador(jugador);

            // Generar y enviar el token de verificación desde VerificationTokenService
            verificationTokenService.enviarTokenConfirmacion(jugador);

            // Redirigir a una página que indique que debe confirmar su correo
            return "redirect:/v1/confirmacion-enviada";  // Crear una página de "confirmación enviada"

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registrar";
        }
    }

    // Página que informa al usuario que se ha enviado el correo de confirmación
    @GetMapping("/confirmacion-enviada")
    public String mostrarConfirmacionEnviada() {
        return "confirmacion-enviada";  // Crear confirmacion-enviada.html en templates
    }

    // Manejar la confirmación del token de activación
    @GetMapping("/confirmar")
    public String confirmarCuenta(@RequestParam("token") String token, Model model) {
        try {
            // Lógica para confirmar la cuenta con el token
            jugadorService.confirmarToken(token);

            // Redirigir al usuario a la página de bienvenida
            return "redirect:/v1/principal";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";  // Crear una página de error si hay problemas con la confirmación
        }
    }
}
