package gui;

import application.controller.Controller;
import application.model.*;
import javafx.application.Application;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        //testGetSamletPrisForDeltagelse();
        Application.launch(StartWindow.class);
    }

    public static void initStorage() {
        Konference havOgHimmel = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 350);
        Konference tedTalk = Controller.createKonference("Ted Talk", "AU", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 500);


        Hotel denHvideSvane = Controller.createHotel("Den Hvide Svane", "XX", 1050, 1250, 0, 50, 0, havOgHimmel);
        Hotel phønix = Controller.createHotel("Høtel Phønix", "XX", 700, 800, 200, 75, 0, havOgHimmel);
        Hotel tusindfryd = Controller.createHotel("Pension Tusindfryd", "XX", 500, 600, 0, 0, 100, havOgHimmel);

        Udflugt byrundTur = Controller.createUdflugt("Byrundtur", "midtby 1, Odense", LocalDate.of(2024, 12, 18), 125, havOgHimmel);
        Udflugt egeskov = Controller.createUdflugt("Egeskov", "skovvej 1", LocalDate.of(2024, 12, 19), 75, havOgHimmel);
        Udflugt trapholt = Controller.createUdflugt("Trapholt Museum", "museumvej 1, Kolding", LocalDate.of(2024, 12, 20), 200, havOgHimmel);

        Deltager finnMadsen = Controller.createDeltager("Finn Madsen", "XX", "12341234");
        Tilmelding finnTilmelding = Controller.createTilmelding(finnMadsen, LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, tedTalk);

        Deltager nielsPetersen = Controller.createDeltager("Niels Petersen", "XX", "11223344");
        Tilmelding nielsTilmelding = nielsPetersen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        nielsTilmelding.setHotel(denHvideSvane, false, false, false);

        Deltager peterSommer = Controller.createDeltager("Peter Sommer", "XX", "43214321");
        Tilmelding peterTilmelding = peterSommer.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        Tilmelding peterTilmelding2 = peterSommer.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, tedTalk);

        peterTilmelding.setHotel(denHvideSvane, false, true, false);
        Ledsager mieSommer = peterSommer.createLedsager("Mie Sommer");
        peterTilmelding.addUdflugt(egeskov);
        peterTilmelding.addUdflugt(trapholt);

        Deltager loneJensen = Controller.createDeltager("Lone Jensen", "XX", "10203040");
        Tilmelding loneTilmeling = loneJensen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), true, havOgHimmel);
        loneTilmeling.setHotel(denHvideSvane, false, true, false);
        Ledsager janMadsen = loneJensen.createLedsager("Jan Madsen");
        loneTilmeling.addUdflugt(egeskov);
        loneTilmeling.addUdflugt(byrundTur);
    }

    public static void testGetSamletPrisForDeltagelse() {
        Konference havOgHimmel = Controller.createKonference("Hav og Himmel", "Odense Universitet", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500);
        Hotel denHvideSvane = Controller.createHotel("Den Hvide Svane", "XX", 1050, 1250, 0, 50, 0, havOgHimmel);
        Udflugt byrundTur = Controller.createUdflugt("Byrundtur", "midtby 1, Odense", LocalDate.of(2024, 12, 18), 125, havOgHimmel);
        Udflugt egeskov = Controller.createUdflugt("Egeskov", "skovvej 1", LocalDate.of(2024, 12, 19), 75, havOgHimmel);
        Udflugt trapholt = Controller.createUdflugt("Trapholt Museum", "museumvej 1, Kolding", LocalDate.of(2024, 12, 20), 200, havOgHimmel);


        Deltager finnMadsen = Controller.createDeltager("Finn Madsen", "XX", "12341234");
        Tilmelding finnTilmelding = Controller.createTilmelding(finnMadsen, LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        System.out.println(finnMadsen.getNavn() + " har en samlet pris på: " + finnTilmelding.getSamletPrisForDeltagelse());

        Deltager nielsPetersen = Controller.createDeltager("Niels Petersen", "XX", "11223344");
        Tilmelding nielsTilmelding = nielsPetersen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        nielsTilmelding.setHotel(denHvideSvane, false, false, false);
        System.out.println(nielsPetersen.getNavn() + " har en samlet pris på: " + nielsTilmelding.getSamletPrisForDeltagelse());

        Deltager peterSommer = Controller.createDeltager("Peter Sommer", "XX", "43214321");
        Tilmelding peterTilmelding = peterSommer.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
        peterTilmelding.setHotel(denHvideSvane, false, true, false);
        Ledsager mieSommer = peterSommer.createLedsager("Mie Sommer");
        peterTilmelding.addUdflugt(egeskov);
        peterTilmelding.addUdflugt(trapholt);
        System.out.println(peterSommer.getNavn() + " har en samlet pris på: " + peterTilmelding.getSamletPrisForDeltagelse());

        Deltager loneJensen = Controller.createDeltager("Lone Jensen", "XX", "10203040");
        Tilmelding loneTilmeling = loneJensen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), true, havOgHimmel);
        loneTilmeling.setHotel(denHvideSvane, false, true, false);
        Ledsager janMadsen = loneJensen.createLedsager("Jan Madsen");
        loneTilmeling.addUdflugt(egeskov);
        loneTilmeling.addUdflugt(byrundTur);
        System.out.println(loneJensen.getNavn() + " har en samlet pris på: " + loneTilmeling.getSamletPrisForDeltagelse() + ". Hun skal selv betale: " + loneTilmeling.getPrisDeltagersUdgift());
    }
}
