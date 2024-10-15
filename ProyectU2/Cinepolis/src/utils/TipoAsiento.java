package utils;

public enum TipoAsiento {
    VIP(200),
    PREMIUM(400);
    private int costo;

    TipoAsiento(int costo) {
        this.costo = costo;
    }

    //--------------Getters y Setters-------------
    public int getCosto() {
        return costo;
    }

}

