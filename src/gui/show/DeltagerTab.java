package gui.show;

import application.controller.Controller;
import application.model.Konference;
import application.model.Tilmelding;
import gui.component.AttributeDisplay;
import gui.component.LabeledListViewInput;
import gui.component.LabeledTextInput;
import gui.tilmelding.TilmeldPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.layout.*;
import application.model.Deltager;


public class DeltagerTab extends GridPane {
    private final Konference selectedKonference;
    private final AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
    private final AttributeDisplay telefonNummerDisplay = new AttributeDisplay("Telefon nummer", "");
    private final AttributeDisplay adresseDisplay = new AttributeDisplay("Adresse", "");
    private final AttributeDisplay erForedragsholderDisplay = new AttributeDisplay("Er foredragsholder?", "");
    private final AttributeDisplay firmaDisplay = new AttributeDisplay("Firma", "");
    private final AttributeDisplay hotelDisplay = new AttributeDisplay("Hotel", "");
    private final AttributeDisplay ledsagerDisplay = new AttributeDisplay("Ledsager", "");
    private final LabeledTextInput deltagerNavn = new LabeledTextInput("Søg deltager: ");
    private final LabeledListViewInput ledsagerUdflugtListview = new LabeledListViewInput("Ledsagers udflugter");
    private final AttributeDisplay prisDsiplay = new AttributeDisplay("Prisen for deltagelse", "");
    private final AttributeDisplay deltagersPrisDisplay = new AttributeDisplay("Deltagers udgifter", "");
    private final ListView<Deltager> deltagerListView;


    public DeltagerTab(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        deltagerListView = new ListView<>();
        deltagerListView.setMinWidth(300);
        deltagerListView.setPrefHeight(500);
        deltagerListView.getItems().setAll(Controller.getDeltagerForKonference(selectedKonference));

        //initiere sorteringsmetoden
        ObservableList<Deltager> deltagere = FXCollections.observableArrayList(Controller.getDeltagerForKonference(selectedKonference));
        SorteringsMetode.sorterNavne(deltagere);
        deltagerListView.getItems().setAll(deltagere);

        VBox venstreBoks = new VBox();
        venstreBoks.setSpacing(5);
        venstreBoks.setPadding(new Insets(0, 5, 10, 10));

        venstreBoks.getChildren().addAll(deltagerNavn, deltagerListView);
        this.add(venstreBoks, 0, 0);

        VBox højreBoks = new VBox();
        højreBoks.setSpacing(5);
        højreBoks.setPadding(new Insets(0, 5, 10, 10));

        højreBoks.getChildren().addAll(navnDisplay, telefonNummerDisplay, adresseDisplay, erForedragsholderDisplay, firmaDisplay, hotelDisplay, ledsagerDisplay, ledsagerUdflugtListview, prisDsiplay, deltagersPrisDisplay);
        this.add(højreBoks, 1, 0);


        deltagerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.toString());
                telefonNummerDisplay.setValue(newValue.getTelefonNummer());
                adresseDisplay.setValue(newValue.getAdresse());
                if (newValue.getFirma() != null) {
                    firmaDisplay.setValue(newValue.getFirma() + "");
                } else {
                    firmaDisplay.setValue("Intet firma");
                }
                Tilmelding tilmelding = Controller.getTilmeldingForDeltager(newValue, selectedKonference);
                if (tilmelding.isForedragsholder()) {
                    erForedragsholderDisplay.setValue("Ja");
                } else {
                    erForedragsholderDisplay.setValue("Nej");
                }
                if (tilmelding.getHotel() != null) {
                    hotelDisplay.setValue(tilmelding.getHotel() + "");
                } else {
                    hotelDisplay.setValue("Intet hotel valgt");
                }
                if (newValue.getLedsager() != null) {
                    ledsagerDisplay.setValue(newValue.getLedsager().getNavn());
                    ledsagerUdflugtListview.getListView().getItems().clear();
                    ledsagerUdflugtListview.getListView().getItems().clear();
                    tilmelding.getUdflugter().forEach(udflugt ->
                            ledsagerUdflugtListview.getListView().getItems().add(udflugt.getNavn()));
                } else {
                    ledsagerDisplay.setValue("Ingen ledsager");
                    ledsagerUdflugtListview.getListView().getItems().clear();
                }

                prisDsiplay.setValue(String.valueOf(Controller.getSamletPrisForDeltagelse(Controller.getTilmeldingForDeltager(newValue, selectedKonference))));
                deltagersPrisDisplay.setValue(String.valueOf(Controller.getPrisDeltagersUdgift(Controller.getTilmeldingForDeltager(newValue, selectedKonference))));
            }
        });

        Button opretTilmelding = new Button("Opret tilmelding");
        this.add(opretTilmelding, 1, 1);
        opretTilmelding.setOnAction(event -> {
            TilmeldPane tilmeldPane = new TilmeldPane(selectedKonference);
            if (!tilmeldPane.isShowing()) {
                tilmeldPane.showAndWait();
            }
            // updateDeltagerList();
        });

        deltagerNavn.getTextField().setOnAction(event -> søgning());
    }

    private void søgning() {
        Deltager søgteNavn = Controller.søgDeltagerIKonference(selectedKonference, deltagerNavn.getInputValue());
        if (søgteNavn != null) {
            navnDisplay.setValue(søgteNavn.getNavn() + "");
            telefonNummerDisplay.setValue(søgteNavn.getTelefonNummer());
            adresseDisplay.setValue(søgteNavn.getAdresse());
            if (søgteNavn.getFirma() != null) {
                firmaDisplay.setValue(søgteNavn.getFirma() + "");
            } else {
                firmaDisplay.setValue("Intet firma");
            }
            Tilmelding tilmelding = Controller.getTilmeldingForDeltager(søgteNavn, selectedKonference);
            if (tilmelding.isForedragsholder()) {
                erForedragsholderDisplay.setValue("Ja");
            } else {
                erForedragsholderDisplay.setValue("Nej");
            }
            if (tilmelding.getHotel() != null) {
                hotelDisplay.setValue(tilmelding.getHotel() + "");
            } else {
                hotelDisplay.setValue("Intet hotel valgt");
            }
            if (søgteNavn.getLedsager() != null) {
                ledsagerDisplay.setValue(søgteNavn.getLedsager().getNavn());
                ledsagerUdflugtListview.getListView().getItems().clear();
                ledsagerUdflugtListview.getListView().getItems().clear();
                tilmelding.getUdflugter().forEach(udflugt ->
                        ledsagerUdflugtListview.getListView().getItems().add(udflugt.getNavn()));
            } else {
                ledsagerDisplay.setValue("Ingen ledsager");
                ledsagerUdflugtListview.getListView().getItems().clear();
            }
            prisDsiplay.setValue(String.valueOf(Controller.getSamletPrisForDeltagelse(Controller.getTilmeldingForDeltager(søgteNavn, selectedKonference))));
            deltagersPrisDisplay.setValue(String.valueOf(Controller.getPrisDeltagersUdgift(Controller.getTilmeldingForDeltager(søgteNavn, selectedKonference))));


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Deltager ikke fundet");
            alert.setHeaderText("Der kan ikke findes noget, som matcher søgningen");
            alert.showAndWait();
        }

    }
}


    //todo er denne metode nødvendig
//    private void updateDeltagerList() {
//
//        ObservableList<Deltager> deltagere = FXCollections.observableArrayList(
//                Controller.getDeltagerForKonference(selectedKonference)
//        );
//
//        SorteringsMetode.sorterVedGivetNavn(deltagere);
//
//       deltagerListView.getItems().setAll(deltagere);
//
//    }
//
//
//}
