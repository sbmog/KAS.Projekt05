package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private final String navn;
    private final String adresse;
    private final LocalDate startDato;
    private final LocalDate slutDato;

    private final int prisPrDagForKonference;

    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private final ArrayList<Hotel> hoteller = new ArrayList<>();
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Konference(String navn, String adresse, LocalDate startDato, LocalDate slutDato, int prisPrDagForKonference) {
        this.navn = navn;
        this.adresse = adresse;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.prisPrDagForKonference = prisPrDagForKonference;
    }

    public ArrayList<Deltager> getDeltagere() {
        ArrayList<Deltager> deltagere = new ArrayList<>();
        for (Tilmelding tilmelding : tilmeldinger) {
            deltagere.add(tilmelding.getDeltager());
        }
     return deltagere;
    }

    public Tilmelding createTilmelding(Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsHolder) {
        Tilmelding tilmelding = new Tilmelding(deltager, ankomstDato, afrejseDato, foredragsHolder, this);
        this.addTilmelding(tilmelding);
        deltager.addTilmelding(tilmelding);
        return tilmelding;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
        }
    }

    public void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
        }
    }

    public int getPrisPrDagForKonference() {
        return prisPrDagForKonference;
    }

    public ArrayList<Deltager> getTilmeldingerSomDeltager() {
        ArrayList<Deltager> deltagere = new ArrayList<>();
        for (Tilmelding tilmelding : tilmeldinger) {
            deltagere.add(tilmelding.getDeltager());
        }
        return deltagere;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalDate getStartdato() {
        return startDato;
    }

    public LocalDate getSlutdato() {
        return slutDato;
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Hotel> getHoteller() {
        return hoteller;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    @Override
    public String toString() {
        return navn;
    }
}
