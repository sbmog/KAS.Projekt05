package storage;

import application.model.*;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();
    private static final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Ledsager> ledsagere = new ArrayList<>();
    private static final ArrayList<Udflugt> udflugter = new ArrayList<>();

    public static void addDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }

    public static ArrayList<Deltager> getDeltagere() {
        return deltagere;
    }

    public static void addFirma(Firma firma) {
        firmaer.add(firma);
    }

    public static ArrayList<Firma> getFirma() {
        return firmaer;
    }

    public static void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    public static ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public static void addHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static void removeHotel(Hotel hotel) {
        hoteller.remove(hotel);
    }

    public static ArrayList<Hotel> getHoteller() {
        return hoteller;
    }

    public static void addKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static ArrayList<Konference> getKonferencer() {
        return konferencer;
    }

    public static void addLedsager(Ledsager ledsager) {
        ledsagere.add(ledsager);
    }

    public static ArrayList<Ledsager> getLedsagere() {
        return ledsagere;
    }

    public static void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public static ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }
}
