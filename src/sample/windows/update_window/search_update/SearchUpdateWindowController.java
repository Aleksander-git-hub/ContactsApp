package sample.windows.update_window.search_update;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SearchUpdateWindowController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateThisContactLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    @FXML
    void initialize() {
        firstNameField.setText("Вика");
        secondNameField.setText("Морозова");

    }
}
