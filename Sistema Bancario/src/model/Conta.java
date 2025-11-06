package model;

import javax.swing.*;

public abstract class Conta implements ContaInterface {

    protected Cliente dono;
    protected int numero;
    protected double saldo;

    public Conta(Cliente dono, int numero, double depositoInicial) {
        this.dono = dono;
        this.numero = numero;
        this.saldo = depositoInicial;
    }

    @Override
    public boolean deposita(double valor) {
        if (valor > 0) { // Valor depositado deve ser positivo [cite: 50]
            saldo += valor;
            return true;
        }
        // Mostrar mensagem na tela informando usuário [cite: 52]
        JOptionPane.showMessageDialog(null, "Valor de depósito deve ser positivo.", "Erro de Operação", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean saca(double valor) {
        if (valor <= 0) { // Valor sacado deve ser positivo [cite: 51]
            // Mostrar mensagem na tela informando usuário [cite: 52]
            JOptionPane.showMessageDialog(null, "Valor de saque deve ser positivo.", "Erro de Operação", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Se a conta base for usada diretamente, verifica o saldo (mas esta classe é abstrata)
        if (saldo >= valor) {
            saldo -= valor;
            return true;
        }
        // Se a conta base não tiver saldo (e não for CC), retorna false
        return false;
    }

    @Override
    public Cliente getDono() {
        return dono;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    // remunera() será implementado nas subclasses
}
