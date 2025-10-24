package view;

import javax.swing.*;
import java.awt.*;

public class TelaVincularConta extends JPanel {

    public TelaVincularConta(App app) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Vincular Cliente à Conta", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 200, 30, 200));
        painel.setBackground(new Color(245, 245, 245));

        painel.add(new JLabel("Tipo de Conta:"));
        JComboBox<String> tipoConta = new JComboBox<>(new String[] { "Conta Corrente", "Conta Investimento" });
        painel.add(tipoConta);

        painel.add(new JLabel("Depósito Inicial:"));
        JTextField depositoInicial = new JTextField();
        painel.add(depositoInicial);

        painel.add(new JLabel("Limite / Montante Mínimo:"));
        JTextField campoExtra = new JTextField();
        painel.add(campoExtra);

        painel.add(new JLabel("Depósito Mínimo (se aplicável):"));
        JTextField depositoMinimo = new JTextField();
        painel.add(depositoMinimo);

        add(painel, BorderLayout.CENTER);

        JButton criar = new JButton("Criar Conta");
        JButton voltar = new JButton("Voltar");
        JPanel botoes = new JPanel();
        botoes.add(criar);
        botoes.add(voltar);

        add(botoes, BorderLayout.SOUTH);

        voltar.addActionListener(e -> app.mostrarTela("principal"));
    }
}
