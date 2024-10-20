package menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

import compra.boleto.Boleto;
import cartelera.Pelicula;
import cine.Cine;
import dulceria.Inventario;
import dulceria.Producto;
import salas.Sala;
import usuarios.Usuario;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;
import usuarios.empleados.Empleado;
import utils.Rol;
import utils.TipoAsiento;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Cine cine = new Cine();
    private final Inventario inventario = new Inventario();
    public Sala sala;

    public void login() {

        int intesntosMax = 5, intentosUsuario = 0;

        //Para empezar con el menu del admin
        this.mostrarMenuAdmin(cine.administradorPredeterminado);

        while (intentosUsuario < intesntosMax) {
            System.out.print("\n--------Bienvenido/a--------\n");
            System.out.println("---Inicia sesión para continuar---");

            System.out.println("Ingresa tu usuario: ");
            String usuario = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Ingresa tu contaseña : ");
            String contaseña = scanner.nextLine();

            Usuario usuarioEnSesion = cine.validarInicioSesion(usuario, contaseña);

            if (usuarioEnSesion instanceof Usuario) {

                if (usuarioEnSesion.getRol() == Rol.CLIENTE) {
                    Cliente clienteEnSesion = (Cliente) usuarioEnSesion;
                    this.mostrarMenuCliente(clienteEnSesion);
                    intentosUsuario = 0;
                } else if (usuarioEnSesion.getRol() == Rol.EMPLEADO) {
                    Empleado empleadoEnSesion = (Empleado) usuarioEnSesion;
                } else {
                    Administrador adminEnSesion = (Administrador) usuarioEnSesion;
                    this.mostrarMenuAdmin(adminEnSesion);
                    intentosUsuario = 0;
                }
            } else {
                intentosUsuario = mostrarErrorInicioSesion(intentosUsuario);

            }
        }
        System.out.println("Intentos maximos permitidos ");
    }

    private int mostrarErrorInicioSesion(int intentosUsuario) {
        System.out.println("Usuario o contraseña incorrectos, intenta de nuevo");
        return intentosUsuario + 1;
    }

    //-------------- Métodos para mostrar -----------------

    public void mostrarMenuAdmin(Administrador admin) {
        //movi de private a public para probar
        int respuesta = 0;

        while (respuesta != 14) {
            cine.inicializar(); //necesario para no 10
            System.out.println("Buen dia " + admin.nombre + " " + admin.apellido);
            System.out.println("""
                    1.-Registrar un cliente
                    2.-Registrar una pelicula
                    3.-Eliminar una pelicula
                    4.-Actualizar una pelicula
                    5.-Mostrar dulceria
                    6.-Registrar empleado
                    7.-Agregar producto a dulceria
                    8.-Eliminar producto de dulceria
                    9.-Asignar pelicula a sala
                    10.-Compra de un Boleto
                    11.-Mostrar Boletos
                    12.-Mostrar Clientes
                    13.-Mostrar Peliculas
                    14.-Compra de productos
                    15.-Salir""");
            System.out.print("Elija una opción: ");
            respuesta = scanner.nextInt();

            switch (respuesta) {
                case 1:
                    this.registrarCliente();
                    break;
                case 2:
                    cine.registrarPelicula();
                    break;
                case 3:
                    System.out.println("---Eliminar una pelicula---");
                    this.mostrarListaPeliculas();
                    scanner.nextLine();
                    System.out.println("Ingrese el id de la pelicula que desea eliminar: ");
                    String idPeliculaE = scanner.nextLine();
                    cine.eliminarPelicula(idPeliculaE);
                    break;
                case 4:
                    System.out.println("---Actualizar una pelicula---");
                    this.mostrarListaPeliculas();
                    System.out.println("Ingrese el id de la pelicula que desea actualizar: ");
                    String idPeliculaA = scanner.nextLine();
                    cine.actualizarDatosPelicula(idPeliculaA);
                    break;
                case 5:
                    System.out.println("---Dulceria---");
                    inventario.mostrarProductos();
                    break;
                case 6:
                    System.out.println("---Registrar empleado---");
                    this.registrarEmpleado();

                    break;
                case 7:
                    Inventario inventario = new Inventario();
                    respuesta = 0;
                    boolean continuar;

                    while (respuesta != 2) {
                        System.out.println("---Registrar un producto---");


                        System.out.println("Nombre del producto:");
                        String nombreProducto = scanner.nextLine();

                        scanner.nextLine();
                        System.out.println("Precio del producto:");
                        Double precioProducto = scanner.nextDouble();

                        Producto producto = new Producto(nombreProducto, precioProducto);
                        inventario.registrarProducto(producto);
                        do {
                            try {
                                continuar = false;
                                System.out.println("""
                                        Desea agregar otro producto?
                                        1-Si
                                        2-No""");
                                respuesta = scanner.nextInt();
                            } catch (InputMismatchException ex) {
                                System.out.println("Debe ingresar obligatoriamente un número entero");
                                scanner.nextLine();
                                continuar = true;
                            }

                        } while (continuar);
                    }
                    break;
                case 8:
                    inventario = new Inventario();
                    System.out.println("\n---Eliminar un producto---");

                    scanner.nextLine();
                    System.out.println("Ingresa el nombre del producto:");
                    String nombreProducto = scanner.nextLine();

                    inventario.eliminarProducto(nombreProducto);

                    break;
                case 9:
                    this.asignarPeliculaASala();
                    break;
                case 10:

                    System.out.println("\n---Compra de un boleto---");
                    int mesActual = LocalDate.now().getMonthValue();//para premium o vip
                    cine.mostrarClientesTodos();
                    System.out.println("Ingresa el Id del cliente");
                    scanner.nextLine();
                    String clienteId = scanner.nextLine();
                    System.out.println("ID introducido: '" + clienteId + "'");
                    String nombreCliente = cine.buscarNombreClientePorId(clienteId);
                    System.out.println(nombreCliente);
                    int MesCliente = cine.MesCumpleañosParaVerSiHayDescuento(clienteId);//este al VIP O PREMIUM


                    this.mostrarListaPeliculas();
                    System.out.println("Ingresa el Id de la pelicula que desea el cliente");
                    String peliculaId = scanner.nextLine();
                    System.out.println("ID introducido: '" + peliculaId + "'");
                    String tituloPelicula = cine.buscarTituloPeliculaPorId(peliculaId);


                    System.out.println("Ingresa el Número de la sala que corresponde");
                    int NoSala = scanner.nextInt();

                    cine.mostrarAsientos();
                    System.out.print("Ingresa el ID del asiento a reservar (ejemplo: A1): ");
                    scanner.nextLine();
                    String idAsiento = scanner.nextLine();
                    String resultado = cine.reservarAsiento(idAsiento); //pasamos a obj
                    System.out.println(resultado);

                    System.out.println("Ingresa tipo de asiento escribe PREMIUM O VIP");
                    String tipo = scanner.nextLine();
                    double x = 100;
                    TipoAsiento tipoAsiento;//=TipoAsiento.Normal;
                    if (tipo.equals("PREMIUM")) {
                        if (MesCliente == mesActual) {
                            tipoAsiento = TipoAsiento.PREMIUM;
                            x = 400 * 0.6;
                        } else {
                            x = 400;
                        }
                    } else if (tipo.equals("VIP")) {
                        if (MesCliente == mesActual) {
                            tipoAsiento = TipoAsiento.VIP;
                            x = 200 * 0.35;
                        } else {
                            x = 200;
                        }
                    } else {
                        System.out.println("Palabra incorrecta");
                    }

                    inventario = new Inventario();
                    inventario.mostrarProductos();
                    List<String> articulosExtra = cine.seleccionarArticulos();

                    String idBoleto = cine.generarIdBoleto();//generamos id del compra.boleto
                    Boleto boleto = new Boleto(idBoleto, NoSala, LocalDateTime.now(), tituloPelicula, resultado, tipo, nombreCliente, x, articulosExtra);
                    cine.registrarBoleto(boleto);
                    System.out.println(cine.listaBoletos.get(0));
                    boleto.mostrarInformacion();
                    break;
                case 11:
                    System.out.println("\n---Compra de un boleto---");
                    cine.mostrarBoletosTodos();
                    break;
                case 12:
                    System.out.println("\n--Mostrar Clientes---");
                    cine.mostrarClientesTodos();
                    break;
                case 13:
                    System.out.println("\n--Mostrar Peliculas---");
                    cine.mostrarPeliculasTodas();
                    break;
                case 14:
                    System.out.println("\n-----Adiosito-----\n");
                    return;
            }
        }
        scanner.close();
    }

    //---------Métodos para mostrar datos-------------

    public void mostrarListaPeliculas() {
        for (Pelicula pelicula : cine.listaPeliculas) {
            System.out.println("Titulo: " + pelicula.titulo + "Id: " + pelicula.id);
        }
    }

    public void mostrarIdListaSalas() {
        for (Sala sala : cine.listaSalas) {
            System.out.println("Id" + sala.getId());
        }
    }

    public void registrarCliente() {
        String idCliente = cine.generarIdCliente();

        System.out.println("---Registrar cliente---");

        System.out.println("Ingresa el nombre del cliente: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();

        System.out.println("Ingresa el apellido del cliente: ");
        String apellido = scanner.nextLine();

        System.out.println("Ingresa el numero de telefono: ");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese la contraseña: ");
        String contraseña = scanner.nextLine();

        System.out.println("Ingresa la fehca de nacimiento del cliente:");
        System.out.println("ingresa el año: ");
        int añoNacimiento = scanner.nextInt();
        System.out.println("Ingresa el mes: ");
        int mesNacimiento = scanner.nextInt();
        System.out.println("Ingresa el día: ");
        int diaNacimiento = scanner.nextInt();

        System.out.println("Ingrese la curp del cliente: ");
        String curp = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingresa el correo electronico: ");
        String correo = scanner.nextLine();
        LocalDate fechaNacimiento = LocalDate.of(añoNacimiento, mesNacimiento, diaNacimiento);

        Cliente cliente = new Cliente(idCliente, nombre, apellido, telefono, contraseña, fechaNacimiento, curp, correo);
        //cine.listaClientes.add(cliente);
        cine.registrarCliente(cliente);
        System.out.println("\n Cliente registrado correctamente ");
    }

    public void registrarEmpleado() {
        String idEmpleado = cine.generarIdEmpleado();
        System.out.println("---Registrar empleado---");
        scanner.nextLine();
        System.out.println("Ingresa el nombre del empleado: ");
        String nombre = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingresa el apellido del empleado: ");
        String apellido = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingresa el numero de telefono: ");
        String telefono = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingresa su contraseña: ");
        String contrasenia = scanner.nextLine();
        scanner.nextLine();
        System.out.println("Ingresa su RFC: ");
        String rfc = scanner.nextLine();

        Empleado empleado = new Empleado(idEmpleado, nombre, apellido, telefono, contrasenia, rfc);
        cine.listaEmpleados.add(empleado);
    }

    public void asignarPeliculaASala() {
        boolean band8 = true;

        System.out.println("\n---Asignar pelicula a sala---");
        this.mostrarListaPeliculas();
        this.mostrarIdListaSalas();
        do {
            scanner.nextLine();
            System.out.println("Ingrese el ID de la película para asignarla:");
            String idPeliculaSala = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Ingrese el ID de la sala para asignar la película: ");
            String idSala = scanner.nextLine();

            Optional<Sala> salaEncontrada = cine.listaSalas.stream().filter(
                    sala -> sala.getId().equals(idSala)).findFirst();
            Optional<Pelicula> peliculaEncontrada = cine.listaPeliculas.stream().filter(
                    peliculaE -> peliculaE.getId().equals(idPeliculaSala)).findFirst();

            if (salaEncontrada.isPresent() && peliculaEncontrada.isPresent()) {
                salaEncontrada.get().asignarPeliculaASala(peliculaEncontrada.get());
                System.out.println("Pelicula asignada correctamente");
                band8 = false;
            } else {
                if (!salaEncontrada.isPresent()) {
                    System.out.println("Sala no encontrada.");
                }
                if (!peliculaEncontrada.isPresent()) {
                    System.out.println("Película no encontrada.");
                }
                System.out.println("\n¿Volver a intentarlo? s/n");
                if (!scanner.nextLine().equalsIgnoreCase("s")) {
                    band8 = false;
                }
            }
        } while (band8);
    }

    public void mostrarMenuCliente(Cliente cliente) {
        int respuesta = 0;

        while (respuesta != 5) {
            System.out.println("Buen dia " + cliente.nombre);
            System.out.println("""
                    1.-Mostrar cartelera
                    2.-Mostrar dulceria
                    3.-Comprar boletos
                    4.-Elegir asientos
                    5.-Salir""");// metodo mostrarAsientos
            respuesta = scanner.nextInt();
            switch (respuesta) {
                case 1:
                    cine.mostrarCartelera();
                    break;
                case 2:
                    inventario.mostrarProductos();
                    break;
                case 3:
                    System.out.println("Comprar boletos");

                    cine.mostrarCartelera();
                    Pelicula peliculaSeleccionada = cine.seleccionarPelicula();
                    cine.mostrarAsientos();
                    List<String> asientosSeleccionados = cine.seleccionarAsientos();
                    inventario.mostrarProductos();
                    List<String> articulosExtra = cine.seleccionarArticulos();

                    Boleto boleto = new Boleto(peliculaSeleccionada, asientosSeleccionados, articulosExtra);
                    boleto.mostrarBoleto();
                    break;
                case 4:
                    cine.mostrarAsientos();
                    System.out.println("¿Que asientos elige? ");
                    String asientos = scanner.nextLine();
                    sala.venderAsiento(asientos);
                    break;
                case 5:
                    return;
            }
        }
    }

}
