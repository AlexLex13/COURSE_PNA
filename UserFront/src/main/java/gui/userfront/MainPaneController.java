package gui.userfront;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MainPaneController {

    @FXML
    private Label hello;

    @FXML
    private ListView<String> list;

    @FXML
    void initialize(){
        ObservableList<String> regions = FXCollections.observableArrayList("Минск","Минская область",
                "Гомельская область","Могилевская область","Витебская область","Гродненская область","Брестская область");
        list.setItems(regions);
    }
}
