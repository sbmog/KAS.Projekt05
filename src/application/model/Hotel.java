package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private int prisForEnkeltVærelse;
    private int prisForDobbeltVærelse;
    private int badTillæg;
    private int wifiTillæg;
    private int morgenmadsTillæg;

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Hotel(String navn, String adresse, int prisForEnkeltVærelse, int prisForDobbeltVærelse, int badTillæg, int wifiTillæg, int morgenmadsTillæg, Konference konference) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisForEnkeltVærelse = prisForEnkeltVærelse;
        this.prisForDobbeltVærelse = prisForDobbeltVærelse;
        this.badTillæg = badTillæg;
        this.wifiTillæg = wifiTillæg;
        this.morgenmadsTillæg = morgenmadsTillæg;
        konference.addHotel(this);
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            tilmelding.setHotel(this);
        }
    }

    public int getPrisForBooking(Tilmelding tilmelding, boolean dobbeltVærelse, boolean badValgt, boolean wifiValgt, boolean morgenmadValgt) {
        int sum = 0;
        if (tilmeldinger.contains(tilmelding)) {
            if (dobbeltVærelse = true) {
                sum += prisForDobbeltVærelse;
            } else {
                sum += prisForEnkeltVærelse;
            }
            if (badValgt = true) {
                sum += badTillæg;
            }
            if (wifiValgt = true) {
                sum += wifiTillæg;
            }
            if (morgenmadValgt = true) {
                sum += morgenmadsTillæg;
            }
        }
        return sum;
    }
}
