package triagelogic.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import triagelogic.modelo.Sintoma;
import triagelogic.modelo.Triaje;

public final class Ventana extends JFrame {

    private static final Color FONDO = new Color(245, 247, 250);
    private static final Color URGENTE = new Color(198, 40, 40);
    private static final Color NO_URGENTE = new Color(46, 125, 50);
    private static final Font FUENTE = new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    private final Map<Sintoma, JCheckBox> casillas = new EnumMap<>(Sintoma.class);
    private final Tarjeta tarjeta = new Tarjeta();
    private final JLabel detalle = new JLabel();

    public Ventana() {
        super("Triaje de urgencia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(construirContenido());
        actualizar();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JPanel construirContenido() {
        JPanel raiz = new JPanel(new BorderLayout(0, 16));
        raiz.setBackground(FONDO);
        raiz.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        raiz.add(construirSintomas(), BorderLayout.CENTER);
        raiz.add(construirResultado(), BorderLayout.SOUTH);
        return raiz;
    }

    private JPanel construirSintomas() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 24, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Sintomas del paciente"),
                BorderFactory.createEmptyBorder(8, 12, 12, 12)));
        for (Sintoma sintoma : Sintoma.values()) {
            JCheckBox casilla = new JCheckBox(sintoma.etiqueta());
            casilla.setOpaque(false);
            casilla.setFont(FUENTE);
            casilla.addActionListener(evento -> actualizar());
            casillas.put(sintoma, casilla);
            panel.add(casilla);
        }
        return panel;
    }

    private JPanel construirResultado() {
        JButton limpiar = new JButton("Limpiar");
        limpiar.addActionListener(evento -> {
            casillas.values().forEach(casilla -> casilla.setSelected(false));
            actualizar();
        });
        JPanel pie = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        pie.setOpaque(false);
        pie.add(limpiar);

        detalle.setFont(FUENTE);
        detalle.setVerticalAlignment(SwingConstants.TOP);
        detalle.setPreferredSize(new Dimension(420, 120));

        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setOpaque(false);
        panel.add(tarjeta, BorderLayout.NORTH);
        panel.add(detalle, BorderLayout.CENTER);
        panel.add(pie, BorderLayout.SOUTH);
        return panel;
    }

    private void actualizar() {
        Set<Sintoma> presentes = EnumSet.noneOf(Sintoma.class);
        casillas.forEach((sintoma, casilla) -> {
            if (casilla.isSelected()) {
                presentes.add(sintoma);
            }
        });
        Triaje.Resultado resultado = Triaje.evaluar(presentes);
        tarjeta.mostrar(resultado.urgente());
        detalle.setText(describir(resultado));
    }

    private static String describir(Triaje.Resultado resultado) {
        if (!resultado.urgente()) {
            return "<html><body style='width: 380px'>Ninguna regla de urgencia se activa.</body></html>";
        }
        StringBuilder texto = new StringBuilder("<html><body style='width: 380px'>Reglas activadas:");
        resultado.activas().forEach(regla -> texto.append("<br>").append(regla));
        return texto.append("</body></html>").toString();
    }

    private static final class Tarjeta extends JPanel {

        private final JLabel texto = new JLabel("", SwingConstants.CENTER);

        Tarjeta() {
            super(new BorderLayout());
            setOpaque(false);
            setPreferredSize(new Dimension(420, 84));
            texto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
            texto.setForeground(Color.WHITE);
            add(texto);
        }

        void mostrar(boolean urgente) {
            setBackground(urgente ? URGENTE : NO_URGENTE);
            texto.setText(urgente ? "URGENTE" : "NO URGENTE");
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
            g2.dispose();
        }
    }
}
