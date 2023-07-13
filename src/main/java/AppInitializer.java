import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("server_form.fxml"))));
        primaryStage.setTitle("Sever");
        primaryStage.show();

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("client_form.fxml"))));
        stage.setTitle("client");
        stage.show();

    }
}
