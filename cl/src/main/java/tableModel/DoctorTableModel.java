package tableModel;


import model.Doctor;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoctorTableModel implements TableModel{
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Doctor> doctors;

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
            case 3 -> "Логин";
            case 7 -> "Должность";
            case 8 -> "Кабинет";
            case 9 -> "Участок";
            default -> "";
        };
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 1, 2, 3, 4, 5, 6, 7, 8, 9 -> String.class;
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
            case 2 -> doctor.getName();
            case 4 -> doctor.getLogin();
            case 7 -> doctor.getPost();
            case 8 -> doctor.getRoom();
            case 9 -> doctor.getDistrict();
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
