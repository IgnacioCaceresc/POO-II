package com.mycompany.sistemapedidostiendaropa;

public abstract class DescuentoDecorator implements Component {

    protected Component componenteDecorado;

    public DescuentoDecorator(Component componenteDecorado) {
        this.componenteDecorado = componenteDecorado;
    }

    @Override
    public double getPrecio() {
        return componenteDecorado.getPrecio();
    }

    @Override
    public String getDescripcion() {
        return componenteDecorado.getDescripcion();
    }
}
