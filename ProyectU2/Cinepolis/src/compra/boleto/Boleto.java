package compra.boleto;
import java.time.LocalDateTime;
public class Boleto {
    public String id;
    public int noSala;
    public LocalDateTime fechaHora;
    public String nombrePelicula;
    public String asiento;
    public String tipoAsiento; //VIP O PREMIUM
    public String cliente;
    public double precio;
    //public boolean tipoDescuento; quite de constructor para pruebas

    public Boleto(String id, int noSala, LocalDateTime fechaHora, String nombrePelicula, String asiento, String tipoAsiento, String cliente, double precio) {
        this.id = id;
        this.noSala = noSala;
        this.fechaHora = fechaHora;
        this.nombrePelicula = nombrePelicula;
        this.asiento = asiento;
        this.tipoAsiento = tipoAsiento;
        this.cliente = cliente;
        this.precio = precio;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNoSala() {
        return noSala;
    }

    public void setNoSala(int noSala) {
        this.noSala = noSala;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getNombrePelicula() {
        return nombrePelicula;
    }

    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(String tipoAsiento) {
        this.tipoAsiento = tipoAsiento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String mostrarInformacion() {
        return   "Id: " + id +
                ", No de sala: " + noSala +
                ", Fecha y Hora: " + fechaHora+
                ", nombre pelicula: " +nombrePelicula +
                ", Asiento: " + asiento +
                ", Tipo Asiento: " + tipoAsiento +
                ",cliente: " + cliente +
                ", precio: " + precio;
    }

}
