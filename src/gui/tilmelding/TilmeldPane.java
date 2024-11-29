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
    private final AttributeDisplay fuldePrisFordeltagelse = new AttributeDisplay("Total pris", "0 DKK");
    private final AttributeDisplay prisForDeltager = new AttributeDisplay("Deltagers pris","0 DKK");



    public TilmeldPane(Konference konference) {
        this.setTitle("Tilmeld Deltager");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_CENTER);
        pane.setPadding(new Insets(10));
        pane.setVgap(2);
        pane.setHgap(2);

        Scene scene = new Scene(pane, 500, 660);
        this.setScene(scene);
        this.show();
        initializeFields(konference);

        Label konferenceLabel = new Label("vælg konference: ");
        VBox venstreVBox = new VBox(10, navnInput, telefonInput, adresseInput, konferenceLabel, konferenceComboBox, ankomstDatoInput, afrejseDatoInput,ForedragsholderCheckBox,firmaCheckBox,firmaNavnInput,firmaTelefonInput);
        venstreVBox.setPadding(new Insets(5));
        venstreVBox.setAlignment(Pos.TOP_LEFT);
        venstreVBox.setPrefHeight(200);
        venstreVBox.setPrefWidth(200);
        pane.add(venstreVBox, 0, 0);

        VBox højreVBox = new VBox(10, hotelComboBox, badCheckBox, wifiCheckBox, morgenmadCheckBox,ledsagerInput,udflugtListViewInput,fuldePrisFordeltagelse,prisForDeltager);
        pane.add(højreVBox, 1, 0);
        højreVBox.setAlignment(Pos.TOP_LEFT);
        højreVBox.setPadding(new Insets(5));
        højreVBox.setPrefHeight(400);
        højreVBox.setPrefWidth(200);

        HBox buttonsBox = new HBox(8);
        buttonsBox.setAlignment(Pos.CENTER);
        Button beregnButton = new Button("Beregn total pris");
        Button registrerButton = new Button("Tilmeld");
        buttonsBox.getChildren().addAll(beregnButton, registrerButton);
        pane.add(buttonsBox,0,2);

        udflugtListViewInput.getListView().setPrefHeight(100);
        udflugtListViewInput.getListView().setPrefWidth(200);
        udflugtListViewInput.getListView().setEditable(false);
        udflugtListViewInput.getListView().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Button actions
        beregnButton.setOnAction(event -> beregnFuldeOmkostninger());
        registrerButton.setOnAction(event -> registrerDeltager());
    }

    private void initializeFields(Konference konference) {
        konferenceComboBox.getItems().addAll(Controller.getKonferencer());
        konferenceComboBox.setPromptText("Vælg konference");

        if (konference != null) {
            konferenceComboBox.setValue(konference);
        }

        konferenceComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ankomstDatoInput.getDatePicker().setValue(newValue.getStartdato());
                afrejseDatoInput.getDatePicker().setValue(newValue.getSlutdato());
            }

        });

        hotelComboBox.getItems().addAll(Controller.getHoteller());
        hotelComboBox.setPromptText("Vælg Hotel");

        ObservableList<Udflugt> udflugter = FXCollections.observableArrayList(Controller.getUdflugter());
        udflugtListViewInput.getListView().setItems(udflugter);

        ankomstDatoInput.getDatePicker().setValue(LocalDate.now());
        afrejseDatoInput.getDatePicker().setValue(LocalDate.now().plusDays(1));

        //Aktiver deaktiver list baseret på ledsager input
        udflugtListViewInput.getListView().setDisable(ledsagerInput.getInputValue().isEmpty());
        ledsagerInput.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
            udflugtListViewInput.getListView().setDisable(newValue.isEmpty());
        });

        firmaNavnInput.setDisable(!firmaCheckBox.isSelected());
        firmaTelefonInput.setDisable(!firmaCheckBox.isSelected());

        firmaCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            firmaNavnInput.setDisable(!newValue);
            firmaTelefonInput.setDisable(!newValue);
        });
    }

    private void beregnFuldeOmkostninger() {
        if (!validerInput(konferenceComboBox, navnInput, telefonInput, ankomstDatoInput, afrejseDatoInput, adresseInput)) {
            return;
        }
        boolean isForedragsholder = ForedragsholderCheckBox.isSelected();
        TilmeldingsogBeregningsMetode.beregnFuldeOmkostninger(konferenceComboBox.getValue(), navnInput, telefonInput, adresseInput,
                ankomstDatoInput, afrejseDatoInput, ledsagerInput, hotelComboBox, badCheckBox,
                wifiCheckBox, morgenmadCheckBox, udflugtListViewInput, fuldePrisFordeltagelse, firmaCheckBox, isForedragsholder);
    }

    private void registrerDeltager() {
        if (!validerInput(konferenceComboBox, navnInput, telefonInput,
                ankomstDatoInput, afrejseDatoInput, adresseInput)) {
            return;
        }
        boolean isForedragsholder = ForedragsholderCheckBox.isSelected();
        TilmeldingsogBeregningsMetode.registrerDeltager(konferenceComboBox.getValue(), navnInput, telefonInput, adresseInput,
                firmaNavnInput, firmaTelefonInput, firmaCheckBox, hotelComboBox,
                badCheckBox, wifiCheckBox, morgenmadCheckBox, ledsagerInput, udflugtListViewInput, fuldePrisFordeltagelse, isForedragsholder);

        this.close();
    }
}