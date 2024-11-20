package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private String adresse;
    private LocalDate dato;
    private int pris;

    private ArrayList<Ledsager>ledsagere=new ArrayList<>();

    public Udflugt(String navn, String adresse, LocalDate dato, int pris, Konference konference) {
        this.navn = navn;
        this.adresse = adresse;
        this.dato = dato;
        this.pris = pris;
        konference.addUdflugt(this);
    }

    public void addLedsager(Ledsager ledsager) {
        if (!ledsagere.contains(ledsager)){
            ledsagere.add(ledsager);
            ledsager.addUdflugt(this);
        }
    }

    public int getPris() {
        return pris;
    }
}
