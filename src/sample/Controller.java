package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField imie, nazwisko, marka, model, zaliczka, data, imie1, nazwisko1, marka1, model1, zaliczka1, data1;

    @FXML
    private Button dodajKlientaButton, usunKlientaButton, zamknijButton, modyfikujDaneButton;

    @FXML
    private TableView<Klient> tabelaAut;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wylistujKlientow();
    }

    @FXML
    public void dodajKlienta() {
            BazaDanych.getBazaDanych().dodajKlienta(imie.getText(), nazwisko.getText(), marka.getText(), model.getText(), data.getText(), zaliczka.getText());
            wylistujKlientow();
    }

    @FXML
    public void modyfikujKlienta() {
        BazaDanych.getBazaDanych().modyfikujKlienta(tabelaAut.getSelectionModel().getSelectedItem(), imie1.getText(), nazwisko1.getText(), marka1.getText(), model1.getText(), data1.getText(), zaliczka1.getText());
        wylistujKlientow();
    }

    @FXML
    public void usunKlienta() {
        try {
            Klient klient = tabelaAut.getSelectionModel().getSelectedItem();
            BazaDanych.getBazaDanych().usunKlienta(klient);
            wylistujKlientow();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie zaznaczyłeś żadnego klienta!");
            alert.showAndWait();
        }
    }

    @FXML
    public void zamknijProgram() {
        BazaDanych.getBazaDanych().zamknijBaze();
        Platform.exit();
    }

    @FXML
    public void modyfikujDane() {

    }

    public void wylistujKlientow() {
        Task<ObservableList<Klient>> zadanie = new PobierzListeKlientow();
        tabelaAut.itemsProperty().bind(zadanie.valueProperty());
        new Thread(zadanie).start();
    }

    public void zaktualizuj() {
        Klient klient = tabelaAut.getSelectionModel().getSelectedItem();
        if (klient != null) {
            imie1.setText(tabelaAut.getSelectionModel().getSelectedItem().getImie());
            nazwisko1.setText(tabelaAut.getSelectionModel().getSelectedItem().getNazwisko());
            marka1.setText(tabelaAut.getSelectionModel().getSelectedItem().getMarka());
            model1.setText(tabelaAut.getSelectionModel().getSelectedItem().getModel());
            data1.setText(tabelaAut.getSelectionModel().getSelectedItem().getDataWypozyczenia());

            Integer zaliczkaInt = tabelaAut.getSelectionModel().getSelectedItem().getZaliczka();
            zaliczka1.setText(zaliczkaInt.toString());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Nie zaznaczyłeś żadnego klienta do modyfikacji!");
            alert.showAndWait();
        }
    }

}

    class PobierzListeKlientow extends Task {

        @Override
        public ObservableList<Klient> call() throws Exception {
            return FXCollections.observableArrayList(BazaDanych.getBazaDanych().listujKlientow());
        }
    }