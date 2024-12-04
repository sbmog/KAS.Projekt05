package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Deltager extends Person {
    private final String adresse;
    private final String telefonNummer;
    private Ledsager ledsager;
    private Firma firma;
    private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public Deltager(String navn, String adresse, String telefonNummer) {
        super(navn);
        this.adresse = adresse;
        this.telefonNummer = telefonNummer;
    }

    public Ledsager createLedsager(String navn) {
        if (this.ledsager != null) {
            this.ledsager = null;
        }
        Ledsager newLedsager = new Ledsager(navn, this);
        this.ledsager = newLedsager;
        setLedsager(newLedsager);
        return newLedsager;
    }

    public void setFirma(Firma firma) {
        if (this.firma != firma) {
            this.firma = firma;
            firma.addDeltager(this);
        }
    }

    public Tilmelding createTilmelding(LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder, Konference konference) {
        Tilmelding tilmelding = new Tilmelding(this, ankomstDato, afrejseDato, foredragsholder, konference);
        this.addTilmelding(tilmelding);
        konference.addTilmelding(tilmelding);
        return tilmelding;
    }

    public void addTilmelding(Tilmelding tilmelding) {
        if (!tilmeldinger.contains(tilmelding)) {
            tilmeldinger.add(tilmelding);
        }
    }

    public void setLedsager(Ledsager ledsager) {
        this.ledsager = ledsager;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public Firma getFirma() {
        return firma;
    }

    @Override
    public String toString() {
        return getNavn();
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public String getAdresse() {
        return adresse;
    }

    public String toStringMedLedsager() {
        if (ledsager != null) {
            return getNavn() + " og " + ledsager;
        }
        return getNavn();
    }
}

