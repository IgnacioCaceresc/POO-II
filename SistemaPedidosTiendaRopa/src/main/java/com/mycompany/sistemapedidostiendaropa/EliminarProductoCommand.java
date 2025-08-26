package com.mycompany.sistemapedidostiendaropa;

public class EliminarProductoCommand implements Command {

    private CarritoCompras carrito;
    private String producto;

    public EliminarProductoCommand(CarritoCompras carrito, String producto) {
        this.carrito = carrito;
        this.producto = producto;
    }

    @Override
    public void ejecutar() {

        carrito.eliminarProducto(producto);
    }
}
