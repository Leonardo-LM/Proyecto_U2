
import cine.Cine;
import menu.Menu;
import usuarios.administrador.Administrador;
import utils.Rol;
//Rol y usuario  importandos para probar al igual que la creacion de un admin

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        Administrador admin = new Administrador("1", "Admin", "Apellido", "1234567890", "contrasenia", Rol.ADMINISTRADOR);

        //menu.login();
        Cine cine = new Cine();
        cine.mostrarAsientos();
        //cine.registrarPelicula();
        cine.mostrarCartelera();
        menu.mostrarMenuAdmin(admin);
    }
}