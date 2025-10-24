package view;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public App() {
        setTitle("Sistema Bancário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Instancia as telas
        TelaMenu telaMenu = new TelaMenu(this);
        TelaClientes telaClientes = new TelaClientes(this);
        TelaVincularConta telaVincular = new TelaVincularConta(this);
        TelaManipularConta telaManipular = new TelaManipularConta(this);

        // Adiciona cada tela ao CardLayout
        mainPanel.add(telaMenu, "menu");
        mainPanel.add(telaClientes, "clientes");
        mainPanel.add(telaVincular, "vincular");
        mainPanel.add(telaManipular, "manipular");

        add(mainPanel);
        setVisible(true);
    }

    // Método público para trocar de tela
    public void mostrarTela(String nomeTela) {
        cardLayout.show(mainPanel, nomeTela);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
