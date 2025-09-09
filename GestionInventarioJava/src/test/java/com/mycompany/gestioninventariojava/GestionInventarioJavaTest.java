
package com.mycompany.gestioninventariojava;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class GestionInventarioJavaTest {
    
 @Test
    public void testCreacionProducto() {
        System.out.println("Prueba: Creación de producto con todos los atributos");
        Producto producto = new Producto("P001", "Laptop", "Laptop gaming", 1500.0, 10);
        
        assertAll("Verificar todos los atributos del producto",
            () -> assertEquals("P001", producto.getCodigo()),
            () -> assertEquals("Laptop", producto.getNombre()),
            () -> assertEquals("Laptop gaming", producto.getDescripcion()),
            () -> assertEquals(1500.0, producto.getPrecio(), 0.001),
            () -> assertEquals(10, producto.getCantidadStock())
        );
        System.out.println("    Producto creado correctamente con todos los atributos");
    }
    
    @Test
    public void testActualizacionAtributosProducto() {
        System.out.println("Prueba: Actualización de atributos del producto");
        Producto producto = new Producto("P001", "Laptop", "Laptop gaming", 1500.0, 10);
        
        // Cambiar y recuperar el precio
        producto.setPrecio(1400.0);
        assertEquals(1400.0, producto.getPrecio(), 0.001);
        System.out.println("    Precio actualizado correctamente");
        
        // Cambiar y verificar la cantidad en stock
        producto.setCantidadStock(5);
        assertEquals(5, producto.getCantidadStock());
        System.out.println("    Stock actualizado correctamente");
    }
    
    // Pruebas para la clase Inventario
    @Test
    public void testAgregarProductoInventario() {
        System.out.println("Prueba: Agregar producto al inventario");
        Inventario inventario = new Inventario();
        Producto producto = new Producto("P001", "Laptop", "Laptop gaming", 1500.0, 10);
        
        // Agregar un producto y verificar que el inventario lo contiene
        boolean resultado = inventario.agregarProducto(producto);
        assertTrue(resultado);
        
        Producto productoEncontrado = inventario.buscarPorCodigo("P001");
        assertNotNull(productoEncontrado);
        assertEquals("Laptop", productoEncontrado.getNombre());
        System.out.println("    Producto agregado correctamente al inventario");
        
        // Probar agregar un producto nulo
        boolean resultadoNulo = inventario.agregarProducto(null);
        assertFalse(resultadoNulo);
        System.out.println("    Producto nulo manejado correctamente");
    }
    
    @Test
    public void testEliminarProductoInventario() {
        System.out.println("Prueba: Eliminar producto del inventario");
        Inventario inventario = new Inventario();
        Producto producto = new Producto("P001", "Laptop", "Laptop gaming", 1500.0, 10);
        inventario.agregarProducto(producto);
        
        // Eliminar un producto por su ID y comprobar que ya no esté en el inventario
        boolean eliminado = inventario.eliminarProducto("P001");
        assertTrue(eliminado);
        
        Producto productoEliminado = inventario.buscarPorCodigo("P001");
        assertNull(productoEliminado);
        System.out.println("   Producto eliminado correctamente");
        
        // Intentar eliminar un producto con un ID inexistente
        boolean noEliminado = inventario.eliminarProducto("P999");
        assertFalse(noEliminado);
        System.out.println("    ID inexistente manejado correctamente");
    }
    
    @Test
    public void testBuscarProductoPorNombre() {
        System.out.println("Prueba: Buscar producto por nombre");
        Inventario inventario = new Inventario();
        Producto producto1 = new Producto("P001", "Laptop HP", "Laptop gaming", 1500.0, 10);
        Producto producto2 = new Producto("P002", "Mouse Logitech", "Mouse inalámbrico", 25.5, 50);
        Producto producto3 = new Producto("P003", "Laptop Dell", "Laptop empresarial", 1200.0, 15);
        
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        inventario.agregarProducto(producto3);
        
        // Buscar por un nombre específico y comprobar que se devuelven todos los productos que coinciden
        List<Producto> resultados = inventario.buscarPorNombre("Laptop");
        assertEquals(2, resultados.size());
        System.out.println("    Búsqueda por nombre devuelve los productos correctos");
        
        // Realizar una búsqueda con un nombre que no existe
        List<Producto> noResultados = inventario.buscarPorNombre("Tablet");
        assertTrue(noResultados.isEmpty());
        System.out.println("    Búsqueda sin resultados manejada correctamente");
    }
    
    @Test
    public void testListarTodosLosProductos() {
        System.out.println("Prueba: Listar todos los productos");
        Inventario inventario = new Inventario();
        Producto producto1 = new Producto("P001", "Laptop", "Laptop gaming", 1500.0, 10);
        Producto producto2 = new Producto("P002", "Mouse", "Mouse inalámbrico", 25.5, 50);
        
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        
        // Verificar que la lista devuelta contiene todos los productos agregados previamente
        List<Producto> todosProductos = inventario.listarTodos();
        assertEquals(2, todosProductos.size());
        
        // Verificar que los productos en la lista son los correctos
        assertTrue(todosProductos.stream().anyMatch(p -> p.getCodigo().equals("P001")));
        assertTrue(todosProductos.stream().anyMatch(p -> p.getCodigo().equals("P002")));
        System.out.println("    Listado de todos los productos funciona correctamente");
    }
    
    @Test
    public void testBuscarPorDescripcion() {
        System.out.println("Prueba: Buscar producto por descripción");
        Inventario inventario = new Inventario();
        Producto producto1 = new Producto("P001", "Laptop", "Laptop gaming 15 pulgadas", 1500.0, 10);
        Producto producto2 = new Producto("P002", "Mouse", "Mouse inalámbrico USB", 25.5, 50);
        
        inventario.agregarProducto(producto1);
        inventario.agregarProducto(producto2);
        
        // Buscar por descripción
        List<Producto> resultados = inventario.buscarPorDescripcion("inalámbrico");
        assertEquals(1, resultados.size());
        assertEquals("P002", resultados.get(0).getCodigo());
        System.out.println("    Búsqueda por descripción funciona correctamente");
    }
}
