package view;

import javax.swing.*;
import java.awt.*;

public class TelaManipularConta extends JPanel {
    public TelaManipularConta(App app) {
        setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Manipular Conta", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> app.mostrarTela("menu"));

        add(titulo, BorderLayout.NORTH);
        add(voltar, BorderLayout.SOUTH);
    }
}
