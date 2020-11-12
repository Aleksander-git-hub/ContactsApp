package sample.second_window;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class SignInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label textHW;

    @FXML
    private ImageView imageButton;

    @FXML
    void initialize() {
        assert textHW != null : "fx:id=\"textHW\" was not injected: check your FXML file 'sign_in_window.fxml'.";
        assert imageButton != null : "fx:id=\"imageButton\" was not injected: check your FXML file 'sign_in_window.fxml'.";

    }
}

