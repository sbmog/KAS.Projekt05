package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Tilmelding {
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    private boolean foredragsholder = false;
    private Deltager deltager;
    private Hotel hotel;
    private Konference konference;
    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    private boolean hotelBad = false;
    private boolean hotelWifi = false;
    private boolean hotelMorgenmad = false;

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

    public void setHotel(Hotel hotel, boolean badValgt, boolean wifiValgt, boolean morgenmadValgt) {
        if (this.hotel != hotel) {
            this.hotel = hotel;
            this.hotelBad = badValgt;
            this.hotelWifi = wifiValgt;
            this.hotelMorgenmad = morgenmadValgt;
            if (hotel != null) {
                hotel.addTilmelding(this, false);
            }
        }
    }

    public int getSamletPrisForDeltagelse() {
        int sum = 0;
        boolean dobbeltVærelse = false;
        Stream<LocalDate> antalDage = ankomstDato.datesUntil(afrejseDato);
        int antalDagePåKonferencen = ((int) antalDage.count() + 1);
        sum += konference.getPrisPrDagForKonference() * antalDagePåKonferencen;
        if (deltager.getLedsager() != null) {
            dobbeltVærelse = true;
            for (Udflugt udflugt : this.getUdflugter()) {
                sum += udflugt.getPris();
            }
        }
        if (hotel != null) {
            sum += (antalDagePåKonferencen - 1) * hotel.getPrisForBooking(this, dobbeltVærelse, hotelBad, hotelWifi, hotelMorgenmad);
        }
        return sum;
    }

    public int getPrisDeltagersUdgift() {
        int sum = getSamletPrisForDeltagelse();
        if (foredragsholder) {
            Stream<LocalDate> antalDage = ankomstDato.datesUntil(afrejseDato);
            int antalDagePåKonferencen = ((int) antalDage.count() + 1);
            sum -= (antalDagePåKonferencen * konference.getPrisPrDagForKonference());
        }
        if (deltager.getFirma() != null) {
            sum = 0;
        }
        return sum;
    }

    public boolean isHotelBad() {
        return hotelBad;
    }

    public boolean isHotelWifi() {
        return hotelWifi;
    }

    public boolean isHotelMorgenmad() {
        return hotelMorgenmad;
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
            udflugt.addTilmelding(this);
        }
    }

    public ArrayList<Udflugt>getUdflugter(){
        return udflugter;
    }

    public void setLedsager(Ledsager ledsager) {
    }
}
