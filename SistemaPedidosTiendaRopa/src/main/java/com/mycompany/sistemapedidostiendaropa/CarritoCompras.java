package com.mycompany.sistemapedidostiendaropa;

public class CarritoCompras {

    private String nombreUsuario;

    public CarritoCompras(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void agregarProducto(String producto) {
        System.out.println(nombreUsuario + ": Agregando '" + producto + "'al carrito.");

    }

    public void eliminarProducto(String producto) {
        System.out.println(nombreUsuario + ": Eliminando '" + producto
                + "' del carrito.");

    }
}
