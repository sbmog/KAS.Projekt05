package gui.tilmelding;

import application.controller.Controller;
import application.model.*;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import storage.Storage;

import java.time.LocalDate;

import static gui.tilmelding.ValideringsMetode.validerInput;

public class Øveklasse extends Stage {
    private TextField telefonTextField = new TextField();
    private TextField navnTextField = new TextField();
    private DatePicker ankomstDatoValg = new DatePicker();
    private DatePicker afrejseDatoValg = new DatePicker();
    private CheckBox udflugtCheckBox = new CheckBox();
    private TextField ledsagerTextField = new TextField();
    private ListView<Udflugt> udflugtListView = new ListView<>();
    private Label totalOmkostningLabel;
    private ComboBox<Konference> konferenceComboBox = new ComboBox<>();
    private Tilmelding nuværendeTilmelding;
    private TextField deltagersAdresseTextField = new TextField();
    private CheckBox firmaCheckBox = new CheckBox();
    private ComboBox<Hotel> hotelComboBox = new ComboBox<>();
    private CheckBox badCheckBox = new CheckBox();
    private CheckBox wifiCheckBox = new CheckBox();
    private CheckBox morgenmadCheckBox = new CheckBox();
    private TextField navnFirmaTextField = new TextField();
    private TextField telefonFirmaTextField = new TextField();


    public Øveklasse(Konference konference) {
        this.setTitle("Tilmeld Deltager");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

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

        Scene scene = new Scene(pane, 700, 600);
        this.setScene(scene);
        this.show();
        initializeFields(konference);

        Label konferenceLabel = new Label("Vælg konference");
        pane.add(konferenceLabel, 0, 0);
        pane.add(konferenceComboBox, 1, 0);

        Label navnLabel = new Label("Navn:");
        pane.add(navnLabel, 0, 1);
        pane.add(navnTextField, 1, 1);

        Label adresseLabel = new Label("Adresse:");
        pane.add(adresseLabel, 0, 2);
        pane.add(deltagersAdresseTextField, 1, 2);

        Label telefonLabel = new Label("Telefonnummer:");
        pane.add(telefonLabel, 0, 3);
        pane.add(telefonTextField, 1, 3);

        Label ankomstDato = new Label("Ankomstdato");
        pane.add(ankomstDato, 0, 4);
        pane.add(ankomstDatoValg, 1, 4);

        Label afrejseLabel = new Label("Afrejsedato:");
        pane.add(afrejseLabel, 0, 5);
        pane.add(afrejseDatoValg, 1, 5);

        Label firmaLabel = new Label("Er du tilknyttet firma?");
        pane.add(firmaLabel, 0, 6);
        pane.add(firmaCheckBox, 1, 6);

        Label firmaNavnLabel = new Label("Firma");
        pane.add(firmaNavnLabel, 0, 7);
        pane.add(navnFirmaTextField, 1, 7);

        Label firmaTelefonLabel = new Label("Telefonnummer");
        pane.add(firmaTelefonLabel, 0, 8);
        pane.add(telefonFirmaTextField, 1, 8);

        Label hotelLabel = new Label("Vælg hotel");
        pane.add(hotelLabel, 0, 9);
        pane.add(hotelComboBox, 1, 9);

        VBox hotelMulighederVbox = new VBox(5);
        hotelMulighederVbox.setAlignment(Pos.TOP_LEFT);
        hotelMulighederVbox.setPadding(new Insets(5,0,5,0));
        hotelMulighederVbox.getChildren().addAll(new Label("Valgfrie tilvalg"), badCheckBox, wifiCheckBox, morgenmadCheckBox);
        hotelMulighederVbox.setMaxWidth(300);
        pane.add(hotelMulighederVbox,2,7); //Fiks det

        Label ledsagerLabel = new Label("Ledsager");
        pane.add(udflugtCheckBox, 1, 10);
        pane.add(ledsagerLabel, 0, 10);
        pane.add(ledsagerTextField, 1, 10);


        Label udflugtLabel = new Label("Vælg udflugt");
        pane.add(udflugtLabel, 0, 11);
        pane.add(udflugtListView, 1, 11);

        totalOmkostningLabel = new Label("Total pris: 0 DKK");
        pane.add(totalOmkostningLabel, 0, 12);
        Button beregnButton = new Button("Beregn total pris");
        pane.add(beregnButton, 1, 12);
        beregnButton.setOnAction(event -> beregnFuldeOmkostninger());

        Button registrerButton = new Button("Tilmeld");
        pane.add(registrerButton, 0, 13);
        registrerButton.setOnAction(event -> registrerDeltager());
    }

    private void initializeFields(Konference konference) {
        navnTextField.setPromptText("Indtast deltagers navn");
        telefonTextField.setPromptText("Indtast telefonnummer");
        ankomstDatoValg.setValue(LocalDate.now());
        afrejseDatoValg.setValue(LocalDate.now().plusDays(1));
        udflugtCheckBox = new CheckBox("Er du foredragsholder?");
        ledsagerTextField.setPromptText("Indtast ledsagers navn (valgfrit)");
        deltagersAdresseTextField.setPromptText("Indtast adresse");
        navnFirmaTextField.setPromptText("Indtast firma navn");
        telefonFirmaTextField.setPromptText("Indtast telefonnummer");

        firmaCheckBox = new CheckBox();
        hotelComboBox = new ComboBox<>();
        hotelComboBox.getItems().addAll(Controller.getHoteller());
        hotelComboBox.setPromptText("Vælg et hotel");

        badCheckBox = new CheckBox("Ønsker du bad?");
        wifiCheckBox = new CheckBox("Ønsker du wifi?");
        morgenmadCheckBox = new CheckBox("Ønsker du morgenmad?");

        udflugtListView.getItems().addAll(Controller.getUdflugter());
        udflugtListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        udflugtListView.setMinWidth(300);


        konferenceComboBox = new ComboBox<>();
        konferenceComboBox.getItems().addAll(Controller.getKonferencer());
        konferenceComboBox.setPromptText("Vælg en konference");

        if (konference != null) {
            konferenceComboBox.setValue(konference);
        } else {
            konferenceComboBox.setPromptText("Vælg en konference");
        }
    }

