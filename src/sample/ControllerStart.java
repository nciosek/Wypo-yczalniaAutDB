package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.BazaDanych;

import java.io.IOException;

public class ControllerStart {

    @FXML
    public Pane mainPane;

    @FXML
    private Button wejscie, end;

    public void wejscie() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("sample.fxml"));
        mainPane.getChildren().setAll(pane);

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setWidth(1200);
        stage.setHeight(490);
        stage.centerOnScreen();
    }

    public void end() {
        BazaDanych.getBazaDanych().zamknijBaze();
        Platform.exit();
    }
}
