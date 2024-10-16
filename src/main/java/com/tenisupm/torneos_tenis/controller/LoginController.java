package com.tenisupm.torneos_tenis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "v1/")
public class LoginController {
	
	@GetMapping("login")
    public String mostrarFormularioLogin() {
        return "login";  // Este nombre corresponde al archivo login.html en src/main/resources/templates
    }
}
