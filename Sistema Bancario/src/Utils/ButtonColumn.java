package Utils;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private JTable table;
    private ActionListener action;
    private JButton renderButton;
    private JButton editorButton;
    private String text;

    public ButtonColumn(JTable table, ActionListener action, int column) {
        this.table = table;
        this.action = action;

        this.renderButton = new JButton();
        this.editorButton = new JButton();
        this.editorButton.addActionListener(this);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        } else {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        text = (value == null) ? "" : value.toString();
        renderButton.setText(text);

        // Garante que o editor esteja ativo na renderização (Correção de clique)
        table.getColumnModel().getColumn(column).setCellEditor(this);
        table.getColumnModel().getColumn(column).setCellRenderer(this);

        return renderButton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        text = (value == null) ? "" : value.toString();
        editorButton.setText(text);
        return editorButton;
    }

    // MÉTODO OBRIGATÓRIO (CORREÇÃO DE COMPILAÇÃO)
    @Override
    public Object getCellEditorValue() {
        return text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + table.getEditingRow());
        action.actionPerformed(event);
    }
}