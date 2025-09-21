package model;

public class ContaCorrente extends Conta {

    private double limite;

    public ContaCorrente(Cliente dono, int numero, double depositoInicial, double limite) {
        super(dono, numero, depositoInicial);
        this.limite = limite;
    }

    @Override
    public boolean saca(double valor) {
        if (valor > 0 && saldo - valor >= -limite) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    @Override
    public void remunera() {
        saldo += saldo * 0.01; // 1% de remuneração
    }

    public double getLimite() { return limite; }
    public void setLimite(double limite) { this.limite = limite; }
}
