package com.mycompany.sistemapedidostiendaropa;

public class AgregarProductoCommand implements Command {

    private CarritoCompras carrito;
    private String producto;

    public AgregarProductoCommand(CarritoCompras carrito, String producto) {
        this.carrito = carrito;
        this.producto = producto;
    }

    @Override
    public void ejecutar() {

        carrito.agregarProducto(producto);
    }
}
