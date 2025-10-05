package cinemagenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListarPeliculas extends JFrame {

    private PeliculaDAO peliculaDAO;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;
    private JComboBox<String> cmbGenero;
    private JSpinner spnAñoInicio;
    private JSpinner spnAñoFin;

    private static final String[] GENEROS = {
        "Todos", "Comedia", "Drama", "Acción", "Ciencia Ficción",
        "Aventura", "Terror", "Romance", "Animación", "Crimen", "Fantasia"
    };

    public ListarPeliculas(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
        initComponents();
        cargarPeliculas();
    }

    private void initComponents() {
        setTitle("Listar Películas - Cine Magenta");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(139, 0, 139));
        JLabel lblTitulo = new JLabel("CARTELERA DE PELÍCULAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Panel de filtros
        JPanel panelFiltros = new JPanel(new GridBagLayout());
        panelFiltros.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(139, 0, 139), 2),
                "Filtros de Búsqueda",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(139, 0, 139)
        ));
        panelFiltros.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Filtro por género
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblGenero = new JLabel("Género:");
        lblGenero.setFont(new Font("Arial", Font.BOLD, 12));
        panelFiltros.add(lblGenero, gbc);

        gbc.gridx = 1;
        cmbGenero = new JComboBox<>(GENEROS);
        cmbGenero.setPreferredSize(new Dimension(150, 25));
        panelFiltros.add(cmbGenero, gbc);

        // Filtro rango de años - Inicio
        gbc.gridx = 2;
        gbc.gridy = 0;
        JLabel lblAñoInicio = new JLabel("Año desde:");
        lblAñoInicio.setFont(new Font("Arial", Font.BOLD, 12));
        panelFiltros.add(lblAñoInicio, gbc);

        gbc.gridx = 3;
        SpinnerNumberModel modelInicio = new SpinnerNumberModel(1888, 1888, 2030, 1);
        spnAñoInicio = new JSpinner(modelInicio);
        spnAñoInicio.setPreferredSize(new Dimension(80, 25));
        panelFiltros.add(spnAñoInicio, gbc);

        // Filtro rango de años - Fin
        gbc.gridx = 4;
        JLabel lblAñoFin = new JLabel("Año hasta:");
        lblAñoFin.setFont(new Font("Arial", Font.BOLD, 12));
        panelFiltros.add(lblAñoFin, gbc);

        gbc.gridx = 5;
        SpinnerNumberModel modelFin = new SpinnerNumberModel(2030, 1888, 2030, 1);
        spnAñoFin = new JSpinner(modelFin);
        spnAñoFin.setPreferredSize(new Dimension(80, 25));
        panelFiltros.add(spnAñoFin, gbc);

        // Botones de filtrado
        gbc.gridx = 6;
        JButton btnFiltrar = new JButton("🔍 Filtrar");
        btnFiltrar.setBackground(new Color(139, 0, 139));
        btnFiltrar.setForeground(Color.BLACK);
        btnFiltrar.setFocusPainted(false);
        btnFiltrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFiltrar.addActionListener(e -> aplicarFiltros());
        panelFiltros.add(btnFiltrar, gbc);

        gbc.gridx = 7;
        JButton btnLimpiar = new JButton("🔄 Limpiar");
        btnLimpiar.setBackground(new Color(100, 100, 100));
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(e -> limpiarFiltros());
        panelFiltros.add(btnLimpiar, gbc);

        // Tabla de películas
        String[] columnas = {"ID", "Título", "Director", "Año", "Duración (min)", "Género"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPeliculas = new JTable(modeloTabla);
        tablaPeliculas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaPeliculas.setRowHeight(25);
        tablaPeliculas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaPeliculas.getTableHeader().setBackground(new Color(139, 0, 139));
        tablaPeliculas.getTableHeader().setForeground(Color.BLACK);
        tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajustar ancho de columnas
        tablaPeliculas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaPeliculas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaPeliculas.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaPeliculas.getColumnModel().getColumn(3).setPreferredWidth(60);
        tablaPeliculas.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaPeliculas.getColumnModel().getColumn(5).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(139, 0, 139), 2));

        // Panel inferior con información
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelInferior.setBackground(Color.WHITE);
        JLabel lblInfo = new JLabel("Seleccione los filtros deseados y presione 'Filtrar'");
        lblInfo.setFont(new Font("Arial", Font.ITALIC, 11));
        lblInfo.setForeground(Color.GRAY);
        panelInferior.add(lblInfo);

        // Botón cerrar
        JPanel panelBotonCerrar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("❌ Cerrar");
        btnCerrar.setBackground(new Color(200, 50, 50));
        btnCerrar.setForeground(Color.BLACK);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(e -> dispose());
        panelBotonCerrar.add(btnCerrar);

        JPanel panelInferiorCompleto = new JPanel(new BorderLayout());
        panelInferiorCompleto.add(panelInferior, BorderLayout.CENTER);
        panelInferiorCompleto.add(panelBotonCerrar, BorderLayout.EAST);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFiltros, BorderLayout.CENTER);

        // Panel central que contiene tabla y botón cerrar
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.add(scrollPane, BorderLayout.CENTER);
        panelCentral.add(panelInferiorCompleto, BorderLayout.SOUTH);

        JPanel panelContenedor = new JPanel(new BorderLayout(10, 10));
        panelContenedor.add(panelFiltros, BorderLayout.NORTH);
        panelContenedor.add(panelCentral, BorderLayout.CENTER);

        panelPrincipal.add(panelContenedor, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void cargarPeliculas() {
        modeloTabla.setRowCount(0);
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasLasPeliculas();

        for (Pelicula pelicula : peliculas) {
            Object[] fila = {
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getDirector(),
                pelicula.getAño(),
                pelicula.getDuracion(),
                pelicula.getGenero()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void aplicarFiltros() {
        String generoSeleccionado = (String) cmbGenero.getSelectedItem();
        int añoInicio = (Integer) spnAñoInicio.getValue();
        int añoFin = (Integer) spnAñoFin.getValue();

        // Validar rango de años
        if (añoInicio > añoFin) {
            JOptionPane.showMessageDialog(
                    this,
                    "El año inicial no puede ser mayor que el año final",
                    "Error en Rango de Años",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        modeloTabla.setRowCount(0);
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasLasPeliculas();
        int registrosEncontrados = 0;

        for (Pelicula pelicula : peliculas) {
            boolean cumpleGenero = generoSeleccionado.equals("Todos")
                    || pelicula.getGenero().equalsIgnoreCase(generoSeleccionado);
            boolean cumpleAño = pelicula.getAño() >= añoInicio && pelicula.getAño() <= añoFin;

            if (cumpleGenero && cumpleAño) {
                Object[] fila = {
                    pelicula.getId(),
                    pelicula.getTitulo(),
                    pelicula.getDirector(),
                    pelicula.getAño(),
                    pelicula.getDuracion(),
                    pelicula.getGenero()
                };
                modeloTabla.addRow(fila);
                registrosEncontrados++;
            }
        }

        if (registrosEncontrados == 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se encontraron películas con los filtros seleccionados",
                    "Sin Resultados",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void limpiarFiltros() {
        cmbGenero.setSelectedIndex(0);
        spnAñoInicio.setValue(1888);
        spnAñoFin.setValue(2030);
        cargarPeliculas();
    }
}
