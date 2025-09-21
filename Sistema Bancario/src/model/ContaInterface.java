package model;

public interface ContaInterface {
    boolean deposita(double valor);
    boolean saca(double valor);
    Cliente getDono();
    int getNumero();
    double getSaldo();
    void remunera();
}
