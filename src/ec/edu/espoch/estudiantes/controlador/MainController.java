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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class MainController {

    @FXML private TableView<Estudiante> tableEstudiantes;
    @FXML private TableColumn<Estudiante, String> colCedula;
    @FXML private TableColumn<Estudiante, String> colNombre;
    @FXML private TableColumn<Estudiante, LocalDate> colFecha;
    @FXML private TableColumn<Estudiante, String> colCarrera;
    @FXML private TableColumn<Estudiante, Double> colPromedio;

    @FXML private TextField tfCedula;
    @FXML private TextField tfNombre;
    @FXML private TextField tfCarrera;
    @FXML private TextField tfPromedio;
    @FXML private DatePicker dpFecha;

    private Estudiante estudianteSeleccionado = null;
    private final ObservableList<Estudiante> listaEstudiantes =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colCedula.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCedula()));

        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombreCompleto()));

        colFecha.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getFechaNacimiento()));

        colCarrera.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getCarrera()));

        colPromedio.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getPromedio()));

        // CENTRAR COLUMNAS
        colCedula.setStyle("-fx-alignment: CENTER;");
        colNombre.setStyle("-fx-alignment: CENTER;");
        colFecha.setStyle("-fx-alignment: CENTER;");
        colCarrera.setStyle("-fx-alignment: CENTER;");
        colPromedio.setStyle("-fx-alignment: CENTER;");

        tableEstudiantes.setItems(listaEstudiantes);
        tableEstudiantes.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
        );
    }


    @FXML
    private void nuevoEstudiante(ActionEvent event) {
        limpiarCampos(null);
        
    }

    @FXML
    private void guardarEstudiante(ActionEvent event) {
        try {
            double promedio = Double.parseDouble(tfPromedio.getText());

            if (promedio < 0 || promedio > 10) {
                mostrarAlerta("El promedio debe estar entre 0 y 10");
                return;
            }

            if (estudianteSeleccionado == null) {
                Estudiante est = new Estudiante(
                        tfCedula.getText(),
                        tfNombre.getText(),
                        dpFecha.getValue(),
                        tfCarrera.getText(),
                        promedio
                );
                listaEstudiantes.add(est);
            } else {
                estudianteSeleccionado.setCedula(tfCedula.getText());
                estudianteSeleccionado.setNombreCompleto(tfNombre.getText());
                estudianteSeleccionado.setFechaNacimiento(dpFecha.getValue());
                estudianteSeleccionado.setCarrera(tfCarrera.getText());
                estudianteSeleccionado.setPromedio(promedio);

                tableEstudiantes.refresh();
                estudianteSeleccionado = null;
            }

            limpiarCampos(null);

        } catch (NumberFormatException e) {
            mostrarAlerta("El promedio debe ser numérico");
        }
    }

    @FXML
    private void editarEstudiante(ActionEvent event) {
        estudianteSeleccionado =
                tableEstudiantes.getSelectionModel().getSelectedItem();

        if (estudianteSeleccionado != null) {
            tfCedula.setText(estudianteSeleccionado.getCedula());
            tfNombre.setText(estudianteSeleccionado.getNombreCompleto());
            dpFecha.setValue(estudianteSeleccionado.getFechaNacimiento());
            tfCarrera.setText(estudianteSeleccionado.getCarrera());
            tfPromedio.setText(
                    String.valueOf(estudianteSeleccionado.getPromedio())
            );
        } else {
            mostrarAlerta("Seleccione un estudiante para editar");
        }
    }

    @FXML
    private void eliminarEstudiante(ActionEvent event) {
        Estudiante seleccionado =
                tableEstudiantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta("Seleccione un estudiante para eliminar");
            return;
        }

        listaEstudiantes.remove(seleccionado);
        limpiarCampos(null);
    }

    @FXML
    private void limpiarCampos(ActionEvent event) {
        tfCedula.clear();
        tfNombre.clear();
        dpFecha.setValue(null);
        tfCarrera.clear();
        tfPromedio.clear();
        tableEstudiantes.getSelectionModel().clearSelection();
        estudianteSeleccionado = null;
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}