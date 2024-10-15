package com.tenisupm.torneos_tenis.entity;

import jakarta.persistence.*;

@Entity
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String username;
	private String contrasena;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    
    public Jugador(Long id, String username, String contrasena, String nombre, String apellidos, String telefono,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
	}

	public Jugador(String username, String contrasena, String nombre, String apellidos, String telefono, String email) {
		super();
		this.username = username;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
	}
	
    // Constructor por defecto
    public Jugador() {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Jugador [id=" + id + ", username=" + username + ", contrasena=" + contrasena + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", telefono=" + telefono + ", email=" + email + "]";
	}
    

}