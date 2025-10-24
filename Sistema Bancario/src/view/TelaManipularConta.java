package view;

import javax.swing.*;
import java.awt.*;

public class TelaManipularConta extends JPanel {

    public TelaManipularConta(App app) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Manipular Conta", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
        painel.setBackground(new Color(245, 245, 245));

        painel.add(new JLabel("CPF do Cliente:"));
        JTextField cpf = new JTextField();
        painel.add(cpf);

        painel.add(new JLabel("Valor:"));
        JTextField valor = new JTextField();
        painel.add(valor);

        JButton sacar = new JButton("Sacar");
        JButton depositar = new JButton("Depositar");
        JButton saldo = new JButton("Ver Saldo");
        JButton remunera = new JButton("Remunera");

        painel.add(sacar);
        painel.add(depositar);
        painel.add(saldo);
        painel.add(remunera);

        add(painel, BorderLayout.CENTER);

        JButton voltar = new JButton("Voltar");
        JPanel south = new JPanel();
        south.add(voltar);
        add(south, BorderLayout.SOUTH);

        voltar.addActionListener(e -> app.mostrarTela("principal"));
    }
}
