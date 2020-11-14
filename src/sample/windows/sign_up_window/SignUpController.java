package sample.windows.sign_up_window;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;
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
            String emailText = emailNewUser.getText().intern();
            DataBaseHandler handler = new DataBaseHandler();
            User user = new User();
            user.setLogin(loginText);
            user.setPassword(passwordText);
            user.setEmail(emailText);

            if (!loginText.equals("") && !passwordText.equals("")) {
                boolean flag = signUser(user, handler);
                if (flag) {
                    System.out.println("Success");
                    handler.signUpUser(user);
                    entrance();
                }
            } else {
                shakeFields();
                System.out.println("Fields are empty");
            }

        });
    }

    //  после добавления нового user заходим в ManagerWindow
    private void entrance() {
        signUpNewUser.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/windows/manager_window/manager_window.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    // проверяем зарегистрирован уже такой пользователь или нет
    private boolean signUser(User user, DataBaseHandler handler) {
        boolean flag = false;
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
            flag = true;
        } else {
            shakeFields();
            System.out.println("This user is already exist");
        }
        return flag;
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
