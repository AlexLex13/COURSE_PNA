package tableModel;


import model.Doctor;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoctorTableModel implements TableModel{
    private final Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private final List<Doctor> doctors;

    public DoctorTableModel(List<Doctor> doctors){
        this.doctors = doctors;
    }


    @Override
    public int getRowCount() {
        return doctors.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ID";
            case 1 -> "Имя";
            case 2 -> "Логин";
            case 3 -> "Должность";
            case 4 -> "Кабинет";
            case 5 -> "Участок";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1, 2, 3, 4, 5 -> String.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Doctor doctor = doctors.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> doctor.getUserId();
            case 1 -> doctor.getName();
            case 2 -> doctor.getLogin();
            case 3 -> doctor.getPost();
            case 4 -> doctor.getRoom();
            case 5 -> doctor.getDistrict();
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

    public void addRow(Doctor admin){
        doctors.add(admin);
    }
}
