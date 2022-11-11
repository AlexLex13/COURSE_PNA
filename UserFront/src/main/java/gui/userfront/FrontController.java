package gui.userfront;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static gui.userfront.FrontApplication.*;

public class FrontController {
    @FXML
    private Label authorization;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button entryButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button registrationButton;

    @FXML
    protected void onEntryButtonClick() {
        String clientMessage, serverMessage;
        try {
            clientMessage ="authorization " + loginField.getText()+ " " + passwordField.getText();
            outStream.writeUTF(clientMessage);
            outStream.flush();
            serverMessage = inStream.readUTF();
            if (!serverMessage.equals("Error!"))
                new MainPaneApplication(socket, inStream, outStream).display();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}