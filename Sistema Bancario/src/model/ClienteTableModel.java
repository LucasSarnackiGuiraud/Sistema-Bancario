package model;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;
    private List<Cliente> clientesOriginais;
    final private String[] colunas = {"Nome", "Sobrenome", "RG", "CPF", "Endereço", "Editar", "Vincular", "Conta"};

    public ClienteTableModel() {
        this.clientes = new ArrayList<>();
        this.clientesOriginais = new ArrayList<>();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        this.clientesOriginais = new ArrayList<>(clientes);
        fireTableDataChanged();
    }

    public Cliente getCliente(int linha) {
        if (linha >= 0 && linha < clientes.size()) {
            return clientes.get(linha);
        }
        return null;
    }

    public void filtrarPorNome(String nomeBusca) {
        if (nomeBusca == null || nomeBusca.trim().isEmpty()) {
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String buscaEmMinusculo = nomeBusca.toLowerCase();
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
            this.clientes = new ArrayList<>(this.clientesOriginais);
        } else {
            List<Cliente> clientesFiltrados = new ArrayList<>();
            String buscaSemEspacos = cpfBusca.replaceAll("\\s+", "");
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
            case 5: return "Editar"; // Texto do botão
            case 6: return "Vincular"; // Texto do botão
            case 7: return "Operar"; // Texto do botão
            default: return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Colunas 5 (Editar), 6 (Vincular) e 7 (Conta) são botões
        return columnIndex == 5 || columnIndex == 6 || columnIndex == 7;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Colunas 5 (Editar), 6 (Vincular) e 7 (Operar)
        if (columnIndex == 5 || columnIndex == 6 || columnIndex == 7) {
            return Object.class;
        }
        // Para todas as outras colunas, retorna String.class (ou o tipo de dado real)
        return String.class;
    }
}

