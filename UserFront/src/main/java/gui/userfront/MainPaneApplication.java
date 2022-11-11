package gui.userfront;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainPaneApplication{
        Socket socket;
        ObjectInputStream inStream;
        ObjectOutputStream outStream;

        public MainPaneApplication(Socket socket, ObjectInputStream inStream, ObjectOutputStream outStream) {
            this.socket = socket;
            this.inStream = inStream;
            this.outStream = outStream;
        }

        public void display() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(FrontApplication.class.getResource("mainPane-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
}
