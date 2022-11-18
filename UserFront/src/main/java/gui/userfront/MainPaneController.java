package gui.userfront;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;

public class MainPaneController {

    @FXML
    private Label hello;

    @FXML
    private ListView<String> list;

    @FXML
    void initialize() throws IOException {
//        String clientMessage ="next";
//        outStream.writeUTF(clientMessage);
//        outStream.flush();
//
//        ObservableList<String> us = FXCollections.observableArrayList(inStream.readUTF().split("`"));
//
//        list.setItems(us);
    }
}
