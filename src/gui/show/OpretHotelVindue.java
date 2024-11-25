package gui.show;

import application.controller.Controller;
import application.model.Konference;
import gui.component.LabeledTextInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OpretHotelVindue extends Stage {
    private Konference selectedKonference;

    public OpretHotelVindue(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setTitle("Opret Hotel");
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setAlignment(Pos.CENTER);

        VBox HotelVindueVBox = new VBox();
        LabeledTextInput navnInput = new LabeledTextInput("Navn:");
        LabeledTextInput adresseInput = new LabeledTextInput("Adresse:");
        LabeledTextInput enkeltVærelsePrisInput = new LabeledTextInput("Pris for enkeltværelse:");
        LabeledTextInput dobbeltVærelsePrisInput = new LabeledTextInput("Pris for dobbeltværelse:");
        LabeledTextInput badTillægInput = new LabeledTextInput("Tillæg for bad:");
        LabeledTextInput wifiTillægInput = new LabeledTextInput("Tillæg for wifi:");
        LabeledTextInput morgenmadsTillægInput = new LabeledTextInput("Tillæg for morgenmad:");
        Button createButton = new Button("Opret");
        HotelVindueVBox.setSpacing(5);
        HotelVindueVBox.setPadding(new Insets(0, 5, 10, 10));
        HotelVindueVBox.getChildren().addAll(navnInput, adresseInput, enkeltVærelsePrisInput, dobbeltVærelsePrisInput, badTillægInput, wifiTillægInput, morgenmadsTillægInput, createButton);
        pane.add(HotelVindueVBox, 0, 0);

        createButton.setOnAction(event -> {
            String navn = navnInput.getInputValue();
            String adresse = adresseInput.getInputValue();
            String prisForEnkeltværelse = enkeltVærelsePrisInput.getInputValue();
            String prisForDobbeltværelse = dobbeltVærelsePrisInput.getInputValue();
            String badtillæg = badTillægInput.getInputValue();
            String wifiTillæg = wifiTillægInput.getInputValue();
            String morgenmadsTillæg = morgenmadsTillægInput.getInputValue();

            if (navn.isEmpty() || adresse.isEmpty() || prisForEnkeltværelse.isEmpty() || prisForDobbeltværelse.isEmpty() || badtillæg.isEmpty() || wifiTillæg.isEmpty() || morgenmadsTillæg.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Manglende oplysninger");
                alert.setHeaderText("Alle felter skal udfyldes");
                alert.setContentText("Udfyld venligst alle felter.");
                alert.showAndWait();
            } else {
                int prisForEnkeltværelseInt = Integer.parseInt(prisForEnkeltværelse);
                int prisForDobbeltværelseInt = Integer.parseInt(prisForDobbeltværelse);
                int badtillægInt = Integer.parseInt(badtillæg);
                int wifiTillægInt = Integer.parseInt(wifiTillæg);
                int morgenmadsTillægInt = Integer.parseInt(morgenmadsTillæg);

                Controller.createHotel(navn, adresse, prisForEnkeltværelseInt, prisForDobbeltværelseInt, badtillægInt, wifiTillægInt, morgenmadsTillægInt, selectedKonference);
                this.close();
            }
        });

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
}