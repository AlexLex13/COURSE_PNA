package tableModel;

import model.Schedule;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class addRecordTableModel implements TableModel{

    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private final List<Schedule> schedule;

    public addRecordTableModel(List<Schedule> schedule){
        this.schedule = schedule;
    }

    @Override
    public int getRowCount() {
        return schedule.size();
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
            case 2 -> "Дата регистрации записи";
            case 3 -> "Номер паспорта клиента";
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
        Schedule sch = schedule.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> sch.getDate();
            case 1 -> sch.getTime();
            case 2 -> sch.getRegistrationTime();
            case 3 -> sch.getPassportNumber();
            case 4 -> sch.getComment();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}

    @Override
    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }
}
