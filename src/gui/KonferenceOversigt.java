package gui;

import application.controller.Controller;
import application.model.Konference;
import gui.show.DeltagerPane;
import gui.show.HotelPane;
import gui.show.UdflugtPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KonferenceOversigt extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
    public void start(Stage stage)  {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().add(new Tab("Deltager", new DeltagerPane()));
        tabPane.getTabs().add(new Tab("HotelPane", new HotelPane()));
        tabPane.getTabs().add(new Tab("UdflugtPane", new UdflugtPane()));
        Scene scene = new Scene(tabPane);
        stage.setScene(scene);
        stage.show();
    }


}

