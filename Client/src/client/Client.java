package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Client extends Application{
    static DataInputStream inStream;
    static DataOutputStream outStream;

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 8888);
        inStream = new DataInputStream(socket.getInputStream());
        outStream = new DataOutputStream(socket.getOutputStream());

        launch(args);

        outStream.close();
        outStream.close();
        socket.close();


//        try {
//            Socket socket = new Socket("127.0.0.1", 8888);
//            DataInputStream inStream = new DataInputStream(socket.getInputStream());
//            DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            String clientMessage = "", serverMessage = "";
//
//            while (true) {
//                System.out.println("Enter number :");
//                clientMessage = br.readLine();
//                outStream.writeUTF(clientMessage);
//                outStream.flush();
//                if (clientMessage.equals("bye"))
//                    break;
//                serverMessage = inStream.readUTF();
//                System.out.println(serverMessage);
//            }
//            outStream.close();
//            outStream.close();
//            socket.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    @Override
    public void start(Stage stage){
        Label lbl = new Label("Enter number :");
        lbl.setPrefWidth(200);
        TextField message = new TextField();
        Button btn = new Button("Click");
        btn.setPrefWidth(80);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    String clientMessage = "", serverMessage = "";

                    clientMessage = message.getText();
                    outStream.writeUTF(clientMessage);
                    outStream.flush();
                    if (clientMessage.equals("bye"))
                        stage.close();
                    serverMessage = inStream.readUTF();
                    lbl.setText(serverMessage);

                } catch (Exception e) {
                    System.out.println(e);
                }
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
