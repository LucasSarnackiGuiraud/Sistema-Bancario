package model;

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
        if (valor >= depositoMinimo) {
            return super.deposita(valor);
        }
        return false;
    }

    @Override
    public boolean saca(double valor) {
        if (valor > 0 && (saldo - valor) >= montanteMinimo) {
            return super.saca(valor);
        }
        return false;
    }

    @Override
    public void remunera() {
        saldo += saldo * 0.02; // 2% de remuneração
    }

    public double getMontanteMinimo() { return montanteMinimo; }
    public void setMontanteMinimo(double montanteMinimo) { this.montanteMinimo = montanteMinimo; }

    public double getDepositoMinimo() { return depositoMinimo; }
    public void setDepositoMinimo(double depositoMinimo) { this.depositoMinimo = depositoMinimo; }
}
