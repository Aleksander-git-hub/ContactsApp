package sample.windows.add_window;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        assert firstNameField != null : "fx:id=\"firstNameField\" was not injected: check your FXML file 'add_window.fxml'.";
        assert secondNameField != null : "fx:id=\"secondNameField\" was not injected: check your FXML file 'add_window.fxml'.";
        assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'add_window.fxml'.";
        assert phoneNumberField != null : "fx:id=\"phoneNumberField\" was not injected: check your FXML file 'add_window.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'add_window.fxml'.";

    }
}
