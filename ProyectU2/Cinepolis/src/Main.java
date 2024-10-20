import cine.Cine;
import menu.Menu;
import usuarios.cliente.Cliente;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        LocalDate date = LocalDate.now();
        Cine cine = new Cine();

        //menu.login();
        Cliente cliente = new Cliente("1","1", "1", "1", "1", date, "1", "q");
        //menu.mostrarMenuCliente(cliente);

        menu.mostrarMenuAdmin(cine.administradorPredeterminado);
    }
}