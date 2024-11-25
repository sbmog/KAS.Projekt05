package gui;

import application.model.Konference;
import gui.show.DeltagerTab;
import gui.show.HotelTab;
import gui.show.UdflugtTab;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class KonferenceOversigt extends Stage {
    public KonferenceOversigt(Konference konference) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(new Tab("Deltager", new DeltagerTab(konference)));
        tabPane.getTabs().add(new Tab("Hotel", new HotelTab(konference)));
        tabPane.getTabs().add(new Tab("Udflugt", new UdflugtTab(konference)));

        Scene scene = new Scene(tabPane, 600, 400);
        this.setScene(scene);
        this.setTitle("Konference Administrations System - " + konference.getNavn());
    }
}