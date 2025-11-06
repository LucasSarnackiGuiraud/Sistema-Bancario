package model;

import javax.swing.*;

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente dono, int numero, double depositoInicial, double limite) {
        super(dono, numero, depositoInicial);
        this.limite = limite;
    }

    @Override
    public boolean saca(double valor) {
        if (valor <= 0) {

            return super.saca(valor);
        }

        if (saldo - valor >= -limite) {
            saldo -= valor;
            return true;
        }

        // Mostrar mensagem na tela informando o usuário [cite: 58]
        JOptionPane.showMessageDialog(null,
                "Saque excede o limite disponível de R$ " + limite + ".",
                "Erro no Saque", JOptionPane.ERROR_MESSAGE);

        return false;
    }

    @Override
    public void remunera() {
        saldo += saldo * 0.01; // Aplicar remuneração de 1% ao saldo da conta [cite: 59]
    }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }
}
