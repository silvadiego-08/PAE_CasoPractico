package uam.edu.ni.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uam.edu.ni.demo.controller.ConsultaClientes;

import java.io.IOException;

public class ConsultaClientePrueba extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                ConsultaClientes.class.getResource("/uam/edu/ni/demo/ConsultaClientes-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Consulta de Clientes");
        stage.setScene(scene);
        stage.show();
    }
}
