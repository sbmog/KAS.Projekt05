package gui.randomGenerator;
import application.model.*;
import gui.randomGenerator.RandomGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KonferenceManager {

    public void opretRandomDataForKonferencer(ArrayList<Konference> konferencer) {
        for (Konference konference : konferencer) {
            opretRandomDataForEnkeltKonference(konference);
        }
    }

    public void opretRandomDataForEnkeltKonference(Konference konference) {
        //Hoteller
        for (int i = 0; i < 5; i++) {
            Hotel hotel = RandomGenerator.opretRandomHotel(konference);
            konference.addHotel(hotel);
        }

        //Deltagere
        for (int i = 0; i < 10; i++) {
            Deltager deltager = RandomGenerator.opretRandomDeltager();
            Tilmelding tilmelding = new Tilmelding(deltager, LocalDate.now(), LocalDate.now().plusDays(3), new Random().nextBoolean(), konference);
            konference.addTilmelding(tilmelding);

            //Ledsager
            if (new Random().nextBoolean()) {
                Ledsager ledsager = RandomGenerator.opretRandomLedsager(deltager);
                deltager.setLedsager(ledsager);
            }
        }

        //Udflugter
        for (int i = 0; i < 3; i++) {
            Udflugt udflugt = RandomGenerator.opretRandomUdflugt(konference);
            konference.addUdflugt(udflugt);
        }


    }
}