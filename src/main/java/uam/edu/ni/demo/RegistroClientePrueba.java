package uam.edu.ni.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroClientePrueba extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    RegistroClientePrueba.class.getResource("/uam/edu/ni/demo/RegistroCliente-view.fxml")
            );
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Registro de Clientes");
            stage.setScene(scene);
            stage.show();
        }
}
