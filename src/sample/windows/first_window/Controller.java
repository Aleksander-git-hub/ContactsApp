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
                signButton.setOnAction(event1 -> {
                    entrance();
                    System.out.println("Entrance has been complete");
                });
            } else {
                System.out.println("Login and password are empty!!!");
            }
        });

        // РЕГИСТРАЦИЯ
        loginSignUpButton.setOnAction(event -> {
            // закрываем firstWindow
            loginSignUpButton.getScene().getWindow().hide();
            // отобразим нужно окно
            FXMLLoader loader = new FXMLLoader();
            // указываем локацию нужного файла
            loader.setLocation(getClass().getResource("/sample/windows/sign_up_window/sign_up_window.fxml"));
            // далее загружаем файл для отображения
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot(); // путь к нужному нам файлу
            Stage stage = new Stage();
            // указываем нужное окно, которое нам надо загрузить
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

//        // Вход в ManagerWindow
//        signButton.setOnAction(event -> {
//           entrance();
//        });
    }

    private boolean loginUser(String loginText, String loginPassword) {
        boolean tmp = false;
        DataBaseHandler handler = new DataBaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet resultSet = handler.getUser(user);

        int counter = 0;
        // проходим по всем взятым пользователям из БД
//        while (resultSet.next()) {
//            counter++;
//        }
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
            Shake userLoginAnimation = new Shake(loginField);
            Shake userPasswordAnimation = new Shake(passwordField);
            userLoginAnimation.playAnimation();
            userPasswordAnimation.playAnimation();
        }
        return tmp;
    }

    // Вход в ManagerWindow
    private void entrance() {
        signButton.getScene().getWindow().hide();
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
}
