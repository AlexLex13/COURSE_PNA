package tableModel;

import model.Admin;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminTableModel implements TableModel {

    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private final List<Admin> admins;

    public AdminTableModel(List<Admin> admins){
        this.admins = admins;
    }


    @Override
    public int getRowCount() {
        return admins.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Имя";
            case 2 -> "Логин";
            case 3 -> "Права доступа";
            case 4 -> "Блокировка";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1, 2, 3, 4 -> String.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Admin admin = admins.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> admin.getUserId();
            case 1 -> admin.getName();
            case 2 -> admin.getLogin();
            case 3 -> admin.getRights();
            case 4 -> admin.getBlock();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void addRow(Admin admin){
        admins.add(admin);
    }
}
