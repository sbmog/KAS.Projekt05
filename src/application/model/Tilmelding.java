package application.model;

import java.time.LocalDate;

public class Tilmelding {
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private boolean foredragsholder;
    private Deltager deltager;
    private Hotel hotel;
    private Konference konference;

    protected Tilmelding(LocalDate ankomstDato, Deltager deltager, LocalDate afrejseDato, boolean foredragsholder, Konference konference) {
        this.ankomstDato = ankomstDato;
        this.deltager = deltager;
        this.afrejseDato = afrejseDato;
        this.foredragsholder = foredragsholder;
        this.konference = konference;
    }

    public void setKonference(Konference konference) {
        if (this.konference != konference) {
            this.konference = konference;
            konference.addTilmelding(this);
        }
    }

    public void setDeltager(Deltager deltager) {
        if (this.deltager != deltager) {
            this.deltager = deltager;
            deltager.addTilmelding(this);
        }
    }

    public void setHotel(Hotel hotel) {
        if (this.hotel != hotel) {
            this.hotel = hotel;
            hotel.addTilmelding(this);
        }
    }
}
