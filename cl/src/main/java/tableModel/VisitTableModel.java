package tableModel;

import model.Client;
import model.Visits;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VisitTableModel implements TableModel{
    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private final List<Client> clients;

    private final List<Visits> visits;

    public VisitTableModel(List<Visits> visits , List<Client> clients){
        this.visits = visits;
        this.clients = clients;
    }

    public String getSurname(Visits visit){
        for (Client client : clients) {
            if (visit.getClient_id() == client.getId()) {
                return client.getName();
            }
        }
        return "";
    }



    @Override
    public int getRowCount() {
        return visits.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "Дата";
            case 1 -> "Время";
            case 2 -> "Дата регистрации";
            case 3 -> "Имя";
            case 4 -> "Комментарий";
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
        Visits visit = visits.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> visit.getDate();
            case 1 -> visit.getTime();
            case 2 -> visit.getRegistrationDate();
            case 3 -> getSurname(visit);
            case 4 -> visit.getComment();
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

}
