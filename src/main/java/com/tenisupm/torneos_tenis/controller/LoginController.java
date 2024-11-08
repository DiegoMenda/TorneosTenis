package com.tenisupm.torneos_tenis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tenisupm.torneos_tenis.service.JugadorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(path = "v1/")
public class LoginController {

    private final JugadorService jugadorService;
    
    @Autowired
    private SessionRegistry sessionRegistry;
    
    @Autowired
    public LoginController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("login")
    public String mostrarFormularioLogin() {
        return "login";  // Asegúrate de que el archivo login.html está en src/main/resources/templates
    }

    @PostMapping("login")
    public String procesarLogin(@RequestParam("username") String username, 
                                 @RequestParam("password") String password, 
                                 Model model) {
        try {
            jugadorService.validarJugador(username, password);
            return "redirect:/v1/principal";  // Redirige a la página principal después de iniciar sesión
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";  // Retorna a la página de login si hay un error
        }
    }
    
    @GetMapping("session")
    public ResponseEntity<?> getDetailsSession(){
    	
    	String sessionId= "";
    	User userObject = null;
    	
    	List<Object> sessions = sessionRegistry.getAllPrincipals();
    	
    	for(Object session : sessions) {
    		if(session instanceof User) {
    			userObject = (User) session;
    		}
    		List<SessionInformation> sessionInformations =sessionRegistry.getAllSessions(session, false);
    		
    		for(SessionInformation sessionInformation : sessionInformations) {
    			sessionId = sessionInformation.getSessionId();
    		}
    	}
    	
    	Map<String, Object> response = new HashMap<>();
    	response.put("response", "Hello World");
    	response.put("sessionId", sessionId);
    	response.put("sessionUser", userObject);
    	
    	return ResponseEntity.ok(response);
    	
    }

    // Este método es innecesario porque Spring Security se encarga del logout
    // No es necesario un método adicional aquí.
//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            new SecurityContextLogoutHandler().logout(request, response, auth);
//            request.getSession().invalidate();
//     // Invalidate the HTTP session
//        }
//        return "redirect:/login?logout"; // Redirect after logout
//    }
}
