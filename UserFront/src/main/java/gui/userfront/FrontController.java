package gui.userfront;

import Classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FrontController {
    @FXML
    private Label authorization;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button entryButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button registrationButton;

    @FXML
    private ToggleButton typeButton;

    @FXML
    protected void onEntryButtonClick() {
        try{
            ObjectOutputStream output = FrontApplication.outStream;
            ObjectInputStream input = FrontApplication.inStream;
            User user = new User();
            user.setLogin(loginField.getText());
            user.setPassword(passwordField.getText());
            if(user.getLogin().equals("")){
                new Alert(Alert.AlertType.ERROR, "Вы не ввели логин!").show();
            }
            else if (user.getLogin().length() <= 4 || user.getLogin().length() >= 15){
                new Alert(Alert.AlertType.ERROR, "Логин должен быть больше 4 и меньше 15 символов!").show();
            }
            else if(user.getPassword().equals("")) {
                new Alert(Alert.AlertType.ERROR, "Вы не ввели пароль!").show();
            }
            else if(user.getPassword().length() <= 4 || user.getPassword().length() >= 15) {
                new Alert(Alert.AlertType.ERROR, "Пароль должен быть больше 4 и меньше 15 символов!").show();}
            else{
                output.writeObject("authorization");
                output.writeObject(user);
                user = (User) input.readObject();
                if(user.getRole().equals("wrong")){
                    new Alert(Alert.AlertType.ERROR, "Пользователя с такимиданными не существует!").show();
                }
                else if(user.getRole().equals("admin")) {
                    new MainPaneApplication(FrontApplication.socket, input, output);
//                    new AdminFrame(user.getId()).setVisible(true);
//                    dispose();
                }
                else if(user.getRole().equals("user")){
                    new Alert(Alert.AlertType.ERROR, "UUUser!").show();
//                    new UserFrame(user.getId()).setVisible(true);
                }
//                else if(user.getRole().equals("doctor")){
//                    new DoctorFrame(user.getId()).setVisible(true);
//                    dispose();
//                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void onTypeButtonClick() {
        if(typeButton.isSelected())
            typeButton.setText("Пользователь");
        else
            typeButton.setText("Администратор");
    }
}