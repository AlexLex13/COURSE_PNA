package gui.userfront;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class FrontApplication extends Application {
    static Socket socket;
    static ObjectInputStream inStream;
    static ObjectOutputStream outStream;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FrontApplication.class.getResource("front-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        socket = new Socket("127.0.0.1", 8888);
        inStream = new ObjectInputStream(socket.getInputStream());
        outStream = new ObjectOutputStream(socket.getOutputStream());

        launch(args);

        inStream.close();
        outStream.close();
        socket.close();
    }
}