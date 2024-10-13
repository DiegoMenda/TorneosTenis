package com.tenisupm.torneos_tenis.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Administrador {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
	private String contrasena;
    private String email;
	public Administrador(Long id, String username, String contrasena, String email) {
		super();
		this.id = id;
		this.username = username;
		this.contrasena = contrasena;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Administrador [id=" + id + ", username=" + username + ", contrasena=" + contrasena + ", email=" + email
				+ "]";
	}
    
    
}
