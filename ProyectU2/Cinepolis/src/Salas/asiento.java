package Salas;

import utils.EstadoAsiento;
import utils.TipoAsiento;

public class asiento {
    public String Id; //// fila y columna
    public TipoAsiento tipo; // normal o premium
    public EstadoAsiento estado; // libre, ocupado, reservado

    public asiento(String id, TipoAsiento tipo, EstadoAsiento estado) {
        this.Id = id;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public TipoAsiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAsiento tipo) {
        this.tipo = tipo;
    }

    public EstadoAsiento getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsiento estado) {
        this.estado = estado;
    }
}
