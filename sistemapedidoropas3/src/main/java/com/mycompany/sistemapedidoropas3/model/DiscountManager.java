package com.mycompany.sistemapedidoropas3.model;

import com.mycompany.sistemapedidoropas3.decorator.*;

public class DiscountManager {

    private static DiscountManager instanciaUnica;

    private DiscountManager() {
        System.out.println("Configurando el gestor de descuentos...");
    }

    public static DiscountManager obtenerInstancia() {
        if (instanciaUnica == null) {
            synchronized (DiscountManager.class) {
                if (instanciaUnica == null) {
                    instanciaUnica = new DiscountManager();
                }
            }
        }
        return instanciaUnica;
    }

    public double aplicarPorcentajeDescuento(Producto producto, double porcentaje) {
        Component productoBase = new ProductoBase(producto.getName(), producto.getPrice());
        Component productoConDescuento = new DescuentoPorcentaje(productoBase, porcentaje);
        return productoConDescuento.getPrecio();
    }

    public double aplicarCategoriaDescuento(Producto producto, String categoria, double porcentaje) {
        if (producto.getCategory().equals(categoria)) { 
            Component productoBase = new ProductoBase(producto.getName(), producto.getPrice());
            Component productoConDescuento = new DescuentoPorcentaje(productoBase, porcentaje);
            return productoConDescuento.getPrecio();
        }
        return producto.getPrice();
    }

    public double aplicarDescuentoFijo(Producto producto, double montoDescuento) { 
        Component productoBase = new ProductoBase(producto.getName(), producto.getPrice());
        Component productoConDescuento = new DescuentoFijo(productoBase, montoDescuento);
        return productoConDescuento.getPrecio();
    }
}
