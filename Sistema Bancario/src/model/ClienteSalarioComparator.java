package model;

import java.util.Comparator;

public class ClienteSalarioComparator implements Comparator<Cliente> {

    @Override
    public int compare(Cliente c1, Cliente c2) {
        double salario1 = c1.getSalario();
        double salario2 = c2.getSalario();

        if (salario1 < salario2) {
            return 1;
        } else if (salario1 > salario2) {
            return -1;
        } else {
            return 0;
        }
    }
}