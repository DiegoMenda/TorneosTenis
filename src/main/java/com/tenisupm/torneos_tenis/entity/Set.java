package com.tenisupm.torneos_tenis.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Anotación que define esta clase como una entidad JPA
@Table(name = "tenis_set")
public class Set {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private int juegosJugador1;
    private int juegosJugador2;

    // Constructor vacío necesario para JPA
    public Set() {}

    // Constructor con parámetros
    public Set(Long id, int juegosJugador1, int juegosJugador2) {
        this.id = id;
        this.juegosJugador1 = juegosJugador1;
        this.juegosJugador2 = juegosJugador2;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJuegosJugador1() {
        return juegosJugador1;
    }

    public void setJuegosJugador1(int juegosJugador1) {
        this.juegosJugador1 = juegosJugador1;
    }

    public int getJuegosJugador2() {
        return juegosJugador2;
    }

    public void setJuegosJugador2(int juegosJugador2) {
        this.juegosJugador2 = juegosJugador2;
    }

    @Override
    public String toString() {
        return "Set [id=" + id + ", juegosJugador1=" + juegosJugador1 + ", juegosJugador2=" + juegosJugador2 + "]";
    }
}
