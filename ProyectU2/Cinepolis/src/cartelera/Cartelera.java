package cartelera;

import java.time.LocalTime;
import java.util.ArrayList;

public class Cartelera {
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

    public void mostrarCartelera() {
        int i = 1;
        System.out.println("=====================================");
        System.out.println("               CARTELERA             ");
        System.out.println("=====================================");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(i + ". " + String.format("Titulo: %s", pelicula.getTitulo()));
            System.out.println("   Clasificación: " + pelicula.getClasificacion());
            System.out.println("   Duración: " + pelicula.getDuracion() + " min");
            System.out.print("   Horarios: ");
            for (LocalTime funcion : pelicula.getHorario()) {
                System.out.print(funcion + " ");
            }
            System.out.println("\n-------------------------------------");
            i++;
        }
    }
}
