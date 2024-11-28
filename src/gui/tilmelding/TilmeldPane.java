package gui.tilmelding;

import application.controller.Controller;
import application.model.*;
import gui.component.AttributeDisplay;
import gui.component.LabeledDateInput;
import gui.component.LabeledListViewInput;
import gui.component.LabeledTextInput;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.LocalDate;

import static gui.tilmelding.TilmeldingsogBeregningsMetode.registrerDeltager;
import static gui.tilmelding.ValideringsMetode.validerInput;

public class TilmeldPane extends Stage {

    private final LabeledTextInput navnInput = new LabeledTextInput("Navn");
    private final LabeledTextInput telefonInput = new LabeledTextInput("Telefonnummer");
    private final LabeledTextInput adresseInput = new LabeledTextInput("Adresse");
    private final LabeledDateInput ankomstDatoInput = new LabeledDateInput("Ankomstdato");
    private final LabeledDateInput afrejseDatoInput = new LabeledDateInput("Afrejsedato");
    private final CheckBox firmaCheckBox = new CheckBox("Er du tilknyttet firma?");
    private final LabeledTextInput firmaNavnInput = new LabeledTextInput("Firma navn");
    private final LabeledTextInput firmaTelefonInput = new LabeledTextInput("Telefonnummer");
    private final ComboBox<Konference> konferenceComboBox = new ComboBox<>();
    private final ComboBox<Hotel> hotelComboBox = new ComboBox<>();
    private final CheckBox badCheckBox = new CheckBox("Ønsker du bad?");
    private final CheckBox wifiCheckBox = new CheckBox("Ønsker du wifi?");
    private final CheckBox morgenmadCheckBox = new CheckBox("Ønsker du morgenmad?");
    private final LabeledTextInput ledsagerInput = new LabeledTextInput("Ledsager");
    private final CheckBox ForedragsholderCheckBox = new CheckBox("Er du foredragsholder?");
    private final LabeledListViewInput<Udflugt> udflugtListViewInput = new LabeledListViewInput("Vælg udflugt");
    private final AttributeDisplay totalOmkostningDisplay = new AttributeDisplay("Total pris", "0 DKK");
    private Tilmelding nuværendeTilmelding;

    public TilmeldPane(Konference konference) {
        this.setTitle("Tilmeld Deltager");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(20));
        pane.setVgap(15);
        pane.setHgap(10);
///////////////////////////////////////////////////////////////////////////
        Image image = new Image("./application/baggrund1.png");
        BackgroundImage bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true)
        );
        Background bg = new Background(bgImage);
        pane.setBackground(bg);
