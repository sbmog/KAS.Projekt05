package gui.tilmelding;

import application.model.Konference;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ValideringsMetode {

    public static boolean validerInput(ComboBox<Konference> konferenceComboBox, TextField navnTextField, TextField telefonTextField,
                                       DatePicker ankomstDatoValg, DatePicker afrejseDatoValg) {

        if (konferenceComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Du skal vælge en konference!");
            return false;
        }
        if (navnTextField.getText().isEmpty() || telefonTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Navn og telefonnummer er påkrævet.");
            return false;
        }
        if (ankomstDatoValg.getValue() == null || afrejseDatoValg.getValue() == null) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Vælg både ankomst- og afrejsedato.");
            return false;
        }
        if (afrejseDatoValg.getValue().isBefore(ankomstDatoValg.getValue())) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Afrejsedato skal være efter ankomstdato.");
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
