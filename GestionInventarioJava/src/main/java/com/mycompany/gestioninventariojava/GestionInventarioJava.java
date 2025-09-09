package com.mycompany.gestioninventariojava;

import java.util.List;
import java.util.Scanner;

public class GestionInventarioJava {

    private Inventario inventario;
    private Scanner scanner;

    public GestionInventarioJava() {
        inventario = new Inventario();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Buscar producto por código");
            System.out.println("3. Buscar producto por nombre");
            System.out.println("4. Buscar producto por descripción");
            System.out.println("5. Listar todos los productos");
            System.out.println("6. Actualizar producto");
            System.out.println("7. Eliminar producto");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                opcion = 0;
            }

            switch (opcion) {
                case 1 ->
                    agregarProducto();
                case 2 ->
                    buscarPorCodigo();
                case 3 ->
                    buscarPorNombre();
                case 4 ->
                    buscarPorDescripcion();
                case 5 ->
                    listarProductos();
                case 6 ->
                    actualizarProducto();
                case 7 ->
                    eliminarProducto();
                case 8 ->
                    System.out.println("Saliendo del sistema...");
                default ->
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 8);
    }

    private void agregarProducto() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");

        String codigo;
        while (true) {
            System.out.print("Código: ");
            codigo = scanner.nextLine().trim();
            if (!codigo.isEmpty()) {

                if (inventario.buscarPorCodigo(codigo) != null) {
                    System.out.println("Error: Ya existe un producto con este código.");
                    return;
                }
                break;
            }
            System.out.println("Error: El código no puede estar vacío.");
        }

        String nombre;
        while (true) {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine().trim();
            if (!nombre.isEmpty()) {
                break;
            }
            System.out.println("Error: El nombre no puede estar vacío.");
        }

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine().trim();

        double precio;
        while (true) {
            System.out.print("Precio: ");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                if (precio >= 0) {
                    break;
                }
                System.out.println("Error: El precio no puede ser negativo.");
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.next();
            }
        }

        int stock;
        while (true) {
            System.out.print("Cantidad en stock: ");
            if (scanner.hasNextInt()) {
                stock = scanner.nextInt();
                if (stock >= 0) {
                    break;
                }
                System.out.println("Error: El stock no puede ser negativo.");
            } else {
                System.out.println("Error: Debe ingresar un número entero válido.");
                scanner.next();
            }
        }
        scanner.nextLine();

        Producto producto = new Producto(codigo, nombre, descripcion, precio, stock);
        if (inventario.agregarProducto(producto)) {
            System.out.println("Producto agregado exitosamente!");
        } else {
            System.out.println("Error: No se pudo agregar el producto.");
        }
    }

    private void buscarPorCodigo() {
        System.out.println("\n--- BUSCAR POR CÓDIGO ---");
        System.out.print("Ingrese el código a buscar: ");
        String codigo = scanner.nextLine();

        Producto producto = inventario.buscarPorCodigo(codigo);

        if (producto != null) {
            System.out.println("Producto encontrado:");
            System.out.println(producto.getDescripcionDetallada());
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    private void buscarPorNombre() {
        System.out.println("\n--- BUSCAR POR NOMBRE ---");
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine();

        List<Producto> resultados = inventario.buscarPorNombre(nombre);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos");
        } else {
            System.out.println("Productos encontrados (" + resultados.size() + "):");
            for (Producto producto : resultados) {
                System.out.println(producto.getDescripcionDetallada());
            }
        }
    }

    private void buscarPorDescripcion() {
        System.out.println("\n--- BUSCAR POR DESCRIPCIÓN ---");
        System.out.print("Ingrese la descripción a buscar: ");
        String descripcion = scanner.nextLine();

        List<Producto> resultados = inventario.buscarPorDescripcion(descripcion);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron productos");
        } else {
            System.out.println("Productos encontrados (" + resultados.size() + "):");
            for (Producto producto : resultados) {
                System.out.println(producto.getDescripcionDetallada());
            }
        }
    }

    private void listarProductos() {
        System.out.println("\n--- LISTA DE PRODUCTOS ---");
        List<Producto> productos = inventario.listarTodos();

        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario");
        } else {
            System.out.println("Total de productos: " + productos.size());
            for (Producto producto : productos) {
                System.out.println(producto.getDescripcionDetallada());
            }
        }
    }

    private void actualizarProducto() {
        System.out.println("\n--- ACTUALIZAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a actualizar: ");
        String codigo = scanner.nextLine();

        Producto producto = inventario.buscarPorCodigo(codigo);

        if (producto != null) {
            System.out.println("Producto actual: " + producto.getDescripcionDetallada());

            System.out.print("Nuevo nombre (enter para mantener actual): ");
            String nombre = scanner.nextLine();
            if (nombre.isEmpty()) {
                nombre = null;
            }

            System.out.print("Nueva descripción (enter para mantener actual): ");
            String descripcion = scanner.nextLine();
            if (descripcion.isEmpty()) {
                descripcion = null;
            }

            System.out.print("Nuevo precio (-1 para mantener actual): ");
            double precio = -1;
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
            }
            scanner.nextLine();

            System.out.print("Nuevo stock (-1 para mantener actual): ");
            int stock = -1;
            if (scanner.hasNextInt()) {
                stock = scanner.nextInt();
            }
            scanner.nextLine();

            if (inventario.actualizarProducto(codigo, nombre, descripcion, precio, stock)) {
                System.out.println("Producto actualizado exitosamente!");
            } else {
                System.out.println("Error: No se pudo actualizar el producto.");
            }
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    private void eliminarProducto() {
        System.out.println("\n--- ELIMINAR PRODUCTO ---");
        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigo = scanner.nextLine();

        if (inventario.eliminarProducto(codigo)) {
            System.out.println("Producto eliminado exitosamente!");
        } else {
            System.out.println("Producto no encontrado");
        }
    }

    public static void main(String[] args) {
        GestionInventarioJava menu = new GestionInventarioJava();
        menu.mostrarMenu();
    }
}
