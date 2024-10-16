package cine;

import cartelera.Cartelera;
import cartelera.Pelicula;
import usuarios.Usuario;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;
import utils.Rol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Cine {
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Administrador> listaAdministradores = new ArrayList<>();
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    private Random random = new Random();

    Cartelera cartelera = new Cartelera();

    //Datos de fecha de Admin
    public LocalDate fechaNacimientoAdmin = LocalDate.of(2004, 11, 23);

    public Cine(Administrador administrador) {
        administrador = new Administrador("A-1", "Admin", "1", "222", Rol.ADMINISTRADOR);
        this.listaAdministradores.add(administrador);
        this.listaUsuarios.add(administrador);
    }
    public Cine() {
    }

    //------------- Métodos de Agregación -----------------
    public void registrarPelicula(Pelicula pelicula) {
        this.listaPeliculas.add(pelicula);
        cartelera.listaPeliculas.add(pelicula);

    }
    public void registrarHorarioPelicula() {

    }

    //--------------Métodos para generar id´s------------
    public String generarIdPelicula() {
        // p - {longitud usuarios.pacientes +1} - {1-100000}
        int longitudPacientesMasUno =  this.listaPeliculas.size() + 1;
        int numeroAleatorio = random.nextInt(1,100000);

        return String.format("P-%d-%d", longitudPacientesMasUno, numeroAleatorio);
    }
}