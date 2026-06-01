package vista;

import controlador.JuegoControlador;
import modelo.TipoCasa;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class PantallaInicio extends JFrame {

    private JuegoControlador controlador;
    private JTextField txtNombre;
    private JComboBox<String> cbCasa;
    private JComboBox<String> cbObjeto;
    private JButton btnComenzar;
    private Timer animTimer;
    private float glowPhase = 0f;

    // Colores temáticos
    private static final Color FONDO_OSCURO = new Color(10, 8, 20);
    private static final Color ORO_MAGICO = new Color(255, 200, 50);
    private static final Color PURPURA_MAGIA = new Color(120, 60, 200);
    private static final Color PLATA_LUNA = new Color(200, 200, 230);
    private static final Color GRYFF_ROJO = new Color(180, 30, 30);
    private static final Color HUFFLEP_AMARILLO = new Color(240, 180, 30);
    private static final Color RAVEN_AZUL = new Color(30, 60, 160);
    private static final Color SLYTH_VERDE = new Color(20, 130, 80);

    public PantallaInicio(JuegoControlador controlador) {
        this.controlador = controlador;
        setTitle("Mundo de Harry Potter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 620);
        setLocationRelativeTo(null);
        setResizable(false);
        initUI();
        iniciarAnimacion();
    }

    private void initUI() {
        setContentPane(new PanelFondo());
        setLayout(new BorderLayout());

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setOpaque(false);
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        // Título
        JLabel lblTitulo = new JLabel("⚡ HOGWARTS ⚡");
        lblTitulo.setFont(new Font("Serif", Font.BOLD, 46));
        lblTitulo.setForeground(ORO_MAGICO);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSubtitulo = new JLabel("Escuela de Magia y Hechicería");
        lblSubtitulo.setFont(new Font("Serif", Font.ITALIC, 20));
        lblSubtitulo.setForeground(PLATA_LUNA);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblEstrella = new JLabel("✦  ✦  ✦");
        lblEstrella.setFont(new Font("Serif", Font.PLAIN, 18));
        lblEstrella.setForeground(PURPURA_MAGIA);
        lblEstrella.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Panel formulario
        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setOpaque(false);
        panelForm.setBorder(crearBordeMagico("Registro de Estudiante"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lNombre = etiqueta("Nombre del Mago:");
        panelForm.add(lNombre, gbc);
        gbc.gridx = 1;
        txtNombre = new JTextField(18);
        estilizarCampo(txtNombre);
        panelForm.add(txtNombre, gbc);

        // Casa
        gbc.gridx = 0; gbc.gridy = 1;
        panelForm.add(etiqueta("Casa de Hogwarts:"), gbc);
        gbc.gridx = 1;
        String[] casas = {"⚔️  GRYFFINDOR", "🌻  HUFFLEPUFF", "📚  RAVENCLAW", "🐍  SLYTHERIN"};
        cbCasa = new JComboBox<>(casas);
        estilizarCombo(cbCasa);
        panelForm.add(cbCasa, gbc);

        // Objeto inicial
        gbc.gridx = 0; gbc.gridy = 2;
        panelForm.add(etiqueta("Objeto Mágico:"), gbc);
        gbc.gridx = 1;
        String[] objetos = {"Varita de Acebo", "Escoba Nimbus 2000", "Poción Veritaserum"};
        cbObjeto = new JComboBox<>(objetos);
        estilizarCombo(cbObjeto);
        panelForm.add(cbObjeto, gbc);

        // Botón
        btnComenzar = new JButton("✨  COMENZAR AVENTURA  ✨");
        btnComenzar.setFont(new Font("Serif", Font.BOLD, 16));
        btnComenzar.setForeground(Color.BLACK);
        btnComenzar.setBackground(ORO_MAGICO);
        btnComenzar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 130, 20), 2),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)));
        btnComenzar.setFocusPainted(false);
        btnComenzar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnComenzar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnComenzar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnComenzar.setBackground(new Color(255, 220, 80));
            }
            public void mouseExited(MouseEvent e) {
                btnComenzar.setBackground(ORO_MAGICO);
            }
        });

        btnComenzar.addActionListener(e -> onComenzar());

        // Footer
        JLabel lblFooter = new JLabel("\"No es nuestra magia lo que nos define, sino nuestras decisiones.\" — Dumbledore");
        lblFooter.setFont(new Font("Serif", Font.ITALIC, 12));
        lblFooter.setForeground(new Color(140, 130, 160));
        lblFooter.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Ensamblar
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(lblTitulo);
        panelCentral.add(Box.createVerticalStrut(5));
        panelCentral.add(lblSubtitulo);
        panelCentral.add(Box.createVerticalStrut(8));
        panelCentral.add(lblEstrella);
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(panelForm);
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(btnComenzar);
        panelCentral.add(Box.createVerticalStrut(15));
        panelCentral.add(lblFooter);

        add(panelCentral, BorderLayout.CENTER);
    }

    private void onComenzar() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "¡Debes ingresar tu nombre, joven mago!",
                    "Campo requerido", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String casaStr = (String) cbCasa.getSelectedItem();
        TipoCasa casa;
        if (casaStr.contains("GRYFFINDOR")) casa = TipoCasa.GRYFFINDOR;
        else if (casaStr.contains("HUFFLEPUFF")) casa = TipoCasa.HUFFLEPUFF;
        else if (casaStr.contains("RAVENCLAW")) casa = TipoCasa.RAVENCLAW;
        else casa = TipoCasa.SLYTHERIN;

        String objeto = (String) cbObjeto.getSelectedItem();
        controlador.crearJugador(nombre, casa, objeto);
    }

    private JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Serif", Font.PLAIN, 14));
        lbl.setForeground(PLATA_LUNA);
        return lbl;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Serif", Font.PLAIN, 14));
        campo.setForeground(ORO_MAGICO);
        campo.setBackground(new Color(20, 15, 40));
        campo.setCaretColor(ORO_MAGICO);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PURPURA_MAGIA, 1),
                BorderFactory.createEmptyBorder(5, 8, 5, 8)));
    }

    private void estilizarCombo(JComboBox<String> combo) {
        combo.setFont(new Font("Serif", Font.PLAIN, 13));
        combo.setForeground(ORO_MAGICO);
        combo.setBackground(new Color(20, 15, 40));
        combo.setBorder(BorderFactory.createLineBorder(PURPURA_MAGIA, 1));
    }

    private TitledBorder crearBordeMagico(String titulo) {
        TitledBorder border = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PURPURA_MAGIA, 1), titulo);
        border.setTitleFont(new Font("Serif", Font.BOLD | Font.ITALIC, 14));
        border.setTitleColor(ORO_MAGICO);
        return border;
    }

    private void iniciarAnimacion() {
        animTimer = new Timer(40, e -> {
            glowPhase += 0.05f;
            repaint();
        });
        animTimer.start();
    }

    // Panel con fondo de estrellas animado
    class PanelFondo extends JPanel {
        private float[] starX, starY, starBrillo;
        private int nEstrellas = 80;

        public PanelFondo() {
            starX = new float[nEstrellas];
            starY = new float[nEstrellas];
            starBrillo = new float[nEstrellas];
            for (int i = 0; i < nEstrellas; i++) {
                starX[i] = (float)(Math.random() * 700);
                starY[i] = (float)(Math.random() * 620);
                starBrillo[i] = (float)(Math.random() * Math.PI * 2);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fondo gradiente
            GradientPaint gp = new GradientPaint(0, 0, new Color(5, 4, 20),
                    0, getHeight(), new Color(20, 10, 50));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());

            // Nebulosa suave
            RadialGradientPaint nebula = new RadialGradientPaint(
                    new Point2D.Float(350, 200),
                    300,
                    new float[]{0f, 1f},
                    new Color[]{new Color(80, 20, 120, 40), new Color(0, 0, 0, 0)});
            g2.setPaint(nebula);
            g2.fillOval(50, 0, 600, 400);

            // Estrellas animadas
            for (int i = 0; i < nEstrellas; i++) {
                float bright = (float)(0.4 + 0.6 * Math.sin(glowPhase + starBrillo[i]));
                int alpha = (int)(bright * 220);
                int size = (i % 5 == 0) ? 3 : 2;
                g2.setColor(new Color(200, 200, 255, Math.max(0, Math.min(255, alpha))));
                g2.fillOval((int)starX[i], (int)starY[i], size, size);
            }
        }
    }
}
