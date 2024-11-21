package gui;

import application.controller.Controller;
import application.model.Deltager;
import application.model.Hotel;
import application.model.Konference;
import application.model.Tilmelding;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        testGetSamletPrisForDeltagelse();
    }

    public static void initStorage(){
        Konference havOgHimmel = Controller.createKonference("Hav og Himmel","Odense Universitet", LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),350);
        Controller.createHotel("Den Hvide Svane","XX",1050,1250,0,50,0,havOgHimmel);
        Controller.createHotel("Høtel Phønix","XX",700,800,200,75,0,havOgHimmel);
        Controller.createHotel("Pension Tusindfryd","XX",500,600,0,0,100,havOgHimmel);
    }

    public static void testGetSamletPrisForDeltagelse() {
        Konference konference = Controller.createKonference("Test Konference", "Test Adresse", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 350);

        Deltager deltager = Controller.createDeltager("Test Deltager", "Test Adresse", "12345678");

        Tilmelding tilmelding = Controller.createTilmelding(LocalDate.of(2024, 12, 16), deltager, LocalDate.of(2024, 12, 18), false, konference);

        Hotel hotel = Controller.createHotel("Test Hotel", "Test Adresse", 1000, 1500, 100, 50, 200, konference);
        tilmelding.setHotel(hotel, true, true, true);

        int samletPris = tilmelding.getSamletPrisForDeltagelse();

        System.out.println("Samlet pris for deltagelse: " + samletPris);
    }
}
