package gui.tilmelding;

import application.controller.Controller;
import application.model.*;
import javafx.collections.ObservableList;
import storage.Storage;
import java.time.LocalDate;

public class TilmeldingsMetode {

        public static int calculateTotalCostForDeltager(Tilmelding tilmelding, String ledsagerNavn, ObservableList<Udflugt> udflugter) {


            if (ledsagerNavn != null && !ledsagerNavn.isEmpty()) {
                Ledsager ledsager = Controller.createLedsager(ledsagerNavn, tilmelding.getDeltager());
                tilmelding.setLedsager(ledsager);

                if (udflugter != null && !udflugter.isEmpty()) {
                    for (Udflugt udflugt : udflugter) {
                        tilmelding.addUdflugt(udflugt);
                    }
                }
            }
            return Controller.getSamletPrisForDeltagelse(tilmelding);
        }


        public static void registrerTilmelding(String navn, String telefon, LocalDate ankomstDato, LocalDate afrejseDato, boolean erTaler, Konference konference, String ledsagerNavn, ObservableList<Udflugt> udflugter) {

            Deltager deltager = Controller.createDeltager(navn, null, telefon);
            Tilmelding tilmelding = Controller.createTilmelding(deltager, ankomstDato, afrejseDato, erTaler, konference);

            if (!ledsagerNavn.isEmpty()) {
                Ledsager ledsager = Controller.createLedsager(ledsagerNavn, deltager);
                tilmelding.setLedsager(ledsager);

                if (udflugter != null && !udflugter.isEmpty()) {
                    for (Udflugt udflugt : udflugter) {
                        tilmelding.addUdflugt(udflugt);
                    }
                }
            }
        }
    }