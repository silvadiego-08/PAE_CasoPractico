package uam.edu.ni.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                RegistroApp.class.getResource("/uam/edu/ni/demo/view/login-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Inicio de sesión");
        stage.setScene(scene);
        stage.show();
    }
}
