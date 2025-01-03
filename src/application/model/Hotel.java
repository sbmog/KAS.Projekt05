package application.model;

import java.util.ArrayList;

public class Hotel {
    private final String navn;
    private final String adresse;
    private final int prisForEnkeltVærelse;
    private final int prisForDobbeltVærelse;
    private final int badTillæg;
    private final int wifiTillæg;
    private final int morgenmadsTillæg;

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

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public void addTilmelding(Tilmelding tilmelding, boolean updateTilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
            if (updateTilmelding) {
                tilmelding.setHotel(this, tilmelding.isHotelBad(), tilmelding.isHotelWifi(), tilmelding.isHotelMorgenmad());
            }
        }
    }

    public int getPrisForBooking(Tilmelding tilmelding, boolean dobbeltVærelse, boolean badValgt, boolean wifiValgt, boolean morgenmadValgt) {
        int sum = 0;
        if (tilmeldinger.contains(tilmelding)) {
            if (dobbeltVærelse) {
                sum += prisForDobbeltVærelse;
            } else {
                sum += prisForEnkeltVærelse;
            }
            if (badValgt) {
                sum += badTillæg;
            }
            if (wifiValgt) {
                sum += wifiTillæg;
            }
            if (morgenmadValgt) {
                sum += morgenmadsTillæg;
            }
        }
        return sum;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getPrisForEnkeltVærelse() {
        return prisForEnkeltVærelse;
    }

    public int getPrisForDobbeltVærelse() {
        return prisForDobbeltVærelse;
    }

    public int getBadTillæg() {
        return badTillæg;
    }

    public int getWifiTillæg() {
        return wifiTillæg;
    }

    public int getMorgenmadsTillæg() {
        return morgenmadsTillæg;
    }

    public String getNavn() {
    return navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
