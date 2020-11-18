package sample.windows.show_window;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.contact.Contact;
import sample.dao.Const;
import sample.dao.DataBaseHandler;

public class ShowWindowController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Contact> table;

    @FXML
    private TableColumn<Contact, Integer> idColumn;

    @FXML
    private TableColumn<Contact, String> firstNameColumn;

    @FXML
    private TableColumn<Contact, String> lastNameColumn;

    @FXML
    private TableColumn<Contact, String> phoneNumberColumn;

    @FXML
    private TableColumn<Contact, String> emailColumn;

    private ObservableList<Contact> setContact = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        initContact();
        idColumn.setCellValueFactory(new PropertyValueFactory<Contact, Integer>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("secondName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNumber"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("email"));

        table.setItems(setContact);
    }

    // подготавливам данные для таблицы
    private void initContact() {
        // получаем данные контактов из БД
        DataBaseHandler handler = new DataBaseHandler();
        ResultSet resultSet = handler.getAllContactFromUser();
        try {
            while (resultSet.next()) {
                setContact.add(new Contact(resultSet.getInt(Const.CONTACT_ID),
                        resultSet.getString(Const.CONTACT_FIRST_NAME),
                        resultSet.getString(Const.CONTACT_SECOND_NAME),
                        resultSet.getString(Const.CONTACT_PHONE_NUMBER),
                        resultSet.getString(Const.CONTACT_EMAIL)));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
