package cinemagenta;

import javax.swing.*;
import java.awt.*;

public class AgregarPelicula extends JFrame {

    private PeliculaDAO peliculaDAO;
    private JTextField txtTitulo;
    private JTextField txtDirector;
    private JSpinner spnAño;
    private JSpinner spnDuracion;
    private JComboBox<String> cmbGenero;

    private static final String[] GENEROS = {
        "Comedia", "Drama", "Acción", "Ciencia Ficción",
        "Aventura", "Terror", "Romance", "Animación", "Crimen", "Fantasia"
    };

    public AgregarPelicula(PeliculaDAO peliculaDAO) {
        this.peliculaDAO = peliculaDAO;
        initComponents();
    }

    private void initComponents() {
        setTitle("Agregar Película - Cine Magenta");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(139, 0, 139));
        JLabel lblTitulo = new JLabel("AGREGAR NUEVA PELÍCULA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(139, 0, 139), 2),
                "Datos de la Película",
                0,
                0,
                new Font("Arial", Font.BOLD, 14),
                new Color(139, 0, 139)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel lblTituloP = new JLabel("Título: *");
        lblTituloP.setFont(new Font("Arial", Font.BOLD, 13));
        panelFormulario.add(lblTituloP, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtTitulo = new JTextField(20);
        txtTitulo.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(txtTitulo, gbc);

        // Director
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        JLabel lblDirector = new JLabel("Director: *");
        lblDirector.setFont(new Font("Arial", Font.BOLD, 13));
        panelFormulario.add(lblDirector, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtDirector = new JTextField(20);
        txtDirector.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(txtDirector, gbc);

        // Año
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        JLabel lblAño = new JLabel("Año: *");
        lblAño.setFont(new Font("Arial", Font.BOLD, 13));
        panelFormulario.add(lblAño, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        SpinnerNumberModel modelAño = new SpinnerNumberModel(2024, 1888, 2030, 1);
        spnAño = new JSpinner(modelAño);
        spnAño.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(spnAño, gbc);

        // Duración
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        JLabel lblDuracion = new JLabel("Duración (min): *");
        lblDuracion.setFont(new Font("Arial", Font.BOLD, 13));
        panelFormulario.add(lblDuracion, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        SpinnerNumberModel modelDuracion = new SpinnerNumberModel(90, 1, 500, 1);
        spnDuracion = new JSpinner(modelDuracion);
        spnDuracion.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(spnDuracion, gbc);

        // Género
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        JLabel lblGenero = new JLabel("Género: *");
        lblGenero.setFont(new Font("Arial", Font.BOLD, 13));
        panelFormulario.add(lblGenero, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        cmbGenero = new JComboBox<>(GENEROS);
        cmbGenero.setFont(new Font("Arial", Font.PLAIN, 12));
        panelFormulario.add(cmbGenero, gbc);

        // Nota de campos obligatorios
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        JLabel lblNota = new JLabel("* Campos obligatorios");
        lblNota.setFont(new Font("Arial", Font.ITALIC, 11));
        lblNota.setForeground(Color.RED);
        panelFormulario.add(lblNota, gbc);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 128, 0));
        btnGuardar.setForeground(Color.BLACK);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 13));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setPreferredSize(new Dimension(120, 35));
        btnGuardar.addActionListener(e -> guardarPelicula());

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(new Color(100, 100, 100));
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 13));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.setPreferredSize(new Dimension(120, 35));
        btnLimpiar.addActionListener(e -> limpiarCampos());

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(200, 50, 50));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 13));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setPreferredSize(new Dimension(120, 35));
        btnCancelar.addActionListener(e -> dispose());

        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCancelar);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);
    }

    private void guardarPelicula() {
        // Validar campos
        if (txtTitulo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El título es obligatorio", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtTitulo.requestFocus();
            return;
        }

        if (txtDirector.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El director es obligatorio", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            txtDirector.requestFocus();
            return;
        }

        // Crear película
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo(txtTitulo.getText().trim());
        pelicula.setDirector(txtDirector.getText().trim());
        pelicula.setAño((Integer) spnAño.getValue());
        pelicula.setDuracion((Integer) spnDuracion.getValue());
        pelicula.setGenero((String) cmbGenero.getSelectedItem());

        // Guardar en base de datos
        if (peliculaDAO.agregarPelicula(pelicula)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Película agregada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE
            );
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al agregar la película",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void limpiarCampos() {
        txtTitulo.setText("");
        txtDirector.setText("");
        spnAño.setValue(2024);
        spnDuracion.setValue(90);
        cmbGenero.setSelectedIndex(0);
        txtTitulo.requestFocus();
    }
}
