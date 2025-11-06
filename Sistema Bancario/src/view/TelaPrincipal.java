package view;

import model.Cliente;
import model.ClienteTableModel;
import model.RepositorioDados;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class TelaPrincipal extends JPanel {

    private JTable tabela;
    private ClienteTableModel modelo;
    private App app;

    public TelaPrincipal(App app) {
        this.app = app;

        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.Y_AXIS)); // Layout vertical
        painelSuperior.setBackground(new Color(245, 245, 245));

        // Título
        JLabel titulo = new JLabel("Sistema Bancário", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 15, 0));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel de Texto (Busca)
        JPanel painelTexto = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelTexto.setBackground(new Color(245, 245, 245));
        JLabel labelNome = new JLabel("Buscar cliente por Cpf:");
        JTextField caixaTexto = new JTextField(20);
        painelTexto.add(labelNome);
        painelTexto.add(caixaTexto);

        // Botão de pesquisa
        JButton botaoBuscar = new JButton("Buscar");
        painelTexto.add(botaoBuscar);
        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String clienteBusca = caixaTexto.getText();
                modelo.filtrarPorCpf(clienteBusca);
                modelo.fireTableDataChanged();
            }
        });
        // Botão de Cadastro Cliente
        JButton botaoCadastrar = new JButton("Cadastrar");
        painelTexto.add(botaoCadastrar);
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.mostrarTela("cadastrarCliente");
            }
        });

        // Adiciona título e painel de texto ao painel superior
        painelSuperior.add(titulo);
        painelSuperior.add(painelTexto);
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Espaço abaixo da busca

        // Adiciona o painel superior ao NORTE
        add(painelSuperior, BorderLayout.NORTH);

        // Modelo e tabela
        modelo = new ClienteTableModel(RepositorioDados.getInstance().getListaClientes());
        tabela = new JTable(modelo);
        tabela.setRowHeight(30);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        // Define renderizadores e editores para ícones
        JButton buttonEditar  = new JButton("Editar");
        add(buttonEditar, BorderLayout.SOUTH);
        buttonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
                int linha = tabela.getSelectedRow();
                System.out.println(linha);
                System.out.println();
                app.mostrarTela("editar");
            }
        });
        //


        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40)); // Remove borda superior
        add(scroll, BorderLayout.CENTER);
    }

    private static class IconeRenderer extends DefaultTableCellRenderer {
        private ImageIcon icone;

        public IconeRenderer(String caminhoIcone) {
            try {
                icone = new ImageIcon(caminhoIcone);
            } catch (Exception e) {
                icone = null;
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setIcon(icone);
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.setOpaque(true);
            label.setBackground(isSelected ? new Color(220, 220, 255) : Color.WHITE);
            return label;
        }
    }

    private static class IconeEditor extends AbstractCellEditor implements TableCellEditor {
        private JLabel label = new JLabel();
        private String acao;
        private App app;
        private ClienteTableModel modelo;
        private int linhaSelecionada;

        public IconeEditor(App app, ClienteTableModel modelo, String acao) {
            this.app = app;
            this.modelo = modelo;
            this.acao = acao;
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    fireEditingStopped();
                    Cliente cliente = modelo.getCliente(linhaSelecionada);
                    if (cliente == null) return;

                    switch (acao) {
                        case "editar":
                            app.mostrarTela("editar");
                            break;
                        case "vincular":
                            app.mostrarTela("vincular");
                            break;
                        case "conta":
                            app.mostrarTela("manipular");
                            break;
                    }
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            this.linhaSelecionada = row;
            label.setOpaque(true);
            label.setBackground(isSelected ? new Color(220, 220, 255) : Color.WHITE);
            IconeRenderer renderer = (IconeRenderer) table.getCellRenderer(row, column);
            label.setIcon(renderer.icone);
            return label;
        }
    }
}