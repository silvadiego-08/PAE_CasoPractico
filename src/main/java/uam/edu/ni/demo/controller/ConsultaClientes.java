package uam.edu.ni.demo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import uam.edu.ni.demo.DATA.ClienteDATA;

import java.io.IOException;

public class ConsultaClientes {

    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<ClienteDATA> tablaClientes;
    @FXML
    private TableColumn<ClienteDATA, String> colNombre;
    @FXML
    private TableColumn<ClienteDATA, String> colTipo;
    @FXML
    private TableColumn<ClienteDATA, String> colCiudad;
    @FXML
    private TableColumn<ClienteDATA, Object> colNacimiento;
    @FXML
    private TableColumn<ClienteDATA, String> colSolicitud;

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colSolicitud.setCellValueFactory(new PropertyValueFactory<>("tipoSolicitud"));

        tablaClientes.setItems(RegistroController.getListaClientes());


        tablaClientes.setItems(RegistroController.getListaClientes());
    }

    @FXML
    private void handleNuevoCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uam/edu/ni/demo/RegistroCliente-view.fxml"));
            Parent root = loader.load();


            Stage stage = new Stage();
            stage.setTitle("Registro de Cliente");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            tablaClientes.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleActualizar(ActionEvent event) {
        tablaClientes.refresh();
    }

    @FXML
    private void handleEditar(ActionEvent event) {
        ClienteDATA seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Cliente Seleccionado");
            alert.setHeaderText(null);
            alert.setContentText("Seleccionaste a: " + seleccionado.getNombres() + " " + seleccionado.getApellidos());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Atención");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona un cliente de la tabla primero.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleCerrar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/uam/edu/ni/demo/view/menu-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Menú principal");
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo abrir el menú principal.");
            alert.showAndWait();
        }
    }
}