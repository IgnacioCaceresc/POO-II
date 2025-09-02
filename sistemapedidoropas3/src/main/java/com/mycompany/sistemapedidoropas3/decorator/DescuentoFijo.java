
package com.mycompany.sistemapedidoropas3.decorator;


public class DescuentoFijo extends DescuentoDecorator {
    private double cantidadDescuento;

    public DescuentoFijo(Component componenteDecorado, double cantidadDescuento) {
        super(componenteDecorado);
        this.cantidadDescuento = cantidadDescuento;
    }

    @Override
    public double getPrecio() {
        double precioFinal = componenteDecorado.getPrecio() - cantidadDescuento;
        return Math.max(precioFinal, 0);
    }

    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion() + " (Descuento Fijo $" + cantidadDescuento + ")";
    }
}