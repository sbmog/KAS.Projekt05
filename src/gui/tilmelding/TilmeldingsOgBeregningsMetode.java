package gui.tilmelding;

import application.controller.Controller;
import application.model.*;
import gui.component.AttributeDisplay;
import gui.component.LabeledDateInput;
import gui.component.LabeledListViewInput;
import gui.component.LabeledTextInput;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import storage.Storage;

import java.time.LocalDate;

public class TilmeldingsOgBeregningsMetode {

    public static void beregnFuldeOmkostninger(Konference konference,
                                               LabeledTextInput navnInput, LabeledTextInput telefonInput, LabeledTextInput adresseInput,
                                               LabeledDateInput ankomstDatoInput, LabeledDateInput afrejseDatoInput, LabeledTextInput ledsagerInput,
                                               ComboBox<Hotel> hotelComboBox, CheckBox badCheckBox, CheckBox wifiCheckBox, CheckBox morgenmadCheckBox,
                                               LabeledListViewInput<Udflugt> udflugtListViewInput, AttributeDisplay totalOmkostningDisplay, AttributeDisplay deltagerOmkostningsDisplay, CheckBox firmaCheckBox, boolean isForedragsholder) {

        try {
            Deltager midlertidligDeltager = new Deltager(navnInput.getInputValue(), adresseInput.getInputValue(), telefonInput.getInputValue());
            Tilmelding tempTilmelding = new Tilmelding(midlertidligDeltager, ankomstDatoInput.getInputValue(), afrejseDatoInput.getInputValue(), isForedragsholder, konference);

            ObservableList<Udflugt> selectedUdflugter = udflugtListViewInput.getListView().getSelectionModel().getSelectedItems();
            for (Udflugt udflugt : selectedUdflugter) {
                tempTilmelding.addUdflugt(udflugt);
            }

            if (!ledsagerInput.getInputValue().isEmpty()) {
                Ledsager tempLedsager = Controller.createLedsager(ledsagerInput.getInputValue(), midlertidligDeltager);
                midlertidligDeltager.setLedsager(tempLedsager);
            }

            Hotel selectedHotel = hotelComboBox.getValue();
            if (selectedHotel != null) {
                boolean badValgt = badCheckBox.isSelected();
                boolean wifiValgt = wifiCheckBox.isSelected();
                boolean morgenmadValgt = morgenmadCheckBox.isSelected();

                tempTilmelding.setHotel(selectedHotel, badValgt, wifiValgt, morgenmadValgt);
            }

            int totalOmkostning = tempTilmelding.getSamletPrisForDeltagelse();
            int deltagersPris = tempTilmelding.getPrisDeltagersUdgift();


            if (firmaCheckBox.isSelected()) {
                totalOmkostning = 0;
            }

            totalOmkostningDisplay.setValue("Total pris: " + totalOmkostning + " DKK");
            deltagerOmkostningsDisplay.setValue("At betale: " + deltagersPris + " DKK");
        } catch (Exception ex) {
            ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Kunne ikke beregne omkostninger: " + ex.getMessage());
        }
    }

    public static void registrerDeltager(Konference konference, LabeledTextInput navnInput, LabeledTextInput telefonInput, LabeledTextInput adresseInput,
                                         LabeledTextInput firmaNavnInput, LabeledTextInput firmaTelefonInput, CheckBox firmaCheckBox,
                                         ComboBox<Hotel> hotelComboBox, CheckBox badCheckBox, CheckBox wifiCheckBox, CheckBox morgenmadCheckBox,
                                         LabeledTextInput ledsagerInput, LabeledListViewInput<Udflugt> udflugtListViewInput, AttributeDisplay totalOmkostningDisplay, boolean isForedragsholder) {

        try {
            Deltager deltager = null;
            Tilmelding nuværendeTilmelding;

            for (Deltager søgteDeltager : Storage.getDeltagere()) {
                if (søgteDeltager.getTelefonNummer().equalsIgnoreCase(telefonInput.getInputValue())) {
                    deltager = søgteDeltager;
                    break;
                }
            }

            if (deltager == null) {
                deltager = Controller.createDeltager(navnInput.getInputValue(), adresseInput.getInputValue(), telefonInput.getInputValue());
            }

            nuværendeTilmelding = Controller.createTilmelding(deltager, LocalDate.now(), LocalDate.now().plusDays(1), isForedragsholder, konference);

            ObservableList<Udflugt> selectedUdflugter = udflugtListViewInput.getListView().getSelectionModel().getSelectedItems();
            for (Udflugt udflugt : selectedUdflugter) {
                nuværendeTilmelding.addUdflugt(udflugt);
            }

            if (!ledsagerInput.getInputValue().isEmpty()) {
                Ledsager ledsager = Controller.createLedsager(ledsagerInput.getInputValue(), deltager);
                deltager.setLedsager(ledsager);
            }

            if (firmaCheckBox.isSelected()) {
                Firma firma = Controller.createFirma(firmaTelefonInput.getInputValue(), firmaNavnInput.getInputValue());
                deltager.setFirma(firma);
            }

            Hotel selectedHotel = hotelComboBox.getValue();
            if (selectedHotel != null) {
                boolean badValgt = badCheckBox.isSelected();
                boolean wifiValgt = wifiCheckBox.isSelected();
                boolean morgenmadValgt = morgenmadCheckBox.isSelected();

                nuværendeTilmelding.setHotel(selectedHotel, badValgt, wifiValgt, morgenmadValgt);
            }

            int totalOmkostningForDeltager = nuværendeTilmelding.getSamletPrisForDeltagelse();
            if (firmaCheckBox.isSelected()) {
                totalOmkostningForDeltager = 0;
            }

            ValideringsMetode.showAlert(Alert.AlertType.CONFIRMATION, "Succes", "Deltageren er nu tilmeldt konferencen. Total pris: " + totalOmkostningForDeltager + " DKK");

        } catch (Exception ex) {
            ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Der opstod en fejl: " + ex.getMessage());
        }
    }
}
