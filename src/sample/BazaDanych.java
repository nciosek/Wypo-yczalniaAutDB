package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BazaDanych {

        public static final String TABLE_KLIENCI = "Klienci";

        public static final String KOLUMNA_KLIENT_ID = "IDklienta";
        public static final String KOLUMNA_KLEINT_IMIE = "ImieKlienta";
        public static final String KOLUMNA_KLIENT_NAZWISKO = "NazwiskoKlienta";
        public static final String KOLUMNA_KLIENT_MARKA = "MarkaSamochodu";
        public static final String KOLUMNA_KLIENT_MODEL = "ModelSamochodu";
        public static final String KOLUMNA_KLIENT_DATA = "DataWypozyczenia";
        public static final String KOLUMNA_KLIENT_ZALICZKA = "Zaliczka";

        private Connection conn;
        private Klient klient;

        private static BazaDanych bazaDanych = new BazaDanych();

        private BazaDanych() {

        }

        public static BazaDanych getBazaDanych() {
            return bazaDanych;
        }

        public void otworzBaze() {
            try {
                conn = DriverManager.getConnection("jdbc:sqlite:Wypozyczalnia.db");
            } catch(SQLException e) {
                System.out.println("Nie mozna polaczyc z baza danych!");
            }
        }

        public void zamknijBaze() {
            try {
                conn.close();
            } catch(SQLException e) {
                System.out.println("Nie mozna zamknac bazy danych!");
            }
        }

        public List<Klient> listujKlientow() {
            String zapytanie = "SELECT * FROM Klienci";
            //StringBuilder sb = new StringBuilder("SELECT * FROM Klienci");
            System.out.println(zapytanie);

            try (Statement statement = conn.createStatement();
                 ResultSet results = statement.executeQuery(zapytanie)) {

                List<Klient> klienci = new ArrayList<>();
                while(results.next()) {
                    Klient klient = new Klient();
                    klient.setId(results.getInt(1));
                    klient.setImie(results.getString(2));
                    klient.setNazwisko(results.getString(3));
                    klient.setMarka(results.getString(4));
                    klient.setModel(results.getString(5));
                    klient.setDataWypozyczenia(results.getString(6));
                    klient.setZaliczka(results.getInt(7));

                    klienci.add(klient);
                }

                return klienci;
            } catch(SQLException e) {
                System.out.println("Błąd listowania klientów");
                return null;
            }
        }

        public void dodajKlienta(String imie, String nazwisko, String marka, String model, String dataWypozyczenia, String zaliczka) {
            int zaliczkaInt = Integer.parseInt(zaliczka);
            String zapytanie = "INSERT INTO Klienci(ImieKlienta, NazwiskoKlienta, MarkaSamochodu, ModelSamochodu, DataWypozyczenia, Zaliczka) " +
                    "VALUES('" + imie + "', '" + nazwisko + "', '" + marka + "', '" + model + "', '" + dataWypozyczenia + "', " + zaliczka + ")";
            System.out.println(zapytanie);

            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate(zapytanie);
            } catch(SQLException e) {
                System.out.println("Błąd dodania klienta!");
            }
        }

        public void modyfikujKlienta(Klient klient, String imie, String nazwisko, String marka, String model, String dataWypozyczenia, String zaliczka) {
            String zapytanie = "UPDATE Klienci SET ImieKlienta = '" + imie + "', " +
                    "NazwiskoKlienta = '" + nazwisko + "', " +
                    "MarkaSamochodu = '" + marka + "', " +
                    "ModelSamochodu = '" + model + "', " +
                    "DataWypozyczenia = '" + dataWypozyczenia + "', " +
                    "Zaliczka = '" + zaliczka + "' WHERE IDklienta = " + klient.getId();

            System.out.println(zapytanie);

            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate(zapytanie);
            } catch(SQLException e) {
                System.out.println("Błąd modyfikacji klienta!");
            }
        }

        public void usunKlienta(Klient klient) {
            String zapytanie = "DELETE FROM Klienci WHERE IDklienta = " + klient.getId();
            System.out.println(zapytanie);

            try {
                Statement statement = conn.createStatement();
                statement.executeUpdate(zapytanie);
            } catch(SQLException e) {
                System.out.println("Błąd usunięcia klienta!");
            }
        }
}
