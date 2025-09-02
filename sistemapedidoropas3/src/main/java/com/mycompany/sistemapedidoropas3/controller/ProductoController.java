package com.mycompany.sistemapedidoropas3.controller;

import com.mycompany.sistemapedidoropas3.model.*;
import com.mycompany.sistemapedidoropas3.view.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoController {

    private List<Producto> productos;
    private VistaProducto vistaProducto;

    public ProductoController(VistaProducto vistaProducto) {
        this.vistaProducto = vistaProducto;
        this.productos = new ArrayList<>();
       
        productos.add(new Producto("P001", "Camiseta Polo", 63990.0, "Camisetas"));
        productos.add(new Producto("P002", "Pantalon Cargo", 39990.0, "Pantalones"));
        productos.add(new Producto("P003", "Zapatos de seguridad", 49990.0, "Calzado"));
        productos.add(new Producto("P004", "Lentes de sol", 75000.0, "Accesorios"));
    }

    public void mostrarTodosLosProductos() {
        StringBuilder infoProductos = new StringBuilder();
        for (Producto producto : productos) {
            infoProductos.append(producto.getName())
                    .append(" - $")
                    .append(producto.getPrice())
                    .append(" (")
                    .append(producto.getCategory())
                    .append(")\n");
        }
        vistaProducto.mostrarProductos(infoProductos.toString());
    }

    public Producto getProductoPorId(String productoId) {
        for (Producto producto : productos) {
            if (producto.getProductId().equals(productoId)) {
                return producto;
            }
        }
        return null;
    }
}
