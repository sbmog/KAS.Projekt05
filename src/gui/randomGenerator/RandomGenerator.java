package gui.randomGenerator;

import application.controller.Controller;
import application.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomGenerator {

    private static final Random random = new Random();

    public static Hotel opretRandomHotel(Konference konference) {
        ArrayList<String> hotelNavne = new ArrayList<>(Arrays.asList(
                "Hotel d'Angleterre", "Radisson Blu Royal Hotel", "Skt. Petri Hotel",
                "Copenhagen Marriott Hotel", "Nimb Hotel", "Hotel Kong Arthur",
                "Clarion Hotel Copenhagen Airport", "Scandic Palace Hotel",
                "AC Hotel Bella Sky Copenhagen", "Hotel SP34", "First Hotel Mayfair",
                "Hotel Danmark", "Admiral Hotel", "Hotel Astoria", "The Square Copenhagen",
                "Ibsens Hotel", "Tivoli Hotel & Congress Center", "Scandic Sydhavnen",
                "Copenhagen Strand Hotel", "Radisson Blu Scandinavia Hotel", "Scandic Falkoner",
                "Best Western Hotel City", "CABINN City Hotel", "Hotel Guldsmeden",
                "Hotel Østerport", "Hotel Tivoli", "The Huxley Copenhagen", "Hotel Løvenborg",
                "Zleep Hotel Copenhagen Airport", "Hotel Nordklit", "Hotel Phoenix Copenhagen", "Villa Copenhagen",
                "Hotel Alexandra", "Wakeup Copenhagen Borgergade", "Wakeup Copenhagen Carsten Niebuhrs Gade",
                "Hotel Sanders", "Hotel Ottilia", "Urban House Copenhagen by MEININGER", "Hotel Christian IV",
                "Hotel Bethel", "Hotel Hebron", "Wakeup Copenhagen Bernstorffsgade", "Hotel Skt. Annæ", "Hotel Maritime",
                "Comfort Hotel Vesterbro", "Hotel Rye 115", "Andersen Boutique Hotel", "Axel Guldsmeden",
                "Brøchner Hotels' Bryggen Guldsmeden", "Copenhagen Admiral Hotel", "Hotel Windsor",
                "Steel House Copenhagen", "Ascot Hotel", "Hotel Danmark by Brøchner Hotels", "First Hotel G & Suites",
                "Copenhagen Island Hotel", "Hotel Absalon", "Scandic Sluseholmen", "Go Hotel City", "CPH Studio Hotel"
        ));
        ArrayList<String> adresser = new ArrayList<>(Arrays.asList(
                "Kongens Nytorv 34, 1050 København K", "Hammerichsgade 1, 1611 København V",
                "Krystalgade 22, 1172 København K", "Kalvebod Brygge 5, 1560 København V",
                "Bernstorffsgade 5, 1577 København V", "Nørre Søgade 11, 1370 København K",
                "Ellehammersvej 20, 2770 Kastrup", "Rådhuspladsen 57, 1550 København V",
                "Bella Center, Center Boulevard 5, 2300 København S", "Sankt Peders Stræde 34, 1453 København K",
                "Helgolandsgade 3, 1653 København V", "Vesterbrogade 89, 1620 København V",
                "Toldbodgade 24-28, 1253 København K", "Vesterbro 5, 1601 København V",
                "Rådhuspladsen 14, 1550 København V", "Vesterbrogade 9, 1620 København V",
                "Arni Magnussons Gade 2, 1577 København V", "Sluseholmen 2, 2450 København SV",
                "Nyhavn 22, 1051 København K", "Amagerbrogade 29, 2300 København S",
                "Falkoner Allé 9, 2000 Frederiksberg", "Linnésgade 25, 1361 København K",
                "Østerbrogade 8, 2100 København Ø", "Vesterbrogade 28, 1620 København V",
                "Kronprinsensgade 9, 1114 København K", "København, Hovedstaden, Amagerbrogade 33",
                "Nørrebro Park 23, 2200 København N", "Sydhavns Plads 5, 2450 København SV",
                "St. Kongensgade 58, 1264 København K"
        ));
        String hotelNavn = hotelNavne.get(random.nextInt(hotelNavne.size()));
        String address = adresser.get(random.nextInt(adresser.size()));
        int enkeltRumsPris = 500 + random.nextInt(1000);
        int dobbleRumsPris = enkeltRumsPris + 200 + random.nextInt(1000);
        int badTillæg = 100 + random.nextInt(200);
        int wifiTillæg = 30 + random.nextInt(200);
        int morgenmadsPris = 200 + random.nextInt(200);
        return Controller.createHotel(hotelNavn, address, enkeltRumsPris, dobbleRumsPris, badTillæg, wifiTillæg, morgenmadsPris, konference);
    }

    public static Firma opretRandomFirma(){
        String tlfNummer = "+45 " + (random.nextInt(90000000) + 10000000);
        ArrayList<String> firmaNavne = new ArrayList<>(Arrays.asList(
                "Novo Nordisk", "Mærsk", "Carlsberg", "Vestas Wind Systems", "LEGO Group", "Danske Bank", "Arla Foods",
                "ISS World Services", "GN Store Nord", "Coloplast", "FLSmidth", "Pandora", "Tryg", "Rockwool",
                "DSV", "Topdanmark", "Ørsted", "SimCorp", "SAS", "Netcompany", "Lundbeck", "Bang & Olufsen",
                "Ecco", "Jyske Bank", "NKT", "Chr. Hansen", "Hempel", "Danfoss", "Velux", "Kamstrup", "Nilfisk",
                "Solar", "Grundfos", "Falck", "Færch Plast", "Palsgaard", "Hartmann", "Scandic Hotels", "Brødrene Dahl",
                "Louis Poulsen", "Toms Gruppen", "Bavarian Nordic", "Fiberline Composites", "HusCompagniet",
                "Rambøll", "Kvik", "Unibrew", "Royal Greenland", "Carsoe", "LM Wind Power"
        ));
        return Controller.createFirma(tlfNummer, firmaNavne.get(random.nextInt(firmaNavne.size())));
    }

    public static Deltager opretRandomDeltager() {
        ArrayList<String> deltagerNavne = new ArrayList<>(Arrays.asList(
                "Mads Hansen", "Søren Andersen", "Lise Sørensen", "Anne Møller", "Jens Nielsen", "Pia Jensen",
                "Marie Christensen", "Emil Frederiksen", "Kasper Larsen", "Ida Poulsen", "Lars Thomsen", "Nina Jakobsen",
                "Peter Johansen", "Hanne Kristensen", "Tobias Mikkelsen", "Sofie Jeppesen", "Jakob Knudsen",
                "Emma Mortensen", "Andreas Clausen", "Camilla Jørgensen", "Frederik Sørensen", "Katrine Rasmussen",
                "Martin Holm", "Christina Laursen", "Thomas Olesen", "Julie Berg", "Rasmus Dahl", "Amalie Pedersen",
                "Mikkel Hansen", "Karla Skov", "Jonas Bruun", "Sara Lind", "Victor Iversen", "Nikolaj Nygaard",
                "Louise Aagaard", "Alexander Skytte", "Caroline Brandt", "Sebastian Bang", "Rebecca Falk",
                "Oliver Anker", "Helena Jensen", "Christian Bro", "Mathilde Riis", "Henrik Johansen", "Astrid Nielsen",
                "Maja Bjørn", "Esben Holm", "Clara Krag", "Lucas Dreyer", "Line Bach", "Jon Andersen", "Isabella Gram",
                "Tobias Lorenzen", "Nina Berg", "Patrick Vestergaard", "Laura Krogh", "Christoffer Dam", "Silke Hald",
                "Mads Nørgaard", "Viktor Enevold", "Kasper Madsen", "Signe Holm", "Simon Kristiansen", "Mette Olsen",
                "Anders Lund", "Pernille Friis", "Oscar Bjerregaard", "Thea Clausen", "Mathias Lundgaard",
                "Cecilie Skovgaard", "Jonathan Bjørn", "Sofia Hvid", "Malthe Jepsen", "Emma Vester", "Laura Høj",
                "Benjamin Bak", "Clara Simonsen", "Nikolaj Juhl", "Louise Thomsen", "Daniel Krogh", "Anna Fogh",
                "Victoria Nyborg", "Alexander Borup", "Freja Bruun", "Lasse Vang", "Caroline Andersen", "Lucas Kjær",
                "Julie Dalgaard", "Rasmus Winther", "Amalie Hede"
        ));
        ArrayList<String> adresser = new ArrayList<>(Arrays.asList(
                "Fynsvej 12, 5000 Odense", "Jyllandsgade 5, 8000 Aarhus C", "Sjællandsvej 7, 4000 Roskilde",
                "Bakkegade 10, 6000 Kolding", "Sønderbro 3, 6700 Esbjerg", "Strandvej 20, 2900 Hellerup",
                "Engvej 2, 8000 Aarhus C", "Skolevej 8, 9220 Aalborg Ø", "Kastanievej 14, 5000 Odense",
                "Roskildevej 9, 4000 Roskilde", "Vestergade 11, 6000 Kolding", "Nyvej 13, 6700 Esbjerg",
                "Nørregade 15, 2900 Hellerup", "Hovedgaden 18, 8000 Aarhus C", "Parkvej 6, 9220 Aalborg Ø",
                "Grønnegade 22, 5000 Odense", "Bredgade 30, 4000 Roskilde", "Viborgvej 2, 8000 Aarhus C",
                "Strandvejen 25, 2900 Hellerup", "Østergade 40, 6700 Esbjerg", "Midtgade 19, 6000 Kolding",
                "Nygårdsvej 50, 9220 Aalborg Ø", "Holmegade 60, 5000 Odense", "Stengade 80, 4000 Roskilde",
                "Lindegade 3, 6700 Esbjerg", "Ahornvej 7, 6000 Kolding", "Pilevej 1, 8000 Aarhus C",
                "Fasanvej 12, 2900 Hellerup", "Lergravsvej 9, 9220 Aalborg Ø", "Teglværksvej 5, 8000 Aarhus C",
                "Blomstergade 14, 5000 Odense", "Fjordvej 18, 4000 Roskilde"
        ));
        String tlfNummer = "+45 " + (random.nextInt(90000000) + 10000000);
        String deltagerNavn = deltagerNavne.get(random.nextInt(deltagerNavne.size()));
        String adresse = adresser.get(random.nextInt(adresser.size()));
        return Controller.createDeltager(deltagerNavn, adresse, tlfNummer);
    }

    public static Udflugt opretRandomUdflugt(Konference konference) {
        ArrayList<String> udflugtsNavne = new ArrayList<>(Arrays.asList(
                "Rundvisning i Nyhavn og Kongens Nytorv", "Besøg på Nationalmuseet", "Slotstur på Kronborg Slot",
                "Vandring gennem Den Gamle By i Aarhus", "Guidet tur i Ribe Domkirke", "Rundvisning på Christiansborg Slot",
                "Besøg på H.C. Andersens Hus", "Vandretur i Mols Bjerge Nationalpark", "Strandtur ved Skagen Grenen",
                "Kanosejlads på Gudenåen", "Cykeltur i Dyrehaven", "Fuglekiggeri i Vadehavet", "Guidet tur i Råbjerg Mile",
                "Ølsmagning på Carlsberg", "Gourmettur på Aarhus Street Food", "Vingårdsbesøg på Skærsøgaard Vin",
                "Guidet tur og smagning i Thy Whisky", "Besøg på Rosenborg Slot", "Rundtur på Vikingeskibsmuseet",
                "Mødet med kunsten på Louisiana Museum of Modern Art", "Bådtur til Lindholm Høje", "Rundvisning på Den Blå Planet",
                "Kulturvandring i Aalborg", "Besøg på Frederiksborg Slot", "Rundtur på Moesgaard Museum",
                "Mødet med Rasmus Klump på Hjemsted Oldtidspark", "Eventyrtur i Legoland", "Historisk tur i Helsingør",
                "Besøg på Danmarks Jernbanemuseum", "Rundtur i Københavns Kanaler"
        ));
        ArrayList<String> udflugtsAdresser = new ArrayList<>(Arrays.asList(
                "Kongens Nytorv, 1050 København K", "Ny Vestergade 10, 1471 København K", "Kronborg 2C, 3000 Helsingør",
                "Viborgvej 2, 8000 Aarhus C", "Torvet 19, 6760 Ribe", "Prins Jørgens Gård 1, 1218 København K",
                "Bangs Boder 29, 5000 Odense C", "Molsvej 31, 8410 Rønde", "Fyrvej 36, 9990 Skagen",
                "Gudenåvej 2, 8600 Silkeborg", "Klampenborgvej 50, 2930 Klampenborg", "Hovedvejen 1, 6780 Skærbæk",
                "Kandestedvej, 9990 Skagen", "Gamle Carlsberg Vej 11, 1799 København V", "Ny Banegårdsgade 46, 8000 Aarhus C",
                "Donsvej 32, 6000 Kolding", "Øster Vandværk 7, 7700 Thisted", "Købmagergade 52, 1150 København K",
                "Vindeboder 12, 8000 Aarhus C", "Gl. Skovvej 15, 2970 Hørsholm", "Kanalhuset, 7500 Holstebro",
                "Dronningens Tværgade 16, 1302 København K", "Vestergade 1, 6200 Aabenraa", "Bredgade 68, 1260 København K",
                "Hornevej 13, 6000 Kolding", "Rødkildevej 35, 7100 Vejle", "Langebrogade 26, 1411 København K",
                "Århusgade 1, 2100 København Ø"
        ));
        LocalDate startDato = konference.getStartdato();
        LocalDate slutDato = konference.getSlutdato();
        LocalDate dato = startDato.plusDays(random.nextInt((int) (slutDato.toEpochDay() - startDato.toEpochDay() + 1)));
        String udflugtsNavn = udflugtsNavne.get(random.nextInt(udflugtsNavne.size()));
        String udflugtsAdresse = udflugtsAdresser.get(random.nextInt(udflugtsAdresser.size()));
        int pris = 50 + random.nextInt(200);
        return Controller.createUdflugt(udflugtsNavn, udflugtsAdresse, dato, pris, konference);
    }

    public static Ledsager opretRandomLedsager(Deltager deltager) {
        ArrayList<String> ledsagerNavne = new ArrayList<>(Arrays.asList(
                "Signe Knudsen", "Oliver Kristensen", "Julie Sørensen", "Simon Larsen", "Sofie Nielsen",
                "Rasmus Andersen", "Freja Petersen", "Lucas Jensen", "Emilie Hansen", "Mikkel Johansen",
                "Victoria Holm", "Lukas Knudsen", "Amalie Thomsen", "Jacob Laursen", "Katrine Poulsen",
                "Oscar Bang", "Anna Larsen", "Theodor Sørensen", "Laura Knudsen", "Noah Nielsen",
                "Alma Rasmussen", "Benjamin Olesen", "Lærke Møller", "Mathias Holm", "Ida Mikkelsen", "William Jensen",
                "Caroline Sørensen", "Tobias Larsen", "Emma Johansen", "Frederik Hansen", "Clara Petersen",
                "Jonas Nielsen", "Helena Thomsen", "Malthe Andersen", "Astrid Knudsen", "Magnus Holm", "Maja Poulsen",
                "Sebastian Laursen", "Sara Kristensen", "Elias Bang", "Alberte Rasmussen", "Victor Olesen",
                "Nanna Møller", "Daniel Sørensen", "Pernille Andersen", "Kasper Johansen", "Louise Jensen",
                "Alexander Larsen", "Nikolaj Hansen", "Mille Poulsen", "Karla Mikkelsen", "Jonathan Thomsen",
                "Rebecca Bang", "Esben Knudsen"
        ));
        String ledsagerNavn = ledsagerNavne.get(random.nextInt(ledsagerNavne.size()));
        return deltager.createLedsager(ledsagerNavn);
    }

}
