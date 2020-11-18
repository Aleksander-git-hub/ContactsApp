package sample.windows.update_window.search_update;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.animations.Shake;
import sample.contact.Contact;
import sample.dao.Const;
import sample.dao.DataBaseHandler;

public class SearchUpdateWindowController
{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateThisContactLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField emailField;

    private static String label = "";
    private static String firstName = "";
    private static String secondName = "";
    private static String phoneNumber = "";
    private static String email = "";

    @FXML
    public void initialize() {
        updateThisContactLabel.setText(label);
        firstNameField.setText(firstName);
        secondNameField.setText(secondName);
        phoneNumberField.setText(phoneNumber);
        emailField.setText(email);
        updateButton.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String secondName = secondNameField.getText().trim();
            String phoneNumber = phoneNumberField.getText().trim();
            String email = emailField.getText().trim();
            if (!firstName.equals("") && !secondName.equals("") &&
            !phoneNumber.equals("") && !email.equals("")) {
                Contact contact = new Contact(firstName, secondName,
                        phoneNumber, email);
                updateContact(contact);
            } else {
                shakeFields();
            }


        });
    }

    private void updateContact(Contact contact) {
        DataBaseHandler handler = new DataBaseHandler();
        handler.updateThisContact(contact);
    }

    private void shakeFields() {
        Shake firstName = new Shake(firstNameField);
        Shake secondName = new Shake(secondNameField);
        Shake phoneNumber = new Shake(phoneNumberField);
        Shake email = new Shake(emailField);
        firstName.playAnimation();
        secondName.playAnimation();
        phoneNumber.playAnimation();
        email.playAnimation();
    }

    // инициализирую поля данными контакта, которого редактируем
    public static void setContactFields(Contact contact) {
        firstName = contact.getFirstName();
        secondName = contact.getSecondName();
        // ищем в БД этот контакт
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet resultSet = handler.findContact(contact);
        while (true) {
            try {
                if (!resultSet.next()) break;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                phoneNumber = resultSet.getString(Const.CONTACT_PHONE_NUMBER);
                email = resultSet.getString(Const.CONTACT_EMAIL);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        label = contact.getFirstName() + " " + contact.getSecondName() + " " +
                phoneNumber + " " + email;
    }
}
