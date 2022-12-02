package view;

import model.*;
import org.jfree.data.category.DefaultCategoryDataset;
import tableModel.VisitTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DoctorFrame extends JFrame{
    private JPanel mainPanel;
    private JButton closeFrameButton;
    private JTextField myNameField;
    private JButton editMyPersonalDataButton;
    private JTextField myLoginField;
    private JPasswordField myPasswordField1;
    private JPasswordField myPasswordField2;
    private JButton editMyAuthorizationDataButton;
    private JTextField myPostField;
    private JTextField myRoomField;
    private JTextField myDistrictField;
    private JLabel moIn, moOut, tuIn, tuOut, weIn, weOut, thIn, thOut, frIn, frOut, saIn, saOut, suIn, suOut;
    private JTable visitsTable;
    private JButton statsButton;
    private JTabbedPane tabbedPane1;
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private final ObjectOutputStream output = MainFrame.output;
    private final ObjectInputStream input = MainFrame.input;
    private ArrayList<Visits> visits = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();
    private final int USER_ID;

    //-------------------------------ИНИЦИАЛИЗАЦИЯ ФРЕЙМА-------------------------------


    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Врач");
        setContentPane(mainPanel);
        setResizable(false);
        readData();
        TableModel clientsModel = new VisitTableModel(visits, clients);
        visitsTable.setModel(clientsModel);
        pack();
        setLocationRelativeTo(null);
    }


    public DoctorFrame(int user_id) {
        this.USER_ID = user_id;
        initComponents();
        editMyPersonalDataButton.addActionListener(e -> editMyPersonalDataActionPerformed());
        editMyAuthorizationDataButton.addActionListener(e -> editMyAuthorizationDataActionPerformed());
        closeFrameButton.addActionListener(e -> closeFrameActionPerformed());
        statsButton.addActionListener(e -> statsButtonActionPerformed());
    }

    public void readData(){
        try{
            output.writeObject("getAllDoctors");

            this.doctors = (ArrayList<Doctor>) input.readObject();
            for (Doctor value : doctors) {
                if (USER_ID == value.getUserId()) {
                    myNameField.setText(value.getName());
                    myLoginField.setText(value.getLogin());
                    myPasswordField1.setText(value.getPassword());
                    myPasswordField2.setText(value.getPassword());
                    myPostField.setText(value.getPost());
                    myRoomField.setText(value.getRoom());
                    myDistrictField.setText(value.getDistrict());
                    moIn.setText(value.getSchedule()[0]);
                    moOut.setText(value.getSchedule()[1]);
                    tuIn.setText(value.getSchedule()[2]);
                    tuOut.setText(value.getSchedule()[3]);
                    weIn.setText(value.getSchedule()[4]);
                    weOut.setText(value.getSchedule()[5]);
                    thIn.setText(value.getSchedule()[6]);
                    thOut.setText(value.getSchedule()[7]);
                    frIn.setText(value.getSchedule()[8]);
                    frOut.setText(value.getSchedule()[9]);
                    saIn.setText(value.getSchedule()[10]);
                    saOut.setText(value.getSchedule()[11]);
                    suIn.setText(value.getSchedule()[12]);
                    suOut.setText(value.getSchedule()[13]);
                }
            }
            output.writeObject("getAllUsers");
            this.users = (ArrayList<User>) input.readObject();
            output.writeObject("getAllAdmins");
            this.admins = (ArrayList<Admin>) input.readObject();
            output.writeObject("getAllVisits");
            this.visits = (ArrayList<Visits>) input.readObject();
            output.writeObject("getAllClients");
            this.clients = (ArrayList<Client>) input.readObject();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private Boolean checkLogin(String login) {
        if (login.equals("")) {
            JOptionPane.showMessageDialog(null, "Вы не ввели логин!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (login.length() <= 4 || login.length() >= 15) {
            JOptionPane.showMessageDialog(null, "Логин должен быть больше 4 и меньше 15 символов!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            for (Admin admin : admins) {
                if (login.equals(admin.getLogin())) {
                    JOptionPane.showMessageDialog(null, "Данный логин уже есть в системе!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            for (User user : users) {
                if (login.equals(user.getLogin())) {
                    JOptionPane.showMessageDialog(null, "Данный логин уже есть в системе!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            for (Doctor doctor : doctors) {
                if (login.equals(doctor.getLogin())) {
                    JOptionPane.showMessageDialog(null, "Данный логин уже есть в системе!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }
    }

    private Boolean checkPassword(String password, String provePassword) {
        if(password.equals("") || provePassword.equals("")) {
            JOptionPane.showMessageDialog(null, "Вы не ввели пароль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(password.length() <= 4 || password.length() >= 15) {
            JOptionPane.showMessageDialog(null, "Пароль должен быть больше 4 и меньше 15 символов!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(!password.equals(provePassword)){
            JOptionPane.showMessageDialog(null, "Пароль и его подтверждение не совпадают!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else return true;
    }

    private void editMyPersonalDataActionPerformed(){
        if(myNameField.isEditable()) {
            try {
                User user = new User();
                user.setId(USER_ID);
                user.setName(myNameField.getText());
                output.writeObject("updatePerson");
                output.writeObject(user);
                String result = (String) input.readObject();
                JOptionPane.showMessageDialog(null, result, "Результат", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("Успешно сохранено!")) {
                    for (int i = 0; i < doctors.size(); i++) {
                        if (USER_ID == doctors.get(i).getUserId()) {
                            Doctor doctor = doctors.get(i);
                            doctor.setName(user.getName());
                            doctors.set(i, doctor);
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            myNameField.setEditable(false);
            editMyPersonalDataButton.setText("Редактировать личные данные");
        }
        else{
            myNameField.setEditable(true);
            editMyPersonalDataButton.setText("Сохранить");
        }
    }

    public void refreshData(){
        admins.clear();
        users.clear();
        doctors.clear();
        readData();
    }

    private void editMyAuthorizationDataActionPerformed(){
        if(myLoginField.isEditable()){
            if(!checkLogin(myLoginField.getText())) return;
            if(!checkPassword(myPasswordField1.getText(), myPasswordField2.getText())) return;
            try{
                ObjectOutputStream output = MainFrame.output;
                ObjectInputStream input = MainFrame.input;
                User user = new User();
                user.setId(USER_ID);
                user.setLogin(myLoginField.getText());
                user.setPassword(myPasswordField1.getText());
                output.writeObject("updateMyUserData");
                output.writeObject(user);
                String result = (String) input.readObject();
                JOptionPane.showMessageDialog(null, result, "Результат", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("Успешно сохранено!")) {
                    for (int i = 0; i < doctors.size(); i++) {
                        if (USER_ID == doctors.get(i).getUserId()) {
                            Doctor doctor = doctors.get(i);
                            doctor.setLogin(user.getLogin());
                            doctors.set(i, doctor);
                        }
                    }
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
            myLoginField.setEditable(false);
            myPasswordField1.setEditable(false);
            myPasswordField2.setEditable(false);
            editMyAuthorizationDataButton.setText("Редактировать данные авторизации");
        }
        else{
            myLoginField.setEditable(true);
            myPasswordField1.setEditable(true);
            myPasswordField2.setEditable(true);
            editMyAuthorizationDataButton.setText("Сохранить");
        }
    }

    private void closeFrameActionPerformed(){
        new MainFrame().setVisible(true);
        dispose();
    }

    private void statsButtonActionPerformed(){
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(clients.size(), "", "Количество клиентов");
        dataSet.setValue(visits.size(), "", "Количество записей на будущее время");
        MainFrame.createGraph(dataSet, "Статистика активности клиентов у текущего врача");
    }
}
