package com.ajadrez.ajedrez;

import java.io.Serializable;
import java.time.LocalDateTime;

// Clase JavaBean que representa una partida de ajedrez
public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime fechaHora;
    private String jugadorBlanco;
    private String jugadorNegro;
    private String resultado;

    // Constructor por defecto
    public Partida() {
        this.fechaHora = LocalDateTime.now();
        this.jugadorBlanco = "Jugador 1";
        this.jugadorNegro = "Jugador 2";
        this.resultado = "En juego";
    }

    // Getters y Setters (JavaBeans)
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getJugadorBlanco() {
        return jugadorBlanco;
    }

    public void setJugadorBlanco(String jugadorBlanco) {
        this.jugadorBlanco = jugadorBlanco;
    }

    public String getJugadorNegro() {
        return jugadorNegro;
    }

    public void setJugadorNegro(String jugadorNegro) {
        this.jugadorNegro = jugadorNegro;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    // Representación en texto
    @Override
    public String toString() {
        return "Partida{" +
                "fechaHora=" + fechaHora +
                ", jugadorBlanco='" + jugadorBlanco + '\'' +
                ", jugadorNegro='" + jugadorNegro + '\'' +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
