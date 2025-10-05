package cinemagenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EliminarPelicula extends JFrame {

    private PeliculaDAO peliculaDAO;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;
    private JButton btnEliminar;
    private int idSeleccionado = -1;

    public EliminarPelicula(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
        initComponents();
        cargarPeliculas();
    }

    private void initComponents() {
        setTitle("Eliminar Película - Cine Magenta");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(200, 50, 50));
        JLabel lblTitulo = new JLabel("ELIMINAR PELÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Panel con instrucciones
        JPanel panelInstruccion = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblInstruccion = new JLabel("Seleccione una película de la tabla para eliminar:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 13));
        lblInstruccion.setForeground(new Color(200, 50, 50));
        panelInstruccion.add(lblInstruccion);

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
        tablaPeliculas.getTableHeader().setBackground(new Color(200, 50, 50));
        tablaPeliculas.getTableHeader().setForeground(Color.BLACK);
        tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ajustar ancho de columnas
        tablaPeliculas.getColumnModel().getColumn(0).setPreferredWidth(40);
        tablaPeliculas.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaPeliculas.getColumnModel().getColumn(2).setPreferredWidth(150);
        tablaPeliculas.getColumnModel().getColumn(3).setPreferredWidth(60);
        tablaPeliculas.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaPeliculas.getColumnModel().getColumn(5).setPreferredWidth(120);

        tablaPeliculas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tablaPeliculas.getSelectedRow();
                if (filaSeleccionada >= 0) {
                    idSeleccionado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
                    btnEliminar.setEnabled(true);
                } else {
                    idSeleccionado = -1;
                    btnEliminar.setEnabled(false);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 50, 50), 2));

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnEliminar = new JButton("Eliminar Seleccionada");
        btnEliminar.setBackground(new Color(200, 50, 50));
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 13));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setPreferredSize(new Dimension(180, 35));
        btnEliminar.setEnabled(false);
        btnEliminar.addActionListener(e -> eliminarPelicula());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(100, 100, 100));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setPreferredSize(new Dimension(130, 35));
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);

        // Panel de advertencia
        JPanel panelAdvertencia = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelAdvertencia.setBackground(new Color(255, 230, 230));
        JLabel lblAdvertencia = new JLabel("ADVERTENCIA: Esta acción no se puede deshacer");
        lblAdvertencia.setFont(new Font("Arial", Font.BOLD, 12));
        lblAdvertencia.setForeground(new Color(150, 0, 0));
        panelAdvertencia.add(lblAdvertencia);

        // Panel central que contiene instrucción, tabla y advertencia
        JPanel panelCentro = new JPanel(new BorderLayout(5, 5));
        panelCentro.add(panelInstruccion, BorderLayout.NORTH);
        panelCentro.add(scrollPane, BorderLayout.CENTER);
        panelCentro.add(panelAdvertencia, BorderLayout.SOUTH);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void cargarPeliculas() {
        modeloTabla.setRowCount(0);
        List<Pelicula> peliculas = peliculaDAO.obtenerTodasLasPeliculas();

        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay películas registradas en el sistema",
                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

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

    private void eliminarPelicula() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una película para eliminar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Obtener datos de la película seleccionada
        int filaSeleccionada = tablaPeliculas.getSelectedRow();
        String titulo = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String director = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
        int año = (int) modeloTabla.getValueAt(filaSeleccionada, 3);

        // Confirmar eliminación con información detallada
        String mensaje = String.format(
                "¿Está SEGURO que desea eliminar la siguiente película?\n\n"
                + "Título: %s\n"
                + "Director: %s\n"
                + "Año: %d\n\n"
                + "Esta acción NO se puede deshacer.",
                titulo, director, año
        );

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                mensaje,
                "⚠️ Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (peliculaDAO.eliminarPelicula(idSeleccionado)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Película eliminada exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarPeliculas();
                idSeleccionado = -1;
                btnEliminar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error al eliminar la película",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
