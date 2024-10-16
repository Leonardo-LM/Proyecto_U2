package Compra;

import cartelera.Pelicula;
import usuarios.cliente.Cliente;
import Salas.sala;

import java.time.LocalDateTime;

public class compra {
    public String Id;
    public Cliente cliente;
    public Pelicula pelicula;
    public LocalDateTime fecha;
    public sala sala;
    public String metodoPago;

    public compra(String id, Cliente cliente, Pelicula pelicula, LocalDateTime fecha, Salas.sala sala, String metodoPago) {
        Id = id;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.sala = sala;
        this.metodoPago = metodoPago;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Salas.sala getSala() {
        return sala;
    }

    public void setSala(Salas.sala sala) {
        this.sala = sala;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
}
//validador descuento