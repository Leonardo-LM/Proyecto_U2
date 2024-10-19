package compra;

import cartelera.Pelicula;
import usuarios.cliente.Cliente;
import salas.Sala;
import utils.TipoAsiento;
import utils.TipoPago;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Compra {
    public String Id;
    public Cliente cliente;
    public Pelicula pelicula;
    public LocalDateTime fecha;
    public LocalTime hora;
    public Sala sala;
    public TipoPago metodoPago;

    public Compra(String id, Cliente cliente, Pelicula pelicula, LocalTime hora, Sala sala, TipoPago metodoPago) {
        Id = id;
        this.cliente = cliente;
        this.pelicula = pelicula;
        this.fecha = LocalDateTime.now();
        this.hora = hora;
        this.sala = sala;
        this.metodoPago = metodoPago;
    }

    public int validarDescuento(LocalDate cumpleaniosCliente, TipoAsiento asiento){
        LocalDate fechaActual = LocalDate.now();
        int descuento = 0;

        if(fechaActual.getMonth().equals(cumpleaniosCliente.getMonth())){
            if(asiento== TipoAsiento.PREMIUM){
                descuento= 60;
            }
            else if (asiento == TipoAsiento.VIP){
                descuento= 35;
            }
        }
        return descuento;
    } //// (costo*descuento)/100



    //------------Getters y Setters-----------------

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
    public LocalTime getHora() {return hora;}
    public void setHora(LocalTime hora) {this.hora = hora;}
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public TipoPago getMetodoPago() {return metodoPago;}
    public void setMetodoPago(TipoPago metodoPago) {this.metodoPago = metodoPago;}
}

