package application.model;

import java.util.ArrayList;

public class Firma {
    private String navn;
    private String telefonNummer;
    private ArrayList<Deltager> deltagere = new ArrayList<>();

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
}