    private void beregnFuldeOmkostninger() {


        if (!ValideringsMetode.validerInput(konferenceComboBox, navnTextField, telefonTextField, ankomstDatoValg, afrejseDatoValg,deltagersAdresseTextField)) {
            return;
        }
        try {
            //TempDeltager for at kunne beregne prisen uden at registerer deltageren
            Deltager midlertidligDeltager = new Deltager(navnTextField.getText(),deltagersAdresseTextField.getText(),telefonTextField.getText());
            Konference selectedKonference = konferenceComboBox.getValue();
            Tilmelding tempTilmelding = new Tilmelding(midlertidligDeltager,ankomstDatoValg.getValue(),afrejseDatoValg.getValue(), udflugtCheckBox.isSelected(), selectedKonference);

            //Tilføjer valgte udfluger for den midlertidling tilmelding.
            ObservableList<Udflugt> selectedUdflugter = udflugtListView.getSelectionModel().getSelectedItems();
            for (Udflugt udflugt : selectedUdflugter) {
                tempTilmelding.addUdflugt(udflugt);
            }

            if (!ledsagerTextField.getText().isEmpty()) {
                Ledsager tempLedsager = Controller.createLedsager(ledsagerTextField.getText(), midlertidligDeltager);
                tempTilmelding.setLedsager(tempLedsager);
            }

            if (firmaCheckBox.isSelected()) {
                tempTilmelding.setFirma(new Firma("222222","leasy"));
            }
            Hotel selectedHotel = hotelComboBox.getValue();
            if (selectedHotel != null) {
                boolean badValgt = badCheckBox.isSelected();
                boolean wifiValgt = wifiCheckBox.isSelected();
                boolean morgenmadValgt = morgenmadCheckBox.isSelected();

                tempTilmelding.setHotel(selectedHotel, badValgt, wifiValgt, morgenmadValgt);
            }

            //Beregner den fulde pris.
            int totalOmkostning = tempTilmelding.getPrisDeltagersUdgift();
            totalOmkostningLabel.setText("Total pris: " + totalOmkostning + " DKK");

        } catch (Exception ex) {
            ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Kunne ikke beregne omkostninger: " + ex.getMessage());
        }
    }


    private void registrerDeltager() {
        Konference selectedKonference = konferenceComboBox.getValue();
        for (Deltager deltager : Storage.getDeltagere()) {
            if (telefonTextField.getText().equalsIgnoreCase(deltager.getTelefonNummer())){
                nuværendeTilmelding = Controller.createTilmelding(deltager, ankomstDatoValg.getValue(), afrejseDatoValg.getValue(), udflugtCheckBox.isSelected(), selectedKonference);

            }else if (!validerInput(konferenceComboBox, navnTextField, telefonTextField,
                    ankomstDatoValg, afrejseDatoValg,deltagersAdresseTextField)) {
                return;
            }
            try {
                Deltager nyDeltager = Controller.createDeltager(navnTextField.getText(),deltagersAdresseTextField.getText(),telefonTextField.getText());

                nuværendeTilmelding = Controller.createTilmelding(nyDeltager, ankomstDatoValg.getValue(), afrejseDatoValg.getValue(), udflugtCheckBox.isSelected(), selectedKonference);

                ObservableList<Udflugt> selectedUdflugter = udflugtListView.getSelectionModel().getSelectedItems();
                for (Udflugt udflugt : selectedUdflugter) {
                    nuværendeTilmelding.addUdflugt(udflugt);
                }

                if (!ledsagerTextField.getText().isEmpty()) {
                    Ledsager ledsager = Controller.createLedsager(ledsagerTextField.getText(), nyDeltager);
                    nuværendeTilmelding.setLedsager(ledsager);
                }
                if (firmaCheckBox.isSelected()) {
                    Firma firma = Controller.createFirma("88888888","leasy");
                    nuværendeTilmelding.setFirma(firma);
                }

                Hotel selectedHotel = hotelComboBox.getValue();
                if (selectedHotel != null) {
                    boolean badValgt = badCheckBox.isSelected();
                    boolean wifiValgt = wifiCheckBox.isSelected();
                    boolean morgenmadValgt = morgenmadCheckBox.isSelected();

                    nuværendeTilmelding.setHotel(selectedHotel, badValgt, wifiValgt, morgenmadValgt);
                }

                int totalOmkostningForDeltager = nuværendeTilmelding.getPrisDeltagersUdgift();

                ValideringsMetode.showAlert(Alert.AlertType.CONFIRMATION,"Succes", "Deltageren er nu tilmeldt konferencen. Total pris: " + totalOmkostningForDeltager + " DKK");
                this.close();
            } catch (Exception ex) {
                ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Der opstod en fejl: " + ex.getMessage());
            }
        }


    }
}