package com.mycompany.sistemapedidostiendaropa;

public class DescuentoFijo extends DescuentoDecorator {

    private double cantidadDescuento;

    public DescuentoFijo(Component componenteDecorado, double cantidadDescuento) {
        super(componenteDecorado);
        this.cantidadDescuento = cantidadDescuento;
    }

    @Override
    public double getPrecio() {

        double precioFinal = componenteDecorado.getPrecio()
                - cantidadDescuento;
        return (precioFinal < 0) ? 0 : precioFinal; 
    }

    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion() + " (Descuento Fijo $"
                + cantidadDescuento + ")";
    }
}
