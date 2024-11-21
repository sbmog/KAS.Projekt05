package gui.show;

import application.controller.Controller;
import application.model.Konference;
import gui.component.AttributeDisplay;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import application.model.Deltager;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeltagerPane extends GridPane {
    public DeltagerPane() {
        this.setPadding(new Insets(5));
        ListView<Deltager> konferenceListView = new ListView<>();
        konferenceListView.setMinWidth(300);
        konferenceListView.getItems().setAll(Controller.getDeltagere());
        this.add(konferenceListView, 0, 0);
        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
        AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");

        detailsBox.getChildren().addAll(navnDisplay, tlfDisplay, ledsagerDisplay);
        this.add(detailsBox, 1, 0);
        konferenceListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.toString());
                DateTimeFormatter longDateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                tlfDisplay.setValue(newValue.getTelefonNummer());
                ledsagerDisplay.setValue(newValue.getLedsager() != null ? newValue.getLedsager().getNavn() : "Ingen ledsager");
            }
        });
    }

}
