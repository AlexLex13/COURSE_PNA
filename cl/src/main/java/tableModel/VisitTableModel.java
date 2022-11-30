package tableModel;

import model.Client;
import model.Visits;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VisitTableModel implements TableModel{
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Client> clients;

    private List<Visits> visits;

    public VisitTableModel(List<Visits> visits , List<Client> clients){
        this.visits = visits;
        this.clients = clients;
    }

    public String getSurname(Visits visit){
        for (int i = 0; i < clients.size(); i++) {
            if (visit.getClient_id() == clients.get(i).getId()) {
                return clients.get(i).getName();
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
        switch (columnIndex) {
            case 0:
                return "Дата";
            case 1:
                return "Время";
            case 2:
                return "Дата регистрации";
            case 3:
                return "Фамилия";
            case 4:
                return "Комментарий";
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
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Visits visit = visits.get(rowIndex);
        switch (columnIndex){
            case 0:
                return visit.getDate();
            case 1:
                return visit.getTime();
            case 2:
                return visit.getRegistrationDate();
            case 3:
                return getSurname(visit);
            case 4:
                return visit.getComment();
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

}
