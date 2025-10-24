package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    final private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereço", "Editar", "Vincular", "Conta"};

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
