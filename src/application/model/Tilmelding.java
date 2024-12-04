package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Tilmelding {
    private final LocalDate ankomstDato;
    private final LocalDate afrejseDato;
    private boolean foredragsholder = false;
    private Deltager deltager;
    private Hotel hotel;
    private Konference konference;
    private final ArrayList<Udflugt> udflugter = new ArrayList<>();
    private boolean hotelBad = false;
    private boolean hotelWifi = false;
    private boolean hotelMorgenmad = false;

    public Tilmelding(Deltager deltager, LocalDate ankomstDato, LocalDate afrejseDato, boolean foredragsholder, Konference konference) {
        this.ankomstDato = ankomstDato;
        this.deltager = deltager;
        this.afrejseDato = afrejseDato;
        this.foredragsholder = foredragsholder;
        this.konference = konference;
    }

    public boolean isForedragsholder() {
        return foredragsholder;
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

    public int getAntalDagePåKonferencen(){
        Stream<LocalDate> antalDage = ankomstDato.datesUntil(afrejseDato);
        return ((int) antalDage.count() + 1);
    }

    public int getSamletPrisForDeltagelse() {
        int sum = 0;
        boolean dobbeltVærelse = false;
        sum += konference.getPrisPrDagForKonference() * getAntalDagePåKonferencen();
        if (deltager.getLedsager() != null) {
            dobbeltVærelse = true;
            for (Udflugt udflugt : this.getUdflugter()) {
                sum += udflugt.getPris();
            }
        }
        if (hotel != null) {
            sum += (getAntalDagePåKonferencen() - 1) * hotel.getPrisForBooking(this, dobbeltVærelse, hotelBad, hotelWifi, hotelMorgenmad);
        }
        return sum;
    }

    public int getPrisDeltagersUdgift() {
        if (deltager.getFirma() != null) {
            return 0;
        }

        int sum = getSamletPrisForDeltagelse();

        if (foredragsholder) {
            Stream<LocalDate> antalDage = ankomstDato.datesUntil(afrejseDato);
            int antalDagePåKonferencen = ((int) antalDage.count() + 1);
            sum -= (antalDagePåKonferencen * konference.getPrisPrDagForKonference());
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

    public Konference getKonference() {
        return konference;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String toStringKonferenceMedPris() {
        return konference + " (" + getSamletPrisForDeltagelse() + " DKK )";
    }
}


