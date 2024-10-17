package Salas;

import cartelera.Pelicula;
import utils.EstadoAsiento;
import utils.TipoAsiento;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class sala {
    public String Id; // num. sala (A, B...)
    public int capacidad; // num. asientos
    private List<asiento> asientos;
    public LocalDateTime horarios;
    public ArrayList<Pelicula> peliculas;

    public sala(String id, int filas, int columnas, LocalDateTime horarios, Pelicula peliculas, int cantidadVIP, int cantidadPremium) {
        this.Id = id;
        this.capacidad = filas * columnas;
        this.asientos = new ArrayList<>();
        this.horarios = horarios;
        this.peliculas = new ArrayList<>();

        // Crear distribucion de asientos

        int totalAsientos = filas * columnas;
        for (int i = 0; i < totalAsientos; i++) {
            if (i < cantidadVIP) {
                asientos.add(new asiento(TipoAsiento.VIP));
            } else if (i < cantidadVIP + cantidadPremium) {
                asientos.add(new asiento(TipoAsiento.PREMIUM));
            } else {
                asientos.add(new asiento(TipoAsiento.NORMAL));
            }
        }
    }

    // Metodo para reservar un asiento

    public boolean reservarAsiento(int indice) {
        if (indice >= 0 && indice < asientos.size()) {
            asiento asiento = asientos.get(indice);
            if (asiento.getEstado() == EstadoAsiento.DISPONIBLE) {
                asiento.setEstado(EstadoAsiento.RESERVADO);
                return true;
            }
        }
        return false;

    }

    // Metodo para vender un asiento

    public boolean venderAsiento(int indice) {
        if (indice >= 0 && indice < asientos.size()) {
            asiento asiento = asientos.get(indice);
            if (asiento.getEstado() == EstadoAsiento.RESERVADO) {
                asiento.setEstado(EstadoAsiento.VENDIDO);
                return true;
            }
        }
        return false;
    }

    //Mostrar estado de los asientos

    public List<asiento> getAsientos() {
        return asientos;
    }

    // Mostrar datos

    public String mostrarDatos() {
        return "Sala{" +
                "numeroSala=" + Id +
                ", capacidad=" + capacidad +
                ", asientos=" + asientos +
                '}';
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

    public LocalDateTime getHorarios() {
        return horarios;
    }

    public void setHorarios(LocalDateTime horarios) {
        this.horarios = horarios;
    }

}
