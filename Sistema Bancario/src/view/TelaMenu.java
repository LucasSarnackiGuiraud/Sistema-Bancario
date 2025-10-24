package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaMenu extends JPanel {
    public TelaMenu(App app) {
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Sistema Bancário", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.PLAIN, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel painelCards = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        painelCards.setBackground(new Color(245, 245, 245));

        painelCards.add(criarCard("Manter Clientes", () -> app.mostrarTela("clientes")));
        painelCards.add(criarCard("Vincular Clientes", () -> app.mostrarTela("vincular")));
        painelCards.add(criarCard("Manipular Conta", () -> app.mostrarTela("manipular")));

        add(painelCards, BorderLayout.CENTER);
    }

    private JPanel criarCard(String texto, Runnable acaoClique) {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
                g2.setColor(new Color(220, 220, 220));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 25, 25);
            }
        };

        card.setPreferredSize(new Dimension(200, 300));
        card.setOpaque(false);
        card.setLayout(new GridBagLayout());

        JLabel label = new JLabel("<html><div style='text-align: center;'>" + texto + "</div></html>");
        label.setFont(new Font("SansSerif", Font.PLAIN, 18));
        card.add(label);

        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { card.setBackground(new Color(250, 250, 250)); card.setOpaque(true); card.repaint(); }
            @Override public void mouseExited(MouseEvent e) { card.setOpaque(false); card.repaint(); }
            @Override public void mouseClicked(MouseEvent e) { acaoClique.run(); }
        });

        return card;
    }
}
