package gui.show;

import application.controller.Controller;
import application.model.Konference;
import gui.component.AttributeDisplay;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DeltagerPane extends GridPane {
    public DeltagerPane() {
        this.setPadding(new Insets(5));
        ListView<Konference> konferenceListView = new ListView<>();
        konferenceListView.setMinWidth(300);
        konferenceListView.getItems().setAll(Controller.getKonferencer());
        this.add(konferenceListView, 0, 0);
        VBox detailsBox = new VBox();
        detailsBox.setSpacing(5);
        detailsBox.setPadding(new Insets(0, 5, 10, 10));
        AttributeDisplay nameDisplay = new AttributeDisplay("Navn", "");
        AttributeDisplay premierDate = new AttributeDisplay("Premier dato", "");
        AttributeDisplay endDate = new AttributeDisplay("Slutdato", "");
        detailsBox.getChildren().addAll(nameDisplay, premierDate, endDate);
        this.add(detailsBox, 1, 0);
    }
}
