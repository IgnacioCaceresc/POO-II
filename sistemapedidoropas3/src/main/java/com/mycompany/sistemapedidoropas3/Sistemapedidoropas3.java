package com.mycompany.sistemapedidoropas3;

import com.mycompany.sistemapedidoropas3.model.*;
import com.mycompany.sistemapedidoropas3.view.*;
import com.mycompany.sistemapedidoropas3.controller.*;
import com.mycompany.sistemapedidoropas3.commands.*;
import com.mycompany.sistemapedidoropas3.decorator.*;

public class Sistemapedidoropas3 {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PEDIDOS TIENDA DE ROPA ===\n");

        VistaProducto vistaProducto = new VistaProducto();
        VistaCarrito vistaCarrito = new VistaCarrito();
        VistaDescuento vistaDescuento = new VistaDescuento();

        ProductoController productoController = new ProductoController(vistaProducto);
        CarritoComprasController carritoController = new CarritoComprasController(vistaCarrito);
        DescuentoController descuentoController = new DescuentoController(vistaDescuento);

        System.out.println(" Sistema MVC inicializado correctamente\n");

        System.out.println("=== DEMOSTRACION DEL PATRON SINGLETON ===");

        DiscountManager gestor1 = DiscountManager.obtenerInstancia();
        System.out.println("Gestor 1 - Hash: " + gestor1.hashCode());

        DiscountManager gestor2 = DiscountManager.obtenerInstancia();
        System.out.println("Gestor 2 - Hash: " + gestor2.hashCode());

        if (gestor1 == gestor2) {
            System.out.println(" Singleton funciona correctamente - Una sola instancia\n");
        } else {
            System.out.println(" Error en Singleton - Multiples instancias\n");
        }


        System.out.println("--- Catalogo de Productos ---");
        productoController.mostrarTodosLosProductos();

        Producto camiseta = productoController.getProductoPorId("P001");
        Producto pantalon = productoController.getProductoPorId("P002");
        Producto zapatos = productoController.getProductoPorId("P003");

        if (camiseta != null) {
            System.out.println("\n--- Detalle del Producto ---");
            vistaProducto.mostrarDetalleProductos(camiseta);
        }

        System.out.println("\n--- Descuentos Disponibles ---");
        descuentoController.mostrarDescuentosDisponibles();

        System.out.println();

        System.out.println("=== DEMOSTRACION DEL PATRON DECORATOR ===");

        System.out.println("--- Aplicando Descuento Porcentual ---");
        descuentoController.aplicarPorcentajeDescuento(camiseta, 10);

        System.out.println("\n--- Aplicando Descuento por Categoria ---");
        descuentoController.aplicarCategoriaDescuento(pantalon, "Pantalones", 20);

        System.out.println("\n--- Aplicando Descuento Fijo ---");
        descuentoController.aplicarDescuentoFijo(zapatos, 15000);

        System.out.println("\n--- Decoradores Combinados ---");
        Component productoBase = new ProductoBase(camiseta.getName(), camiseta.getPrice());
        Component conDescuento1 = new DescuentoPorcentaje(productoBase, 10);
        Component conDescuento2 = new DescuentoFijo(conDescuento1, 5000);

        System.out.println("Producto original: " + productoBase.getDescripcion() + " - $" + productoBase.getPrecio());
        System.out.println("Con descuentos combinados: " + conDescuento2.getDescripcion() + " - $" + conDescuento2.getPrecio());

        System.out.println();

        System.out.println("=== DEMOSTRACION DEL PATRON COMMAND ===");

        Command agregarCamiseta = new AgregarProductoCommand(carritoController, camiseta);
        Command agregarPantalon = new AgregarProductoCommand(carritoController, pantalon);
        Command eliminarCamiseta = new EliminarProductoCommand(carritoController, camiseta);
        Command agregarZapatos = new AgregarProductoCommand(carritoController, zapatos);

        System.out.println("--- Ejecutando Comandos ---");

        carritoController.ejecutarComando(agregarCamiseta);
        carritoController.ejecutarComando(agregarPantalon);
        carritoController.ejecutarComando(agregarZapatos);

        System.out.println("\n--- Estado del Carrito ---");
        carritoController.mostrarCarrito();

        carritoController.ejecutarComando(eliminarCamiseta);

        System.out.println("\n--- Carrito despues de eliminar ---");
        carritoController.mostrarCarrito();

        System.out.println("\n=== DEMOSTRACION INTEGRADA ===");

        System.out.println("--- Aplicando Descuento al Carrito Completo ---");
        carritoController.aplicarDescuentoCarrito(15);

        Usuario usuario = new Usuario("USR001", "Juan Perez", "juan@email.com");
        System.out.println("\n--- Usuario Creado ---");
        System.out.println("Usuario: " + usuario.getName() + " (" + usuario.getEmail() + ")");

    }
}
