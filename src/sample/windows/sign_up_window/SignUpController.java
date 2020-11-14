package sample.windows.sign_up_window;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.animations.Shake;
import sample.dao.Const;
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
            String loginText = loginNewUser.getText().trim();
            String passwordText = passwordNewUser.getText().trim();

            if (!loginText.equals("") && !passwordText.equals("")) {
                signUser(loginText, passwordText);
            } else {
                shakeFields();
                System.out.println("Fields are empty");
            }

        });
    }

    private void signUser(String loginText, String passwordText) {
        DataBaseHandler handler = new DataBaseHandler();
        User user = new User();
        String emailText = emailNewUser.getText().intern();
        user.setLogin(loginText);
        user.setPassword(passwordText);
        user.setEmail(emailText);
        ResultSet resultSet = handler.getUser(user);
        long counter = 0L;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }
        if (counter == 0L) {
            handler.signUpUser(user);
            System.out.println("Success");
        } else {
            shakeFields();
            System.out.println("This user is already exist");
        }

    }

    private void shakeFields() {
        Shake shakeLogin = new Shake(loginNewUser);
        Shake shakePassword = new Shake(passwordNewUser);
        Shake shakeEmail = new Shake(emailNewUser);
        shakeLogin.playAnimation();
        shakePassword.playAnimation();
        shakeEmail.playAnimation();
    }


}
