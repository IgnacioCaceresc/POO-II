package com.mycompany.sistemapedidoropas3.view;

import com.mycompany.sistemapedidoropas3.model.*;

public class VistaProducto {

    public void mostrarProductos(String infoproductos) {
        System.out.println("=== PRODUCTOS DISPONIBLES ===");
        System.out.println(infoproductos);
    }

    public void mostrarDetalleProductos(Producto producto) {
        System.out.println("Detalles del producto:");
        System.out.println("Nombre: " + producto.getName());
        System.out.println("Precio: $" + producto.getPrice());
        System.out.println("Categoria: " + producto.getCategory());
    }
}
