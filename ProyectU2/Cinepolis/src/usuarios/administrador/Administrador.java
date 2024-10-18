package usuarios.administrador;

import usuarios.Usuario;
import utils.Rol;

public class Administrador extends Usuario {
    public Administrador(String id, String nombre, String apellido, String telefono,String contrasenia, Rol rol) {
        super(id, nombre, apellido, telefono, contrasenia, Rol.ADMINISTRADOR);
    }

    //---------Getters y Setters-------------

}

