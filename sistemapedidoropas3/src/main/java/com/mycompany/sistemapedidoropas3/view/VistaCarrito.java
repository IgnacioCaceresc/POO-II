package com.mycompany.sistemapedidoropas3.view;

import java.util.List;
import com.mycompany.sistemapedidoropas3.model.*;

public class VistaCarrito {

    public void mostrarCarrito(List<Producto> itemsCarrito, double total) {
        System.out.println("=== CARRITO DE COMPRAS ===");
        for (Producto item : itemsCarrito) {
            System.out.println("- " + item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total: $" + total);
    }

    public void mostrarDescuentoAplicado(String message) {
        System.out.println("Descuento aplicado: " + message);
    }
}
