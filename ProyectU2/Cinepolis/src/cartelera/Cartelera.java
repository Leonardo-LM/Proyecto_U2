package cartelera;

import salas.Sala;

import java.time.LocalTime;
import java.util.ArrayList;

public class Cartelera {
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();
    public Sala sala;

    public void mostrarCartelera() {
        int i = 1;
        System.out.println("=====================================");
        System.out.println("               CARTELERA             ");
        System.out.println("=====================================");
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(i + ". " + String.format("Titulo: %s", sala.peliculas.get(i-1).getTitulo()));
            System.out.println("   Clasificación: " + sala.peliculas.get(i-1).getClasificacion());
            System.out.println("   Duración: " + sala.peliculas.get(i-1).getDuracion() + " min");
            System.out.print("   Horarios: ");
            for (Pelicula pelicula1 : sala.peliculas){
                System.out.println();
            }
            System.out.println("\n-------------------------------------");
            i++;
        }
    }
}
