package cinemagenta;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private PeliculaDAO peliculaDAO;

    public MenuPrincipal() {
        peliculaDAO = new PeliculaDAO();
        initComponents();
    }

    private void initComponents() {
        setTitle("SISTEMA CINE MAGENTA - Gestión de Cartelera");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal con fondo
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        panelPrincipal.setBackground(new Color(139, 0, 139)); // Color magenta
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(139, 0, 139));
        JLabel lblTitulo = new JLabel("CINE MAGENTA");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Sistema de Gestión de Cartelera");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(Color.WHITE);

        JPanel panelEncabezado = new JPanel(new GridLayout(2, 1));
        panelEncabezado.setBackground(new Color(139, 0, 139));
        panelEncabezado.add(panelTitulo);
        JPanel panelSub = new JPanel();
        panelSub.setBackground(new Color(139, 0, 139));
        panelSub.add(lblSubtitulo);
        panelEncabezado.add(panelSub);

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1, 10, 15));
        panelBotones.setBackground(new Color(139, 0, 139));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Botones del menu
        JButton btnListar = crearBoton(" Listar Películas");
        JButton btnAgregar = crearBoton(" Agregar Película");
        JButton btnModificar = crearBoton("️ Modificar Película");
        JButton btnEliminar = crearBoton("️ Eliminar Película");
        JButton btnSalir = crearBoton("Salir");

        btnListar.addActionListener(e -> abrirVentanaListar());
        btnAgregar.addActionListener(e -> abrirVentanaAgregar());
        btnModificar.addActionListener(e -> abrirVentanaModificar());
        btnEliminar.addActionListener(e -> abrirVentanaEliminar());
        btnSalir.addActionListener(e -> salir());

        panelBotones.add(btnListar);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnSalir);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelEncabezado, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(Color.WHITE);
        boton.setForeground(new Color(139, 0, 139));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(255, 192, 203)); // Rosa claro
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(Color.WHITE);
            }
        });

        return boton;
    }

    private void abrirVentanaListar() {
        ListarPeliculas ventana = new ListarPeliculas(peliculaDAO);
        ventana.setVisible(true);
    }

    private void abrirVentanaAgregar() {
        AgregarPelicula ventana = new AgregarPelicula(peliculaDAO);
        ventana.setVisible(true);
    }

    private void abrirVentanaModificar() {
        ModificarPelicula ventana = new ModificarPelicula(peliculaDAO);
        ventana.setVisible(true);
    }

    private void abrirVentanaEliminar() {
        EliminarPelicula ventana = new EliminarPelicula(peliculaDAO);
        ventana.setVisible(true);
    }

    private void salir() {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea salir del sistema?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
    }
}
