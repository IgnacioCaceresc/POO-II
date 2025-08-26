package com.mycompany.sistemapedidostiendaropa;

public class SistemaPedidosTiendaRopa {

    public static void main(String[] args) {
        System.out.println("..:: Demostracion del Singleton en el Gestor de Descuentos ::..");
        System.out.println("\nSolicitando la instancia principal del gestor...");
        DiscountManager gestorPrincipal = DiscountManager.obtenerInstancia();
        System.out.println("Identificador unico del gestorPrincipal: "
                + gestorPrincipal.hashCode());
        System.out.println("\nVerificando si se crea una nueva instancia...");
        DiscountManager gestorSecundario = DiscountManager.obtenerInstancia();
        System.out.println("Identificador unico del gestorSecundario: "
                + gestorSecundario.hashCode());
        if (gestorPrincipal == gestorSecundario) {
            System.out.println("\nConfirmado: Solo existe una unica instancia del gestor de descuentos.");
        } else {
            System.out.println("\nAlerta: Se han creado instancias duplicadas del gestor.");
        }
        System.out.println("\nAplicacion de descuentos mediante el gestor:");
        gestorPrincipal.aplicarDescuento("Zapatos de seguridad", 49990.0);
        gestorSecundario.aplicarDescuento("Lentes de sol", 75000.0);

        System.out.println("\n" + "=".repeat(70));
        System.out.println("..:: Demostracion del Patron Decorator ::..");

        Component camiseta = new ProductoBase("Camiseta Polo", 63990.0);
        System.out.println("Producto Original: " + camiseta.getDescripcion()
                + ", Precio: $" + String.format("%.2f", camiseta.getPrecio()));

        Component camisetaConDescuento10PorCiento = new DescuentoPorcentaje(camiseta, 10);
        System.out.println("Despues de 10% Descuento: "
                + camisetaConDescuento10PorCiento.getDescripcion()
                + ", Precio: $" + String.format("%.2f", camisetaConDescuento10PorCiento.getPrecio()));

        Component camisetaConDescuentoDoble = new DescuentoFijo(camisetaConDescuento10PorCiento, 15000.0);
        System.out.println("Despues de Descuento Fijo ($15.000): "
                + camisetaConDescuentoDoble.getDescripcion()
                + ", Precio: $" + String.format("%.2f", camisetaConDescuentoDoble.getPrecio()));

        Component pantalon = new ProductoBase("Pantalon Cargo", 39990.0);
        Component pantalonConDescuento20PorCiento = new DescuentoPorcentaje(pantalon, 20);
        System.out.println("Producto Original: " + pantalon.getDescripcion()
                + ", Precio: $" + String.format("%.2f", pantalon.getPrecio()));
        System.out.println("Despues de 20% Descuento: "
                + pantalonConDescuento20PorCiento.getDescripcion()
                + ", Precio: $" + String.format("%.2f", pantalonConDescuento20PorCiento.getPrecio()));

        System.out.println("\n" + "=".repeat(70));
        System.out.println("..:: Demostracion del Patron Command ::..");

        CarritoCompras miCarrito = new CarritoCompras("Cliente");

        GestorDePedidos gestor = new GestorDePedidos();

        Command agregarCamiseta = new AgregarProductoCommand(miCarrito, "Polera");
        Command agregarPantalon = new AgregarProductoCommand(miCarrito, "Jeans");
        Command eliminarCamiseta = new EliminarProductoCommand(miCarrito, "Polera");
        Command agregarZapatos = new AgregarProductoCommand(miCarrito, "Chalas");

        System.out.println("--- Ejecutando Comandos ---");

        gestor.setCommand(agregarCamiseta);
        gestor.realizarOperacion();

        gestor.setCommand(agregarPantalon);
        gestor.realizarOperacion();

        gestor.setCommand(eliminarCamiseta);
        gestor.realizarOperacion();

        gestor.setCommand(agregarZapatos);
        gestor.realizarOperacion();

        gestor.mostrarHistorial();

        System.out.println("\n" + "=".repeat(70));

    }
}
