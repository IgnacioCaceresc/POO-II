package com.mycompany.gestioninventariojava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inventario {

    private HashMap<String, Producto> productos;

    public Inventario() {
        productos = new HashMap<>();
    }

    public boolean agregarProducto(Producto producto) {

        if (producto == null || producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()) {
            return false;
        }

        if (productos.containsKey(producto.getCodigo())) {
            return false;
        }

        productos.put(producto.getCodigo(), producto);
        return true;
    }

    public boolean eliminarProducto(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return false;
        }

        return productos.remove(codigo) != null;
    }

    public Producto buscarPorCodigo(String codigo) {

        if (codigo == null || codigo.trim().isEmpty()) {
            return null;
        }

        return productos.get(codigo);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        List<Producto> resultados = new ArrayList<>();

        if (nombre == null || nombre.trim().isEmpty()) {
            return resultados;
        }

        String nombreBusqueda = nombre.toLowerCase();
        for (Producto producto : productos.values()) {
            if (producto.getNombre().toLowerCase().contains(nombreBusqueda)) {
                resultados.add(producto);
            }
        }
        return resultados;
    }

    public List<Producto> buscarPorDescripcion(String descripcion) {
        List<Producto> resultados = new ArrayList<>();

        if (descripcion == null || descripcion.trim().isEmpty()) {
            return resultados;
        }

        String descBusqueda = descripcion.toLowerCase();
        for (Producto producto : productos.values()) {
            if (producto.getDescripcion().toLowerCase().contains(descBusqueda)) {
                resultados.add(producto);
            }
        }
        return resultados;
    }

    public List<Producto> listarTodos() {
        return new ArrayList<>(productos.values());
    }

    public boolean actualizarProducto(String codigo, String nombre, String descripcion, double precio, int stock) {
        Producto producto = buscarPorCodigo(codigo);
        if (producto != null) {
            if (nombre != null) {
                producto.setNombre(nombre);
            }
            if (descripcion != null) {
                producto.setDescripcion(descripcion);
            }
            if (precio >= 0) {
                producto.setPrecio(precio);
            }
            if (stock >= 0) {
                producto.setCantidadStock(stock);
            }
            return true;
        }
        return false;
    }
}
