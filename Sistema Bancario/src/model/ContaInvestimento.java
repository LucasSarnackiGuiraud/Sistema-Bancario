package model;

import javax.swing.*;

public class ContaInvestimento extends Conta {

    private double montanteMinimo;
    private double depositoMinimo;

    public ContaInvestimento(Cliente dono, int numero, double depositoInicial, double montanteMinimo, double depositoMinimo) {
        super(dono, numero, depositoInicial);
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
    }

    @Override
    public boolean deposita(double valor) {
        // Recebe como parâmetro o valor a ser depositado [cite: 61]

        if (valor >= depositoMinimo) { // Se o valor for maior ou igual ao depositoMinimo [cite: 62]
            return super.deposita(valor); // Chama o método deposita da classe pai [cite: 63]
        }

        JOptionPane.showMessageDialog(null,
                "Depósito mínimo exigido é de R$ " + depositoMinimo + ".",
                "Erro no Depósito", JOptionPane.ERROR_MESSAGE);

        return false;
    }

    @Override
    public boolean saca(double valor) {
        // Recebe como parâmetro o valor a ser sacado [cite: 65]

        if (valor <= 0) {
            // Reusa a validação da classe pai (Conta)
            return super.saca(valor);
        }

        // Se o novo valor do saldo (considerando o saque) for maior ou igual ao montante Minimo [cite: 66, 67]
        if ((saldo - valor) >= montanteMinimo) {
            return super.saca(valor); // Invoque o método saque da classe pai [cite: 67]
        }

        // Caso contrário, deve-se retornar false [cite: 68]
        // Mostrar mensagem na tela informando usuário [cite: 68]
        JOptionPane.showMessageDialog(null,
                "Saque não permitido. O saldo remanescente deve ser maior ou igual ao Montante Mínimo de R$ " + montanteMinimo + ".",
                "Erro no Saque", JOptionPane.ERROR_MESSAGE);

        return false;
    }

    @Override
    public void remunera() {
        saldo += saldo * 0.02; // Aplicar remuneração de 2% ao saldo da conta [cite: 69]
    }

    public double getMontanteMinimo() { return montanteMinimo; }
    public void setMontanteMinimo(double montanteMinimo) { this.montanteMinimo = montanteMinimo; }

    public double getDepositoMinimo() { return depositoMinimo; }
    public void setDepositoMinimo(double depositoMinimo) { this.depositoMinimo = depositoMinimo; }
}
