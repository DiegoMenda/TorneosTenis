package com.tenisupm.torneos_tenis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Desactiva CSRF para permitir solicitudes POST sin token CSRF
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/jugador").authenticated()  // Requiere autenticación para /jugador
            .anyRequest().authenticated()  // Cualquier otra solicitud requiere autenticación
            .and()
            .httpBasic();  // Usar autenticación básica (Basic Auth)

        return http.build();
    }

    // Método opcional para crear usuarios en memoria
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
