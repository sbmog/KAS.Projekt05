package gui;

import application.controller.Controller;
import application.model.Konference;
import gui.show.SøgAlleDeltagere;
import gui.tilmelding.TilmeldPane;
import gui.component.AttributeDisplay;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class StartWindow extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Konference adminstrations system");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        Scene scene = new Scene(pane, 650, 450);
        primaryStage.setScene(scene);
        primaryStage.show();

        Label konferenceLabel = new Label("Konferencer");
        konferenceLabel.setStyle("-fx-font-weight: bold;");
        pane.add(konferenceLabel, 0, 0);

        ListView<Konference> konferenceListView = new ListView<>();
        konferenceListView.getItems().addAll(Controller.getKonferencer());
        konferenceListView.setMinWidth(300);
        konferenceListView.getItems().setAll(Controller.getKonferencer());
        pane.add(konferenceListView, 0, 1);

        VBox detailsBox = new VBox(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));

        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay adresseDisplay = new AttributeDisplay("Adresse", "");
        AttributeDisplay startdatoDisplay = new AttributeDisplay("Startdato", "");
        AttributeDisplay slutdatoDisplay = new AttributeDisplay("Slutdato", "");
        AttributeDisplay prisDisplay = new AttributeDisplay("Pris per dag", "");

        detailsBox.getChildren().addAll(navnDisplay, adresseDisplay, startdatoDisplay, slutdatoDisplay, prisDisplay);
        pane.add(detailsBox, 1, 1);

        konferenceListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.toString());
                adresseDisplay.setValue(newValue.getAdresse());
                DateTimeFormatter longDateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                startdatoDisplay.setValue(newValue.getStartdato().format(longDateFormat));
                slutdatoDisplay.setValue(newValue.getSlutdato().format(longDateFormat));
                prisDisplay.setValue(String.valueOf(newValue.getPrisPrDagForKonference()));
            }
        });

        Button vælgKonferenceButton = new Button("Se konferencedetaljer");


        vælgKonferenceButton.setOnAction(event -> {
            Konference selectedKonference = konferenceListView.getSelectionModel().getSelectedItem();
            if (selectedKonference == null) {
                showAlert(Alert.AlertType.ERROR,"Fejl", "Vælg en konference");
            }else{
                KonferenceOversigt konferenceOversigt = new KonferenceOversigt(selectedKonference);
                if (!konferenceOversigt.isShowing()) {
                    konferenceOversigt.showAndWait();
                }
            }
        });

        Button tilmeldingsButton = new Button("Opret tilmelding");

        tilmeldingsButton.setOnAction(event -> {
            Konference selectedKonference = konferenceListView.getSelectionModel().getSelectedItem();
            if (selectedKonference != null) {
                TilmeldPane tilmeldPane = new TilmeldPane(selectedKonference);
                if (!tilmeldPane.isShowing()) {
                    tilmeldPane.showAndWait();
                }
            }else {
                TilmeldPane tilmeldPane = new TilmeldPane(null);
                if (!tilmeldPane.isShowing()) {
                    tilmeldPane.showAndWait();
                }
            }
        });

        Button søgDeltagerButton = new Button("Søg deltager");

        søgDeltagerButton.setOnAction(event -> {
            SøgAlleDeltagere søgDeltagerPane = new SøgAlleDeltagere();
            if (!søgDeltagerPane.isShowing()) {
                søgDeltagerPane.showAndWait();
            }
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(søgDeltagerButton, tilmeldingsButton, vælgKonferenceButton);
        pane.add(buttonBox, 0, 3);
    }
    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
