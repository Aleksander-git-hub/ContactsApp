package sample.sign_up_window;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.dao.DataBaseHandler;

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


        DataBaseHandler handler = new DataBaseHandler();
        signUpNewUser.setOnAction(event -> {
            handler.signUpUser(loginNewUser.getText(), passwordNewUser.getText(),
                    passwordRepNewUser.getText(), emailNewUser.getText());
        });
    }
}
