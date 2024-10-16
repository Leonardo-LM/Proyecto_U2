package metodoPago;

public class Tarjeta {
    public int numeroTarjeta;
    public String nombreTitular;
    private int nip;

    public Tarjeta(int numeroTarjeta, String nombreTitular, int nip) {
        this.numeroTarjeta = numeroTarjeta;
        this.nombreTitular = nombreTitular;

    }

    public int getNumeroTarjeta() {return numeroTarjeta;}
    public String getNombreTitular() {return nombreTitular;}
    public int getNip() {return nip;}
}
