package gui;

import application.controller.Controller;
import application.model.Deltager;
import application.model.Konference;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KonferenceOversigt extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();


        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setTitle("Konference adminstrations system");
        primaryStage.setScene(scene);
        primaryStage.show();


        ListView<Konference> konferenceListView = new ListView<>();
        konferenceListView.getItems().addAll(Controller.getKonferencer()); // s

        // Participant ListView
        ListView<Deltager> deltagerListView = new ListView<>();

        // Conference ListView Selection Listener
        konferenceListView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Clear and populate deltagerListView with participants of the selected conference
                deltagerListView.getItems().clear();
                deltagerListView.getItems().addAll(newSelection.getTilmeldingerSomDeltager());
            }
        });

        //button
        Button åbenButton = new Button("Åben");


        //buttonaction
        //åbenButton.setOnAction(event -> åbenKonferencePane());

        //laout for left side (konferencer)
        VBox leftPane = new VBox(10, new Label("Konferencer:"), konferenceListView, åbenButton);
        leftPane.setPadding(new Insets(10));
        leftPane.setPrefHeight(300);

        VBox rightPane = new VBox(10, new Label("Deltagere:"), deltagerListView);
        rightPane.setPadding(new Insets(10));
        rightPane.setPrefWidth(300);


        HBox mainContent = new HBox(20, leftPane, rightPane); // 20 is the gap between the panes
        mainContent.setPadding(new Insets(10)); // Add padding around the entire content

        borderPane.setCenter(mainContent);


    }
}
