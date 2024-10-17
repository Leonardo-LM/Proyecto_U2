package salas;

import cartelera.Pelicula;
import utils.EstadoAsiento;
import utils.TipoAsiento;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Sala {
    public String Id; // num. sala (A, B...)
    public int capacidad; // num. asientos
    private List<Asiento> Asientos;
    public ArrayList<LocalTime> horarios;
    public ArrayList<Pelicula> peliculas;
    public LocalTime horaEntrada = LocalTime.of(12,0,0);
    public LocalTime horaSalida = LocalTime.of(3,30,0);


    public Sala(String id, int filas, int columnas, int cantidadVIP, int cantidadPremium) {
        this.Id = id;
        this.capacidad = filas * columnas;
        this.Asientos = new ArrayList<>();
        this.horarios = new ArrayList<>();
        this.peliculas = new ArrayList<>();

        horarios.add(LocalTime.of(0, 0));

        // Crear distribucion de asientos

        int totalAsientos = filas * columnas;
        for (int i = 0; i < totalAsientos; i++) {
            if (i < cantidadVIP) {
                Asientos.add(new Asiento(TipoAsiento.VIP));
            } else if (i < cantidadVIP + cantidadPremium) {
                Asientos.add(new Asiento(TipoAsiento.PREMIUM));
            } else {
                Asientos.add(new Asiento(TipoAsiento.NORMAL));
            }
        }
    }

    // Metodo para reservar un asiento

    public boolean reservarAsiento(int indice) {
        if (indice >= 0 && indice < Asientos.size()) {
            Asiento asiento = Asientos.get(indice);
            if (asiento.getEstado() == EstadoAsiento.DISPONIBLE) {
                asiento.setEstado(EstadoAsiento.RESERVADO);
                return true;
            }
        }
        return false;

    }

    // Metodo para vender un asiento

    public boolean venderAsiento(int indice) {
        if (indice >= 0 && indice < Asientos.size()) {
            Asiento asiento = Asientos.get(indice);
            if (asiento.getEstado() == EstadoAsiento.RESERVADO) {
                asiento.setEstado(EstadoAsiento.VENDIDO);
                return true;
            }
        }
        return false;
    }

    //Mostrar estado de los asientos

    public List<Asiento> getAsientos() {
        return Asientos;
    }

    // Mostrar datos

    public String mostrarDatos() {
        return "Sala{" +
                "numeroSala=" + Id +
                ", capacidad=" + capacidad +
                ", asientos=" + Asientos +
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

    public ArrayList<LocalTime> getHorarios() {
        return horarios;
    }

    public void setHorarios(ArrayList<LocalTime> horarios) {
        this.horarios = horarios;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public void setAsientos(List<Asiento> asientos) {
        Asientos = asientos;
    }
}
