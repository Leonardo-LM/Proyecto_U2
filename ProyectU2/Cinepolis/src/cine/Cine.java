package cine;

import compra.Compra;
import compra.boleto.Boleto;
import cartelera.Pelicula;
import salas.Asiento;
import salas.Sala;
import usuarios.Usuario;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;
import usuarios.empleados.Empleado;
import utils.EstadoAsiento;
import utils.EstadoPelicula;
import utils.Rol;
import utils.TipoAsiento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Cine {
    public ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    public ArrayList<Administrador> listaAdministradores = new ArrayList<>();
    public ArrayList<Cliente> listaClientes = new ArrayList<>();
    public ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    public ArrayList<Sala> listaSalas = new ArrayList<>();
    public ArrayList<Compra> listaCompras = new ArrayList<>();
    private Random random = new Random();
    public Scanner scanner = new Scanner(System.in);
    //
    public ArrayList<Asiento> listaAsientos = new ArrayList<>();
    public ArrayList<Boleto> listaBoletos = new ArrayList<>();

    public Cine(Administrador administrador) {
        administrador = new Administrador("A-1", "Admin", "1", "222", "ajcrrf", Rol.ADMINISTRADOR);
        this.listaAdministradores.add(administrador);
        this.listaUsuarios.add(administrador);
    }

    public Cine() {
    }

    //------------- Métodos de Agregación -----------------

    public void registrarPelicula(Pelicula pelicula) {
        this.listaPeliculas.add(pelicula);
    }

    //--------------Métodos para generar id´s------------

    public String generarIdPelicula() {
        // p - {longitud usuarios.pacientes +1} - {1-100000}
        int longitudPeliculaMasUno = this.listaPeliculas.size() + 1;
        int numeroAleatorio = random.nextInt(1, 100000);

        return String.format("P-%d-%d", longitudPeliculaMasUno, numeroAleatorio);
    }

    public String generarIdCliente(){  // ID que inicie con C - año actual - mes actual - listaClientes+1 - random 1/100000
        Random random = new Random();

        LocalDate fecha = LocalDate.now();
        int anoActual= fecha.getYear();
        int mesActual= fecha.getMonthValue();
        int longitudClientes = this.listaClientes.size() +1 ;
        int numeroAleatorio= random.nextInt(10000);

        String id = String.format("C%d%d%d%d",
                anoActual,mesActual,longitudClientes,numeroAleatorio);

        return id ; }

    public String generarIdEmpleado(){  // ID que inicie con E - año actual - mes actual - listaEmpleados+1 - random 1/100000

        LocalDate fecha = LocalDate.now();
        int anoActual= fecha.getYear();
        int mesActual= fecha.getMonthValue();
        int longitudEmpleados = this.listaEmpleados.size() +1 ;
        int numeroAleatorio= random.nextInt(10000);

        return String.format("E-%d-%d-%d-%d",anoActual,mesActual,longitudEmpleados,numeroAleatorio);
    }

    public String generarIdCompra(){  // ID que inicie con E - año actual - mes actual - listaEmpleados+1 - random 1/100000

        LocalDate fecha = LocalDate.now();
        int anoActual= fecha.getYear();
        int mesActual= fecha.getMonthValue();
        int longitudListaCompras = this.listaCompras.size() +1 ;
        int numeroAleatorio= random.nextInt(10000);

        return String.format("VTA-%d-%d-%d-%d",anoActual,mesActual,longitudListaCompras,numeroAleatorio);
    }

    //------------Métodos para C.R.U.D----------------

    public void registrarPelicula() {
        boolean continuar = true;
        do {
            System.out.println("Registro de una pelicula");
            String id = this.generarIdPelicula();
            System.out.print("Ingrese el titulo: ");
            String titulo = scanner.nextLine();
            System.out.print("Ingrese la duración(min): ");
            int duracion = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese el genero: ");
            String genero = scanner.nextLine();
            System.out.print("Ingrese la clasificación: ");
            String clasificacion = scanner.nextLine();
            System.out.print("Ingrese la sinopsis: ");
            String sinopsis = scanner.nextLine();

            System.out.println("Seleccione el estado de la pelicula:" +
                    "\n1. Estado Actual \n" +
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
                System.out.println("Ingrese la hora y los minutos de la función: ");
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

            System.out.print("Quiere agregar otra pelicula: s/n");
            String r = scanner.nextLine().charAt(0) + "";
            if (!r.toLowerCase().equals("s")) {
                continuar = false;
            }
        }while(continuar);
    }

    public void actualizarDatosPelicula(String idPelicula) {
        Optional<Pelicula> peliculaEncontrada = this.listaPeliculas.stream().filter(
                pelicula -> pelicula.getId().equals(idPelicula)).findFirst();

        if (peliculaEncontrada.isPresent()) {
            System.out.println("--Actualice los datos--");
            System.out.println("Titulo actual: " + peliculaEncontrada.get().getTitulo());
            System.out.println("Nuevo Titulo: ");
            String nuevoTitulo = scanner.nextLine();

            int duracion = 0;
            boolean bandDuracion = false;
            System.out.println("Duracion actual: " + peliculaEncontrada.get().getDuracion());
            do {
                // Posible expansion de condición para los horarios >:(
                System.out.println("Nueva Duración (en minutos): ");
                //Esto en caso de que nos equivoquemos e ingresemos una letra, que no se cierre el programa :)
                if (scanner.hasNextInt()) {
                    duracion = scanner.nextInt();
                    bandDuracion = true;
                } else {
                    System.out.println("Ingresa un valor entero, intenta de nuevo");
                    scanner.next();
                }
            } while (!bandDuracion);

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
                System.out.println("Nuevo Estado de la pelicula:" +
                        "\n1. Estado Actual \n" +
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
        } else {
            System.out.println("\nPelicula no encontrada");
        }
    }

    public void eliminarPelicula(String idPelicula) {
        for (Pelicula pelicula : this.listaPeliculas) {
            if (pelicula.getId().equals(idPelicula)) {
                this.listaPeliculas.remove(pelicula);
                return;
            }
        }
    }


    //----------- Validaciones -----------

    public Usuario validarInicioSesion(String idUsuario, String contraseña) {
        for (Usuario usuario : this.listaUsuarios) {
            if (usuario.getId().equals(idUsuario) && usuario.getContrasenia().equals(contraseña)) {
                return usuario;
            }

        }
        return null;
    }

    //--------------Métodos para mostrar datos----------------

    public void mostrarAsientos () {

        //String mostrarAsientos [] [];
        String butacas[] = {"A", "B", "C", "D", "E", "F"};
        int filas = 6, columnas = 6, columna;
        int fila;

        String mostrarAsientos[][] = new String[filas][columnas];

        //// llenar la matriz
        for (fila = 0; fila < filas; fila++) {
            for (columna = 0; columna < columnas; columna++) {
                mostrarAsientos[fila][columna] = butacas[fila] + String.valueOf(columna + 1);
            }
        }

        System.out.println("\t\tPANTALLA\n========================");

            for (fila = 0; fila < filas; fila++) {
                for (columna = 0; columna < columnas; columna++) {
                    System.out.print (mostrarAsientos[fila][columna] + "\t");

                }
                System.out.println("\t");
            }
    }

    public void mostrarCartelera() {
        int i = 1;
        System.out.println("=====================================");
        System.out.println("             CARTELERA               ");
        System.out.println("=====================================");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(i + ". " + String.format("Titulo: %s", pelicula.getTitulo()));
            System.out.println("   Clasificación: " + pelicula.getClasificacion());
            System.out.println("   Duración: " + pelicula.getDuracion() + " min");
            System.out.print("   Horarios: \n");
            for (LocalTime funcion : pelicula.getHorario()) {
                System.out.print(funcion + " - ");
            }
            System.out.println("\n-------------------------------------");
            i++;
        }
    }
//añadi n
    public String generarIdBoleto(){
        Random random = new Random();
        LocalDate fecha= LocalDate.now();
        int anoActual=fecha.getYear();
        int mesActual= fecha.getMonthValue();
        int numeroAleatorio= random.nextInt(10,1000);
        String id=String.format("T%d%d%d",anoActual,mesActual,numeroAleatorio);
        return id;
    }
    public String buscarNombreClientePorId(String id) {
        for ( Cliente cliente : listaClientes) {
            if (cliente.getId().equals(id)) {
                return cliente.getNombre();
            }
        }
        return "El id es incorrecto";
    }
    public String buscarTituloPeliculaPorId(String id) {
        for ( Pelicula pelicula : listaPeliculas) {
            if (pelicula.getId().equals(id)) {
               return pelicula.getTitulo();
            }
        }
        return "El id es incorrecto";
    }


    public  void inicializarAsientos() {
        String[] filas = {"A", "B", "C", "D", "E", "F"};
        TipoAsiento tipo;
        EstadoAsiento estado;
        for (String fila : filas) {
            for (int i = 1; i <= 6; i++) {
                String id = fila + i;
                tipo= TipoAsiento.NORMAL;
                Asiento asiento = new Asiento(tipo);
                asiento.Id = id; // Asignar el ID al asiento
                asiento.setEstado(EstadoAsiento.DISPONIBLE);
                this.listaAsientos.add(asiento); //Agg lista:)
            }
        }
    }

    public Asiento buscarAsiento(String id) {
        for (Asiento asiento : listaAsientos) {
            if (asiento.getId().equalsIgnoreCase(id)) {
                return asiento;
            }
        }
        return null;
    }

    public int SeleccionarAsiento(String idAsiento) {
        inicializarAsientos();
        Asiento asientoSeleccionado = buscarAsiento(idAsiento);
        if (asientoSeleccionado != null) {

            if (asientoSeleccionado.getEstado() == EstadoAsiento.DISPONIBLE) {
                System.out.println("El asiento " + idAsiento + " está disponible.");
                asientoSeleccionado.setEstado(EstadoAsiento.RESERVADO);
                System.out.println("Has reservado el asiento " + idAsiento);
                return 1;
            } else {
                System.out.println("Lo sentimos, el asiento " + idAsiento + " está " + asientoSeleccionado.getEstado());
                return 2; //--Vuelva a elegir
            }
        } else {
            System.out.println("Asiento no encontrado. Por favor, verifique el ID.");
            return 2;//vuelva a elegir
        }
    }
    public void registrarBoleto(Boleto boleto){
        this.listaBoletos.add(boleto);
    }
    public void mostrarBoletosTodos(){
        System.out.println("\n BOLETOS VENDIDOS");
        for(Boleto boleto : this.listaBoletos){
            System.out.println(boleto.mostrarInformacion());
        }
    }
   //añadi n
}