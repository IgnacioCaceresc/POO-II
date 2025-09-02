package com.mycompany.sistemapedidoropas3.decorator;

public class ProductoBase implements Component {

    private String nombre;
    private double precio;

    public ProductoBase(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return this.precio;
    }

    @Override
    public String getDescripcion() {
        return this.nombre;
    }
}
