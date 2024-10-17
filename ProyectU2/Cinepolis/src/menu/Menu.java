package menu;

import java.util.InputMismatchException;

import cartelera.Pelicula;
import cine.Cine;
import dulceria.Inventario;
import dulceria.Producto;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;
import utils.EstadoPelicula;

import java.time.LocalTime;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final Cine cine = new Cine();

    //-------------- Métodos para mostrar -----------------
    private void mostrarMenuAdmin(Administrador admin) {
        int respuesta = 0;

        while (respuesta != 12) {
            System.out.println("Buen dia " + admin.nombre + "-" + admin.apellido);
            System.out.println("""
                    1.-Registrar una pelicula
                    2.-Eliminar una pelicula
                    3.-Actualizar una pelicula
                    4.-Mostrar dulceria
                    5.-Registrar empleado
                    6.-Agregar producto a dulceria
                    7.-Eliminar producto a dulceria
                    12.-Salir""");
            System.out.print("Elija una opción: ");
            respuesta = scanner.nextInt();

            switch (respuesta) {
                case 1:
                    System.out.println("Registro de una pelicula");
                    String id = cine.generarIdPelicula();
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

                    cine.registrarPelicula(pelicula);
                    System.out.println("Registro Exitoso");
                    break;
                case 2:
                    System.out.println("---Eliminar una pelicula---");
                    break;
                case 3:
                    System.out.println("---Actualizar una pelicula---");
                    for (Pelicula pelicula1 : cine.listaPeliculas) {
                        System.out.println("Titulo: " + pelicula1.titulo + "Id: " + pelicula1.id);
                    }
                    ;
                    System.out.println("Ingrese el id de la pelicula que desea actualizar: ");
                    String idPelicula = scanner.nextLine();
                    cine.actualizarDatosPelicula(idPelicula);
                    break;
                case 4:
                    System.out.println("---Dulceria---");
                    break;
                case 5:
                    System.out.println("---Registrar empleado---");
                    break;
                case 6:
                    Inventario inventario = new Inventario();
                    respuesta = 0;
                    boolean continuar;

                    while (respuesta != 2) {
                        System.out.println("---Registrar un producto---");

                        System.out.println("Nombre del producto:");
                        String nombreProducto = scanner.nextLine();

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
                case 7:
                    inventario = new Inventario();
                    System.out.println("\n---Eliminar un producto---\n");

                    System.out.println("Ingresa el nombre del producto:");
                    String nombreProducto = scanner.nextLine();

                    inventario.eliminarProducto(nombreProducto);

                    break;
                case 12:
                    System.out.println("\n-----Adiosito-----\n");
                    return;
            }
        }
        scanner.close();

    }

    public void mostrarMenuCliente(Cliente cliente) {
        int respuesta = 0;

        while (respuesta != 12) {
            System.out.println("Buen dia " + cliente.nombre);
            System.out.println("""
                    1.-Mostrar cartelera
                    2.-Mostrar dulceria
                    3.-Elegir pelicula
                    4.-""");
        }
    }
}
