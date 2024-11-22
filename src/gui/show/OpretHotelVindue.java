package gui.show;

import application.controller.Controller;
import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OpretHotelVindue extends Stage {
    private Konference selectedKonference;

    public OpretHotelVindue(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setTitle("Opret Hotel");
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        Label navnLabel = new Label("Navn:");
        TextField nameField = new TextField();
        pane.add(navnLabel, 0, 0);
        pane.add(nameField, 1, 0);

        Label adresseLabel = new Label("Adresse:");
        TextField adresseFelt = new TextField();
        pane.add(adresseLabel, 0, 1);
        pane.add(adresseFelt, 1, 1);

        Label enkeltVærelsePrisLabel = new Label("Pris for enkeltværelse:");
        TextField enkeltVærelsePrisFelt = new TextField();
        pane.add(enkeltVærelsePrisLabel, 0, 2);
        pane.add(enkeltVærelsePrisFelt, 1, 2);

        Label dobbeltVærelsePrisLabel = new Label("Pris for dobbeltværelse:");
        TextField dobbeltVærelsePrisFelt = new TextField();
        pane.add(dobbeltVærelsePrisLabel, 0, 3);
        pane.add(dobbeltVærelsePrisFelt, 1, 3);

        Label badTillægLabel = new Label("Tillæg for bad:");
        TextField badTillægFelt = new TextField();
        pane.add(badTillægLabel, 0, 4);
        pane.add(badTillægFelt, 1, 4);

        Label wifiTillægLabel = new Label("Tillæg for wifi:");
        TextField wifiTillægFelt = new TextField();
        pane.add(wifiTillægLabel, 0, 5);
        pane.add(wifiTillægFelt, 1, 5);

        Label morgenmadsTillægLabel = new Label("Tillæg for morgenmad:");
        TextField morgenmadTillægFelt = new TextField();
        pane.add(morgenmadsTillægLabel, 0, 6);
        pane.add(morgenmadTillægFelt, 1, 6);

        Button createButton = new Button("Opret");
        pane.add(createButton, 1, 7);

        createButton.setOnAction(event -> {
            String navn = nameField.getText();
            String adresse = adresseFelt.getText();
            int prisForEnkeltværelse = Integer.parseInt(enkeltVærelsePrisFelt.getText());
            int prisForDobbeltværelse = Integer.parseInt(dobbeltVærelsePrisFelt.getText());
            int badtillæg = Integer.parseInt(badTillægFelt.getText());
            int wifiTillæg = Integer.parseInt(wifiTillægFelt.getText());
            int morgenmadsTillæg = Integer.parseInt(morgenmadTillægFelt.getText());

            Controller.createHotel(navn, adresse,prisForEnkeltværelse,prisForDobbeltværelse, badtillæg, wifiTillæg, morgenmadsTillæg, selectedKonference);
            this.close();
        });

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
}