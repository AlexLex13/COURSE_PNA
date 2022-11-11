package gui.userfront;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import static gui.userfront.FrontApplication.*;

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
        String clientMessage, serverMessage, type;
        if(typeButton.isSelected())
            type = "User ";
        else
            type = "Admin ";
        try {
            clientMessage ="authorization "+ type + loginField.getText()+ " " + passwordField.getText();
            outStream.writeUTF(clientMessage);
            outStream.flush();
            serverMessage = inStream.readUTF();
            if (!serverMessage.equals("Error!"))
                new MainPaneApplication(socket, inStream, outStream).display();

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