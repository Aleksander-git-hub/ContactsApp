package sample.sign_up_window;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginNewUser;

    @FXML
    private PasswordField passwordNewUser;

    @FXML
    private PasswordField passwordRepNewUser;

    @FXML
    private TextField emailNewUser;

    @FXML
    private Button signUpNewUser;

    @FXML
    void initialize() {
        assert loginNewUser != null : "fx:id=\"loginNewUser\" was not injected: check your FXML file 'sign_up_window.fxml'.";
        assert passwordNewUser != null : "fx:id=\"passwordNewUser\" was not injected: check your FXML file 'sign_up_window.fxml'.";
        assert passwordRepNewUser != null : "fx:id=\"passwordRepNewUser\" was not injected: check your FXML file 'sign_up_window.fxml'.";
        assert emailNewUser != null : "fx:id=\"emailNewUser\" was not injected: check your FXML file 'sign_up_window.fxml'.";
        assert signUpNewUser != null : "fx:id=\"signUpNewUser\" was not injected: check your FXML file 'sign_up_window.fxml'.";

    }
}
