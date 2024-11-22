package gui.show;

import application.controller.Controller;
import application.model.Hotel;
import gui.component.AttributeDisplay;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HotelTab extends GridPane {
    public HotelTab() {

    this.setPadding(new Insets(5));

    ListView<Hotel> hotelListView = new ListView<>();
        hotelListView.setMinWidth(300);
        hotelListView.getItems().setAll(Controller.getHoteller());
        this.add(hotelListView, 0, 0);

    VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
    AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
    AttributeDisplay adresseDisplay = new AttributeDisplay("Adresse", "");
    AttributeDisplay prisForBadDisplay = new AttributeDisplay("Pris for bad", "");
    AttributeDisplay prisForWifiDisplay = new AttributeDisplay("Pris for wifi","");
    AttributeDisplay prisMorgenMadDisplay = new AttributeDisplay("Pris for Morgenmad","");


        detailsBox.getChildren().addAll(navnDisplay, adresseDisplay, prisForBadDisplay, prisForWifiDisplay, prisMorgenMadDisplay);
        this.add(detailsBox, 1, 0);

        hotelListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue != null) {
            navnDisplay.setValue(newValue.getNavn());
            adresseDisplay.setValue(newValue.getAdresse());
            prisForBadDisplay.setValue(String.valueOf(newValue.getBadTillæg()));
            prisForWifiDisplay.setValue(String.valueOf(newValue.getWifiTillæg()));
            prisMorgenMadDisplay.setValue(String.valueOf(newValue.getMorgenmadsTillæg()));
        }
    });
}
}

