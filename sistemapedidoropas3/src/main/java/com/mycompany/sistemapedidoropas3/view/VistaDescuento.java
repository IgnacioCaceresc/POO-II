package com.mycompany.sistemapedidoropas3.view;

public class VistaDescuento {

    public void mostrarDescuentosDisponibles() {
        System.out.println("=== DESCUENTOS DISPONIBLES ===");
        System.out.println("1. 10% de descuento general");
        System.out.println("2. 20% de descuento en categoria especifica");
        System.out.println("3. Descuento fijo de $15,000");
    }

    public void mostrarResultadoDescuento(String nombreProducto, double precioOriginal, double precioConDescuento) {
        System.out.println("Producto: " + nombreProducto);
        System.out.println("Precio original: $" + precioOriginal);
        System.out.println("Precio con descuento: $" + precioConDescuento);
        System.out.println("Ahorro: $" + (precioOriginal - precioConDescuento));
    }
}
