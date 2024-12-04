package gui.show;

import application.controller.Controller;
import application.model.Konference;
import gui.component.LabeledDateInput;
import gui.component.LabeledTextInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


import java.time.LocalDate;

public class OpretUdflugtVindue extends Stage {

    public OpretUdflugtVindue(Konference selectedKonference) {
        this.setTitle("Opret Udflugt til: " + selectedKonference.getNavn());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setAlignment(Pos.CENTER);

        VBox UdflugtVindueVBox = new VBox();
        LabeledTextInput navnInput = new LabeledTextInput("Navn:");
        LabeledTextInput adresseInput = new LabeledTextInput("Adresse:");
        LabeledDateInput datoInput = new LabeledDateInput("Dato:");
        LabeledTextInput prisInput = new LabeledTextInput("Pris:");
        Button createButton = new Button("Opret");
        UdflugtVindueVBox.setSpacing(5);
        UdflugtVindueVBox.setPadding(new Insets(0,5,10,10));
        UdflugtVindueVBox.getChildren().addAll(navnInput, adresseInput, datoInput, prisInput,createButton);
        pane.add(UdflugtVindueVBox,0,0);


        createButton.setOnAction(event -> {
            String navn = navnInput.getInputValue();
            String adresse = adresseInput.getInputValue();
            LocalDate dato = datoInput.getInputValue();
            String pris = prisInput.getInputValue();

            if (navn.isEmpty() || adresse.isEmpty() || dato == null || pris.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Manglende oplysninger");
                alert.setHeaderText("Alle felter skal udfyldes");
                alert.setContentText("Udfyld venligst alle felter.");
                alert.showAndWait();
            } else {
                int prisInt = Integer.parseInt(pris);

                Controller.createUdflugt(navn, adresse, dato, prisInt, selectedKonference);
                this.close();
            }
        });

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
}
