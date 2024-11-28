package gui;

import application.controller.Controller;
import application.model.*;
import gui.randomGenerator.RandomGenerator;
import javafx.application.Application;
import gui.randomGenerator.KonferenceManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        initStorage();
        //testGetSamletPrisForDeltagelse();
        Application.launch(StartWindow.class);
    }

    public static void initStorage() {
        Konference havOgHimmel = Controller.createKonference("Hav og Himmel", "Odense Universitet, 5000 Odense", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500);
        Konference tedTalk = Controller.createKonference("Ted Talk", "Aarhus Universitet, 8000 Aarhus C", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1900);
        Konference hjerneOgLæring = Controller.createKonference("Hjerne og Læring", "Aarhus Universitet, 8000 Aarhus C", LocalDate.of(2024, 11, 13), LocalDate.of(2024, 11, 18), 1700);
        Konference teaterSomBrobygger = Controller.createKonference("Teater som brobygger", "Esbjerg Universitet, 6705 Esbjerg Ø", LocalDate.of(2025, 1, 3), LocalDate.of(2025, 1, 6), 1200);
        Konference skoleudviklingGennemIt = Controller.createKonference("Skoleudvikling gennem it", "Aalborg Universitet, 9220 Aalborg Ø", LocalDate.of(2024, 12, 2), LocalDate.of(2024, 12, 6), 1350);
        Konference relationelVelfærd = Controller.createKonference("Relationel velfærd", "Crecea, 8000 Aarhus C", LocalDate.of(2025, 5, 8), LocalDate.of(2025, 5, 10), 2800);
        Konference altoxTemadag = Controller.createKonference("Altox Temadag", "Altox, 8000 Aarhus C", LocalDate.of(2025, 4, 2), LocalDate.of(2025, 4, 5), 3200);
        Konference denProfessionelleSekretær = Controller.createKonference("Den professionelle sekretær", "Montus Business Academy, 2900 Hellerup", LocalDate.of(2025, 5, 13), LocalDate.of(2025, 5, 14), 3850);
        Konference salgskursusSalgetsPsykologi = Controller.createKonference("Salgskursus: Salgets Psykologi", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 2, 27), LocalDate.of(2025, 3, 2), 4100);
        Konference forhandlingsteknik = Controller.createKonference("Forhandlingsteknik", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 2), LocalDate.of(2025, 3, 6), 3200);
        Konference personligEffektivitet = Controller.createKonference("Personlig effektivitet", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 6), LocalDate.of(2025, 3, 9), 3500);
        Konference effektivitetOgTrivsel = Controller.createKonference("Effektivitet og trivsel", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 9), LocalDate.of(2025, 3, 13), 3800);
        Konference personaleJura = Controller.createKonference("Personalejura", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 13), LocalDate.of(2025, 3, 16), 4200);
        Konference arbejdsmiljøOgHelbred = Controller.createKonference("Arbejdsmiljø og helbred", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 16), LocalDate.of(2025, 3, 20), 3900);
        Konference arbejdsret = Controller.createKonference("Arbejdsret", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 20), LocalDate.of(2025, 3, 23), 4300);
        Konference personaleUdvikling = Controller.createKonference("Personaleudvikling", "TACK International, 8000 Aarhus C", LocalDate.of(2025, 3, 23), LocalDate.of(2025, 3, 27), 3800);

//        //ArrayList over de forskellige inputs til RandomGenerator

        ArrayList<Konference> konferencer = Controller.getKonferencer();
        KonferenceManager konferenceManager = new KonferenceManager();
//        konferenceManager.opretRandomDataForKonferencer(konferencer);
        konferenceManager.opretRandomDataForEnkeltKonference(havOgHimmel);




//        Hotel denHvideSvane = Controller.createHotel("Den Hvide Svane", "XX", 1050, 1250, 0, 50, 0, havOgHimmel);
//        Hotel phønix = Controller.createHotel("Høtel Phønix", "XX", 700, 800, 200, 75, 0, havOgHimmel);
//        Hotel tusindfryd = Controller.createHotel("Pension Tusindfryd", "XX", 500, 600, 0, 0, 100, havOgHimmel);
//
//        Udflugt byrundTur = Controller.createUdflugt("Byrundtur", "midtby 1, Odense", LocalDate.of(2024, 12, 18), 125, havOgHimmel);
//        Udflugt egeskov = Controller.createUdflugt("Egeskov", "skovvej 1", LocalDate.of(2024, 12, 19), 75, havOgHimmel);
//        Udflugt trapholt = Controller.createUdflugt("Trapholt Museum", "museumvej 1, Kolding", LocalDate.of(2024, 12, 20), 200, havOgHimmel);
//
//        Deltager finnMadsen = Controller.createDeltager("Finn Madsen", "XX", "12341234");
//        Tilmelding finnTilmelding = Controller.createTilmelding(finnMadsen, LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, tedTalk);
//
//        Deltager nielsPetersen = Controller.createDeltager("Niels Petersen", "XX", "11223344");
//        Tilmelding nielsTilmelding = nielsPetersen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
//        nielsTilmelding.setHotel(denHvideSvane, false, false, false);
//
//        Deltager peterSommer = Controller.createDeltager("Peter Sommer", "XX", "43214321");
//        Tilmelding peterTilmelding = peterSommer.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, havOgHimmel);
//        Tilmelding peterTilmelding2 = peterSommer.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), false, tedTalk);
//
//        peterTilmelding.setHotel(denHvideSvane, false, true, false);
//        Ledsager mieSommer = peterSommer.createLedsager("Mie Sommer");
//        peterTilmelding.addUdflugt(egeskov);
//        peterTilmelding.addUdflugt(trapholt);
//
//        Deltager loneJensen = Controller.createDeltager("Lone Jensen", "XX", "10203040");
//        Tilmelding loneTilmeling = loneJensen.createTilmelding(LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), true, havOgHimmel);
//        loneTilmeling.setHotel(denHvideSvane, false, true, false);
//        Ledsager janMadsen = loneJensen.createLedsager("Jan Madsen");
//        loneTilmeling.addUdflugt(egeskov);
//        loneTilmeling.addUdflugt(byrundTur);
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
