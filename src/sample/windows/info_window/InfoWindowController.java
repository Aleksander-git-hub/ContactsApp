package sample.windows.info_window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InfoWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okButton;

    @FXML
    private Label label;

    private static String string = "";

    @FXML
    void initialize() {
        label.setText(string);
        okButton.setOnAction(event -> {
            okButton.getScene().getWindow().hide();
        });
    }

    public static void entranceInfoWindow() {
        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(InfoWindowController.class.
                    getResource("/sample/windows/info_window/info_window.fxml"));
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLabel(String successfully) {
        string = successfully;
    }
}





