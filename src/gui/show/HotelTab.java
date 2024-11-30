package gui.show;

import application.controller.Controller;
import application.model.Hotel;
import application.model.Konference;
import gui.component.AttributeDisplay;
import gui.component.LabeledListViewInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

public class HotelTab extends GridPane {
    private Konference selectedKonference;
    private ListView<Hotel> hotelListView;

    public HotelTab(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        hotelListView = new ListView<>();
        hotelListView.setMinWidth(300);
        hotelListView.getItems().setAll(Controller.getHotellerForKonference(selectedKonference));
        this.add(hotelListView, 0, 0);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay adresseDisplay = new AttributeDisplay("Adresse", "");
        AttributeDisplay prisForEnkeltVærelseDisplay = new AttributeDisplay("Pris for enkelt værelse", "");
        AttributeDisplay prisForDobbeltVærelseDisplay = new AttributeDisplay("Pris for dobbelt værelse", "");
        AttributeDisplay prisForBadDisplay = new AttributeDisplay("Pris for bad", "");
        AttributeDisplay prisForWifiDisplay = new AttributeDisplay("Pris for wifi", "");
        AttributeDisplay prisMorgenMadDisplay = new AttributeDisplay("Pris for Morgenmad", "");
        LabeledListViewInput<String> listeOverOvernattende = new LabeledListViewInput<>("Overnattende");

        detailsBox.getChildren().addAll(navnDisplay, adresseDisplay, prisForEnkeltVærelseDisplay, prisForDobbeltVærelseDisplay, prisForBadDisplay, prisForWifiDisplay, prisMorgenMadDisplay, listeOverOvernattende);
        this.add(detailsBox, 1, 0);

        hotelListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.getNavn());
                adresseDisplay.setValue(newValue.getAdresse());
                prisForEnkeltVærelseDisplay.setValue(String.valueOf(newValue.getPrisForEnkeltVærelse()));
                prisForDobbeltVærelseDisplay.setValue(String.valueOf(newValue.getPrisForDobbeltVærelse()));
                prisForBadDisplay.setValue(String.valueOf(newValue.getBadTillæg()));
                prisForWifiDisplay.setValue(String.valueOf(newValue.getWifiTillæg()));
                prisMorgenMadDisplay.setValue(String.valueOf(newValue.getMorgenmadsTillæg()));
                listeOverOvernattende.getListView().getItems().clear();
                newValue.getTilmeldinger().forEach(tilmelding ->
                        listeOverOvernattende.getListView().getItems().add(tilmelding.getDeltager().toStringMedLedsager()));
            }
        });

        Button opretHotel = new Button("Opret Hotel");
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().add(opretHotel);

        this.add(buttonBox, 1, 1);

        opretHotel.setOnAction(event -> {
            if (selectedKonference != null) {
                OpretHotelVindue createHotelWindow = new OpretHotelVindue(selectedKonference);
                createHotelWindow.showAndWait();
                updateHotelList();
            }
        });
    }

    private void updateHotelList() {
        hotelListView.getItems().setAll(Controller.getHotellerForKonference(selectedKonference));
    }
}