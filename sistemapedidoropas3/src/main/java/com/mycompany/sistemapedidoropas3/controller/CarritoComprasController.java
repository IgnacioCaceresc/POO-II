package com.mycompany.sistemapedidoropas3.controller;

import com.mycompany.sistemapedidoropas3.model.*;
import com.mycompany.sistemapedidoropas3.view.*;
import com.mycompany.sistemapedidoropas3.commands.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoComprasController {

    private List<Producto> itemsCarrito;
    private VistaCarrito vistaCarrito;
    private DiscountManager discountManager;
    private List<Command> historialComandos; 

    public CarritoComprasController(VistaCarrito vistaCarrito) {
        this.vistaCarrito = vistaCarrito;
        this.itemsCarrito = new ArrayList<>();
        this.discountManager = DiscountManager.obtenerInstancia();
        this.historialComandos = new ArrayList<>(); 
    }


    public void ejecutarComando(Command comando) {
        if (comando != null) {
            comando.ejecutar();
            historialComandos.add(comando);
        }
    }

    public void agregarCarrito(Producto product) {
        itemsCarrito.add(product);
        System.out.println("Producto agregado: " + product.getName());
        mostrarCarrito();
    }

    public void removerCarrito(Producto product) {
        itemsCarrito.remove(product);
        System.out.println("Producto eliminado: " + product.getName());
        mostrarCarrito();
    }

    public void mostrarCarrito() {
        double total = calcularTotal();
        vistaCarrito.mostrarCarrito(itemsCarrito, total);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto item : itemsCarrito) {
            total += item.getPrice();
        }
        return total;
    }

    public void aplicarDescuentoCarrito(double porcentajeDescuento) {
        double total = calcularTotal();
        double descuentoTotal = total * (1 - porcentajeDescuento / 100);
        vistaCarrito.mostrarDescuentoAplicado("Descuento del " + porcentajeDescuento + "% aplicado. Nuevo total: $" + descuentoTotal);
    }
}