//////////////////////////////////////////////////////////////////////////////

        Scene scene = new Scene(pane, 600, 850);
        this.setScene(scene);
        this.show();
        initializeFields(konference);

        VBox personligBox = new VBox(10, navnInput, telefonInput, adresseInput);
        personligBox.setPadding(new Insets(10));
        personligBox.setAlignment(Pos.TOP_LEFT);
        pane.add(personligBox, 0, 0);

        Label konferenceLabel = new Label("vælg konference: ");
        VBox konferenceVBox = new VBox(10, konferenceLabel, konferenceComboBox, ankomstDatoInput, afrejseDatoInput);
        konferenceVBox.setAlignment(Pos.TOP_LEFT);
        konferenceVBox.setPadding(new Insets(5));

        pane.add(konferenceVBox, 0, 1);

        VBox firmaBox = new VBox(10, firmaCheckBox, firmaNavnInput, firmaTelefonInput);
        firmaBox.setPadding(new Insets(10));
        firmaBox.setAlignment(Pos.TOP_LEFT);
        pane.add(firmaBox, 0, 2);

        firmaNavnInput.setDisable(!firmaCheckBox.isSelected());
        firmaTelefonInput.setDisable(!firmaCheckBox.isSelected());

        firmaCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            firmaNavnInput.setDisable(!newValue);
            firmaTelefonInput.setDisable(!newValue);

        });

        VBox foredragsholderBox = new VBox(10, ForedragsholderCheckBox);
        foredragsholderBox.setPadding(new Insets(5));
        pane.add(foredragsholderBox, 0, 3);

        VBox ledsagerBox = new VBox(10, ledsagerInput, udflugtListViewInput);
        pane.add(ledsagerBox, 2, 1);
        ledsagerBox.setPadding(new Insets(10));
        ledsagerBox.setAlignment(Pos.TOP_LEFT);

        udflugtListViewInput.getListView().setDisable(ledsagerInput.getInputValue().isEmpty());

        ledsagerInput.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            udflugtListViewInput.getListView().setDisable(newValue.isEmpty());
        });

        VBox hotelOptionsBox = new VBox(10,hotelComboBox, badCheckBox, wifiCheckBox, morgenmadCheckBox);
        pane.add(hotelOptionsBox, 2, 0);
        hotelOptionsBox.setPadding(new Insets(10,10,10,10));
        hotelOptionsBox.setAlignment(Pos.TOP_LEFT);
        hotelOptionsBox.setPrefWidth(200);

        udflugtListViewInput.getListView().setPrefHeight(100);
        udflugtListViewInput.getListView().setPrefWidth(200);
        udflugtListViewInput.getListView().setEditable(false);
        udflugtListViewInput.getListView().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        pane.add(totalOmkostningDisplay, 0, 7);

        HBox buttonsBox = new HBox(8);
        buttonsBox.setAlignment(Pos.CENTER);
        Button beregnButton = new Button("Beregn total pris");
        Button registrerButton = new Button("Tilmeld");
        buttonsBox.getChildren().addAll(beregnButton, registrerButton);

        // Button actions
        beregnButton.setOnAction(event -> beregnFuldeOmkostninger());
        registrerButton.setOnAction(event -> registrerDeltager());

        HBox buttonbox = new HBox(20, beregnButton, registrerButton);
        buttonbox.setAlignment(Pos.CENTER);
        pane.add(buttonbox, 0, 9, 2, 1);
    }

    private void initializeFields(Konference konference) {
        konferenceComboBox.getItems().addAll(Controller.getKonferencer());
        konferenceComboBox.setPromptText("Vælg konference");
        if (konference != null) {
            konferenceComboBox.setValue(konference);
        }

        hotelComboBox.getItems().addAll(Controller.getHoteller());
        hotelComboBox.setPromptText("Vælg Hotel");


        ObservableList<Udflugt> udflugter = FXCollections.observableArrayList(Controller.getUdflugter());
        udflugtListViewInput.getListView().setItems(udflugter);

        ankomstDatoInput.getDatePicker().setValue(LocalDate.now());
        afrejseDatoInput.getDatePicker().setValue(LocalDate.now().plusDays(1));

    }


    private void beregnFuldeOmkostninger() {
        if (!validerInput(konferenceComboBox, navnInput, telefonInput, ankomstDatoInput, afrejseDatoInput, adresseInput)) {
            return;
        }
        TilmeldingsogBeregningsMetode.beregnFuldeOmkostninger(konferenceComboBox.getValue(), navnInput, telefonInput, adresseInput,
                ankomstDatoInput, afrejseDatoInput, ledsagerInput, hotelComboBox, badCheckBox,
                wifiCheckBox, morgenmadCheckBox, udflugtListViewInput, totalOmkostningDisplay);

    }

    private void registrerDeltager() {
        if (!validerInput(konferenceComboBox, navnInput, telefonInput,
                ankomstDatoInput, afrejseDatoInput, adresseInput)) {
            return;
        }
        TilmeldingsogBeregningsMetode.registrerDeltager(konferenceComboBox.getValue(), navnInput, telefonInput, adresseInput,
                firmaNavnInput, firmaTelefonInput, firmaCheckBox, hotelComboBox,
                badCheckBox, wifiCheckBox, morgenmadCheckBox, ledsagerInput, udflugtListViewInput, totalOmkostningDisplay);

    }
}

