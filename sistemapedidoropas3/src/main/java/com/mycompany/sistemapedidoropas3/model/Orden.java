package com.mycompany.sistemapedidoropas3.model;

import java.util.List;

public class Orden {

    private String ordenId;
    private String usuarioId;
    private List<Producto> productos;
    private double total;

    public Orden(String ordenId, String usuarioId, List<Producto> productos, double total) {
        this.ordenId = ordenId;
        this.usuarioId = usuarioId;
        this.productos = productos;
        this.total = total;
    }

    // Getters y setters
    public String getOrderId() {
        return ordenId;
    }

    public String getUserId() {
        return usuarioId;
    }

    public List<Producto> getProducts() {
        return productos;
    }

    public double getTotal() {
        return total;
    }
}
