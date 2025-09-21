package model;

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
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean saca(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
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
