package view;

import model.Cliente;
import model.ClienteTableModel;

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

        JLabel titulo = new JLabel("Sistema Bancário", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(titulo, BorderLayout.NORTH);
        //Caixa de texto
        JPanel painelTexto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTexto.setBackground(new Color(245, 245, 245));
        JLabel labelNome = new JLabel("Buscar cliente:");
        JTextField caixaTexto = new JTextField(20);
        painelTexto.add(labelNome);
        painelTexto.add(caixaTexto);
        add(painelTexto, BorderLayout.BEFORE_FIRST_LINE);
        String clienteCaixa = caixaTexto.getText();
        //
        // Modelo e tabela
        modelo = new ClienteTableModel(gerarClientesExemplo());
        tabela = new JTable(modelo);
        tabela.setRowHeight(30);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

        // Define renderizadores e editores para ícones
        tabela.getColumn("Editar").setCellRenderer(new IconeRenderer("../assets/edit.webp"));
        tabela.getColumn("Vincular").setCellRenderer(new IconeRenderer("../assets/link.webp"));
        tabela.getColumn("Conta").setCellRenderer(new IconeRenderer("../assets/money"));

        tabela.getColumn("Editar").setCellEditor(new IconeEditor(app, modelo, "editar"));
        tabela.getColumn("Vincular").setCellEditor(new IconeEditor(app, modelo, "vincular"));
        tabela.getColumn("Conta").setCellEditor(new IconeEditor(app, modelo, "conta"));

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        add(scroll, BorderLayout.CENTER);
    }

    // Simula dados iniciais
    private List<Cliente> gerarClientesExemplo() {
        List<Cliente> clientes = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Cliente c = new Cliente("Cliente" + i, "Teste" + i, "10427977908", "111.111.111-11", "rua exemplo");
            clientes.add(c);
        }
        return clientes;
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
            return label;
        }
    }
}
