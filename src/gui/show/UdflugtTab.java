package gui.show;

import application.controller.Controller;
import application.model.Konference;
import application.model.Ledsager;
import application.model.Udflugt;
import gui.component.AttributeDisplay;
import gui.component.LabeledListViewInput;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;

public class UdflugtTab extends GridPane {
    private Konference selectedKonference;
    private ListView<Udflugt> udflugtListView = new ListView<>();

    public UdflugtTab(Konference selectedKonference) {
        this.selectedKonference = selectedKonference;

        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        udflugtListView = new ListView<>();
        udflugtListView.setMinWidth(300);
        udflugtListView.getItems().setAll(Controller.getUdflugterForKonference(selectedKonference));
        this.add(udflugtListView, 0, 0);

        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay navnDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay adresseDisplay = new AttributeDisplay("Adresse", "");
        AttributeDisplay datoForUdflugt = new AttributeDisplay("Dato", "");
        AttributeDisplay prisForUdflugt = new AttributeDisplay("Pris", "");
        LabeledListViewInput<String> deltagerForUdflugten = new LabeledListViewInput<>("Deltager for udflugt");

        detailsBox.getChildren().addAll(navnDisplay, adresseDisplay, datoForUdflugt, prisForUdflugt, deltagerForUdflugten);
        this.add(detailsBox, 1, 0);

        udflugtListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                navnDisplay.setValue(newValue.getNavn());
                adresseDisplay.setValue(newValue.getAdresse());
                datoForUdflugt.setValue(String.valueOf(newValue.getDato()));
                prisForUdflugt.setValue(String.valueOf(newValue.getPris()));
                deltagerForUdflugten.getListView().getItems().clear();
                newValue.getTilmeldinger().forEach(tilmelding ->
                        deltagerForUdflugten.getListView().getItems().add(tilmelding.getDeltager().getLedsager().toStringMedDeltager()));
            }
        });

        Button opretUdflugt = new Button("Opret Udflugt");
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().add(opretUdflugt);

        this.add(buttonBox, 1, 1);

        opretUdflugt.setOnAction(event -> {
            if (selectedKonference != null) {
                OpretUdflugtVindue createUdflugtVindue = new OpretUdflugtVindue(selectedKonference);
                createUdflugtVindue.showAndWait();
                updateUdflugtList();
            }
        });
    }

    private void updateUdflugtList() {
        udflugtListView.getItems().setAll(Controller.getUdflugterForKonference(selectedKonference));
    }
}

