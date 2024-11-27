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

import java.time.LocalDate;

public class TilmeldingsogBeregningsMetode {

    public static void beregnFuldeOmkostninger(Konference konference,
                                               LabeledTextInput navnInput, LabeledTextInput telefonInput, LabeledTextInput adresseInput,
                                               LabeledDateInput ankomstDatoInput, LabeledDateInput afrejseDatoInput, LabeledTextInput ledsagerInput,
                                               ComboBox<Hotel> hotelComboBox, CheckBox badCheckBox, CheckBox wifiCheckBox, CheckBox morgenmadCheckBox,
                                               LabeledListViewInput<Udflugt> udflugtListViewInput, AttributeDisplay totalOmkostningDisplay) {

        try {
            // Temporary participant for calculation without registering
            Deltager midlertidligDeltager = new Deltager(navnInput.getInputValue(), adresseInput.getInputValue(), telefonInput.getInputValue());
            Tilmelding tempTilmelding = new Tilmelding(midlertidligDeltager, ankomstDatoInput.getInputValue(), afrejseDatoInput.getInputValue(), false, konference);

            // Add selected excursions to temporary registration
            ObservableList<Udflugt> selectedUdflugter = udflugtListViewInput.getListView().getSelectionModel().getSelectedItems();
            for (Udflugt udflugt : selectedUdflugter) {
                tempTilmelding.addUdflugt(udflugt);
            }

            // Add ledsager if provided
            if (!ledsagerInput.getInputValue().isEmpty()) {
                Ledsager tempLedsager = Controller.createLedsager(ledsagerInput.getInputValue(), midlertidligDeltager);
                midlertidligDeltager.setLedsager(tempLedsager);
            }

            // Add selected hotel options
            Hotel selectedHotel = hotelComboBox.getValue();
            if (selectedHotel != null) {
                boolean badValgt = badCheckBox.isSelected();
                boolean wifiValgt = wifiCheckBox.isSelected();
                boolean morgenmadValgt = morgenmadCheckBox.isSelected();

                tempTilmelding.setHotel(selectedHotel, badValgt, wifiValgt, morgenmadValgt);
            }

            // Calculate and display the total cost
            int totalOmkostning = tempTilmelding.getPrisDeltagersUdgift();
            totalOmkostningDisplay.setValue("Total pris: " + totalOmkostning + " DKK");

        } catch (Exception ex) {
            ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Kunne ikke beregne omkostninger: " + ex.getMessage());
        }
    }

    // Method to register a participant
    public static void registrerDeltager(Konference konference, LabeledTextInput navnInput, LabeledTextInput telefonInput, LabeledTextInput adresseInput,
                                         LabeledTextInput firmaNavnInput, LabeledTextInput firmaTelefonInput, CheckBox firmaCheckBox,
                                         ComboBox<Hotel> hotelComboBox, CheckBox badCheckBox, CheckBox wifiCheckBox, CheckBox morgenmadCheckBox,
                                         LabeledTextInput ledsagerInput, LabeledListViewInput<Udflugt> udflugtListViewInput, AttributeDisplay totalOmkostningDisplay) {

        try {
            Deltager deltager = Controller.createDeltager(navnInput.getInputValue(), adresseInput.getInputValue(), telefonInput.getInputValue());
            Tilmelding nuværendeTilmelding = Controller.createTilmelding(deltager, LocalDate.now(), LocalDate.now().plusDays(1), false, konference);

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

            // Add hotel options
            Hotel selectedHotel = hotelComboBox.getValue();
            if (selectedHotel != null) {
                boolean badValgt = badCheckBox.isSelected();
                boolean wifiValgt = wifiCheckBox.isSelected();
                boolean morgenmadValgt = morgenmadCheckBox.isSelected();

                nuværendeTilmelding.setHotel(selectedHotel, badValgt, wifiValgt, morgenmadValgt);
            }

            // Calculate and show total cost
            int totalOmkostningForDeltager = nuværendeTilmelding.getPrisDeltagersUdgift();
            ValideringsMetode.showAlert(Alert.AlertType.CONFIRMATION, "Succes", "Deltageren er nu tilmeldt konferencen. Total pris: " + totalOmkostningForDeltager + " DKK");

        } catch (Exception ex) {
            ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Der opstod en fejl: " + ex.getMessage());
        }
    }
}

