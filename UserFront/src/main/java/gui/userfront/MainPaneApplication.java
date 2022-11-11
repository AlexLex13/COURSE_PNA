package gui.userfront;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.*;
import java.net.Socket;

public class MainPaneApplication{
        Socket socket;
        DataInputStream inStream;
        DataOutputStream outStream;

        public MainPaneApplication(Socket socket, DataInputStream inStream, DataOutputStream outStream) {
            this.socket = socket;
            this.inStream = inStream;
            this.outStream = outStream;
        }

        public void display() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPane-view.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Hello!");
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.show();
        }
}
