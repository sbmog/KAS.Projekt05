package gui.show;

import application.controller.Controller;
import application.model.Konference;
import gui.component.AttributeDisplay;
import gui.tilmelding.TilmeldPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import application.model.Deltager;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeltagerTab extends GridPane {
    private Konference selectedKonference;

    public DeltagerTab(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        ListView<Deltager> deltagerListView = new ListView<>();
        deltagerListView.setMinWidth(300);
        deltagerListView.getItems().setAll(Controller.getDeltagerForKonference(selectedKonference));
        this.add(deltagerListView, 0, 0);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
        AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");

        detailsBox.getChildren().addAll(navnDisplay, tlfDisplay, ledsagerDisplay);
        this.add(detailsBox, 1, 0);

        deltagerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.toString());
                tlfDisplay.setValue(newValue.getTelefonNummer());
                ledsagerDisplay.setValue(newValue.getLedsager() != null ? newValue.getLedsager().getNavn() : "Ingen ledsager");
            }
        });

        Button opretTilmelding = new Button("Opret tilmelding");
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().add(opretTilmelding);

        this.add(buttonBox, 1, 1);
        opretTilmelding.setOnAction(event -> {
            TilmeldPane tilmeldPane = new TilmeldPane(selectedKonference);
            if (!tilmeldPane.isShowing()) {
                tilmeldPane.showAndWait();
            }
        });
    }
}