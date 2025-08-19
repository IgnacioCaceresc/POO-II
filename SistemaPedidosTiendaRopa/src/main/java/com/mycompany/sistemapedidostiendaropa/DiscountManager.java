package com.mycompany.sistemapedidostiendaropa;

public class DiscountManager {

    private static DiscountManager instanciaUnica;

    private DiscountManager() {
        System.out.println("Configurando el gestor de descuentos...");
    }

    public static DiscountManager obtenerInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new DiscountManager();
        }
        return instanciaUnica;
    }

    public void aplicarDescuento(String articulo, double precioBase) {
        System.out.println("Procesando descuento para: " + articulo
                + " | Valor inicial: $" + precioBase);

        double precioFinal = precioBase * 0.9;
        System.out.println("Precio promocional: $" + precioFinal);
    }
}
