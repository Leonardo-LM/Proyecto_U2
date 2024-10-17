package dulceria;

import java.util.ArrayList;

public class Inventario {
    public ArrayList<Producto> listaProductos = new ArrayList<Producto>();


    public void registrarProducto(Producto producto) {
        this.listaProductos.add(producto);
    }


    public void eliminarProducto(String nombreProducto) {
        int longitudOriginal = this.listaProductos.size(); // 3

        this.listaProductos.removeIf((producto) -> producto.getNombre() == nombreProducto);

        if (longitudOriginal != this.listaProductos.size()) {
            System.out.println("Se eliminó el prodcuto con el ID: " + nombreProducto);
        } else {
            System.out.println("No existe un producto con el ID: " + nombreProducto);
        }
    }

    public void mostrarProductos() {
        System.out.println("\n*** PRODUCTOS EN EL SISTEMA ***");

        if (this.listaProductos.size() == 0) {
            System.out.println("\nNo existen productos en el sistema");
            return;
        }

        int iterador = 1;
        for (Producto producto : this.listaProductos) {
            System.out.println("\nEste es el producto: " + iterador);
            System.out.println(producto.mostrarProducto());
            iterador++;
        }
    }


}


