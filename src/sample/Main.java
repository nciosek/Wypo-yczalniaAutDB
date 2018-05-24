package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
        primaryStage.setTitle("Wypożyczalnia samochodw");

        primaryStage.setScene(new Scene(root, 510, 290));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        BazaDanych.getBazaDanych().otworzBaze();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

