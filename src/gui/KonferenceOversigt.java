package gui;

import gui.show.DeltagerPane;
import gui.show.HotelPane;
import gui.show.UdflugtPane;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class KonferenceOversigt extends Stage {
    public KonferenceOversigt() {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(new Tab("Deltager", new DeltagerPane()));
        tabPane.getTabs().add(new Tab("HotelPane", new HotelPane()));
        tabPane.getTabs().add(new Tab("UdflugtPane", new UdflugtPane()));

        Scene scene = new Scene(tabPane, 600, 400);
        this.setScene(scene);
        this.setTitle("Konference Administrations System");
    }
}