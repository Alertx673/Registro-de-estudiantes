/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.estudiantes;

/**
 *
 * @author gsupe
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ec/edu/espoch/estudiantes/MainView.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/ec/edu/espoch/estudiantes/estilos.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Sistema de Estudiantes");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}