package sample.windows.sign_up_window;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.dao.DataBaseHandler;
import sample.users.User;

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
    private TextField emailNewUser;

    @FXML
    private Button signUpNewUser;


    @FXML
    void initialize() {

        signUpNewUser.setOnAction(event -> {
            signUser();
        });
    }

    private void signUser() {
        DataBaseHandler handler = new DataBaseHandler();

        String login = loginNewUser.getText();
        String password = passwordNewUser.getText();
        String email = emailNewUser.getText();

        User user = new User(login, password, email);

        handler.signUpUser(user);
    }

}
