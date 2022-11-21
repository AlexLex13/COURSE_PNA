package tableModel;

import model.Client;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientTableModel implements TableModel{
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Client> clients;

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
        switch (columnIndex) {
            case 0:
                return "Номер паспорта";
            case 1:
                return "Фамилия";
            case 2:
                return "Имя";
            case 3:
                return "Отчество";
            case 4:
                return "Телефон";
            case 5:
                return "Участок";
            case 6:
                return "Дата рождения";
            case 7:
                return "Адрес";
        }
        return "";
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return String.class;
            case 6: return String.class;
            case 7: return String.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex){
            case 0:
                return client.getPassportNumber();
            case 1:
                return client.getSurname();
            case 2:
                return client.getName();
            case 3:
                return client.getLastname();
            case 4:
                return client.getPhone();
            case 5:
                return client.getDistrict();
            case 6:
                return client.getDateOfBirth();
            case 7:
                return client.getAddress();
        }
        return null;
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
