package view;

import model.Cliente;
import model.ClienteTableModel;
import model.RepositorioDados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarCliente extends JPanel {

    private ClienteTableModel modeloDaTabela;

    public TelaEditarCliente(App app, ClienteTableModel modeloRecebido) {

        this.modeloDaTabela = modeloRecebido;
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


        add(form, BorderLayout.CENTER);

        JButton salvar = new JButton("Salvar");
        JButton voltar = new JButton("Voltar");
        JPanel botoes = new JPanel();
        botoes.add(salvar);
        botoes.add(voltar);

        add(botoes, BorderLayout.SOUTH);

        voltar.addActionListener(e -> app.mostrarTela("principal"));

        salvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome_g = nome.getText();
                String sobrenome_g = sobrenome.getText();
                String rg_g = rg.getText();
                String cpf_g = cpf.getText();
                String endereco_g = endereco.getText();
                //
                Cliente c =  new Cliente(nome_g, sobrenome_g, rg_g, cpf_g, endereco_g);


                JOptionPane.showMessageDialog(app, "Cliente salvo com sucesso!");
                modeloDaTabela.atualizarTabela();
            }
        });
    }
}
