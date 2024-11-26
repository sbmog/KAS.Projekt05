package gui.tilmelding;

import application.controller.Controller;
import application.model.*;
import javafx.collections.ObservableList;


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
}