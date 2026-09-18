package uam.edu.ni.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.Date;

public class RegistroController {
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidos;
    @FXML
    private ComboBox<String> cbTipoCliente;
    @FXML
    private DatePicker dpFechaNacimiento;

    @FXML
    private RadioButton rbConsulta;
    @FXML
    private RadioButton rbContratacion;
    @FXML
    private RadioButton rbSoporte;
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



}
