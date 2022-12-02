package tableModel;

import model.Client;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientTableModel implements TableModel{
    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private final List<Client> clients;

    public ClientTableModel(List<Client> clients){
        this.clients = clients;
    }


    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Номер паспорта";
            case 1 -> "Имя";
            case 2 -> "Участок";
            case 3 -> "Дата рождения";
            case 4 -> "Адрес";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0, 1, 2, 3, 4 -> String.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> client.getPassportNumber();
            case 1 -> client.getName();
            case 2 -> client.getDistrict();
            case 3 -> client.getDateOfBirth();
            case 4 -> client.getAddress();
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

    public void addRow(Client client){
        clients.add(client);
    }
}
