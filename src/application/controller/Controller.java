package application.controller;

import application.model.*;
import gui.show.SorteringsMetode;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {

    public static int getSamletPrisForDeltagelse(Tilmelding tilmelding) {
        if (tilmelding == null) {
            throw new IllegalArgumentException("Tilmelding cannot be null");
        }
        return tilmelding.getSamletPrisForDeltagelse();
    }

    public static int getPrisDeltagersUdgift(Tilmelding tilmelding) {
        return tilmelding.getPrisDeltagersUdgift();
    }

    public static Deltager createDeltager(String navn, String adresse, String telefonNummer) {
        Deltager deltager = new Deltager(navn, adresse, telefonNummer);
        Storage.addDeltager(deltager);
        return deltager;
    }

    public static Deltager søgDeltagerAlle(String name) {
        Deltager deltager = null;
        ArrayList<Deltager> sorteretListe = new ArrayList<>(Storage.getDeltagere());
        SorteringsMetode.sorterNavneArrayListe(sorteretListe);
        int left = 0;
        int right = Storage.getDeltagere().size() - 1;
        while (deltager == null && left <= right) {
            int middle = (left+right) / 2;

            Deltager k = sorteretListe.get(middle);
            if (k.getNavn().compareToIgnoreCase(name) == 0)
                deltager = k;
            else if (k.getNavn().compareToIgnoreCase(name) > 0)
                right = middle - 1;
            else
                left = middle + 1;
        }
        return deltager;
    }

    public static Deltager søgDeltagerIKonference(Konference konference, String name) {
        Deltager deltager = null;
        ArrayList<Deltager> sorteretListe = new ArrayList<>(Controller.getDeltagerForKonference(konference));
        SorteringsMetode.sorterNavneArrayListe(sorteretListe);
        int left = 0;
        int right = getDeltagerForKonference(konference).size() - 1;
        while (deltager == null && left <= right) {
            int middle = (left+right) / 2;
            Deltager k =getDeltagerForKonference(konference).get(middle);
            if (k.getNavn().compareToIgnoreCase(name) == 0)
                deltager = k;
            else if (k.getNavn().compareToIgnoreCase(name) > 0)
                right = middle - 1;
            else
                left = middle + 1;
        }
        return deltager;
    }


    public static ArrayList<Deltager> getDeltagerForKonference(Konference konference) {
        return konference.getDeltagere();
    }

    public static ArrayList<Deltager> getDeltagere() {
        return Storage.getDeltagere();
    }

    public static Firma createFirma(String telefonNummer, String navn) {
        Firma firma = new Firma(telefonNummer, navn);
        Storage.addFirma(firma);
        return firma;
    }

    public static ArrayList<Firma> getFirma() {
        return Storage.getFirma();
    }

    public static Tilmelding createTilmelding(Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder, Konference konference) {
        Tilmelding tilmelding = konference.createTilmelding(deltager, ankomstDato, afrejseDato, foredragsholder);
        Storage.addTilmelding(tilmelding);
        return tilmelding;
    }

    public static ArrayList<Tilmelding> getTilmeldinger() {
        return Storage.getTilmeldinger();
    }

    public static Hotel createHotel(String navn, String adresse, int prisForEnkeltVærelse, int prisForDobbeltVærelse, int badTillæg, int wifiTillæg, int morgenmadsTillæg, Konference konference) {
        Hotel hotel = new Hotel(navn, adresse, prisForEnkeltVærelse, prisForDobbeltVærelse, badTillæg, wifiTillæg, morgenmadsTillæg, konference);
        Storage.addHotel(hotel);
        return hotel;
    }

    public static ArrayList<Hotel> getHoteller() {
        return Storage.getHoteller();
    }

    public static ArrayList<Hotel> getHotellerForKonference(Konference konference) {
        return konference.getHoteller();
    }

    public static Konference createKonference(String navn, String adresse, LocalDate startDato, LocalDate slutDato, int prisPrDagForKonference) {
        Konference konference = new Konference(navn, adresse, startDato, slutDato, prisPrDagForKonference);
        Storage.addKonference(konference);
        return konference;
    }


    public static ArrayList<Konference> getKonferencer() {
        return Storage.getKonferencer();
    }

    public static Ledsager createLedsager(String navn, Deltager deltager) {
        if (deltager != null) {
            Ledsager ledsager = deltager.createLedsager(navn);
            Storage.addLedsager(ledsager);
            return ledsager;
        }
        return null;
    }

    public static ArrayList<Ledsager> getLedsagere() {
        return Storage.getLedsagere();
    }

    public static Udflugt createUdflugt(String navn, String adresse, LocalDate dato, int pris, Konference konference) {
        Udflugt udflugt = new Udflugt(navn, adresse, dato, pris, konference);
        Storage.addUdflugt(udflugt);
        return udflugt;
    }

    public static ArrayList<Udflugt> getUdflugter() {
        return Storage.getUdflugter();
    }

    public static ArrayList<Udflugt> getUdflugterForKonference(Konference konference) {
        return konference.getUdflugter();
    }



    public static Tilmelding getTilmeldingForDeltager(Deltager deltager, Konference konference) {
        if (deltager != null && konference != null) {
            for (Tilmelding tilmelding : deltager.getTilmeldinger()) {
                if (tilmelding.getKonference().equals(konference)) {
                    return tilmelding;
                }
            }
        }
        return null;
    }
}