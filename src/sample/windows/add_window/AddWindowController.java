package sample.windows.add_window;

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
import sample.users.User;

public class AddWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String secondName = secondNameField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();
            String email = emailField.getText().trim();

            if (!firstName.equals("") && !secondName.equals("") &&
            !phoneNumber.equals("") && !email.equals("")) {
                Contact contact = new Contact(firstName, secondName,
                        phoneNumber, email);
                // проверяем по номеру есть такой контакт или нет в БД
                boolean flag = contactValidator(contact.getFirstName(), contact.getSecondName(),
                        contact.getPhoneNumber());
                if (flag) {
                    setNewContact(contact);
                    System.out.println("Contact add successfully");
                }
            } else {
                shakeFields();
            }
        });
    }

    // проверяем по номеру есть такой контакт или нет в БД
    private boolean contactValidator(String firstName, String secondName, String phoneNumber) {
        boolean flag = true;
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet resultSet = handler.getContactInNumber(firstName, secondName, phoneNumber);
        long counter = 0L;
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            counter++;
        }
        if (counter >= 1L) {
            flag = false;
            shakeFields();
            System.out.println("This contact is already exist");
        }
        return flag;
    }

    // добавление нового контакта
    private void setNewContact(Contact contact) {
        DataBaseHandler handler = new DataBaseHandler();
        handler.setContact(contact);

    }

    private void shakeFields() {
        Shake firstNameAnimation = new Shake(firstNameField);
        Shake secondNameAnimation = new Shake(secondNameField);
        Shake phoneNumberAnimation = new Shake(phoneNumberField);
        Shake emailAnimation = new Shake(emailField);
        firstNameAnimation.playAnimation();
        secondNameAnimation.playAnimation();
        phoneNumberAnimation.playAnimation();
        emailAnimation.playAnimation();
    }
}
