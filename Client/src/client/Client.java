package client;

import GUI.InitialWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class Client extends Application{
    static Socket socket;
    static DataInputStream inStream;
    static DataOutputStream outStream;

    public static void main(String[] args) throws Exception {

        socket = new Socket("127.0.0.1", 8888);
        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());

        launch(args);

        inStream.close();
        outStream.close();
        socket.close();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label lbl = new Label("Enter number :");
        lbl.setPrefWidth(200);
        TextField message = new TextField();
        Button btn = new Button("Click");
        btn.setPrefWidth(80);

        btn.setOnAction(event -> {

            String clientMessage, serverMessage;
            try {
                clientMessage = message.getText();
                outStream.writeUTF(clientMessage);
                outStream.flush();
                if (clientMessage.equals("100"))
                    new InitialWindow(socket, inStream, outStream).display();
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
