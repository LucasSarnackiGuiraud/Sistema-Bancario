// Ajuste o nome do pacote para corresponder ao seu projeto
package model;

import java.util.ArrayList;

import model.Cliente;
import model.ContaCorrente;
import model.ContaInvestimento;

public class RepositorioDados {

    private static RepositorioDados instance;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<ContaInvestimento> listaContasInvestimento;
    private ArrayList<ContaCorrente> listaContasCorrente;

    private RepositorioDados() {
        listaClientes = new ArrayList<>();
        listaContasInvestimento = new ArrayList<>();
        listaContasCorrente = new ArrayList<>();
    }

    public static RepositorioDados getInstance() {
        if (instance == null) {
            instance = new RepositorioDados();
        }
        return instance;
    }

    //getters
    
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<ContaInvestimento> getListaContasInvestimento() {
        return listaContasInvestimento;
    }

    public void setListaContasInvestimento(ArrayList<ContaInvestimento> listaContasInvestimento) {
        this.listaContasInvestimento = listaContasInvestimento;
    }

    public ArrayList<ContaCorrente> getListaContasCorrente() {
        return listaContasCorrente;
    }


    // Métodos de Atalho

    public void adicionarCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    public void adicionarContaInvestimento(ContaInvestimento contaInvestimento) {
        this.listaContasInvestimento.add(contaInvestimento);
    }

    public void adicionarContaCorrente(ContaCorrente contaCorrente) {
        this.listaContasCorrente.add(contaCorrente);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
}