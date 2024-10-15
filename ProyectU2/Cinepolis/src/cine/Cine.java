package cine;

import usuarios.Usuario;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;
import utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cine {
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Administrador> listaAdministradores = new ArrayList<>();
    public ArrayList<Cliente> listaClientes = new ArrayList<>();

    public Administrador administrador;
    //Datos de fecha de Admin
    public LocalDate fechaNacimientoAdmin = LocalDate.of(2004, 11, 23);


    public Cine(Administrador administrador) {
        administrador = new Administrador("A-1", "Admin", "1", "222", Rol.ADMINISTRADOR);
        this.listaAdministradores.add(administrador);
        this.listaUsuarios.add(administrador);
    }
}