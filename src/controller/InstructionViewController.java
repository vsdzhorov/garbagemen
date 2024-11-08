package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InstructionViewController {

    @FXML
    private Button backBtn;

    public void back(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();

        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("views/WelcomeView.fxml"));
        } catch (Exception ex) {
            System.exit(0);
        }

        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
}
