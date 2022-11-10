package GUI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class InitialWindow {
    Socket socket;
    DataInputStream inStream;
    DataOutputStream outStream;

    public InitialWindow(Socket socket, DataInputStream inStream, DataOutputStream outStream) {
        this.socket = socket;
        this.inStream = inStream;
        this.outStream = outStream;
    }


    public void display(){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label lbl = new Label("Enter number :");
        lbl.setPrefWidth(200);
        TextField message = new TextField();
        Button btn = new Button("Click");
        btn.setPrefWidth(80);

        btn.setOnAction(event -> {

            String clientMessage, serverMessage;
            try {
                clientMessage = message.getText();
                outStream.writeUTF("next "+ clientMessage);
                outStream.flush();
                serverMessage = inStream.readUTF();
                lbl.setText(serverMessage);

            } catch (Exception e) {
                System.out.println(e);
            }
        });

        FlowPane root = new FlowPane(lbl, message, btn);
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.setTitle("Hello JavaFX");
        stage.setWidth(250);
        stage.setHeight(200);

        stage.show();
    }
}
