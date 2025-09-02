package com.mycompany.sistemapedidoropas3.controller;

import com.mycompany.sistemapedidoropas3.model.*;
import com.mycompany.sistemapedidoropas3.view.*;


public class DescuentoController {
    private VistaDescuento vistaDescuento;
    private DiscountManager discountManager;
    
    public DescuentoController(VistaDescuento vistaDescuento) {
        this.vistaDescuento = vistaDescuento;
        this.discountManager = DiscountManager.obtenerInstancia();
    }
    
    public void mostrarDescuentosDisponibles() {
        vistaDescuento.mostrarDescuentosDisponibles();
    }
    
    public void aplicarPorcentajeDescuento(Producto producto, double porcentaje) {
        double precioOriginal = producto.getPrice();
        double precioDescontado = discountManager.aplicarPorcentajeDescuento(producto, porcentaje);
        vistaDescuento.mostrarResultadoDescuento(producto.getName(), precioOriginal, precioDescontado);
    }
    
    public void aplicarCategoriaDescuento(Producto producto, String categoria, double porcentaje) {
        double precioOriginal = producto.getPrice();
        double precioDescontado = discountManager.aplicarCategoriaDescuento(producto, categoria, porcentaje);
        vistaDescuento.mostrarResultadoDescuento(producto.getName(), precioOriginal, precioDescontado);
    }
    
    public void aplicarDescuentoFijo(Producto producto, double montoDescuento) { 
        double precioOriginal = producto.getPrice();
        double precioDescontado = discountManager.aplicarDescuentoFijo(producto, montoDescuento);
        vistaDescuento.mostrarResultadoDescuento(producto.getName(), precioOriginal, precioDescontado);
    }
}