package uam.edu.ni.demo.controller;

import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class MenuController {
    @FXML
    private MenuItem mitElegirCarpetasReportes;
    @FXML
    private MenuItem mitCerrarSesion;
    @FXML
    private MenuItem mitSalir;
    @FXML
    private MenuItem mitRegistrar;
    @FXML
    private MenuItem mitConsultar;
    @FXML
    private MenuItem mitAcercaDe;
    @FXML
    private Button btnTB_Registrar;
    @FXML
    private Button btnTB_Consultar;
    @FXML
    private Button btnTB_ElegirCarpetaReportes;
    @FXML
    private Button btnTB_Salir;
    @FXML
    private VBox panelCentral;
    @FXML
    private ContextMenu contextMenuPrincipal;
    @FXML
    private MenuItem ctxMenuRegistrar;
    @FXML
    private MenuItem ctxMenuConsultar;
    @FXML
    private MenuItem ctxMenuCopiarRuta;
    @FXML
    private MenuItem ctxMenuResetearRuta;
    @FXML
    private Button btnRegistro;
    @FXML
    private Button btnConsultar;
    @FXML
    private Label lblRutaSeleccionada;
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblEstado;

    private File carpetaReportesSeleccionada;
    private String usuarioActual = "admin";

    public void initialize(){
        lblUsuario.setText("Usuario: " + usuarioActual);
        lblEstado.setText("Estado: Activo");}

    public void setUsuarioActual(String usuarioActual) {
        if (usuarioActual != null && !usuarioActual.isBlank()) {
            this.usuarioActual = usuarioActual;
            if (lblUsuario != null) {
                lblUsuario.setText("Usuario: " + usuarioActual);
            }
        }
    }

    @FXML
    protected void accionElegirCarpetasReportes(ActionEvent event) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Seleccionar carpeta de reportes");

        // verificacion de la carpeta seleccionada
        if (carpetaReportesSeleccionada != null && carpetaReportesSeleccionada.exists()) {
            chooser.setInitialDirectory(carpetaReportesSeleccionada);
        }
        Window owner = null;
        Object source = event.getSource();

        if (source instanceof Node node) {
            owner = node.getScene().getWindow();
        } else if (source instanceof MenuItem menuItem && menuItem.getParentPopup() != null) {
            owner = menuItem.getParentPopup().getOwnerWindow();
        }
        File seleccion = chooser.showDialog(owner); // se abre sobre la ventana que se le pasa como parametro
        if (seleccion != null) {
            carpetaReportesSeleccionada = seleccion;
            lblRutaSeleccionada.setText("Ruta de carpeta reportes: " + seleccion.getAbsolutePath());        }
    }
    @FXML
    protected void accionCopiarRuta(ActionEvent event) {
        if (carpetaReportesSeleccionada != null) {
            String ruta = carpetaReportesSeleccionada.getAbsolutePath();
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(ruta);
            clipboard.setContent(content);
        } else {
            MostrarAlertaRuta();
        }
    }
    @FXML
    protected void accionResetearRuta(ActionEvent event) {
        carpetaReportesSeleccionada = null;
        lblRutaSeleccionada.setText("Ruta de carpeta reportes: No seleccionada");
    }
    @FXML
    protected void accionSalir(ActionEvent event) { // metodo que se ejecuta al presionar cualquier boton de salir
        Optional<ButtonType> confirmacion = mostrarAlertaSalir();
        if (confirmacion.isPresent() && confirmacion.get() == ButtonType.OK) {
            Salir();
        }
    }
    @FXML
    protected void accionSalirEscape(KeyEvent event) { // metodo que se ejecuta al presionar esc
        if (event.getCode() == KeyCode.ESCAPE) {
            Optional<ButtonType> confirmacion = mostrarAlertaSalir();
            if (confirmacion.isPresent() && confirmacion.get() == ButtonType.OK) {
                Salir();
            }
        }
    }
    @FXML
    protected void accionAcercaDe(ActionEvent event) {
        mostrarAlertaAcercaDe();
    }
    @FXML
    protected void accionAbrirRegistroCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uam/edu/ni/demo/view/RegistroCliente-view.fxml"));
            Parent root = loader.load();
            Stage stageActual = obtenerStageDesdeEvento(event);
            if (stageActual == null) {
                throw new IllegalStateException("No se pudo obtener la ventana actual desde el evento.");
            }
            Scene scene = new Scene(root);
            stageActual.setScene(scene);
            stageActual.setTitle("Registro de Cliente");
            stageActual.show();
        } catch (IOException e) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error");
            alertaError.setHeaderText(null);
            alertaError.setContentText("No se pudo abrir la vista de registro de cliente.");
            alertaError.showAndWait();
        }
    }
    @FXML
    protected void accionAbrirConsultaClientes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uam/edu/ni/demo/view/ConsultaClientes-view.fxml"));
            Parent root = loader.load();
            Stage stageActual = obtenerStageDesdeEvento(event);
            if (stageActual == null) {
                throw new IllegalStateException("No se pudo obtener la ventana actual desde el evento.");
            }
            Scene scene = new Scene(root);
            stageActual.setScene(scene);
            stageActual.setTitle("Consulta de Clientes");
            stageActual.show();
        } catch (IOException e) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error");
            alertaError.setHeaderText(null);
            alertaError.setContentText("No se pudo abrir la vista de consulta de clientes.");
            alertaError.showAndWait();
        }
    }
    @FXML
    protected void accionCerrarSesion(ActionEvent event){
        Optional<ButtonType> confirmacion = mostrarAlertaCerrarSesion();
        if (confirmacion.isPresent() && confirmacion.get() == ButtonType.OK) {
            bloquearInteraccionDuranteCierreSesion();
            lblEstado.setText("Estado: Inactivo");
            lblEstado.setStyle("-fx-background-color: #7F1D1D; -fx-text-fill: #FCA5A5; -fx-padding: 3 8; -fx-background-radius: 4;");

            PauseTransition pausa = new PauseTransition(Duration.seconds(2));
            pausa.setOnFinished(e -> RegresarLogin(event));
            pausa.play();
        }
    }

    // metodos auxiliares
    public Optional<ButtonType> mostrarAlertaSalir() {
        Alert alertaSalir = new Alert(Alert.AlertType.CONFIRMATION);
        alertaSalir.setTitle("Salir del sistema");
        alertaSalir.setHeaderText(null);
        alertaSalir.setContentText("¿Está seguro que desea salir del sistema?");
        return alertaSalir.showAndWait();
    }
    public void Salir() {
        Platform.exit();
        System.exit(0);
    }
    public void mostrarAlertaAcercaDe() {
        Alert alertaAcercaDe = new Alert(Alert.AlertType.INFORMATION);
        alertaAcercaDe.setTitle("Acerca de");
        alertaAcercaDe.setHeaderText(null);
        alertaAcercaDe.setContentText("Sistema de registro de usuarios\nDesarrollado por: Diego Silva, Claudia Lira, Maria Celeste, Mia Flores");
        alertaAcercaDe.showAndWait();
    }
    public Optional<ButtonType> mostrarAlertaCerrarSesion() {
        Alert alertaCerrarSesion = new Alert(Alert.AlertType.CONFIRMATION);
        alertaCerrarSesion.setTitle("Cerrar sesión");
        alertaCerrarSesion.setHeaderText(null);
        alertaCerrarSesion.setContentText("¿Está seguro que desea cerrar la sesión?");
        return alertaCerrarSesion.showAndWait();
    }
    public void MostrarAlertaRuta(){
        Alert alertaError = new Alert(Alert.AlertType.ERROR);
        alertaError.setTitle("Error");
        alertaError.setHeaderText(null);
        alertaError.setContentText("No hay una carpeta seleccionada para copiar la ruta.");
        alertaError.showAndWait();
    }

    public void RegresarLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uam/edu/ni/demo/view/login-view.fxml"));
            Parent root = loader.load();
            Stage stageActual = obtenerStageDesdeEvento(event);
            if (stageActual == null) {
                throw new IllegalStateException("No se pudo obtener la ventana actual desde el evento.");
            }
            Scene scene = new Scene(root);
            stageActual.setScene(scene);
            stageActual.setTitle("Iniciar sesión");
            stageActual.show();
        } catch (IOException e) {
            Alert alertaError = new Alert(Alert.AlertType.ERROR);
            alertaError.setTitle("Error");
            alertaError.setHeaderText(null);
            alertaError.setContentText("No se pudo abrir la ventana de login.");
            alertaError.showAndWait();
        }
    }

    private Stage obtenerStageDesdeEvento(ActionEvent event) {
        Object source = event.getSource();

        if (source instanceof Node node) {
            return (Stage) node.getScene().getWindow();
        }
        if (source instanceof MenuItem menuItem
                && menuItem.getParentPopup() != null
                && menuItem.getParentPopup().getOwnerWindow() instanceof Stage stage) {
            return stage;
        }
        return null;
    }

    private void bloquearInteraccionDuranteCierreSesion() {
        if (panelCentral != null) {
            panelCentral.setDisable(true);
        }
        if (btnTB_Registrar != null) btnTB_Registrar.setDisable(true);
        if (btnTB_Consultar != null) btnTB_Consultar.setDisable(true);
        if (btnTB_ElegirCarpetaReportes != null) btnTB_ElegirCarpetaReportes.setDisable(true);
        if (btnTB_Salir != null) btnTB_Salir.setDisable(true);
        if (btnRegistro != null) btnRegistro.setDisable(true);
        if (btnConsultar != null) btnConsultar.setDisable(true);
        if (mitElegirCarpetasReportes != null) mitElegirCarpetasReportes.setDisable(true);
        if (mitCerrarSesion != null) mitCerrarSesion.setDisable(true);
        if (mitSalir != null) mitSalir.setDisable(true);
        if (mitRegistrar != null) mitRegistrar.setDisable(true);
        if (mitConsultar != null) mitConsultar.setDisable(true);
        if (mitAcercaDe != null) mitAcercaDe.setDisable(true);
        if (ctxMenuRegistrar != null) ctxMenuRegistrar.setDisable(true);
        if (ctxMenuConsultar != null) ctxMenuConsultar.setDisable(true);
        if (ctxMenuCopiarRuta != null) ctxMenuCopiarRuta.setDisable(true);
        if (ctxMenuResetearRuta != null) ctxMenuResetearRuta.setDisable(true);
    }

}
