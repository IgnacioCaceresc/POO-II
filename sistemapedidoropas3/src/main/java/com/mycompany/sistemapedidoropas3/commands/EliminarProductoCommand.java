package com.mycompany.sistemapedidoropas3.commands;

import com.mycompany.sistemapedidoropas3.controller.*;
import com.mycompany.sistemapedidoropas3.model.*;

public class EliminarProductoCommand implements Command {

    private CarritoComprasController carritoController;
    private Producto producto;

    public EliminarProductoCommand(CarritoComprasController carritoController, Producto producto) {
        this.carritoController = carritoController;
        this.producto = producto;
    }

    @Override
    public void ejecutar() {
        carritoController.removerCarrito(producto);
    }
}
