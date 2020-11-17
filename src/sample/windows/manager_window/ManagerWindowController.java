package sample.windows.manager_window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.windows.add_window.AddWindowController;

public class ManagerWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button getAllButton;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Stage stage = new Stage();
            try {
                Parent root = FXMLLoader.load(AddWindowController.class.
                        getResource("/sample/windows/add_window/add_window.fxml"));
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void entranceToManagerWindow() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(ManagerWindowController.class.
                    getResource("/sample/windows/manager_window/manager_window.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
