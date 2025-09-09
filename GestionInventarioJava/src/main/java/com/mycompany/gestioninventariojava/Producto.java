package com.mycompany.gestioninventariojava;


public class Producto {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidadStock;
    

    public Producto(String codigo, String nombre, String descripcion, double precio, int cantidadStock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }
    

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getCantidadStock() { return cantidadStock; }
    
 
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    

    public void actualizarPrecio(double nuevoPrecio) {
        this.precio = nuevoPrecio;
    }
    

    public String getDescripcionDetallada() {
        return String.format("Código: %s | Nombre: %s | Descripción: %s | Precio: $%.2f | Stock: %d",
                codigo, nombre, descripcion, precio, cantidadStock);
    }
    
    @Override
    public String toString() {
        return getDescripcionDetallada();
    }
}