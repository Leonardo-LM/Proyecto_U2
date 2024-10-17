package menu;

import cartelera.Pelicula;
import cine.Cine;
import salas.Sala;
import usuarios.administrador.Administrador;
import usuarios.cliente.Cliente;

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
                    4.-Dulceria
                    5.-Registrar empleado
                    6.-
                    8.-Asignar pelicula a sala
                    
                    12.-Salir""");
            System.out.print("Elija una opción: ");
            respuesta = scanner.nextInt();

            switch (respuesta) {
                case 1:
                    cine.registrarPelicula();
                    break;
                case 2:
                    System.out.println("---Eliminar una pelicula---");
                    this.mostrarPeliculasConId();
                    System.out.println("Ingrese el id de la pelicula que desea eliminar: ");
                    String idPeliculaE = scanner.nextLine();
                    cine.eliminarPelicula(idPeliculaE);
                    break;
                case 3:
                    System.out.println("---Actualizar una pelicula---");
                    this.mostrarPeliculasConId();
                    System.out.println("Ingrese el id de la pelicula que desea actualizar: ");
                    String idPeliculaA = scanner.nextLine();
                    cine.actualizarDatosPelicula(idPeliculaA);
                    break;
                case 4:
                    System.out.println("---Dulceria---");
                    break;
                case 5:
                    System.out.println("---Registrar empleado---");
                    break;
                case 6:
                    break;
                case 8:
                    System.out.println("---Asignar pelicula a sala---");
                    this.mostrarPeliculasConId();
                    for (Sala sala : cine.listaSalas){
                        System.out.println();
                    }
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
    public void mostrarPeliculasConId(){
        for (Pelicula pelicula : cine.listaPeliculas){
            System.out.println("Titulo: "+pelicula.titulo + "Id: " + pelicula.id);
        }
    }
}
