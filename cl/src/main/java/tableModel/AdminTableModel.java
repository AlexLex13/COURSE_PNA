package tableModel;

import model.Admin;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AdminTableModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Admin> admins;

    public AdminTableModel(List<Admin> admins){
        this.admins = admins;
    }


    @Override
    public int getRowCount() {
        return admins.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Имя";
            case 4 -> "Логин";
            case 7 -> "Права доступа";
            case 8 -> "Блокировка";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1, 2, 3, 4, 5, 6, 7, 8 -> String.class;
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
            case 2 -> admin.getName();
            case 4 -> admin.getLogin();
            case 7 -> admin.getRights();
            case 8 -> admin.getBlock();
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
