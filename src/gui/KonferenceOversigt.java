package gui;

import gui.show.DeltagerTab;
import gui.show.HotelTab;
import gui.show.UdflugtTab;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class KonferenceOversigt extends Stage {
    public KonferenceOversigt() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(new Tab("Deltager", new DeltagerTab()));
        tabPane.getTabs().add(new Tab("Hotel", new HotelTab()));
        tabPane.getTabs().add(new Tab("Udflugt", new UdflugtTab()));

        Scene scene = new Scene(tabPane, 600, 400);
        this.setScene(scene);
        this.setTitle("Konference Administrations System - " + getKonference().getNavn());
    }
}