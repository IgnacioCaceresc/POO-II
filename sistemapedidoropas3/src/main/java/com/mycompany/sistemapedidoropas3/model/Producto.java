package com.mycompany.sistemapedidoropas3.model;

public class Producto {

    private String productoId;
    private String nombre;
    private double precio;
    private String categoria;

    public Producto(String productoId, String nombre, double precio, String categoria) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getProductId() {
        return productoId;
    }

    public String getName() {
        return nombre;
    }

    public double getPrice() {
        return precio;
    }

    public String getCategory() {
        return categoria;
    }
}
