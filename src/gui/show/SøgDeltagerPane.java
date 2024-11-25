package gui.show;

import application.controller.Controller;
import application.model.Deltager;
import application.model.Konference;
import application.model.Tilmelding;
import gui.component.AttributeDisplay;
import gui.component.LabeledTextInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class SøgDeltagerPane extends Stage {
    private TextField deltagerTextField = new TextField();
    private TextField result = new TextField();
    private ArrayList<String> tilmeldinger = new ArrayList<>();
    LabeledTextInput deltagerTextInput = new LabeledTextInput("Deltager");

    public SøgDeltagerPane() {


        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

        Scene scene = new Scene(pane, 250, 500);
        this.setScene(scene);
        this.show();

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
        AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");
        ListView<String> tilmeldingerDisplay = new ListView<>();
        Button søgDeltagerButton = new Button("Søg deltager");

        detailsBox.getChildren().addAll(deltagerTextInput, navnDisplay, tlfDisplay, ledsagerDisplay, tilmeldingerDisplay, søgDeltagerButton);
        pane.add(detailsBox, 2, 0);


        søgDeltagerButton.setOnAction(event->{
            Deltager søgteNavn = Controller.søgDeltagerAlle(deltagerTextInput.getInputValue());
            navnDisplay.setValue(søgteNavn.getNavn() + "");
            tlfDisplay.setValue(søgteNavn.getTelefonNummer() + "");
            ledsagerDisplay.setValue(søgteNavn.getLedsager() + "");
            tilmeldingerDisplay.getItems().clear();
            for (Tilmelding tilmelding : søgteNavn.getTilmeldinger()) {
                    tilmeldingerDisplay.getItems().add(tilmelding.getKonference().getNavn());
                }

        });




    }
}