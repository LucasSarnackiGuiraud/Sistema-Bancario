package view;

import model.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastrarCliente extends JPanel {
    private String nome_g;
    private String sobrenome_g;
    private String rg_g;
    private String cpf_g;
    private String endereco_g;
    public TelaCadastrarCliente(App app) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JLabel titulo = new JLabel("Cadastrar Cliente", SwingConstants.CENTER);
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

        //comentado pois não aparece na tela principal
        /*form.add(new JLabel("Salário:"));
        JTextField salario = new JTextField();
        form.add(salario); */

        add(form, BorderLayout.CENTER);

        JButton salvar = new JButton("Salvar");
        JButton voltar = new JButton("Voltar");
        JPanel botoes = new JPanel();
        botoes.add(salvar);
        botoes.add(voltar);

        add(botoes, BorderLayout.SOUTH);

        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome.setText("");
                sobrenome.setText("");
                rg.setText("");
                cpf.setText("");
                endereco.setText("");
                app.mostrarTela("principal");
            }
        });

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nome_g = nome.getText();
                sobrenome_g = sobrenome.getText();
                rg_g = rg.getText();
                cpf_g = cpf.getText();
                endereco_g = endereco.getText();
                //Cliente c =  new Cliente(nome_g, sobrenome_g, rg_g, cpf_g, endereco_g);
                System.out.println(nome_g + " " + sobrenome_g  + " " + rg_g + " " + cpf_g + " " + endereco_g);
                nome.setText("");
                sobrenome.setText("");
                rg.setText("");
                cpf.setText("");
                endereco.setText("");
                app.mostrarTela("principal");
            }
        });
    }
}
