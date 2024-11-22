package gui.show;

import application.controller.Controller;
import application.model.Deltager;
import gui.component.AttributeDisplay;
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
    private ArrayList<Deltager> deltagere = new ArrayList<>();


    public SøgDeltagerPane() {

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

        Scene scene = new Scene(pane, 500, 400);
        this.setScene(scene);
        this.show();

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
        AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");

        detailsBox.getChildren().addAll(navnDisplay, tlfDisplay, ledsagerDisplay);
        pane.add(detailsBox, 2, 0);

        Label søgLabel = new Label("Indtast deltager");
        pane.add(søgLabel, 0, 0);


        pane.add(deltagerTextField, 1, 0);

        Button søgDeltagerButton = new Button("Søg deltager");
        pane.add(søgDeltagerButton,0,3);

        søgDeltagerButton.setOnAction(event->{
            Deltager søgteNavn = Controller.søgDeltagerAlle(deltagerTextField.getText());
            navnDisplay.setValue(søgteNavn.getNavn() + "");
            tlfDisplay.setValue(søgteNavn.getTelefonNummer() + "");
            ledsagerDisplay.setValue(søgteNavn.getLedsager() + "");

        });




    }
}