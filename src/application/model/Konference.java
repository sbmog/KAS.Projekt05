package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private String adresse;
    private LocalDate startDato;
    private LocalDate slutDato;

    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Konference(String navn, String adresse, LocalDate startDato, LocalDate slutDato) {
        this.navn = navn;
        this.adresse = adresse;
        this.startDato = startDato;
        this.slutDato = slutDato;
    }

    public Tilmelding createTilmelding(Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsHolder) {
        Tilmelding tilmelding = new Tilmelding(ankomstDato, deltager, afrejseDato, foredragsHolder, this);
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
}
