/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.estudiantes.controlador;

/**
 *
 * @author gsupe
 */

import ec.edu.espoch.estudiantes.modelo.Estudiante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class MainController {

    @FXML private TableView<Estudiante> tableEstudiantes;
    @FXML private TableColumn<Estudiante, String> colCedula, colNombre, colCarrera;
    @FXML private TableColumn<Estudiante, LocalDate> colFecha;
    @FXML private TableColumn<Estudiante, Double> colPromedio;

    @FXML private TextField tfCedula, tfNombre, tfCarrera, tfPromedio;
    @FXML private DatePicker dpFecha;

    private ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCedula.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCedula()));
        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreCompleto()));
        colFecha.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getFechaNacimiento()));
        colCarrera.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getCarrera()));
        colPromedio.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPromedio()));
        tableEstudiantes.setItems(listaEstudiantes);
    }

    @FXML
    private void guardarEstudiante() {
        try {
            double promedio = Double.parseDouble(tfPromedio.getText());
            Estudiante est = new Estudiante(tfCedula.getText(), tfNombre.getText(), dpFecha.getValue(), tfCarrera.getText(), promedio);
            listaEstudiantes.add(est);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Promedio inválido");
        }
    }

    @FXML
    private void limpiarCampos() {
        tfCedula.clear();
        tfNombre.clear();
        dpFecha.setValue(null);
        tfCarrera.clear();
        tfPromedio.clear();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}