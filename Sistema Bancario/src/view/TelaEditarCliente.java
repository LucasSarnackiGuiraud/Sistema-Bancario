package view;

import javax.swing.*;
import java.awt.*;

public class TelaEditarCliente extends JPanel {

    public TelaEditarCliente(App app) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Editar Cliente", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titulo, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        form.setBackground(new Color(245, 245, 245));

        form.add(new JLabel("Nome:"));
        JTextField nome = new JTextField();
        form.add(nome);

        form.add(new JLabel("Sobrenome:"));
        JTextField sobrenome = new JTextField();
        form.add(sobrenome);

        form.add(new JLabel("RG:"));
        JTextField rg = new JTextField();
        form.add(rg);

        form.add(new JLabel("CPF:"));
        JTextField cpf = new JTextField();
        form.add(cpf);

        form.add(new JLabel("Endereço:"));
        JTextField endereco = new JTextField();
        form.add(endereco);

        form.add(new JLabel("Salário:"));
        JTextField salario = new JTextField();
        form.add(salario);

        add(form, BorderLayout.CENTER);

        JButton salvar = new JButton("Salvar");
        JButton voltar = new JButton("Voltar");
        JPanel botoes = new JPanel();
        botoes.add(salvar);
        botoes.add(voltar);

        add(botoes, BorderLayout.SOUTH);

        voltar.addActionListener(e -> app.mostrarTela("principal"));
    }
}
