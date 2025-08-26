package com.mycompany.sistemapedidostiendaropa;

import java.util.ArrayList;
import java.util.List;

class GestorDePedidos {

    private Command command;
    private List<Command> historialCommands = new ArrayList<>(); // Para posible historial/deshacer
    // Método para configurar el comando a ejecutar

    public void setCommand(Command command) {
        this.command = command;
    }
    // Método para ejecutar el comando actual y guardarlo en el historial

    public void realizarOperacion() {
        if (command != null) {
            command.ejecutar();
            historialCommands.add(command); // Guarda para posibles funcionalidades futuras (deshacer, reintentar)
        } else {
            System.out.println("No se ha configurado ningún comando para ejecutar.");
        }
    }

    public void mostrarHistorial() {
        System.out.println("\n--- Historial de Comandos ---");
        for (int i = 0; i < historialCommands.size(); i++) {
            System.out.println((i + 1) + ". "
                    + historialCommands.get(i).getClass().getSimpleName());
        }
        System.out.println("--------------------------");
    }
}
