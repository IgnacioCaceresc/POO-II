package cinemagenta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ModificarPelicula extends JFrame {

    private PeliculaDAO peliculaDAO;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;
    private JTextField txtTitulo;
    private JTextField txtDirector;
    private JSpinner spnAño;
    private JSpinner spnDuracion;
    private JComboBox<String> cmbGenero;
    private JButton btnModificar;
    private int idSeleccionado = -1;

    private static final String[] GENEROS = {
        "Comedia", "Drama", "Acción", "Ciencia Ficción",
        "Aventura", "Terror", "Romance", "Animación", "Crimen", "Fantasia"
    };

    public ModificarPelicula(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
        initComponents();
        cargarPeliculas();
    }

    private void initComponents() {
        setTitle("Modificar Película - Cine Magenta");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(139, 0, 139));
        JLabel lblTitulo = new JLabel("MODIFICAR PELÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Panel superior con tabla
        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));

        JLabel lblInstruccion = new JLabel("Seleccione una película de la tabla:");
        lblInstruccion.setFont(new Font("Arial", Font.BOLD, 13));

        String[] columnas = {"ID", "Título", "Director", "Año", "Duración", "Género"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPeliculas = new JTable(modeloTabla);
        tablaPeliculas.setFont(new Font("Arial", Font.PLAIN, 12));
        tablaPeliculas.setRowHeight(22);
        tablaPeliculas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        tablaPeliculas.getTableHeader().setBackground(new Color(139, 0, 139));
        tablaPeliculas.getTableHeader().setForeground(Color.BLACK);
        tablaPeliculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        tablaPeliculas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDatosPelicula();
            }
        });

        JScrollPane scrollPane = new JScrollPane(tablaPeliculas);
        scrollPane.setPreferredSize(new Dimension(850, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(139, 0, 139), 2));

        panelSuperior.add(lblInstruccion, BorderLayout.NORTH);
        panelSuperior.add(scrollPane, BorderLayout.CENTER);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(139, 0, 139), 2),
                "Datos a Modificar",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(139, 0, 139)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panelFormulario.add(new JLabel("Título: *"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtTitulo = new JTextField(20);
        txtTitulo.setEnabled(false);
        panelFormulario.add(txtTitulo, gbc);

        // Director
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Director: *"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtDirector = new JTextField(20);
        txtDirector.setEnabled(false);
        panelFormulario.add(txtDirector, gbc);

        // Año
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Año: *"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        SpinnerNumberModel modelAño = new SpinnerNumberModel(2024, 1888, 2030, 1);
        spnAño = new JSpinner(modelAño);
        spnAño.setEnabled(false);
        panelFormulario.add(spnAño, gbc);

        // Duración
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Duración (min): *"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        SpinnerNumberModel modelDuracion = new SpinnerNumberModel(90, 1, 500, 1);
        spnDuracion = new JSpinner(modelDuracion);
        spnDuracion.setEnabled(false);
        panelFormulario.add(spnDuracion, gbc);

        // Género
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        panelFormulario.add(new JLabel("Género: *"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbGenero = new JComboBox<>(GENEROS);
        cmbGenero.setEnabled(false);
        panelFormulario.add(cmbGenero, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnModificar = new JButton("Modificar");
        btnModificar.setBackground(new Color(0, 128, 0));
        btnModificar.setForeground(Color.BLACK);
        btnModificar.setFont(new Font("Arial", Font.BOLD, 13));
        btnModificar.setFocusPainted(false);
        btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnModificar.setPreferredSize(new Dimension(130, 35));
        btnModificar.setEnabled(false);
        btnModificar.addActionListener(e -> modificarPelicula());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(200, 50, 50));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setPreferredSize(new Dimension(130, 35));
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnModificar);
        panelBotones.add(btnCancelar);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelSuperior, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelFormulario, BorderLayout.CENTER);
        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

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

    private void cargarDatosPelicula() {
        int filaSeleccionada = tablaPeliculas.getSelectedRow();

        if (filaSeleccionada >= 0) {
            idSeleccionado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

            Pelicula pelicula = peliculaDAO.buscarPorId(idSeleccionado);

            if (pelicula != null) {
                txtTitulo.setText(pelicula.getTitulo());
                txtDirector.setText(pelicula.getDirector());
                spnAño.setValue(pelicula.getAño());
                spnDuracion.setValue(pelicula.getDuracion());

                for (int i = 0; i < GENEROS.length; i++) {
                    if (GENEROS[i].equals(pelicula.getGenero())) {
                        cmbGenero.setSelectedIndex(i);
                        break;
                    }
                }

                habilitarFormulario(true);
            }
        }
    }

    private void habilitarFormulario(boolean habilitar) {
        txtTitulo.setEnabled(habilitar);
        txtDirector.setEnabled(habilitar);
        spnAño.setEnabled(habilitar);
        spnDuracion.setEnabled(habilitar);
        cmbGenero.setEnabled(habilitar);
        btnModificar.setEnabled(habilitar);
    }

    private void modificarPelicula() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una película", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (txtTitulo.getText().trim().isEmpty() || txtDirector.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea modificar esta película?",
                "Confirmar Modificación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            Pelicula pelicula = new Pelicula();
            pelicula.setId(idSeleccionado);
            pelicula.setTitulo(txtTitulo.getText().trim());
            pelicula.setDirector(txtDirector.getText().trim());
            pelicula.setAño((Integer) spnAño.getValue());
            pelicula.setDuracion((Integer) spnDuracion.getValue());
            pelicula.setGenero((String) cmbGenero.getSelectedItem());

            if (peliculaDAO.actualizarPelicula(pelicula)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Película modificada exitosamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE
                );
                cargarPeliculas();
                limpiarFormulario();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Error al modificar la película",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void limpiarFormulario() {
        txtTitulo.setText("");
        txtDirector.setText("");
        spnAño.setValue(2024);
        spnDuracion.setValue(90);
        cmbGenero.setSelectedIndex(0);
        habilitarFormulario(false);
        idSeleccionado = -1;
        tablaPeliculas.clearSelection();
    }
}
