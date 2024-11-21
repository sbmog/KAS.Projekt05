package gui;

import application.controller.Controller;
import application.model.*;
import javafx.application.Application;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        testGetSamletPrisForDeltagelse();
//        Application.launch(StartWindow.class);
        Application.launch(KonferenceOversigt.class);
    }

    public static void initStorage(){
        Konference havOgHimmel = Controller.createKonference("Hav og Himmel","Odense Universitet", LocalDate.of(2024,12,16),LocalDate.of(2024,12,18),350);
        Controller.createHotel("Den Hvide Svane","XX",1050,1250,0,50,0,havOgHimmel);
        Controller.createHotel("Høtel Phønix","XX",700,800,200,75,0,havOgHimmel);
        Controller.createHotel("Pension Tusindfryd","XX",500,600,0,0,100,havOgHimmel);
    }

    public static void testGetSamletPrisForDeltagelse() {

        Konference havOgHimmel = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500);
        Hotel denHvideSvane = Controller.createHotel("Den Hvide Svane", "XX", 1050, 1250, 0, 50, 0, havOgHimmel);
        Udflugt byrundTur = Controller.createUdflugt("Byrundtur", "midtby 1, Odense", LocalDate.of(2024, 12, 18), 125, havOgHimmel);
        Udflugt egeskov = Controller.createUdflugt("Egeskov", "skovvej 1", LocalDate.of(2024, 12, 19), 75, havOgHimmel);
        Udflugt trapholt = Controller.createUdflugt("Trapholt Museum", "museumvej 1, Kolding", LocalDate.of(2024, 12, 20), 200, havOgHimmel);


        Deltager finnMadsen = Controller.createDeltager("Finn Madsen", "XX", "xxxxxxxx");
        Tilmelding finnTilmelding = Controller.createTilmelding(finnMadsen, LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        System.out.println(finnMadsen.getNavn() + " har en samlet pris på: " + finnTilmelding.getSamletPrisForDeltagelse());

        Deltager nielsPetersen = Controller.createDeltager("Niels Petrsen", "XX", "xxxxxxxx");
        Tilmelding nielsTilmelding = nielsPetersen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        nielsTilmelding.setHotel(denHvideSvane, false, false, false);
        System.out.println(nielsPetersen.getNavn() + " har en samlet pris på: " + nielsTilmelding.getSamletPrisForDeltagelse());

        Deltager peterSommer = Controller.createDeltager("Peter Sommer", "XX", "xxxxxxxx");
        Tilmelding peterTilmelding = peterSommer.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        peterTilmelding.setHotel(denHvideSvane, false, true, false);
        Ledsager mieSommer = peterSommer.createLedsager("Mie Sommer");
        mieSommer.addUdflugt(egeskov);
        mieSommer.addUdflugt(trapholt);
        System.out.println(peterSommer.getNavn() + " har en samlet pris på: " + peterTilmelding.getSamletPrisForDeltagelse());

        Deltager loneJensen = Controller.createDeltager("Lone Jensen", "XX", "xxxxxxxx");
        Tilmelding loneTilmeling = loneJensen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), true, havOgHimmel);
        loneTilmeling.setHotel(denHvideSvane, false, true, false);
        Ledsager janMadsen = loneJensen.createLedsager("Jan Madsen");
        janMadsen.addUdflugt(egeskov);
        janMadsen.addUdflugt(byrundTur);
        System.out.println(loneJensen.getNavn() + " har en samlet pris på: " + loneTilmeling.getSamletPrisForDeltagelse() + ". Hun skal selv betale: " + loneTilmeling.getPrisDeltagersUdgift());


        Konference konference = Controller.createKonference("Test Konference", "Test Adresse", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 350);

        Deltager deltager = Controller.createDeltager("Test Deltager", "Test Adresse", "12345678");

        Tilmelding tilmelding = Controller.createTilmelding(deltager, LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, konference);

        Hotel hotel = Controller.createHotel("Test Hotel", "Test Adresse", 1000, 1500, 100, 50, 200, konference);
        tilmelding.setHotel(hotel, true, true, true);

        int samletPris = tilmelding.getSamletPrisForDeltagelse();

        System.out.println("Samlet pris for deltagelse: " + samletPris);


        //Opret konference
        Konference konference = Controller.createKonference("Test Konference", "Test Adresse", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 350);

        //Opret deltager
        Deltager deltager = Controller.createDeltager("Test Deltager", "Test Adresse", "12345678");

        //Opret tilmelding
        Tilmelding tilmelding = Controller.createTilmelding(LocalDate.of(2024, 12, 16), deltager, LocalDate.of(2024, 12, 18), false, konference);

        //Opret hotel
        Hotel hotel = Controller.createHotel("Test Hotel", "Test Adresse", 1000, 1500, 100, 50, 200, konference);
        tilmelding.setHotel(hotel, true, true, true);

        //Opret ledsager og tilføj til deltager
//        deltager.createLedsager("Test Ledsager");

        //udregn samlet pris for deltagelse
        int samletPris = tilmelding.getSamletPrisForDeltagelse();

        //print samlet pris for deltagelse
        System.out.println("Samlet pris for deltagelse: " + samletPris);
    }
}
