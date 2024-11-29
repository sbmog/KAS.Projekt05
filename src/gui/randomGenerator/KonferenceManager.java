package gui.randomGenerator;
import application.controller.Controller;
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
        //Udflugter
        for (int i = 0; i < 3; i++) {
            Udflugt udflugt = RandomGenerator.opretRandomUdflugt(konference);
            konference.addUdflugt(udflugt);
        }

        //Hoteller
        for (int i = 0; i < 5; i++) {
            Hotel hotel = RandomGenerator.opretRandomHotel(konference);
            konference.addHotel(hotel);
        }

        //Deltagere
        for (int i = 0; i < 10; i++) {
            Deltager deltager = RandomGenerator.opretRandomDeltager();
            Tilmelding tilmelding = Controller.createTilmelding(deltager, konference.getStartdato(), konference.getSlutdato(), new Random().nextBoolean(), konference);
            konference.addTilmelding(tilmelding);

            if (tilmelding.getHotel() == null && new Random().nextBoolean()) {
                Hotel hotel = konference.getHoteller().get(new Random().nextInt(konference.getHoteller().size()));
                tilmelding.setHotel(hotel, new Random().nextBoolean(), new Random().nextBoolean(), new Random().nextBoolean());
            }
            if (deltager.getFirma() == null && new Random().nextBoolean()) {
                Firma firma = RandomGenerator.opretRandomFirma();
                deltager.setFirma(firma);
            }

            // Tilføj tilfældigt en ledsager til nogle af deltagerne
            if (new Random().nextBoolean()) {
                Ledsager ledsager = RandomGenerator.opretRandomLedsager(deltager);
                deltager.setLedsager(ledsager);

                // Ledsager vælger en tilfældig udflugt
                if (tilmelding.getUdflugter().isEmpty()) {
                    Udflugt udflugt = konference.getUdflugter().get(new Random().nextInt(konference.getUdflugter().size()));
                    tilmelding.addUdflugt(udflugt);
                        if (new Random().nextBoolean()){
                            Udflugt udflugt2 = konference.getUdflugter().get(new Random().nextInt(konference.getUdflugter().size()));
                            tilmelding.addUdflugt(udflugt2);
                        }
                }
            }
        }
    }
}