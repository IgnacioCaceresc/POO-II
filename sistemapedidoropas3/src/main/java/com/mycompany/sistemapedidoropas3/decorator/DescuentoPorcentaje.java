package com.mycompany.sistemapedidoropas3.decorator;

public class DescuentoPorcentaje extends DescuentoDecorator {

    private double porcentaje;

    public DescuentoPorcentaje(Component componenteDecorado, double porcentaje) {
        super(componenteDecorado);
        this.porcentaje = porcentaje;
    }

    @Override
    public double getPrecio() {
        return componenteDecorado.getPrecio() * (1 - porcentaje / 100);
    }

    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion() + " (Descuento " + porcentaje + "%)";
    }
}
