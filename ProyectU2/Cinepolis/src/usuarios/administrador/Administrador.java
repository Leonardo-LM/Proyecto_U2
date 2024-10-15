package usuarios.administrador;

import usuarios.Usuario;
import utils.Rol;

public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String apellido, String telefono, Rol rol) {
        super(id, nombre, apellido, telefono, Rol.ADMINISTRADOR);
    }

    //---------Getters y Setters-------------

}

