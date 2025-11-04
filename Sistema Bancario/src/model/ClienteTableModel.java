package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    private List<Cliente> clientesOriginais; // Não precisa inicializar aqui
    final private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereço", "Editar", "Vincular", "Conta"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
        this.clientesOriginais = new ArrayList<>(); // Inicializa ambas
    }

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
        this.clientesOriginais = new ArrayList<>(clientes);
    }

    // Método para atualizar a lista de clientes
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        this.clientesOriginais = new ArrayList<>(clientes);
        fireTableDataChanged();
    }

    // Método para obter um cliente em uma linha específica (necessário para atualização/exclusão)
    public Cliente getCliente(int linha) {
        if (linha >= 0 && linha < clientes.size()) {
            return clientes.get(linha);
        }
        return null;
    }

    //Busca cliente pela pesquisa
    public void filtrarPorNome(String nomeBusca) {
        if (nomeBusca == null || nomeBusca.trim().isEmpty()) {
            // Restaura da lista original
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String buscaEmMinusculo = nomeBusca.toLowerCase();
            // Filtra a partir da lista original
            for (Cliente c : this.clientesOriginais) {
                if (c.getNome().toLowerCase().contains(buscaEmMinusculo)) {
                    clientesFiltrados.add(c);
                }
            }
            this.clientes = clientesFiltrados;
        }
        fireTableDataChanged();
    }

    public void filtrarPorCpf(String cpfBusca) {
        if (cpfBusca == null || cpfBusca.trim().isEmpty()) {
            // Restaura a lista original
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String buscaSemEspacos = cpfBusca.replaceAll("\\s+", ""); // remove espaços
            // Filtra a partir da lista original
            for (Cliente c : this.clientesOriginais) {
                if (c.getCpf().replaceAll("\\s+", "").contains(buscaSemEspacos)) {
                    clientesFiltrados.add(c);
                }
            }
            this.clientes = clientesFiltrados;
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente c = clientes.get(rowIndex);
        switch (columnIndex) {
            case 0: return c.getNome();
            case 1: return c.getSobrenome();
            case 2: return c.getRg();
            case 3: return c.getCpf();
            case 4: return c.getEndereco();
            case 5: return null; // editar
            case 6: return null; // vincular
            case 7: return null; // conta
            default: return null;
        }
    }
}