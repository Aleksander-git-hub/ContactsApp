package sample.windows.delete_window;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.animations.Shake;
import sample.contact.Contact;
import sample.dao.DataBaseHandler;
import sample.windows.info_window.InfoWindowController;

public class DeleteWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private Button deleteButton;

    @FXML
    void initialize() {
        deleteButton.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String secondName = secondNameField.getText().trim();

            if (!firstName.equals("") && !secondName.equals("")) {
                Contact contact = new Contact(firstName, secondName);
                // проверяем есть ли такой контакт
                boolean flag = contactValidator(contact);
                if (!flag) {
                    deleteContact(contact);
                    InfoWindowController.setLabel("contact has been deleted");
                    InfoWindowController.entranceInfoWindow();
                }
            } else {
                shakeFields();
            }
        });
    }

    // проверка на наличие данного контакта в БД
    private boolean contactValidator(Contact contact) {
        boolean flag = false;
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet resultSet = handler.getContact(contact);
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
            shakeFields();
            InfoWindowController.setLabel("This contact is not exist");
            InfoWindowController.entranceInfoWindow();
        }
        return flag;
    }

    private void shakeFields() {
        Shake firstNameAnimation = new Shake(firstNameField);
        Shake secondNameAnimation = new Shake(secondNameField);
        firstNameAnimation.playAnimation();
        secondNameAnimation.playAnimation();
    }

    private void deleteContact(Contact contact) {
        DataBaseHandler handler = new DataBaseHandler();
        handler.deleteContactFromDB(contact);
    }
}
