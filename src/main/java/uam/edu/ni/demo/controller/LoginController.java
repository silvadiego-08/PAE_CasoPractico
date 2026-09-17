package uam.edu.ni.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField pswPassword;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnIniciarSesion;

    private final String usuario = "admin";
    private final String contrasena = "admin";

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
        if (user.equals(usuario) && password.equals(contrasena)) {
            mostrarAlertaExito();
        } else {
            mostrarAlertaError();
            limpiarCampos();
        }
        txtUsuario.setOnAction(event -> accionIniciarSesion());
        pswPassword.setOnAction(event -> accionIniciarSesion());
    }


    @FXML
    public void accionSalir() {
        mostrarAlertaSalir();
        salir();
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

    public void mostrarAlertaSalir() {
        Alert alertaSalir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaSalir.setTitle("Salir del sistema");
        alertaSalir.setHeaderText(null);
        alertaSalir.setContentText("¿Está seguro que desea salir del sistema?");
        alertaSalir.showAndWait();
    }

    public void salir() {
        System.exit(0);
    }
    public void limpiarCampos() {
        txtUsuario.clear();
        pswPassword.clear();
    }
}

