package com.tenisupm.torneos_tenis.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String temporada;
    
    @OneToMany // Mapeo adecuado para las listas de entidades relacionadas
    private List<Partido> listaPartidos;
    
    @OneToMany
    private List<Jugador> listaInscritos;
    
    @OneToMany
    private List<Jugador> listaJugadoresFinales;
    
    @Temporal(TemporalType.DATE)
    private Date fechaLimiteInscripcion; // Sin el carácter especial 'ñ'

    // Constructor vacío necesario para JPA
    public Torneo() {}

    // Constructor con parámetros
    public Torneo(Long id, String temporada, List<Partido> listaPartidos, List<Jugador> listaInscritos,
                  List<Jugador> listaJugadoresFinales, Date fechaLimiteInscripcion) {
        this.id = id;
        this.temporada = temporada;
        this.listaPartidos = listaPartidos;
        this.listaInscritos = listaInscritos;
        this.listaJugadoresFinales = listaJugadoresFinales;
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public List<Partido> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(List<Partido> listaPartidos) {
        this.listaPartidos = listaPartidos;
    }

    public List<Jugador> getListaInscritos() {
        return listaInscritos;
    }

    public void setListaInscritos(List<Jugador> listaInscritos) {
        this.listaInscritos = listaInscritos;
    }

    public List<Jugador> getListaJugadoresFinales() {
        return listaJugadoresFinales;
    }

    public void setListaJugadoresFinales(List<Jugador> listaJugadoresFinales) {
        this.listaJugadoresFinales = listaJugadoresFinales;
    }

    public Date getFechaLimiteInscripcion() {
        return fechaLimiteInscripcion;
    }

    public void setFechaLimiteInscripcion(Date fechaLimiteInscripcion) {
        this.fechaLimiteInscripcion = fechaLimiteInscripcion;
    }

    @Override
    public String toString() {
        return "Torneo [id=" + id + ", temporada=" + temporada + ", listaPartidos=" + listaPartidos
                + ", listaInscritos=" + listaInscritos + ", listaJugadoresFinales=" + listaJugadoresFinales
                + ", fechaLimiteInscripcion=" + fechaLimiteInscripcion + "]";
    }
}
