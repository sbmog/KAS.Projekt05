package gui.show;

import application.controller.Controller;
import application.model.Konference;
import application.model.Tilmelding;
import gui.component.AttributeDisplay;
import gui.component.LabeledListViewInput;
import gui.component.LabeledTextInput;
import gui.tilmelding.TilmeldPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import application.model.Deltager;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DeltagerTab extends GridPane {
    private final Konference selectedKonference;
    private final AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
    private final AttributeDisplay tlfDisplay = new AttributeDisplay("Telefon nummer", "");
    private final AttributeDisplay firmaDisplay = new AttributeDisplay("Firma", "");
    private final AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");
    private final LabeledTextInput deltagerNavn = new LabeledTextInput("Søg deltager: ");
    private final LabeledListViewInput ledsagerUdflugtListview = new LabeledListViewInput("Ledsagers udflugter");

    public DeltagerTab(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        ListView<Deltager> deltagerListView = new ListView<>();
        deltagerListView.setMinWidth(300);
        deltagerListView.getItems().setAll(Controller.getDeltagerForKonference(selectedKonference));

        VBox venstreBoks = new VBox();
        venstreBoks.setSpacing(5);
        venstreBoks.setPadding(new Insets(0, 5, 10, 10));

        venstreBoks.getChildren().addAll(deltagerNavn, deltagerListView);
        this.add(venstreBoks, 0, 0);

        VBox højreBoks = new VBox();
        højreBoks.setSpacing(5);
        højreBoks.setPadding(new Insets(0, 5, 10, 10));

        højreBoks.getChildren().addAll(navnDisplay, tlfDisplay, firmaDisplay, ledsagerDisplay, ledsagerUdflugtListview);
        this.add(højreBoks, 1, 0);

        deltagerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.toString());
                DateTimeFormatter longDateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                tlfDisplay.setValue(newValue.getTelefonNummer());
                if (newValue.getFirma().toString() != null) {
                    firmaDisplay.setValue(newValue.getFirma().toString());
                }else {
                    firmaDisplay.setValue("Intet firma");
                }
                if (newValue.getLedsager() != null) {
                    ledsagerDisplay.setValue(newValue.getLedsager().getNavn());
                    ledsagerUdflugtListview.getListView().getItems().clear();
                    Tilmelding tilmelding = Controller.getTilmeldingForDeltager(newValue, selectedKonference);
                    if (tilmelding != null) {
                        ledsagerUdflugtListview.getListView().getItems().clear();
                        tilmelding.getUdflugter().forEach(udflugt ->
                                ledsagerUdflugtListview.getListView().getItems().add(String.valueOf(udflugt)));
                    }
                } else {
                    ledsagerDisplay.setValue("Ingen ledsager");
                    ledsagerUdflugtListview.getListView().getItems().clear();
                }
            }
        });

        Button opretTilmelding = new Button("Opret tilmelding");
        this.add(opretTilmelding, 1, 1);
        opretTilmelding.setOnAction(event -> {
            TilmeldPane tilmeldPane = new TilmeldPane(selectedKonference);
            if (!tilmeldPane.isShowing()) {
                tilmeldPane.showAndWait();
            }
        });

        deltagerNavn.getTextField().setOnAction(event -> søgning());
    }

    private void søgning() {
        Deltager søgteNavn = Controller.søgDeltagerIKonference(selectedKonference, deltagerNavn.getInputValue());
        if (søgteNavn != null) {
            navnDisplay.setValue(søgteNavn.getNavn() + "");
            tlfDisplay.setValue(søgteNavn.getTelefonNummer());
            if (søgteNavn.getFirma() != null) {
                firmaDisplay.setValue(søgteNavn.getFirma() + "");
            } else {
                firmaDisplay.setValue("Intet firma");
            }
            ledsagerDisplay.setValue(søgteNavn.getLedsager() + "");
            ledsagerUdflugtListview.getListView().getItems().clear();
            Tilmelding tilmelding = Controller.getTilmeldingForDeltager(søgteNavn, selectedKonference);
            if (tilmelding != null) {
                ledsagerUdflugtListview.getListView().getItems().clear();
                tilmelding.getUdflugter().forEach(udflugt ->
                        ledsagerUdflugtListview.getListView().getItems().add(String.valueOf(udflugt)));
            } else {
                ledsagerDisplay.setValue("Ingen ledsager");
                ledsagerUdflugtListview.getListView().getItems().clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Deltager ikke fundet");
            alert.setHeaderText("Der kan ikke findes noget, som matcher søgningen");
            alert.showAndWait();
        }
    }
}