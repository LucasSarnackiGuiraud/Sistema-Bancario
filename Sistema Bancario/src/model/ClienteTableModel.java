package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    final private String[] colunas = {"ID", "Nome", "Sobrenome", "RG", "CPF", "Endereço", "Salário"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
    }

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Método para atualizar a lista de clientes
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged(); // Notifica a tabela que os dados mudaram
    }

    // Método para obter um cliente em uma linha específica (necessário para atualização/exclusão)
    public Cliente getCliente(int linha) {
        if (linha >= 0 && linha < clientes.size()) {
            return clientes.get(linha);
        }
        return null;
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
    public Object getValueAt(int linha, int coluna) {
        Cliente cliente = clientes.get(linha);

        switch (coluna) {
            case 1: return cliente.getNome();
            case 2: return cliente.getSobrenome();
            case 3: return cliente.getRg();
            case 4: return cliente.getCpf();
            case 5: return cliente.getEndereco();
            default: return null;
        }
    }
}
