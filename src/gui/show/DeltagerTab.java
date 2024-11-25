package gui.show;

import application.controller.Controller;
import application.model.Konference;
import application.model.Tilmelding;
import gui.component.AttributeDisplay;
import gui.component.LabeledTextInput;
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
    private AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
    private AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
    private AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");
    LabeledTextInput deltagerNavn = new LabeledTextInput("Søg deltager: ");

    public DeltagerTab(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        ListView<Deltager> deltagerListView = new ListView<>();
        deltagerListView.setMinWidth(300);
        deltagerListView.getItems().setAll(Controller.getDeltagerForKonference(selectedKonference));

        VBox højreBoks = new VBox();
        højreBoks.setSpacing(5);
        højreBoks.setPadding(new Insets(0, 5, 10, 10));


        VBox venstreBoks = new VBox();
        venstreBoks.setSpacing(5);
        venstreBoks.setPadding(new Insets(0, 5, 10, 10));
        Button søgDeltagerKnap = new Button("Søg");


        venstreBoks.getChildren().addAll(navnDisplay, tlfDisplay, ledsagerDisplay);
        this.add(venstreBoks, 0, 0);

        højreBoks.getChildren().addAll(deltagerListView, deltagerNavn, søgDeltagerKnap);
        this.add(højreBoks, 1, 0);


        deltagerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.toString());
                DateTimeFormatter longDateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                tlfDisplay.setValue(newValue.getTelefonNummer());
                ledsagerDisplay.setValue(newValue.getLedsager() != null ? newValue.getLedsager().getNavn() : "Ingen ledsager");
            }
        });

        Button opretTilmelding = new Button("Opret tilmelding");
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().add(opretTilmelding);

        this.add(buttonBox, 0, 1);
        opretTilmelding.setOnAction(event -> {
            TilmeldPane tilmeldPane = new TilmeldPane(selectedKonference);
            if (!tilmeldPane.isShowing()) {
                tilmeldPane.showAndWait();
            }
        });

        søgDeltagerKnap.setOnAction(event -> søgning());
        deltagerNavn.getTextField().setOnAction(event -> søgning());

    }

    private void søgning() {
        Deltager søgteNavn = Controller.søgDeltagerIKonference(selectedKonference, deltagerNavn.getInputValue());
        navnDisplay.setValue(søgteNavn.getNavn() + "");
        tlfDisplay.setValue(søgteNavn.getTelefonNummer() + "");
        ledsagerDisplay.setValue(søgteNavn.getLedsager() + "");
    }


}

