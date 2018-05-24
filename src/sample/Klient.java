package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Klient {

    private SimpleIntegerProperty id;

    private SimpleStringProperty imie;
    private SimpleStringProperty nazwisko;
    private SimpleStringProperty marka;
    private SimpleStringProperty model;
    private SimpleStringProperty dataWypozyczenia;
    private SimpleIntegerProperty zaliczka;

    public Klient() {
        this.id = new SimpleIntegerProperty();
        this.imie = new SimpleStringProperty();
        this.nazwisko = new SimpleStringProperty();
        this.marka = new SimpleStringProperty();
        this.model = new SimpleStringProperty();
        this.dataWypozyczenia = new SimpleStringProperty();
        this.zaliczka = new SimpleIntegerProperty();
    }

    public String getDataWypozyczenia() {
        return dataWypozyczenia.get();
    }

    public SimpleStringProperty dataWypozyczeniaProperty() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(String dataWypozyczenia) {
        this.dataWypozyczenia.set(dataWypozyczenia);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getImie() { return imie.get(); }

    public SimpleStringProperty imieProperty() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie.set(imie);
    }

    public String getNazwisko() { return nazwisko.get(); }

    public SimpleStringProperty nazwiskoProperty() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko.set(nazwisko);
    }

    public String getMarka() {
        return marka.get();
    }

    public SimpleStringProperty markaProperty() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka.set(marka);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getZaliczka() {
        return zaliczka.get();
    }

    public SimpleIntegerProperty zaliczkaProperty() {
        return zaliczka;
    }

    public void setZaliczka(int zaliczka) {
        this.zaliczka.set(zaliczka);
    }
}

