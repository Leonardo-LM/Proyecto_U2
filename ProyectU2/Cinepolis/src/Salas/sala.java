package Salas;

import cartelera.Pelicula;

import java.time.LocalDateTime;

public class sala {
    public String Id; // num. sala (A, B...)
    public int capacidad; // num. asientos
    public int filas; // 1, 2, 3...
    public int columnas; /// a, b, c....
    public int asientosVip;
    public int asientosPremium;
    public LocalDateTime horarios;
    public Pelicula peliculas;

    public sala(String id, int capacidad, int filas, int columnas, int asientosVip, int asientosPremium, LocalDateTime horarios, Pelicula peliculas) {
        this.Id = id;
        this.capacidad = capacidad;
        this.filas = filas;
        this.columnas = columnas;
        this.asientosVip = asientosVip;
        this.asientosPremium = asientosPremium;
        this.horarios = horarios;
        this.peliculas = peliculas;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public int getAsientosVip() {
        return asientosVip;
    }

    public void setAsientosVip(int asientosVip) {
        this.asientosVip = asientosVip;
    }

    public int getAsientosPremium() {
        return asientosPremium;
    }

    public void setAsientosPremium(int asientosPremium) {
        this.asientosPremium = asientosPremium;
    }

    public LocalDateTime getHorarios() {
        return horarios;
    }

    public void setHorarios(LocalDateTime horarios) {
        this.horarios = horarios;
    }

    public Pelicula getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Pelicula peliculas) {
        this.peliculas = peliculas;
    }
}
