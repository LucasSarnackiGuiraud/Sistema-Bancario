package view;

import javax.swing.*;
import java.awt.*;

public class TelaVincularConta extends JPanel {
    public TelaVincularConta(App app) {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Vincular Cliente à Conta", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> app.mostrarTela("menu"));

        add(titulo, BorderLayout.NORTH);
        add(voltar, BorderLayout.SOUTH);
    }
}
