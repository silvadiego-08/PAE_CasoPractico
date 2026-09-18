package uam.edu.ni.demo.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uam.edu.ni.demo.model.Usuario;

import java.io.IOException;
import java.util.Optional;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pswPassword;

    private Usuario usuario1;
    private Usuario usuario2;

    public void initialize() {
        usuario1 = new Usuario("admin", "admin");
        usuario2 = new Usuario("Duran", "UAM");
    }

    @FXML
    protected void accionIniciarSesion() {
        String user = txtUsuario.getText();
        String password = pswPassword.getText();

        if (user == null || user.isBlank()) {
            mostrarAlertaNull();
            return;
        }
        if (password == null || password.isBlank()) {
            mostrarAlertaNull();
            return;
        }
        if (user.equals(usuario1.getUsername()) && password.equals(usuario1.getPassword())) {
            mostrarAlertaExito();
            abrirMenu();
        } else if (user.equals(usuario2.getUsername()) && password.equals(usuario2.getPassword())) {
            mostrarAlertaExito();
            abrirMenu();
        } else {
            mostrarAlertaError();
            limpiarCampos();
        }
        txtUsuario.setOnAction(event -> accionIniciarSesion());
        pswPassword.setOnAction(event -> accionIniciarSesion());
    }


    @FXML
    public void accionSalir() {
        Optional<ButtonType> confirmacion = mostrarAlertaSalir();
        if (confirmacion.isPresent() && confirmacion.get() == ButtonType.OK) {
            salir();
        }
    }

    public void mostrarAlertaNull() {
        Alert alertaNull = new Alert(Alert.AlertType.ERROR);
        alertaNull.setTitle("Error de inicio de sesión");
        alertaNull.setHeaderText(null);
        alertaNull.setContentText("Ingrese un usuario y una contraseña válidos.");
        alertaNull.showAndWait();
    }

    public void mostrarAlertaError() {
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error de inicio de sesión");
        alertaError.setHeaderText(null);
        alertaError.setContentText("Usuario o contraseña incorrectos.");
        alertaError.showAndWait();
    }

    public void mostrarAlertaExito() {
        Alert alertaExito = new Alert(Alert.AlertType.INFORMATION);
        alertaExito.setTitle("Inicio de sesión exitoso");
        alertaExito.setHeaderText(null);
        alertaExito.setContentText("Bienvenido al sistema.");
        alertaExito.showAndWait();
    }

    public Optional<ButtonType> mostrarAlertaSalir() {
        Alert alertaSalir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaSalir.setTitle("Salir del sistema");
        alertaSalir.setHeaderText(null);
        alertaSalir.setContentText("¿Está seguro que desea salir del sistema?");
        return alertaSalir.showAndWait();
    }

    public void salir() {
        System.exit(0);
    }
    public void limpiarCampos() {
        txtUsuario.clear();
        pswPassword.clear();
    }

    private void abrirMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uam/edu/ni/demo/view/menu.fxml"));
            Parent root = loader.load();
            MenuController menuController = loader.getController();
            menuController.setUsuarioActual(txtUsuario.getText());
            Stage stageActual = (Stage) txtUsuario.getScene().getWindow();
            Scene scene = new Scene(root);
            stageActual.setScene(scene);
            stageActual.setTitle("Menú principal");
            stageActual.show();
        } catch (IOException e) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error");
            alertaError.setHeaderText(null);
            alertaError.setContentText("No se pudo abrir la vista de menú.");
            alertaError.showAndWait();
        }
    }
}
