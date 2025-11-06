package model;

import java.util.ArrayList;

public class RepositorioDados {

    private static RepositorioDados instance;

    private ArrayList<Cliente> listaClientes;
    private ArrayList<ContaInvestimento> listaContasInvestimento;
    private ArrayList<ContaCorrente> listaContasCorrente;
    private int proximoNumeroConta;

    private RepositorioDados() {
        listaClientes = new ArrayList<>();
        listaContasInvestimento = new ArrayList<>();
        listaContasCorrente = new ArrayList<>();
        proximoNumeroConta = 1001; // Inicia a contagem em 1001
    }

    public static RepositorioDados getInstance() {
        if (instance == null) {
            instance = new RepositorioDados();
        }
        return instance;
    }

    // --- Getters das Listas ---

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<ContaInvestimento> getListaContasInvestimento() {
        return listaContasInvestimento;
    }

    public ArrayList<ContaCorrente> getListaContasCorrente() {
        return listaContasCorrente;
    }

    // --- Métodos de Manipulação (CRUD) ---

    public void adicionarCliente(Cliente cliente) {
        this.listaClientes.add(cliente);
    }

    public void adicionarContaInvestimento(ContaInvestimento contaInvestimento) {
        this.listaContasInvestimento.add(contaInvestimento);
    }

    public void adicionarContaCorrente(ContaCorrente contaCorrente) {
        this.listaContasCorrente.add(contaCorrente);
    }

    public void removerCliente(Cliente cliente) {
        if (cliente == null) return;

        Conta contaDoCliente = cliente.getConta();

        if (contaDoCliente != null) {
            if (contaDoCliente instanceof ContaCorrente) {
                listaContasCorrente.remove((ContaCorrente) contaDoCliente);
            } else if (contaDoCliente instanceof ContaInvestimento) {
                listaContasInvestimento.remove((ContaInvestimento) contaDoCliente);
            }
        }
        listaClientes.remove(cliente);
    }

    // --- Métodos de Busca (Query) ---

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public Conta buscarContaPorCpf(String cpf) {
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            return cliente.getConta();
        }
        return null;
    }

    // --- Métodos Utilitários ---

    /**
     * Gera e retorna um número de conta único e sequencial.
     */
    public int gerarProximoNumeroConta() {
        return this.proximoNumeroConta++;
    }
}