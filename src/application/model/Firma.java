package application.model;

import java.util.ArrayList;

public class Firma {
    private final String navn;
    private final String telefonNummer;
    private final ArrayList<Deltager> deltagere = new ArrayList<>();

    public Firma(String telefonNummer, String navn) {
        this.telefonNummer = telefonNummer;
        this.navn = navn;
    }

    public void addDeltager(Deltager deltager) {
        if (!deltagere.contains(deltager)) {
            deltagere.add(deltager);
            deltager.setFirma(this);
        }
    }

    @Override
    public String toString() {
        return navn + " (" + telefonNummer + ")";
    }
}
