package Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txtUserName;

    public void initialize(){

    }

    @FXML
    void BtnActionLogin(ActionEvent event) throws IOException {
        if (!txtUserName.getText().isEmpty()&&txtUserName.getText().matches("[A-Za-z0-9]+")){
            Stage primaryStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ClinetForm.fxml"));

            ClinetFormController controller = new ClinetFormController();
            controller.setClientName(txtUserName.getText()); // Set the parameter
            fxmlLoader.setController(controller);

            primaryStage.setScene(new Scene(fxmlLoader.load()));
            primaryStage.setTitle(txtUserName.getText());
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();
            primaryStage.setOnCloseRequest(windowEvent -> {
                controller.shutdown();
            });
            primaryStage.show();

            txtUserName.clear();

        }else{
            new Alert(Alert.AlertType.ERROR, "Please enter your name").show();
        }
    }
}

