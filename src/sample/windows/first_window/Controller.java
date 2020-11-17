package sample.windows.first_window;

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
import sample.windows.add_window.AddWindowController;
import sample.windows.manager_window.ManagerWindowController;
import sample.windows.sign_up_window.SignUpController;

import javax.xml.crypto.Data;

public class Controller
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signButton;

    @FXML
    private Button loginSignUpButton;

    @FXML
    void initialize() {

        // Кнопка войти
        signButton.setOnAction(event -> {
            String loginText = loginField.getText().trim(); // trim удаляет ненужные пробелы
            String loginPassword = passwordField.getText().trim(); // trim удаляет ненужные пробелы

            if (!loginText.equals("") && !loginPassword.equals("")) {
                boolean tmp = loginUser(loginText, loginPassword);
                if (tmp) {
                    // Вход в ManagerWindow
                    signButton.getScene().getWindow().hide();
                    ManagerWindowController.entranceToManagerWindow();
                    System.out.println("Entrance has been complete");
                }
            } else {
                shakeFields();
                System.out.println("Login and password are empty!!!");
            }
        });

        // РЕГИСТРАЦИЯ
        loginSignUpButton.setOnAction(event -> {
            loginSignUpButton.getScene().getWindow().hide();
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(SignUpController.class.
                        getResource("/sample/windows/sign_up_window/sign_up_window.fxml"));
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean loginUser(String loginText, String loginPassword) {
        boolean tmp = false;
        DataBaseHandler handler = new DataBaseHandler();

        User user = new User(loginText, loginPassword);
        /*// передаем пользователя который зайдет в ManagerWindow
        AddWindowController.myUser(user);*/
        //User user = new User(loginText, loginPassword);

        ResultSet resultSet = handler.getUser(user);

        int counter = 0;
         /*проходим по всем взятым пользователям из БД
        while (resultSet.next()) {
            counter++;
        }*/
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1) {
            System.out.println("SUCCESS!");
            tmp = true;
        } else {
            shakeFields();
        }
        return tmp;
    }

    private void shakeFields() {
        Shake userLoginAnimation = new Shake(loginField);
        Shake userPasswordAnimation = new Shake(passwordField);
        userLoginAnimation.playAnimation();
        userPasswordAnimation.playAnimation();
    }

}

