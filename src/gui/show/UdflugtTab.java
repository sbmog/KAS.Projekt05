package gui.show;

import application.controller.Controller;
import application.model.Udflugt;
import gui.component.AttributeDisplay;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UdflugtTab extends GridPane {
    public UdflugtTab() {

        this.setPadding(new Insets(5));

        ListView<Udflugt> hotelListView = new ListView<>();
        hotelListView.setMinWidth(300);
        hotelListView.getItems().setAll(Controller.getUdflugter());
        this.add(hotelListView, 0, 0);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay adresseDisplay = new AttributeDisplay("Adresse", "");
        AttributeDisplay datoForUdflugt = new AttributeDisplay("Dato", "");
        AttributeDisplay prisForUdflugt = new AttributeDisplay("Pris","");



        detailsBox.getChildren().addAll(navnDisplay, adresseDisplay, datoForUdflugt, prisForUdflugt);
        this.add(detailsBox, 1, 0);

        hotelListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.getNavn());
                adresseDisplay.setValue(newValue.getAdresse());
                datoForUdflugt.setValue(String.valueOf(newValue.getDato()));
                prisForUdflugt.setValue(String.valueOf(newValue.getPris()));
            }
        });
    }
}

