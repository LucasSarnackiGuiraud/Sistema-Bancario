package view;

import model.Conta;
import model.ContaCorrente;
import model.ContaInvestimento;
import model.RepositorioDados;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class TelaOperacoesConta extends JDialog {

    private Conta conta;
    private JLabel lblSaldoAtual;
    private JTextField txtValor;
    private final DecimalFormat df = new DecimalFormat("R$ #,##0.00");

    public TelaOperacoesConta(Frame owner, Conta conta) {
        super(owner, true);
        this.conta = conta;

        setTitle("Operações da Conta: " + conta.getNumero() + " (" + conta.getDono().getNome() + ")");
        setBounds(250, 250, 400, 300);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel Central (Saldo e Campos)
        JPanel painelCentro = new JPanel(new GridLayout(3, 1, 5, 5));
        
        // 1. Label do Saldo Atual
        lblSaldoAtual = new JLabel();
        painelCentro.add(lblSaldoAtual);

        // 2. Campo de Valor
        painelCentro.add(new JLabel("Valor da Operação (R$):"));
        txtValor = new JTextField(10);
        painelCentro.add(txtValor);
        
        atualizarSaldoVisual();

        add(painelCentro, BorderLayout.NORTH);

        // Painel de Botões (Operações)
        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 10, 10));

        JButton btnSaque = new JButton("Sacar");
        JButton btnDeposito = new JButton("Depositar");
        JButton btnVerSaldo = new JButton("Ver Saldo");
        JButton btnRemunera = new JButton("Remunerar");
        
        btnSaque.addActionListener(e -> efetuarSaque());
        btnDeposito.addActionListener(e -> efetuarDeposito());
        btnVerSaldo.addActionListener(e -> mostrarSaldo());
        btnRemunera.addActionListener(e -> remunerarConta());

        painelBotoes.add(btnSaque);
        painelBotoes.add(btnDeposito);
        painelBotoes.add(btnVerSaldo);
        painelBotoes.add(btnRemunera);

        add(painelBotoes, BorderLayout.CENTER);
        
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        add(btnFechar, BorderLayout.SOUTH);
    }
    
    // --- Métodos de Controle ---
    
    /**
     * Atualiza o JLabel com o saldo atual da conta.
     */
    private void atualizarSaldoVisual() {
        lblSaldoAtual.setText("Saldo Atual: " + df.format(conta.getSaldo()));
    }
    
    /**
     * Requisito 3.iii: Mostra o saldo.
     */
    private void mostrarSaldo() {
        JOptionPane.showMessageDialog(this, 
            "Saldo atual na conta " + conta.getNumero() + ":\n" + df.format(conta.getSaldo()), 
            "Ver Saldo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Tenta obter o valor digitado. Retorna -1 se for inválido.
     */
    private double getValorDigitado() {
        try {
            double valor = Double.parseDouble(txtValor.getText());
            if (valor <= 0) {
                 JOptionPane.showMessageDialog(this, "O valor deve ser positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                 return -1;
            }
            return valor;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Insira um valor numérico válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    /**
     * Requisito 3.i: Efetua um saque.
     */
    private void efetuarSaque() {
        double valor = getValorDigitado();
        if (valor == -1) return;

        // Chama o método saca, que usa o Polimorfismo
        boolean sucesso = conta.saca(valor);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Saque de " + df.format(valor) + " efetuado com sucesso!", "Saque", JOptionPane.INFORMATION_MESSAGE);
            atualizarSaldoVisual();
        } else {
            // A mensagem específica de erro (limite, montante mínimo) deve ser tratada dentro dos métodos saca()
            // das classes ContaCorrente e ContaInvestimento (conforme o Requisito 5.b.i e 5.c.ii)
            JOptionPane.showMessageDialog(this, "Saque não permitido. Verifique saldo ou limite/montante mínimo.", "Erro no Saque", JOptionPane.ERROR_MESSAGE);
        }
        txtValor.setText("");
    }

    /**
     * Requisito 3.ii: Efetua um depósito.
     */
    private void efetuarDeposito() {
        double valor = getValorDigitado();
        if (valor == -1) return;

        // Chama o método deposita, que usa o Polimorfismo
        boolean sucesso = conta.deposita(valor);

        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Depósito de " + df.format(valor) + " efetuado com sucesso!", "Depósito", JOptionPane.INFORMATION_MESSAGE);
            atualizarSaldoVisual();
        } else {
            // Mensagem de erro para depósito (depositoMinimo para Conta Investimento)
            JOptionPane.showMessageDialog(this, "Depósito não permitido. Verifique se atende o depósito mínimo (se for Conta Investimento).", "Erro no Depósito", JOptionPane.ERROR_MESSAGE);
        }
        txtValor.setText("");
    }

    /**
     * Requisito 3.iv: Aplica a remuneração.
     */
    private void remunerarConta() {
        // Chama o método remunera, que usa o Polimorfismo
        conta.remunera(); 
        
        String tipoConta = (conta instanceof ContaCorrente) ? "Conta Corrente (1%)" : "Conta Investimento (2%)";
        
        JOptionPane.showMessageDialog(this, 
            "Remuneração aplicada com sucesso para: " + tipoConta, 
            "Remuneração", JOptionPane.INFORMATION_MESSAGE);
        
        atualizarSaldoVisual();
    }
}