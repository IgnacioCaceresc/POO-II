package cinemagenta;

import java.util.Scanner;
import java.util.List;

public class CineMagenta {

    private static Scanner scanner = new Scanner(System.in);
    private static PeliculaDAO peliculaDAO = new PeliculaDAO();

    private static final String[] GENEROS = {
        "Comedia", "Drama", "Acción", "Ciencia Ficción",
        "Aventura", "Terror", "Romance", "Animación", "Crimen", "Fantasia"
    };

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("    SISTEMA CINE MAGENTA - CARTELERA");
        System.out.println("==========================================");

        mostrarMenuPrincipal();
    }

    private static void mostrarMenuPrincipal() {
        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Agregar película");
            System.out.println("2. Modificar película");
            System.out.println("3. Eliminar película");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarPelicula();
                        break;
                    case 2:
                        modificarPelicula();
                        break;
                    case 3:
                        eliminarPelicula();
                        break;
                    case 4:
                        System.out.println("¡Gracias por usar el sistema!");
                        return;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }

    private static void agregarPelicula() {
        System.out.println("\n--- AGREGAR NUEVA PELÍCULA ---");

        // Validar y obtener título
        String titulo = "";
        while (titulo.trim().isEmpty()) {
            System.out.print("Título: ");
            titulo = scanner.nextLine();
            if (titulo.trim().isEmpty()) {
                System.out.println("Error: El título es obligatorio.");
            }
        }

        // Validar y obtener director
        String director = "";
        while (director.trim().isEmpty()) {
            System.out.print("Director: ");
            director = scanner.nextLine();
            if (director.trim().isEmpty()) {
                System.out.println("Error: El director es obligatorio.");
            }
        }

        // Validar y obtener año
        int año = 0;
        while (año < 1888 || año > 2030) {
            System.out.print("Año: ");
            try {
                año = Integer.parseInt(scanner.nextLine());
                if (año < 1888 || año > 2030) {
                    System.out.println("Error: El año debe ser entre 1888 y 2030.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un año válido.");
            }
        }

        // Validar y obtener duración
        int duracion = 0;
        while (duracion <= 0 || duracion > 500) {
            System.out.print("Duración (minutos): ");
            try {
                duracion = Integer.parseInt(scanner.nextLine());
                if (duracion <= 0 || duracion > 500) {
                    System.out.println("Error: La duración debe ser entre 1 y 500 minutos.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar una duración válida.");
            }
        }

        // Mostrar géneros disponibles
        System.out.println("\nGéneros disponibles:");
        for (int i = 0; i < GENEROS.length; i++) {
            System.out.println((i + 1) + ". " + GENEROS[i]);
        }

        // Validar y obtener género
        int generoIndex = -1;
        while (generoIndex < 0 || generoIndex >= GENEROS.length) {
            System.out.print("Seleccione el número del género: ");
            try {
                generoIndex = Integer.parseInt(scanner.nextLine()) - 1;
                if (generoIndex < 0 || generoIndex >= GENEROS.length) {
                    System.out.println("Error: Seleccione un número válido del 1 al " + GENEROS.length);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }

        String genero = GENEROS[generoIndex];

        // Crear y guardar la película
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(titulo.trim());
        pelicula.setDirector(director.trim());
        pelicula.setAño(año);
        pelicula.setDuracion(duracion);
        pelicula.setGenero(genero);

        if (peliculaDAO.agregarPelicula(pelicula)) {
            System.out.println("\n✅ Película agregada exitosamente!");
        } else {
            System.out.println("\n❌ Error al agregar la película.");
        }
    }

    private static void modificarPelicula() {
        System.out.println("\n--- MODIFICAR PELÍCULA ---");

        // Primero mostrar todas las películas para que el usuario vea los IDs
        System.out.println("\nPelículas en cartelera:");
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasLasPeliculas();
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas.");
            return;
        }

        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula.toString());
        }

        // Buscar película por ID
        System.out.print("\nIngrese el ID de la película a modificar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            Pelicula pelicula = peliculaDAO.buscarPorId(id);
            if (pelicula == null) {
                System.out.println("❌ Error: No existe una película con ese ID.");
                return;
            }

            System.out.println("\nPelícula encontrada:");
            System.out.println(pelicula.toString());

            // Solicitar nuevos datos
            System.out.println("\nIngrese los nuevos datos (deje en blanco para mantener el valor actual):");

            // Título
            System.out.print("Título [" + pelicula.getTitulo() + "]: ");
            String titulo = scanner.nextLine();
            if (!titulo.trim().isEmpty()) {
                pelicula.setTitulo(titulo.trim());
            }

            // Director
            System.out.print("Director [" + pelicula.getDirector() + "]: ");
            String director = scanner.nextLine();
            if (!director.trim().isEmpty()) {
                pelicula.setDirector(director.trim());
            }

            // Año
            System.out.print("Año [" + pelicula.getAño() + "]: ");
            String añoStr = scanner.nextLine();
            if (!añoStr.trim().isEmpty()) {
                try {
                    int año = Integer.parseInt(añoStr);
                    if (año >= 1888 && año <= 2030) {
                        pelicula.setAño(año);
                    } else {
                        System.out.println("Año no válido. Se mantiene el año actual.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Año no válido. Se mantiene el año actual.");
                }
            }

            // Duración
            System.out.print("Duración [" + pelicula.getDuracion() + "]: ");
            String duracionStr = scanner.nextLine();
            if (!duracionStr.trim().isEmpty()) {
                try {
                    int duracion = Integer.parseInt(duracionStr);
                    if (duracion > 0 && duracion <= 500) {
                        pelicula.setDuracion(duracion);
                    } else {
                        System.out.println("Duración no válida. Se mantiene la duración actual.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Duración no válida. Se mantiene la duración actual.");
                }
            }

            // Género
            System.out.println("\nGéneros disponibles:");
            for (int i = 0; i < GENEROS.length; i++) {
                System.out.println((i + 1) + ". " + GENEROS[i]);
            }
            System.out.print("Género [" + pelicula.getGenero() + "] (ingrese número o Enter para mantener): ");
            String generoStr = scanner.nextLine();
            if (!generoStr.trim().isEmpty()) {
                try {
                    int generoIndex = Integer.parseInt(generoStr) - 1;
                    if (generoIndex >= 0 && generoIndex < GENEROS.length) {
                        pelicula.setGenero(GENEROS[generoIndex]);
                    } else {
                        System.out.println("Género no válido. Se mantiene el género actual.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Género no válido. Se mantiene el género actual.");
                }
            }

            // Mostrar datos finales y confirmar
            System.out.println("\nDatos a actualizar:");
            System.out.println(pelicula.toString());
            System.out.print("¿Confirmar modificación? (s/n): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                if (peliculaDAO.actualizarPelicula(pelicula)) {
                    System.out.println("✅ Película modificada exitosamente!");
                } else {
                    System.out.println("❌ Error al modificar la película.");
                }
            } else {
                System.out.println("Modificación cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un ID válido.");
        }
    }

    private static void eliminarPelicula() {
        System.out.println("\n--- ELIMINAR PELÍCULA ---");

        // Primero mostrar todas las películas para que el usuario vea los IDs
        System.out.println("\nPelículas en cartelera:");
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasLasPeliculas();
        if (peliculas.isEmpty()) {
            System.out.println("No hay películas registradas.");
            return;
        }

        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula.toString());
        }

        System.out.print("\nIngrese el ID de la película a eliminar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());

            if (!peliculaDAO.existePelicula(id)) {
                System.out.println("❌ Error: No existe una película con ese ID.");
                return;
            }

            Pelicula pelicula = peliculaDAO.buscarPorId(id);
            System.out.println("\nPelícula a eliminar:");
            System.out.println(pelicula.toString());

            System.out.print("¿Está seguro que desea ELIMINAR esta película? (s/n): ");
            String confirmacion = scanner.nextLine();

            if (confirmacion.equalsIgnoreCase("s")) {
                if (peliculaDAO.eliminarPelicula(id)) {
                    System.out.println("✅ Película eliminada exitosamente!");
                } else {
                    System.out.println("❌ Error al eliminar la película.");
                }
            } else {
                System.out.println("Eliminación cancelada.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un ID válido.");
        }
    }
}
