package cartelera;

import java.time.LocalTime;
import java.util.ArrayList;

public class Cartelera {
    public ArrayList<Pelicula> listaPeliculas = new ArrayList<>();

    public void mostrarCartelera() {
        for (Pelicula pelicula : listaPeliculas) {
            System.out.println(String.format("Titulo: %s, Clasificación: %s, Duración: %d",
                    pelicula.getTitulo(), pelicula.getClasificacion(), pelicula.getDuracion())
            );
            for (LocalTime funcion : pelicula.getHorario()) {
                System.out.print(funcion + " ") ;
            }
            System.out.println();
        }
    }
}
