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
    }
}
