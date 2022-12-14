package view;

import model.Admin;
import model.Doctor;
import model.User;
import org.jfree.data.category.DefaultCategoryDataset;
import tableModel.AdminTableModel;
import tableModel.DoctorTableModel;
import tableModel.UserTableModel;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AdminFrame extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane newDoctor;
    private JButton closeFrameButton;
    private JTextField myNameField;
    private JButton editMyPersonalDataButton;
    private JTextField myLoginField;
    private JPasswordField myPasswordField1;
    private JPasswordField myPasswordField2;
    private JButton editMyAuthorizationDataButton;
    private JTextField myRightsField;
    private JTextField myBlockField;
    private JTable tableAdmins;
    private JTable tableUsers;
    private JButton addNewAdminButton;
    private JButton clearNewAdminFormButton;
    private JTextField newAdminLoginField;
    private JPasswordField newAdminPasswordField1;
    private JPasswordField newAdminPasswordField2;
    private JComboBox newAdminRightsComboBox;
    private JComboBox newAdminBlockComboBox;
    private JTextField newAdminNameField;
    private JCheckBox deleteAdminCheckBox;
    private JButton deleteAdminButton;
    private JTextField editAdminLoginField;
    private JComboBox editAdminRightsComboBox;
    private JComboBox editAdminBlockComboBox;
    private JTextField editAdminNameField;
    private JButton editAdminButton;
    private JPasswordField editAdminPasswordField1;
    private JPasswordField editAdminPasswordField2;
    private JButton editAdminPasswordButton;
    private JButton statsAdminBlockButton;
    private JButton statsAdminRightsButton;
    private JTextField newUserLoginField;
    private JPasswordField newUserPasswordField1;
    private JPasswordField newUserPasswordField2;
    private JTextField newUserNameField;
    private JButton addNewUserButton;
    private JButton clearNewUserFormButton;
    private JTextField editUserLoginField;
    private JTextField editUserNameField;
    private JButton editUserButton;
    private JPasswordField editUserPasswordField1;
    private JPasswordField editUserPasswordField2;
    private JButton editUserPasswordButton;
    private JTable tableDoctors;
    private JCheckBox deleteUserCheckBox;
    private JButton deleteUserButton;
    private JButton addNewDoctorButton;
    private JButton clearNewDoctorFormButton;
    private JTextField newDoctorLoginField;
    private JPasswordField newDoctorPasswordField1;
    private JPasswordField newDoctorPasswordField2;
    private JTextField newDoctorPostField;
    private JTextField newDoctorRoomField;
    private JTextField newDoctorDistrictField;
    private JTextField newDoctorNameField;
    private JButton editDoctorButton;
    private JTextField editDoctorLoginField;
    private JTextField editDoctorPostField;
    private JTextField editDoctorRoomField;
    private JTextField editDoctorDistrictField;
    private JTextField editDoctorNameField;
    private JPasswordField editDoctorPasswordField1;
    private JPasswordField editDoctorPasswordField2;
    private JButton editDoctorPasswordButton;
    private JCheckBox deleteDoctorCheckBox;
    private JButton deleteDoctorButton;
    private JButton editDoctorScheduleButton;
    private JTabbedPane tabbedPane1, tabbedPane2, tabbedPane3;
    private JComboBox moIn, moOut, tuIn, tuOut, weIn, weOut, thIn, thOut, frIn, frOut, saIn, saOut, suIn, suOut;
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
    private final ObjectOutputStream output = MainFrame.output;
    private final ObjectInputStream input = MainFrame.input;
    private final int USER_ID;
    private String rights;
    private String block;

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("??????????????????????????");
        setContentPane(mainPanel);
        setResizable(false);
        readData();
        TableModel adminsModel = new AdminTableModel(admins);
        tableAdmins.setModel(adminsModel);
        tableAdmins.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableModel usersModel = new UserTableModel(users);
        tableUsers.setModel(usersModel);
        tableUsers.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TableModel doctorsModel = new DoctorTableModel(doctors);
        tableDoctors.setModel(doctorsModel);
        tableDoctors.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        pack();
        setLocationRelativeTo(null);
        for (Admin admin : admins) {
            if (USER_ID == admin.getUserId()) {
                rights = admin.getRights();
                block = admin.getBlock();
            }
        }
    }


    public AdminFrame(int user_id){
        this.USER_ID = user_id;
        initComponents();

        closeFrameButton.addActionListener(e -> closeFrameActionPerformed());
        editMyPersonalDataButton.addActionListener(e -> editMyPersonalDataActionPerformed());
        editMyAuthorizationDataButton.addActionListener(e -> editMyAuthorizationDataActionPerformed());
        tableAdmins.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableAdminsMouseClickedActionPerformed();
            }
        });
        addNewAdminButton.addActionListener(e -> addNewAdminActionPerformed());
        clearNewAdminFormButton.addActionListener(e -> clearNewAdminFormActionPerformed());
        deleteAdminButton.addActionListener(e -> deleteAdminActionPerformed());
        editAdminButton.addActionListener(e -> editAdminActionPerformed());
        editAdminPasswordButton.addActionListener(e -> editAdminPasswordActionPerformed());
        statsAdminBlockButton.addActionListener(e -> statsAdminBlockActionPerformed());
        statsAdminRightsButton.addActionListener(e -> statsAdminRightsActionPerformed());
        clearNewUserFormButton.addActionListener(e -> clearNewUserFormActionPerformed());
        addNewUserButton.addActionListener(e -> addNewUserActionPerformed());
        tableUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableUsersMouseClickedActionPerformed();
            }
        });
        editUserPasswordButton.addActionListener(e -> editUserPasswordActionPerformed());
        editUserButton.addActionListener(e -> editUserActionPerformed());
        deleteUserButton.addActionListener(e -> deleteUserActionPerformed());
        clearNewDoctorFormButton.addActionListener(e -> clearNewDoctorFormActionPerformed());
        addNewDoctorButton.addActionListener(e -> addNewDoctorButtonActionPerformed());
        tableDoctors.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableDoctorsMouseClickedActionPerformed();
            }
        });
        editDoctorButton.addActionListener(e -> editDoctorActionPerformed());
        editDoctorPasswordButton.addActionListener(e -> editDoctorPasswordActionPerformed());
        editDoctorScheduleButton.addActionListener(e -> editDoctorScheduleActionPerformed());
        deleteDoctorButton.addActionListener(e -> deleteDoctorActionPerformed());
        newDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isBlock();
            }
        });
    }

    private void isBlock(){
        if(block.equals("????")) {
            JOptionPane.showMessageDialog(null, "???????? ?????????????? ???????????? ??????????????????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            closeFrameActionPerformed();
        }
    }


    public void readData(){
        try{
            output.writeObject("getAllAdmins");
            this.admins = (ArrayList<Admin>) input.readObject();
            for(int i = 0; i < admins.size(); i++){
                if(USER_ID == admins.get(i).getUserId()){
                    Admin admin = admins.get(i);
                    myLoginField.setText(admin.getLogin());
                    myPasswordField1.setText(admin.getPassword());
                    myPasswordField2.setText(admin.getPassword());
                    myRightsField.setText(admin.getRights());
                    myBlockField.setText(admin.getBlock());
                }
            }
            output.writeObject("getAllUsers");
            this.users = (ArrayList<User>) input.readObject();
            output.writeObject("getAllDoctors");
            this.doctors = (ArrayList<Doctor>) input.readObject();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refreshData(){
        admins.clear();
        users.clear();
        doctors.clear();
        readData();
        TableModel adminsModel = new AdminTableModel(admins);
        tableAdmins.setModel(adminsModel);
        TableModel usersModel = new UserTableModel(users);
        tableUsers.setModel(usersModel);
        TableModel doctorsModel = new DoctorTableModel(doctors);
        tableDoctors.setModel(doctorsModel);
    }

    public void clearEditAndPasswordForm(){
        editAdminLoginField.setText("");
        editAdminRightsComboBox.setSelectedIndex(0);
        editAdminBlockComboBox.setSelectedIndex(0);
        editAdminNameField.setText("");
        editAdminPasswordField1.setText("");
        editAdminPasswordField2.setText("");

        editUserLoginField.setText("");
        editUserNameField.setText("");
        editUserPasswordField1.setText("");
        editUserPasswordField2.setText("");

        editDoctorLoginField.setText("");
        editDoctorPostField.setText("");
        editDoctorRoomField.setText("");
        editDoctorDistrictField.setText("");
        editDoctorNameField.setText("");
        editDoctorPasswordField1.setText("");
        editDoctorPasswordField2.setText("");

        moIn.setSelectedIndex(0);
        moOut.setSelectedIndex(0);
        tuIn.setSelectedIndex(0);
        tuOut.setSelectedIndex(0);
        weIn.setSelectedIndex(0);
        weOut.setSelectedIndex(0);
        thIn.setSelectedIndex(0);
        thOut.setSelectedIndex(0);
        frIn.setSelectedIndex(0);
        frOut.setSelectedIndex(0);
        saIn.setSelectedIndex(0);
        saOut.setSelectedIndex(0);
        suIn.setSelectedIndex(0);
        suOut.setSelectedIndex(0);

        deleteAdminCheckBox.setSelected(false);
    }

    private Boolean checkLogin(String login) {
        if (login.equals("")) {
            JOptionPane.showMessageDialog(null, "???? ???? ?????????? ??????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (login.length() <= 4 || login.length() >= 15) {
            JOptionPane.showMessageDialog(null, "?????????? ???????????? ???????? ???????????? 4 ?? ???????????? 15 ????????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            for (Admin admin : admins) {
                if (login.equals(admin.getLogin())) {
                    JOptionPane.showMessageDialog(null, "???????????? ?????????? ?????? ???????? ?? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            for (User user : users) {
                if (login.equals(user.getLogin())) {
                    JOptionPane.showMessageDialog(null, "???????????? ?????????? ?????? ???????? ?? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            for (Doctor doctor : doctors) {
                if (login.equals(doctor.getLogin())) {
                    JOptionPane.showMessageDialog(null, "???????????? ?????????? ?????? ???????? ?? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }
    }

    private Boolean checkPassword(String password, String provePassword) {
        if(password.equals("") || provePassword.equals("")) {
            JOptionPane.showMessageDialog(null, "???? ???? ?????????? ????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(password.length() <= 4 || password.length() >= 15) {
            JOptionPane.showMessageDialog(null, "???????????? ???????????? ???????? ???????????? 4 ?? ???????????? 15 ????????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(!password.equals(provePassword)){
            JOptionPane.showMessageDialog(null, "???????????? ?? ?????? ?????????????????????????? ???? ??????????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else return true;
    }

    private void closeFrameActionPerformed(){
        new MainFrame().setVisible(true);
        dispose();
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
                JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("?????????????? ??????????????????!")) {
                    for (int i = 0; i < admins.size(); i++) {
                        if (USER_ID == admins.get(i).getUserId()) {
                            Admin admin = admins.get(i);
                            admin.setName(user.getName());
                            admins.set(i, admin);
                        }
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
            }
            myNameField.setEditable(false);
            editMyPersonalDataButton.setText("?????????????????????????? ???????????? ????????????");
        }
        else{
            myNameField.setEditable(true);
            editMyPersonalDataButton.setText("??????????????????");
        }
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
                JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
                if (result.equals("?????????????? ??????????????????!")) {
                    for (int i = 0; i < admins.size(); i++) {
                        if (USER_ID == admins.get(i).getUserId()) {
                            Admin admin = admins.get(i);
                            admin.setLogin(user.getLogin());
                            admins.set(i, admin);
                        }
                    }
                }
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
            }
            myLoginField.setEditable(false);
            myPasswordField1.setEditable(false);
            myPasswordField2.setEditable(false);
            editMyAuthorizationDataButton.setText("?????????????????????????? ???????????? ??????????????????????");
        }
        else{
            myLoginField.setEditable(true);
            myPasswordField1.setEditable(true);
            myPasswordField2.setEditable(true);
            editMyAuthorizationDataButton.setText("??????????????????");
        }
    }


    private void tableAdminsMouseClickedActionPerformed(){
        Admin admin = admins.get(tableAdmins.getSelectedRow());
        editAdminLoginField.setText(admin.getLogin());
        if(admin.getRights().equals("????????????")) editAdminRightsComboBox.setSelectedIndex(0);
        else editAdminRightsComboBox.setSelectedIndex(1);
        if(admin.getBlock().equals("??????")) editAdminBlockComboBox.setSelectedIndex(0);
        else editAdminBlockComboBox.setSelectedIndex(1);
        editAdminNameField.setText(admin.getName());
        editAdminPasswordField1.setText(admin.getPassword());
        editAdminPasswordField2.setText(admin.getPassword());
    }

    private void tableUsersMouseClickedActionPerformed(){
        User user = users.get(tableUsers.getSelectedRow());
        editUserLoginField.setText(user.getLogin());
        editUserNameField.setText(user.getName());
        editUserPasswordField1.setText(user.getPassword());
        editUserPasswordField2.setText(user.getPassword());
    }

    private void tableDoctorsMouseClickedActionPerformed(){
        Doctor doctor = doctors.get(tableDoctors.getSelectedRow());
        editDoctorLoginField.setText(doctor.getLogin());
        editDoctorPostField.setText(doctor.getPost());
        editDoctorRoomField.setText(doctor.getRoom());
        editDoctorDistrictField.setText(doctor.getDistrict());
        editDoctorNameField.setText(doctor.getName());
        editDoctorPasswordField1.setText(doctor.getPassword());
        editDoctorPasswordField2.setText(doctor.getPassword());

        if(doctor.getSchedule()[0].equals("")) moIn.setSelectedIndex(0);
        else moIn.setSelectedItem(doctor.getSchedule()[0]);
        if(doctor.getSchedule()[1].equals("")) moOut.setSelectedIndex(0);
        else moOut.setSelectedItem(doctor.getSchedule()[1]);
        if(doctor.getSchedule()[2].equals("")) tuIn.setSelectedIndex(0);
        else tuIn.setSelectedItem(doctor.getSchedule()[2]);
        if(doctor.getSchedule()[3].equals("")) tuOut.setSelectedIndex(0);
        else tuOut.setSelectedItem(doctor.getSchedule()[3]);
        if(doctor.getSchedule()[4].equals("")) weIn.setSelectedIndex(0);
        else weIn.setSelectedItem(doctor.getSchedule()[4]);
        if(doctor.getSchedule()[5].equals("")) weOut.setSelectedIndex(0);
        else weOut.setSelectedItem(doctor.getSchedule()[5]);
        if(doctor.getSchedule()[6].equals("")) thIn.setSelectedIndex(0);
        else thIn.setSelectedItem(doctor.getSchedule()[6]);
        if(doctor.getSchedule()[7].equals("")) thOut.setSelectedIndex(0);
        else  thOut.setSelectedItem(doctor.getSchedule()[7]);
        if(doctor.getSchedule()[8].equals("")) frIn.setSelectedIndex(0);
        else frIn.setSelectedItem(doctor.getSchedule()[8]);
        if(doctor.getSchedule()[9].equals("")) frOut.setSelectedIndex(0);
        else  frOut.setSelectedItem(doctor.getSchedule()[9]);
        if(doctor.getSchedule()[10].equals("")) saIn.setSelectedIndex(0);
        else saIn.setSelectedItem(doctor.getSchedule()[10]);
        if(doctor.getSchedule()[11].equals("")) saOut.setSelectedIndex(0);
        else  saOut.setSelectedItem(doctor.getSchedule()[11]);
        if(doctor.getSchedule()[12].equals("")) suIn.setSelectedIndex(0);
        else  suIn.setSelectedItem(doctor.getSchedule()[12]);
        if(doctor.getSchedule()[13].equals("")) suOut.setSelectedIndex(0);
        else suOut.setSelectedItem(doctor.getSchedule()[13]);
    }


    private void addNewAdminActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!checkLogin(newAdminLoginField.getText())) return;
        if(!checkPassword(newAdminPasswordField1.getText(), newAdminPasswordField2.getText())) return;
        try{
            Admin admin = new Admin();
            admin.setLogin(newAdminLoginField.getText());
            admin.setPassword(newAdminPasswordField1.getText());
            admin.setRights(String.valueOf(newAdminRightsComboBox.getSelectedItem()));
            admin.setBlock(String.valueOf(newAdminBlockComboBox.getSelectedItem()));
            admin.setName(newAdminNameField.getText());
            admin.setRole("admin");
            output.writeObject("insertAdmin");
            output.writeObject(admin);
            String result = (String) input.readObject();
            JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addNewUserActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!checkLogin(newUserLoginField.getText())) return;
        if(!checkPassword(newUserPasswordField1.getText(), newUserPasswordField2.getText())) return;
        try{
            User user = new User();
            user.setLogin(newUserLoginField.getText());
            user.setPassword(newUserPasswordField1.getText());
            user.setName(newUserNameField.getText());
            user.setRole("user");
            output.writeObject("insertUser");
            output.writeObject(user);
            String result = (String) input.readObject();
            JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addNewDoctorButtonActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!checkLogin(newDoctorLoginField.getText())) return;
        if(!checkPassword(newDoctorPasswordField1.getText(), newDoctorPasswordField2.getText())) return;
        if(newDoctorPostField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "???? ???? ?????????? ?????????????????? ??????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(newDoctorRoomField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "???? ???? ?????????? ???????????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(newDoctorDistrictField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "???? ???? ?????????? ??????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Doctor doctor = new Doctor();
            doctor.setLogin(newDoctorLoginField.getText());
            doctor.setPassword(newDoctorPasswordField1.getText());
            doctor.setPost(newDoctorPostField.getText());
            doctor.setRoom(newDoctorRoomField.getText());
            doctor.setDistrict(newDoctorDistrictField.getText());
            doctor.setRole("doctor");
            doctor.setName(newDoctorNameField.getText());
            output.writeObject("insertDoctor");
            output.writeObject(doctor);
            String result = (String) input.readObject();
            JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void clearNewAdminFormActionPerformed(){
        newAdminLoginField.setText("");
        newAdminPasswordField1.setText("");
        newAdminPasswordField2.setText("");
        newAdminRightsComboBox.setSelectedIndex(0);
        newAdminBlockComboBox.setSelectedIndex(0);
        newAdminNameField.setText("");
    }

    private void clearNewUserFormActionPerformed(){
        newUserLoginField.setText("");
        newUserPasswordField1.setText("");
        newUserPasswordField2.setText("");
        newUserNameField.setText("");
    }

    private void clearNewDoctorFormActionPerformed(){
        newDoctorLoginField.setText("");
        newDoctorPasswordField1.setText("");
        newDoctorPasswordField2.setText("");
        newDoctorPostField.setText("");
        newDoctorRoomField.setText("");
        newDoctorDistrictField.setText("");
        newDoctorNameField.setText("");
    }


    private void deleteAdminActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            if(deleteAdminCheckBox.isSelected()){
                Admin admin = admins.get(tableAdmins.getSelectedRow());
                if(admin.getUserId() != USER_ID){
                    output.writeObject("deleteAdmin");
                    output.writeObject(admin);
                    String result = (String) input.readObject();
                    JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
                    refreshData();
                    clearEditAndPasswordForm();
                }
                else{
                    JOptionPane.showMessageDialog(null, "???? ???? ???????????? ?????????????? ???????? ?????????????? ????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
        deleteAdminCheckBox.setSelected(false);
    }

    private void deleteUserActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            if(deleteUserCheckBox.isSelected()){
                User user = users.get(tableUsers.getSelectedRow());
                if(user.getId() != USER_ID){
                    output.writeObject("deleteUser");
                    output.writeObject(user);
                    String result = (String) input.readObject();
                    JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
                    refreshData();
                    clearEditAndPasswordForm();
                }
                else{
                    JOptionPane.showMessageDialog(null, "???? ???? ???????????? ?????????????? ???????? ?????????????? ????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }





    private void statsAdminBlockActionPerformed(){
        int notBlock = 0;
        int block = 0;
        for (Admin admin : admins) {
            if (admin.getBlock().equals("??????")) notBlock++;
            else block++;
        }
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(notBlock, "", "???? ??????????????????????");
        dataSet.setValue(block, "", "??????????????????????");

        MainFrame.createGraph(dataSet, "???????????????????? ??????????????????????????/???? ?????????????????????????? ??????????????????????????????");
    }

    private void statsAdminRightsActionPerformed(){
        int full = 0;
        int read = 0;
        for (Admin admin : admins) {
            if (admin.getRights().equals("????????????")) full++;
            else read++;
        }
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.setValue(full, "", "????????????");
        dataSet.setValue(read, "", "????????????");

        MainFrame.createGraph(dataSet, "???????????????????? ???????? ??????????????");
    }



    private void editAdminActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Admin admin;
            try {
                admin = admins.get(tableAdmins.getSelectedRow());
                if(admin.getUserId() == USER_ID){
                    JOptionPane.showMessageDialog(null, "???? ???? ???????????? ?????????????????????????? ???????? ???????????? ???????????? ?? ???????? ??????????????! ?????????????????? ???? ???????????? \"?????? ????????????\"" , "????????????", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(!editAdminLoginField.getText().equals(admin.getLogin())){
                    if(!checkLogin(editAdminLoginField.getText())) return;
                }
                admin.setLogin(editAdminLoginField.getText());
                admin.setRights(String.valueOf(editAdminRightsComboBox.getSelectedItem()));
                admin.setBlock(String.valueOf(editAdminBlockComboBox.getSelectedItem()));
                admin.setName(editAdminNameField.getText());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ???????????????????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            output.writeObject("updateAdmin");
            output.writeObject(admin);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editUserActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            User user;
            try {
                user = users.get(tableUsers.getSelectedRow());
                if(!editUserLoginField.getText().equals(user.getLogin())){
                    if(!checkLogin(editUserLoginField.getText())) return;
                }
                user.setLogin(editUserLoginField.getText());
                user.setName(editUserNameField.getText());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ???????????????????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            output.writeObject("updateUser");
            output.writeObject(user);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editDoctorActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Doctor doctor;
            try{
                doctor = doctors.get(tableDoctors.getSelectedRow());
                if(!editDoctorLoginField.getText().equals(doctor.getLogin())){
                    if(!checkLogin(editDoctorLoginField.getText())) return;
                }
                doctor.setLogin(editDoctorLoginField.getText());
                doctor.setPost(editDoctorPostField.getText());
                doctor.setRoom(editDoctorRoomField.getText());
                doctor.setDistrict(editDoctorDistrictField.getText());
                doctor.setName(editDoctorNameField.getText());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ?????????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            output.writeObject("updateDoctor");
            output.writeObject(doctor);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void editAdminPasswordActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!checkPassword(editAdminPasswordField1.getText(), editAdminPasswordField2.getText())) return;
        try{
            Admin admin;
            User user = new User();
            try{
                admin = admins.get(tableAdmins.getSelectedRow());
                if(admin.getUserId() == USER_ID){
                    JOptionPane.showMessageDialog(null, "???? ???? ???????????? ?????????????????????????? ???????? ???????????? ???????????? ?? ???????? ??????????????! ?????????????????? ???? ???????????? \"?????? ????????????\"" , "????????????", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                user.setId(admin.getUserId());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ???????????????????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            user.setPassword(editAdminPasswordField1.getText());
            output.writeObject("updatePassword");
            output.writeObject(user);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editUserPasswordActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!checkPassword(editUserPasswordField1.getText(), editUserPasswordField2.getText())) return;
        try{
            User user;
            try {
                user = users.get(tableUsers.getSelectedRow());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ???????????????????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            user.setPassword(editUserPasswordField1.getText());
            output.writeObject("updatePassword");
            output.writeObject(user);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editDoctorPasswordActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!checkPassword(editDoctorPasswordField1.getText(), editDoctorPasswordField2.getText())) return;
        try{
            Doctor doctor;
            User user = new User();
            try {
                doctor = doctors.get(tableDoctors.getSelectedRow());
                user.setId(doctor.getUserId());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ?????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            user.setPassword(editDoctorPasswordField1.getText());
            output.writeObject("updatePassword");
            output.writeObject(user);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editDoctorScheduleActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            Doctor doctor;
            try{
                doctor = doctors.get(tableDoctors.getSelectedRow());
            }
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "?????????? ?????????????? ?????????????? ???? ????????????!" , "????????????", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String[] newSchedule = new String[14];
            newSchedule[0] = (String)moIn.getSelectedItem();
            if(moIn.getSelectedIndex() != 0) newSchedule[1] = (String)moOut.getSelectedItem();
            else newSchedule[1] = "";
            newSchedule[2] = (String)tuIn.getSelectedItem();
            if(tuIn.getSelectedIndex() != 0) newSchedule[3] = (String)tuOut.getSelectedItem();
            else newSchedule[3] = "";
            newSchedule[4] = (String)weIn.getSelectedItem();
            if(weIn.getSelectedIndex() != 0) newSchedule[5] = (String)weOut.getSelectedItem();
            else newSchedule[5] = "";
            newSchedule[6] = (String)thIn.getSelectedItem();
            if(thIn.getSelectedIndex() != 0) newSchedule[7] = (String)thOut.getSelectedItem();
            else newSchedule[7] = "";
            newSchedule[8] = (String)frIn.getSelectedItem();
            if(frIn.getSelectedIndex() != 0) newSchedule[9] = (String)frOut.getSelectedItem();
            else newSchedule[9] = "";
            newSchedule[10] = (String)saIn.getSelectedItem();
            if(saIn.getSelectedIndex() != 0) newSchedule[11] = (String)saOut.getSelectedItem();
            else newSchedule[11] = "";
            newSchedule[12] = (String)suIn.getSelectedItem();
            if(suIn.getSelectedIndex() != 0) newSchedule[13] = (String)suOut.getSelectedItem();
            else newSchedule[13] = "";
            doctor.setSchedule(newSchedule);
            output.writeObject("updateDoctor");
            output.writeObject(doctor);
            JOptionPane.showMessageDialog(null, input.readObject(), "??????????????????", JOptionPane.INFORMATION_MESSAGE);
            refreshData();
            clearEditAndPasswordForm();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteDoctorActionPerformed(){
        if(!rights.equals("????????????")) {
            JOptionPane.showMessageDialog(null, "???????????????? ?? ??????????????", "????????????", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try{
            if(deleteDoctorCheckBox.isSelected()){
                try{
                    Doctor doctor = doctors.get(tableDoctors.getSelectedRow());
                    output.writeObject("deleteDoctor");
                    output.writeObject(doctor);
                    String result = (String) input.readObject();
                    JOptionPane.showMessageDialog(null, result, "??????????????????", JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "???? ???? ?????????????? ???????????? ?????? ????????????????!", "????????????", JOptionPane.ERROR_MESSAGE);
                }
                refreshData();
                clearEditAndPasswordForm();
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "????????????", JOptionPane.ERROR_MESSAGE);
        }
    }

}
