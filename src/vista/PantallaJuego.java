package vista;

import controlador.JuegoControlador;
import modelo.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class PantallaJuego extends JFrame {

    private JuegoControlador controlador;

    // Colores
    private static final Color FONDO_OSCURO = new Color(10, 8, 20);
    private static final Color PANEL_COLOR = new Color(18, 14, 35);
    private static final Color ORO_MAGICO = new Color(255, 200, 50);
    private static final Color PURPURA_MAGIA = new Color(120, 60, 200);
    private static final Color PLATA_LUNA = new Color(200, 200, 230);
    private static final Color VERDE_MAGIA = new Color(50, 220, 120);
    private static final Color ROJO_PELIGRO = new Color(220, 60, 60);

    // Componentes de estado del jugador
    private JLabel lblNombreJugador;
    private JLabel lblCasa;
    private JLabel lblNivel;
    private JLabel lblXP;
    private JLabel lblProgreso;
    private JLabel lblUbicacion;
    private JProgressBar barraXP;
    private JProgressBar barraProgreso;

    // Pestañas de acción
    private JTabbedPane tabbedPane;

    // Panel de hechizos
    private JList<String> listHechizosDisponibles;
    private JList<String> listHechizosAprendidos;
    private JButton btnAprenderHechizo;
    private JButton btnDuelo;

    // Panel de exploración
    private JList<String> listLugares;
    private JButton btnMoverse;
    private JTextArea txtDescripcionLugar;

    // Panel de personajes
    private JList<String> listPersonajes;
    private JButton btnInteractuar;

    // Panel de criaturas
    private JList<String> listCriaturas;

    // Bitácora
    private JTextArea txtBitacora;

    public PantallaJuego(JuegoControlador controlador) {
        this.controlador = controlador;
        setTitle("⚡ Hogwarts - Aventura Mágica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 650));
        initUI();
    }

    private void initUI() {
        getContentPane().setBackground(FONDO_OSCURO);
        setLayout(new BorderLayout(5, 5));

        // Header
        add(crearHeader(), BorderLayout.NORTH);

        // Panel central con pestañas + bitácora
        JPanel panelCentral = new JPanel(new BorderLayout(5, 5));
        panelCentral.setOpaque(false);
        panelCentral.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(PANEL_COLOR);
        tabbedPane.setForeground(ORO_MAGICO);
        tabbedPane.setFont(new Font("Serif", Font.BOLD, 13));
        UIManager.put("TabbedPane.selected", new Color(40, 25, 70));

        tabbedPane.addTab("✨ Hechizos", crearTabHechizos());
        tabbedPane.addTab("🗺️ Explorar", crearTabExploracion());
        tabbedPane.addTab("💬 Personajes", crearTabPersonajes());
        tabbedPane.addTab("🐉 Criaturas", crearTabCriaturas());
        tabbedPane.addTab("🎒 Inventario", crearTabInventario());

        panelCentral.add(tabbedPane, BorderLayout.CENTER);
        panelCentral.add(crearBitacora(), BorderLayout.SOUTH);

        add(panelCentral, BorderLayout.CENTER);
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout(10, 0));
        header.setBackground(new Color(15, 10, 35));
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, PURPURA_MAGIA),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        // Título izquierda
        JLabel titulo = new JLabel("⚡ HOGWARTS - AVENTURA MÁGICA");
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(ORO_MAGICO);

        // Stats del jugador a la derecha
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 15, 4));
        statsPanel.setOpaque(false);

        lblNombreJugador = statLabel("👤 Jugador");
        lblCasa = statLabel("🏠 Casa");
        lblNivel = statLabel("⭐ Nivel");
        lblXP = statLabel("✨ XP: 0");
        lblProgreso = statLabel("📈 Progreso: 0%");
        lblUbicacion = statLabel("📍 Ubicación");

        statsPanel.add(lblNombreJugador);
        statsPanel.add(lblCasa);
        statsPanel.add(lblNivel);
        statsPanel.add(lblXP);
        statsPanel.add(lblProgreso);
        statsPanel.add(lblUbicacion);

        header.add(titulo, BorderLayout.WEST);
        header.add(statsPanel, BorderLayout.EAST);
        return header;
    }

    private JLabel statLabel(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Serif", Font.PLAIN, 12));
        lbl.setForeground(PLATA_LUNA);
        return lbl;
    }

    private JPanel crearTabHechizos() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel izq = new JPanel(new BorderLayout(5, 5));
        izq.setOpaque(false);
        izq.setBorder(tituloBorde("📖 Hechizos Disponibles"));

        DefaultListModel<String> modeloDisp = new DefaultListModel<>();
        listHechizosDisponibles = new JList<>(modeloDisp);
        estilizarLista(listHechizosDisponibles);
        izq.add(new JScrollPane(listHechizosDisponibles), BorderLayout.CENTER);

        btnAprenderHechizo = crearBoton("✨ Aprender Hechizo", PURPURA_MAGIA);
        btnAprenderHechizo.addActionListener(e -> {
            String seleccionado = listHechizosDisponibles.getSelectedValue();
            if (seleccionado != null) {
                String nombre = seleccionado.split(" \\(")[0];
                controlador.aprenderHechizo(nombre);
            } else {
                mostrarMensaje("⚠️ Selecciona un hechizo primero.");
            }
        });
        izq.add(btnAprenderHechizo, BorderLayout.SOUTH);

        JPanel der = new JPanel(new BorderLayout(5, 5));
        der.setOpaque(false);
        der.setBorder(tituloBorde("⚡ Hechizos Aprendidos"));

        DefaultListModel<String> modeloApr = new DefaultListModel<>();
        listHechizosAprendidos = new JList<>(modeloApr);
        estilizarLista(listHechizosAprendidos);
        der.add(new JScrollPane(listHechizosAprendidos), BorderLayout.CENTER);

        btnDuelo = crearBoton("⚔️ ¡Duelo Mágico!", ROJO_PELIGRO);
        btnDuelo.addActionListener(e -> {
            String sel = listHechizosAprendidos.getSelectedValue();
            if (sel != null) {
                String nombre = sel.split(" \\(")[0];
                controlador.realizarDuelo(nombre);
            } else {
                mostrarMensaje("⚠️ Selecciona un hechizo para duelos.");
            }
        });
        der.add(btnDuelo, BorderLayout.SOUTH);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, izq, der);
        split.setOpaque(false);
        split.setDividerLocation(400);
        split.setDividerSize(4);
        panel.add(split, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearTabExploracion() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel izq = new JPanel(new BorderLayout(5, 5));
        izq.setOpaque(false);
        izq.setBorder(tituloBorde("🗺️ Lugares de Hogwarts"));

        String[] lugares = {"Gran Comedor", "Bosque Prohibido", "Sala de los Menesteres"};
        listLugares = new JList<>(lugares);
        estilizarLista(listLugares);
        listLugares.addListSelectionListener(e -> actualizarDescripcionLugar());
        izq.add(new JScrollPane(listLugares), BorderLayout.CENTER);

        btnMoverse = crearBoton("🚶 Ir a este Lugar", VERDE_MAGIA);
        btnMoverse.setForeground(Color.BLACK);
        btnMoverse.addActionListener(e -> {
            String lugar = listLugares.getSelectedValue();
            if (lugar != null) controlador.moverse(lugar);
            else mostrarMensaje("⚠️ Selecciona un lugar primero.");
        });
        izq.add(btnMoverse, BorderLayout.SOUTH);

        JPanel der = new JPanel(new BorderLayout(5, 5));
        der.setOpaque(false);
        der.setBorder(tituloBorde("📜 Descripción"));

        txtDescripcionLugar = new JTextArea(5, 20);
        txtDescripcionLugar.setFont(new Font("Serif", Font.ITALIC, 13));
        txtDescripcionLugar.setForeground(PLATA_LUNA);
        txtDescripcionLugar.setBackground(new Color(12, 10, 25));
        txtDescripcionLugar.setLineWrap(true);
        txtDescripcionLugar.setWrapStyleWord(true);
        txtDescripcionLugar.setEditable(false);
        txtDescripcionLugar.setText("Selecciona un lugar para ver su descripción...");
        der.add(new JScrollPane(txtDescripcionLugar), BorderLayout.CENTER);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, izq, der);
        split.setOpaque(false);
        split.setDividerLocation(350);
        panel.add(split, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearTabPersonajes() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel izq = new JPanel(new BorderLayout(5, 5));
        izq.setOpaque(false);
        izq.setBorder(tituloBorde("👥 Personajes de Hogwarts"));

        String[] personajes = {
            "Harry Potter", "Hermione Granger", "Ron Weasley", "Draco Malfoy",
            "Albus Dumbledore", "Severus Snape", "Minerva McGonagall"
        };
        listPersonajes = new JList<>(personajes);
        estilizarLista(listPersonajes);
        izq.add(new JScrollPane(listPersonajes), BorderLayout.CENTER);

        btnInteractuar = crearBoton("💬 Interactuar", ORO_MAGICO);
        btnInteractuar.setForeground(Color.BLACK);
        btnInteractuar.addActionListener(e -> {
            String sel = listPersonajes.getSelectedValue();
            if (sel != null) controlador.interactuar(sel);
            else mostrarMensaje("⚠️ Selecciona un personaje primero.");
        });
        izq.add(btnInteractuar, BorderLayout.SOUTH);

        JPanel der = new JPanel(new GridLayout(0, 1, 0, 5));
        der.setOpaque(false);
        der.setBorder(tituloBorde("📖 Datos del Personaje"));

        String[][] datos = {
            {"⚔️ Harry Potter", "Casa: Gryffindor\nNivel: Alto\nEspecialidad: Defensa\n\"El Elegido\""},
            {"📚 Hermione Granger", "Casa: Gryffindor\nNivel: Experto\nEspecialidad: Encantamientos"},
            {"🎮 Ron Weasley", "Casa: Gryffindor\nNivel: Medio\nEspecialidad: Ajedrez mágico"},
            {"🐍 Draco Malfoy", "Casa: Slytherin\nNivel: Alto\nEspecialidad: Duelos"},
            {"⭐ Dumbledore", "Casa: Gryffindor\nNivel: Maestro\nCargo: Director"},
            {"🧪 Severus Snape", "Casa: Slytherin\nNivel: Avanzado\nCargo: Pociones"},
            {"🐱 McGonagall", "Casa: Gryffindor\nNivel: Avanzado\nCargo: Transformaciones"}
        };

        for (String[] d : datos) {
            JPanel card = new JPanel(new BorderLayout(5, 3));
            card.setBackground(new Color(25, 18, 50));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(PURPURA_MAGIA, 1),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)));
            JLabel nombre = new JLabel(d[0]);
            nombre.setFont(new Font("Serif", Font.BOLD, 13));
            nombre.setForeground(ORO_MAGICO);
            card.add(nombre, BorderLayout.NORTH);
            JLabel info = new JLabel("<html>" + d[1].replace("\n", "<br>") + "</html>");
            info.setFont(new Font("Serif", Font.PLAIN, 11));
            info.setForeground(PLATA_LUNA);
            card.add(info, BorderLayout.CENTER);
            der.add(card);
        }

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, izq, new JScrollPane(der));
        split.setOpaque(false);
        split.setDividerLocation(280);
        panel.add(split, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearTabCriaturas() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.setBorder(tituloBorde("🐉 Criaturas Mágicas de Hogwarts"));

        String[][] criaturas = {
            {"🧝 Dobby", "Elfo Doméstico", "Magia doméstica intensa", "Amistoso"},
            {"🦅 Buckbeak", "Hipogrifo", "Vuelo poderoso", "Neutral - Requiere respeto"},
            {"👻 Dementor", "Dementor", "Absorbe la felicidad", "¡MUY PELIGROSO!"},
            {"🔥 Fawkes", "Fénix", "Canto curativo, renacimiento", "Aliado de Dumbledore"},
            {"🕷️ Acromántula", "Acromántula", "Veneno mortal", "Peligroso"},
            {"🐍 Basilisco", "Basilisco", "Mirada mortal", "¡EXTREMO PELIGRO!"}
        };

        String[] cols = {"Criatura", "Tipo", "Habilidad", "Peligrosidad"};
        DefaultTableModel tableModel = new DefaultTableModel(criaturas, cols) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(tableModel);
        tabla.setFont(new Font("Serif", Font.PLAIN, 13));
        tabla.setForeground(PLATA_LUNA);
        tabla.setBackground(new Color(15, 12, 30));
        tabla.setSelectionBackground(new Color(60, 30, 100));
        tabla.setSelectionForeground(ORO_MAGICO);
        tabla.setRowHeight(30);
        tabla.getTableHeader().setFont(new Font("Serif", Font.BOLD, 13));
        tabla.getTableHeader().setForeground(ORO_MAGICO);
        tabla.getTableHeader().setBackground(new Color(25, 15, 50));
        tabla.setGridColor(new Color(60, 40, 90));
        tabla.setShowGrid(true);

        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearTabInventario() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBorder(tituloBorde("🎒 Inventario del Jugador"));

        JTextArea txtInventario = new JTextArea();
        txtInventario.setFont(new Font("Serif", Font.PLAIN, 14));
        txtInventario.setForeground(PLATA_LUNA);
        txtInventario.setBackground(new Color(12, 10, 25));
        txtInventario.setEditable(false);
        txtInventario.setLineWrap(true);

        Jugador j = controlador.getJugador();
        if (j != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("═══════════════════════════════\n");
            sb.append("   INVENTARIO DE ").append(j.getNombre()).append("\n");
            sb.append("═══════════════════════════════\n\n");
            sb.append("🪄 Objetos Mágicos:\n");
            if (j.getObjetos().isEmpty()) {
                sb.append("   (Sin objetos)\n");
            } else {
                j.getObjetos().forEach(o -> sb.append("   • ").append(o).append("\n"));
            }
            sb.append("\n✨ Hechizos Aprendidos:\n");
            if (j.getHechizosAprendidos().isEmpty()) {
                sb.append("   (Sin hechizos)\n");
            } else {
                j.getHechizosAprendidos().forEach(h -> sb.append("   • ").append(h).append("\n"));
            }
            txtInventario.setText(sb.toString());
        }

        panel.add(new JScrollPane(txtInventario), BorderLayout.CENTER);

        JButton btnActualizar = crearBoton("🔄 Actualizar Inventario", PURPURA_MAGIA);
        btnActualizar.addActionListener(e -> {
            Jugador jug = controlador.getJugador();
            if (jug != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("═══════════════════════════════\n");
                sb.append("   INVENTARIO DE ").append(jug.getNombre()).append("\n");
                sb.append("═══════════════════════════════\n\n");
                sb.append("🪄 Objetos Mágicos:\n");
                jug.getObjetos().forEach(o -> sb.append("   • ").append(o).append("\n"));
                sb.append("\n✨ Hechizos Aprendidos (").append(jug.getHechizosAprendidos().size()).append("):\n");
                jug.getHechizosAprendidos().forEach(h -> sb.append("   • ").append(h).append("\n"));
                sb.append("\n📊 Estadísticas:\n");
                sb.append("   XP Total: ").append(jug.getExperiencia()).append("\n");
                sb.append("   Nivel de Magia: ").append(jug.getNivelMagia()).append("\n");
                sb.append("   Progreso: ").append(jug.getProgreso()).append("%\n");
                txtInventario.setText(sb.toString());
            }
        });
        panel.add(btnActualizar, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearBitacora() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(12, 10, 25));
        panel.setBorder(tituloBorde("📜 Bitácora Mágica"));
        panel.setPreferredSize(new Dimension(0, 155));

        txtBitacora = new JTextArea();
        txtBitacora.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtBitacora.setForeground(VERDE_MAGIA);
        txtBitacora.setBackground(new Color(5, 5, 15));
        txtBitacora.setEditable(false);
        txtBitacora.setLineWrap(true);
        txtBitacora.setWrapStyleWord(true);

        JScrollPane scroll = new JScrollPane(txtBitacora);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(40, 30, 70)));
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    public void actualizarEstado() {
        Jugador j = controlador.getJugador();
        if (j == null) return;

        lblNombreJugador.setText("👤 " + j.getNombre());
        lblCasa.setText("🏠 " + j.getCasaHogwarts());
        lblNivel.setText("⭐ " + j.getNivelMagia());
        lblXP.setText("✨ XP: " + j.getExperiencia());
        lblProgreso.setText("📈 " + j.getProgreso() + "%");
        lblUbicacion.setText("📍 " + (j.getUbicacion() != null ? j.getUbicacion().getNombre() : "Hogwarts"));

        // Actualizar lista de hechizos disponibles
        DefaultListModel<String> modeloDisp = (DefaultListModel<String>) listHechizosDisponibles.getModel();
        modeloDisp.clear();
        for (var h : controlador.getHechizosDisponibles()) {
            modeloDisp.addElement(h.getNombre() + " (" + h.getTipo() + ")");
        }

        // Actualizar hechizos aprendidos
        DefaultListModel<String> modeloApr = (DefaultListModel<String>) listHechizosAprendidos.getModel();
        modeloApr.clear();
        for (var h : j.getHechizosAprendidos()) {
            modeloApr.addElement(h.getNombre() + " (" + h.getTipo() + ")");
        }

        // Bitácora
        List<String> bitacora = controlador.getBitacora();
        StringBuilder sb = new StringBuilder();
        for (String entry : bitacora) sb.append(entry).append("\n");
        txtBitacora.setText(sb.toString());
        txtBitacora.setCaretPosition(txtBitacora.getDocument().getLength());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Hogwarts", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarDescripcionLugar() {
        String lugar = listLugares.getSelectedValue();
        if (lugar == null) return;
        switch (lugar) {
            case "Gran Comedor":
                txtDescripcionLugar.setText("El Gran Comedor es el corazón de Hogwarts. Aquí se reúnen estudiantes y profesores para las comidas. Su techo encantado refleja el cielo exterior.\n\n⚡ Puedes encontrar profesores e interactuar con estudiantes de todas las casas.");
                break;
            case "Bosque Prohibido":
                txtDescripcionLugar.setText("El Bosque Prohibido es un lugar oscuro y peligroso en las afueras de Hogwarts. Alberga centauros, unicornios, arañas gigantes y otros seres mágicos.\n\n⚠️ ¡Peligroso! Solo para estudiantes avanzados.");
                break;
            case "Sala de los Menesteres":
                txtDescripcionLugar.setText("La Sala de los Menesteres es una sala mágica que aparece solo cuando alguien la necesita. Cambia de forma según las necesidades del visitante.\n\n✨ Perfecta para práctica secreta de hechizos.");
                break;
        }
    }

    private void estilizarLista(JList<String> lista) {
        lista.setFont(new Font("Serif", Font.PLAIN, 13));
        lista.setForeground(PLATA_LUNA);
        lista.setBackground(new Color(15, 12, 30));
        lista.setSelectionBackground(new Color(70, 35, 120));
        lista.setSelectionForeground(ORO_MAGICO);
        lista.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    private JButton crearBoton(String texto, Color colorFondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Serif", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorFondo);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorFondo.darker(), 1),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(colorFondo.brighter()); }
            public void mouseExited(MouseEvent e) { btn.setBackground(colorFondo); }
        });
        return btn;
    }

    private TitledBorder tituloBorde(String titulo) {
        TitledBorder b = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PURPURA_MAGIA, 1), titulo);
        b.setTitleFont(new Font("Serif", Font.BOLD | Font.ITALIC, 13));
        b.setTitleColor(ORO_MAGICO);
        return b;
    }
}
