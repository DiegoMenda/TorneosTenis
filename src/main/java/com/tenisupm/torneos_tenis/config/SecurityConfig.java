package com.tenisupm.torneos_tenis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.tenisupm.torneos_tenis.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	return http
                .authorizeHttpRequests(auth -> {
                	auth.requestMatchers("/v1/register").permitAll();
                	auth.requestMatchers("/v1/torneos/inscribir/**").authenticated();
                	auth.anyRequest().authenticated();
                })
                .formLogin()
                	.successHandler(succeshandler()) //URI a la que se redirige despues de iniciar seion
                	.permitAll()
                .and()
                .sessionManagement()
                	.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                	.invalidSessionUrl("/login")
                	.maximumSessions(1)
                	.expiredUrl("/login")
                	.sessionRegistry(sessionRegistry())
                .and()
                .sessionFixation()
                	.migrateSession()
                .and()
                .httpBasic() // sirve para autenticacion en heade ( para usar postman)
                .and()
                .build();
    }
    
    @Bean
    public SessionRegistry sessionRegistry() {
    	return new SessionRegistryImpl();
    }
    public AuthenticationSuccessHandler succeshandler() {
    	return ((request, response, authentication) -> {
    		response.sendRedirect("/v1/principal");
    	});
    }



    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authBuilder
            .userDetailsService(customUserDetailsService)  // Inyectar el servicio de detalles de usuario
            .passwordEncoder(passwordEncoder());  // Usar el PasswordEncoder

        return authBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usa BCrypt para codificar contraseñas
    }
    
}
