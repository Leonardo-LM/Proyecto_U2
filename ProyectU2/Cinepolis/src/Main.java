import cine.Cine;
import menu.Menu;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        //menu.login();
        Cine cine = new Cine();
        cine.mostrarAsientos();
        cine.registrarPelicula();
        cine.mostrarCartelera();
    }
}