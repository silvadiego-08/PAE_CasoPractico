package uam.edu.ni.demo.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.RadioButtonSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import uam.edu.ni.demo.DATA.ClienteDATA;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroController {
    private static final ObservableList<ClienteDATA> listaClientes = FXCollections.observableArrayList();

    public static ObservableList<ClienteDATA> getListaClientes() {
        return listaClientes;
    }

    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private ComboBox<String> cbTipoCliente;
    @FXML
    private ComboBox<String> cbCiudad;
    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private RadioButton rbConsulta;
    @FXML
    private RadioButton rbContratacion;
    @FXML
    private RadioButton rbSoporte;
    @FXML
    private RadioButton rbReclamo;
    @FXML
    private ToggleGroup tgTipoSolicitud;

    @FXML
    private CheckBox chkInternet;
    @FXML
    private CheckBox chkTelefonia;
    @FXML
    private CheckBox chkCable;
    @FXML
    private CheckBox chkSoporteTecnico;

    @FXML
    private ImageView imgFotografia;
    private String rutaImagenSeleccionada;

    @FXML
    public void initialize() {
        cbTipoCliente.getItems().addAll("Regular", "VIP", "Corporativo", "Nuevo");
        cbCiudad.getItems().addAll("Managua", "Leon", "Granada", "Esteli", "Matagalpa", "Chinandega");

        if (tgTipoSolicitud == null) {
            tgTipoSolicitud = new ToggleGroup();
        }
        rbConsulta.setToggleGroup(tgTipoSolicitud);
        rbContratacion.setToggleGroup(tgTipoSolicitud);
        rbSoporte.setToggleGroup(tgTipoSolicitud);
        rbReclamo.setToggleGroup(tgTipoSolicitud);

        rbConsulta.setSelected(true);
    }

    @FXML
    private void handleSeleccionarFotografia(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Fotografia");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes (*.png, *.jpg)", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File archivo = fileChooser.showOpenDialog(stage);

        if (archivo != null) {
            rutaImagenSeleccionada = archivo.toURI().toString();
            imgFotografia.setImage(new Image(rutaImagenSeleccionada));
        }
    }

    @FXML
    private void handleGuardar(ActionEvent event) {
        // Validaciones requeridas por la guía (Alert)
        if (txtNombres.getText().trim().isEmpty() || txtApellidos.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Por favor ingrese nombres y apellidos.");
            return;
        }
        if (cbTipoCliente.getValue() == null || cbCiudad.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Seleccione el tipo de cliente y la ciudad.");
            return;
        }
        if (dpFechaNacimiento.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Validación", "Seleccione la fecha de nacimiento.");
            return;
        }

        RadioButton radioSeleccionado = (RadioButton) tgTipoSolicitud.getSelectedToggle();
        String tipoSolicitud = radioSeleccionado != null ? radioSeleccionado.getText() : "Consulta";

        List<String> servicios = new ArrayList<>();
        if (chkInternet.isSelected()) servicios.add("Internet");
        if (chkTelefonia.isSelected()) servicios.add("Telefonía");
        if (chkCable.isSelected()) servicios.add("Cable");
        if (chkSoporteTecnico.isSelected()) servicios.add("Soporte técnico");

        ClienteDATA cliente = ClienteDATA.builder()
                .nombres(txtNombres.getText().trim())
                .apellidos(txtApellidos.getText().trim())
                .tipoCliente(cbTipoCliente.getValue())
                .ciudad(cbCiudad.getValue())
                .fechaNacimiento(dpFechaNacimiento.getValue())
                .tipoSolicitud(tipoSolicitud)
                .serviciosInteres(servicios)
                .rutaFotografia(rutaImagenSeleccionada)
                .build();
        listaClientes.add(cliente);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Éxito", "El cliente ha sido registrado correctamente.");
        handleLimpiar(null);

    }

    @FXML
    private void handleLimpiar(ActionEvent event) {
        txtNombres.clear();
        txtApellidos.clear();
        cbTipoCliente.getSelectionModel().clearSelection();
        cbCiudad.getSelectionModel().clearSelection();
        dpFechaNacimiento.setValue(null);
        rbConsulta.setSelected(true);
        chkInternet.setSelected(false);
        chkTelefonia.setSelected(false);
        chkCable.setSelected(false);
        chkSoporteTecnico.setSelected(false);
        imgFotografia.setImage(null);
        rutaImagenSeleccionada = null;
    }

    @FXML
    private void handleCancelar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}