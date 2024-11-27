package gui.show;

import application.controller.Controller;
import application.model.Deltager;
import application.model.Tilmelding;
import gui.component.AttributeDisplay;
import gui.component.LabeledListViewInput;
import gui.component.LabeledTextInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class SøgAlleDeltagere extends Stage {
    private final LabeledTextInput deltagerTextInput = new LabeledTextInput("Søg deltager");
    private final AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
    private final AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
    private final AttributeDisplay firmaDisplay = new AttributeDisplay("Firma", "");
    private final AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");
    private final LabeledListViewInput tilmeldingerListview = new LabeledListViewInput("Tilmeldte konferencer");

    public SøgAlleDeltagere() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

        Scene scene = new Scene(pane, 250, 500);
        this.setScene(scene);
        this.show();

        VBox informationBox = new VBox();
        informationBox.setSpacing(5);
        informationBox.setPadding(new Insets(0, 5, 10, 10));

        Button søgDeltagerButton = new Button("Søg deltager");

        informationBox.getChildren().addAll(deltagerTextInput, navnDisplay, tlfDisplay, firmaDisplay, ledsagerDisplay, tilmeldingerListview, søgDeltagerButton);
        pane.add(informationBox, 0, 0);

        søgDeltagerButton.setOnAction(event -> søgning());
        deltagerTextInput.getTextField().setOnAction(event -> søgning());
    }

    private void søgning() {
        Deltager søgteNavn = Controller.søgDeltagerAlle(deltagerTextInput.getInputValue());
        if (søgteNavn != null) {
            navnDisplay.setValue(søgteNavn.getNavn() + "");
            tlfDisplay.setValue(søgteNavn.getTelefonNummer() + "");
            if (søgteNavn.getFirma() != null) {
                firmaDisplay.setValue(søgteNavn.getFirma() + "");
            }
            if (søgteNavn.getLedsager() != null) {
                ledsagerDisplay.setValue(søgteNavn.getLedsager() + "");
            }
            tilmeldingerListview.getListView().getItems().clear();
            for (Tilmelding tilmelding : søgteNavn.getTilmeldinger()) {
                tilmeldingerListview.getListView().getItems().add(tilmelding.getKonference().getNavn());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Deltager ikke fundet");
            alert.setHeaderText("Der kan ikke findes noget, som matcher søgningen");
            alert.showAndWait();
        }
    }
}