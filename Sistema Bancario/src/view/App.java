package view;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public App() {
        setTitle("Sistema Bancário");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Instancia as telas
        TelaPrincipal telaPrincipal = new TelaPrincipal(this);
        TelaEditarCliente telaEditarCliente = new TelaEditarCliente(this);
        TelaVincularConta telaVincularConta = new TelaVincularConta(this);
        TelaManipularConta telaManipularConta = new TelaManipularConta(this);

        // Adiciona todas ao painel principal
        mainPanel.add(telaPrincipal, "principal");
        mainPanel.add(telaEditarCliente, "editar");
        mainPanel.add(telaVincularConta, "vincular");
        mainPanel.add(telaManipularConta, "manipular");

        add(mainPanel);
        cardLayout.show(mainPanel, "principal");

        setVisible(true);
    }

    public void mostrarTela(String nome) {
        cardLayout.show(mainPanel, nome);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}
