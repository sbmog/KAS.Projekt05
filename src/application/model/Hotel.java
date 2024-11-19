package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private int pris;
    private int badTillæg;
    private int wifiTillæg;
    private int morgenmadsTillæg;

    private ArrayList<Tilmelding>tilmeldinger=new ArrayList<>();

    public Hotel(String navn, String adresse, int pris, int badTillæg, int wifiTillæg, int morgenmadsTillæg) {
        this.navn = navn;
        this.adresse = adresse;
        this.pris = pris;
        this.badTillæg = badTillæg;
        this.wifiTillæg = wifiTillæg;
        this.morgenmadsTillæg = morgenmadsTillæg;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)){
            tilmeldinger.add(tilmelding);
            tilmelding.setHotel(this);
        }
    }
}
