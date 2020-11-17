package sample.windows.update_window;

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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.animations.Shake;
import sample.contact.Contact;
import sample.dao.DataBaseHandler;
import sample.windows.update_window.search_update.SearchUpdateWindowController;

public class UpdateWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameOldField;

    @FXML
    private TextField secondNameOldField;

    @FXML
    private Button updateButton;

    @FXML
    void initialize() {
        updateButton.setOnAction(event -> {
            String firstName = firstNameOldField.getText().trim();
            String secondName = secondNameOldField.getText().trim();

            boolean flag = false;
            if (!firstName.equals("") && !secondName.equals("")) {
                Contact contact = new Contact(firstName, secondName);
                flag = findThisContact(contact);
                if (flag) {
                    //тут заходим в следующее окно
                    updateButton.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(SearchUpdateWindowController.class.
                                getResource("/sample/windows/update_window/search_update/" +
                                        "search_update_contact.fxml"));
                        stage.setScene(new Scene(root));
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                shakeFields();
            }
        });
    }

    private boolean findThisContact(Contact contact) {
        boolean flag = false;
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet resultSet = handler.findContact(contact);
        int counter = 0;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1) {
            System.out.println("This contact in DB");
            flag = true;
        } else {
            shakeFields();
        }
        return flag;
    }

    private void shakeFields() {
        Shake firstNameFieldAnimation = new Shake(firstNameOldField);
        Shake secondNameFieldAnimation = new Shake(secondNameOldField);
        firstNameFieldAnimation.playAnimation();
        secondNameFieldAnimation.playAnimation();
    }
}
