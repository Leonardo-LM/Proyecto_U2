package cine;

import cartelera.Cartelera;
import cartelera.Pelicula;
import usuarios.Usuario;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;
import utils.EstadoPelicula;
import utils.Rol;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Cine {
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Administrador> listaAdministradores = new ArrayList<>();
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    private Random random = new Random();
    public Scanner scanner = new Scanner(System.in);

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
        int longitudPeliculaMasUno =  this.listaPeliculas.size() + 1;
        int numeroAleatorio = random.nextInt(1,100000);

        return String.format("P-%d-%d", longitudPeliculaMasUno, numeroAleatorio);
    }
    //------------Métodos para C.R.U.D----------------

    public void registrarPelicula(){
        System.out.println("Registro de una pelicula");
        String id = this.generarIdPelicula();
        System.out.print("Ingrese el titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese la duración: ");
        int duracion = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el genero: ");
        String genero = scanner.nextLine();
        System.out.print("Ingrese la clasificación: ");
        String clasificacion = scanner.nextLine();
        System.out.print("Ingrese la sinopsis: ");
        String sinopsis = scanner.nextLine();

        System.out.print("Seleccione el estado de la pelicula:" +
                "\n1. Estado Actual " +
                "2. Estado Proximamente");
        System.out.print("Selección: ");
        int estado = scanner.nextInt();
        scanner.nextLine();
        Pelicula pelicula = null;
        switch (estado) {
            case 1:
                pelicula = new Pelicula(id, titulo, duracion, genero, clasificacion, sinopsis, EstadoPelicula.ACTUAL);
                break;
            case 2:
                pelicula = new Pelicula(id, titulo, duracion, genero, clasificacion, sinopsis, EstadoPelicula.PROXIMAMENTE);
                break;
        }

        boolean band = true;
        LocalTime funcion = null;
        do {
            System.out.println("Ingrese la hora y los minutos de una función: ");
            System.out.print("Ingrese la hora: ");
            int hora = scanner.nextInt();
            System.out.print("Ingrese los minutos: ");
            int minutos = scanner.nextInt();
            scanner.nextLine();

            funcion = LocalTime.of(hora, minutos);
            pelicula.agregarFuncion(funcion);

            System.out.print("¿Desea Agregar otra función? S/N");
            String r = scanner.nextLine().charAt(0) + "";
            if (!r.toLowerCase().equals("s")) {
                band = false;
            }
        } while (band);

        this.registrarPelicula(pelicula);
        System.out.println("Registro Exitoso");
    }
    public void actualizarDatosPelicula(String idPelicula) {
        Optional<Pelicula> peliculaEncontrada = this.listaPeliculas.stream().filter(
                pelicula -> pelicula.getId().equals(idPelicula)).findFirst();

        if (peliculaEncontrada.isPresent()) {
            System.out.println("--Actualice los datos--");
            System.out.println("Titulo actual: " + peliculaEncontrada.get().getTitulo());
            System.out.println("Nuevo Titulo: ");
            String nuevoTitulo = scanner.nextLine();

            int duracion=0;
            boolean bandDuracion = false;
            System.out.println("Duracion actual: " + peliculaEncontrada.get().getDuracion());
            do {
                // Posible expansion de condición para los horarios >:(
                System.out.println("Nueva Duración (en minutos): ");
                //Esto en caso de que nos equivoquemos e ingresemos una letra, que no se cierre el programa :)
                if (scanner.hasNextInt()) {
                    duracion = scanner.nextInt();
                    bandDuracion = true;
                }else{
                    System.out.println("Ingresa un valor entero, intenta de nuevo");
                    scanner.next();
                }
            }while (!bandDuracion);

            scanner.nextLine();
            System.out.println("Género actual: " + peliculaEncontrada.get().getGenero());
            System.out.println("Nuevo Género: ");
            String genero = scanner.nextLine();

            System.out.println("Clasificación actual: " + peliculaEncontrada.get().getClasificacion());
            System.out.println("Nueva Clasificación: ");
            String clasificacion = scanner.nextLine();

            System.out.println("Sinopsis Actual: " + peliculaEncontrada.get().getSinopsis());
            System.out.println("Nueva Sinopsis: ");
            String sinopsis = scanner.nextLine();

            boolean bandEstado = false;
            int seleccion;
            System.out.println("Estado de pelicula actual: " + peliculaEncontrada.get().getEstado());
            while (!bandEstado) {
            System.out.print("Nuevo Estado de la pelicula:" +
                    "\n1. Estado Actual " +
                    "2. Estado Proximamente");
            System.out.print("Selección: ");
                //Otra vez de que si nos equivoquemos e ingresemos una letra, que no se cierre el programa
                if (scanner.hasNextInt()) {
                    seleccion = scanner.nextInt();
                    if (seleccion == 1) {
                        peliculaEncontrada.get().setEstado(EstadoPelicula.ACTUAL);
                        bandEstado = true;
                    } else if (seleccion == 2) {
                        peliculaEncontrada.get().setEstado(EstadoPelicula.PROXIMAMENTE);
                        bandEstado = true;
                    } else {
                        System.out.println("Opción no válida, intente de nuevo.");
                    }
                } else {
                    System.out.println("Ingresa un valor entero, intenta de nuevo");
                    scanner.next(); // Limpiar la entrada no válida <:}
                }
            }

            peliculaEncontrada.get().setTitulo(nuevoTitulo);
            peliculaEncontrada.get().setDuracion(duracion);
            peliculaEncontrada.get().setGenero(genero);
            peliculaEncontrada.get().setClasificacion(clasificacion);
            peliculaEncontrada.get().setSinopsis(sinopsis);
        }else{
            System.out.println("\nPelicula no encontrada");
        }
    }

    public void eliminarPelicula(String idPelicula) {
            for (Pelicula pelicula : this.listaPeliculas) {
                if (pelicula.getId().equals(idPelicula)) {
                    this.listaPeliculas.remove(pelicula);
                    cartelera.listaPeliculas.remove(pelicula);
                    return;
                }
            }
    }
}