package gui.tilmelding;


import application.model.Konference;
import gui.component.LabeledDateInput;
import gui.component.LabeledTextInput;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;

public class ValideringsMetode {

    public static boolean validerInput(ComboBox<Konference> konferenceComboBox, LabeledTextInput navnTextField, LabeledTextInput telefonTextField,
                                       LabeledDateInput ankomstDatoValg, LabeledDateInput afrejseDatoValg, LabeledTextInput adresse, CheckBox firmaCheckBox, LabeledTextInput firmaNavnInput,LabeledTextInput firmaTelefonInput) {


        if (konferenceComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Fejl", "Du skal vælge en konference!");
            return false;
        }

        if (navnTextField.getInputValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fejl", "Navn er påkrævet.");
            return false;
        }
        if (telefonTextField.getInputValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fejl", "Telefonnummer er påkrævet.");
            return false;
        }
        if (adresse.getInputValue().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Fejl", "Adresse er påkrævet.");
            return false;
        }

        if (firmaCheckBox.isSelected()) {
            if (firmaNavnInput.getInputValue().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Fejl", "Firma navn er påkrævet.");
                return false;
            }
            if (firmaTelefonInput.getInputValue().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Fejl", "Firma telefonnummer er påkrævet.");
                return false;
            }
        }

            if (ankomstDatoValg.getInputValue() == null || afrejseDatoValg.getInputValue() == null) {
                showAlert(Alert.AlertType.ERROR, "Fejl", "Vælg både ankomst- og afrejsedato.");
                return false;
            }
            if (afrejseDatoValg.getInputValue().isBefore(ankomstDatoValg.getInputValue())) {
                showAlert(Alert.AlertType.ERROR, "Fejl", "Afrejsedato skal være efter ankomstdato.");
                return false;
            }
            return true;
        }





    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
