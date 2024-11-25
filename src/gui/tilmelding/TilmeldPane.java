package gui.tilmelding;

import application.controller.Controller;
import application.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;

public class TilmeldPane extends Stage {
    private TextField telefonTextField = new TextField();
    private TextField navnTextField = new TextField();
    private DatePicker ankomstDatoValg = new DatePicker();
    private DatePicker afrejseDatoValg = new DatePicker();
    private CheckBox talerCheckBox = new CheckBox();
    private TextField ledsagerTextField = new TextField();
    private ListView<Udflugt> udflugtListView = new ListView<>();
    private Label totalOmkostningLabel;
    private ComboBox<Konference> konferenceComboBox = new ComboBox<>();



    public TilmeldPane(Konference konference) {
        this.setTitle("Tilmeld Deltager");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20));
        pane.setVgap(10);
        pane.setHgap(10);

        Scene scene = new Scene(pane, 500, 400);
        this.setScene(scene);
        this.show();

        initializeFields();

        Label konferenceLabel = new Label("Vælg konference");
        pane.add(konferenceLabel, 0, 0);
        pane.add(konferenceComboBox, 1, 0);
        Label navnLabel = new Label("Navn:");
        pane.add(navnLabel, 0, 1);
        pane.add(navnTextField, 1, 1);

        Label telefonLabel = new Label("Telefonnummer:");
        pane.add(telefonLabel, 0, 2);
        pane.add(telefonTextField, 1, 2);

        Label ankomstDato = new Label("Ankomstdato");
        pane.add(ankomstDato, 0, 3);
        pane.add(ankomstDatoValg, 1, 3);

        Label afrejseLabel = new Label("Afrejsedato:");
        pane.add(afrejseLabel, 0, 4);
        pane.add(afrejseDatoValg, 1, 4);

        Label ledsagerLabel = new Label("Ledsager");
        pane.add(talerCheckBox, 1, 5);
        pane.add(ledsagerLabel, 0, 6);
        pane.add(ledsagerTextField, 1, 6);

        Label udflugtLabel = new Label("Vælg udflugt");
        pane.add(udflugtLabel, 0, 7);
        pane.add(udflugtListView, 1, 7);

        totalOmkostningLabel = new Label("Total pris: 0 DKK");
        pane.add(totalOmkostningLabel, 0, 8);
        Button beregnButton = new Button("Beregn total pris");
        pane.add(beregnButton, 1, 8);
        beregnButton.setOnAction(event -> beregnFuldeOmkostninger());

        Button registrerButton = new Button("Tilmeld");
        pane.add(registrerButton, 0, 9);
        registrerButton.setOnAction(event -> registrerDeltager());
    }

    private void initializeFields() {
        navnTextField.setPromptText("Indtast deltagers navn");

        telefonTextField.setPromptText("Indtast telefonnummer");

        ankomstDatoValg.setValue(LocalDate.now());

        afrejseDatoValg.setValue(LocalDate.now().plusDays(1));

        talerCheckBox = new CheckBox("Er du foredragsholder?");

        ledsagerTextField.setPromptText("Indtast ledsagers navn (valgfrit)");

        udflugtListView.getItems().addAll(Controller.getUdflugter());
        udflugtListView.setMinWidth(300);

        konferenceComboBox = new ComboBox<>();
        konferenceComboBox.getItems().addAll(Controller.getKonferencer());
        konferenceComboBox.setPromptText("Vælg en konference");

        konferenceComboBox.getItems().addAll(Controller.getKonferencer());
        konferenceComboBox.setPromptText("Vælg en konference");

    }

    private boolean validerInput() {
        if (konferenceComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Du skal vælge en konference!");
            return false;
        }
        if (navnTextField.getText().isEmpty() || telefonTextField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Navn og telefonnummer er påkrævet.");
            return false;
        }
        if (ankomstDatoValg.getValue() == null || afrejseDatoValg.getValue() == null) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Vælg både ankomst- og afrejsedato.");
            return false;
        }
        if (afrejseDatoValg.getValue().isBefore(ankomstDatoValg.getValue())) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Afrejsedato skal være efter ankomstdato.");
            return false;
        }
        return true;
    }

    private void beregnFuldeOmkostninger() {
        if (!validerInput())
            return;

        try {
            Konference selectedKonference = konferenceComboBox.getValue();
            LocalDate ankomstDato = ankomstDatoValg.getValue();
            LocalDate afrejseDato = afrejseDatoValg.getValue();
            boolean erTaler = talerCheckBox.isSelected();
            String ledsagerNavn = ledsagerTextField.getText();
            Udflugt valgtUdflugt = udflugtListView.getSelectionModel().getSelectedItem();

            Deltager deltager = new Deltager(navnTextField.getText(), null, telefonTextField.getText());
            Tilmelding tilmelding = Controller.createTilmelding(deltager, ankomstDato, afrejseDato, erTaler, selectedKonference);

            // Håndter ledsager
            if (!ledsagerNavn.isEmpty()) {
                Ledsager ledsager = Controller.createLedsager(ledsagerNavn, deltager);
                tilmelding.setLedsager(ledsager);

                if (valgtUdflugt != null) {
                    tilmelding.addUdflugt(valgtUdflugt);
                }
            }

            int totalOmkostning = Controller.getSamletPrisForDeltagelse(tilmelding);
            totalOmkostningLabel.setText("Total pris: " + totalOmkostning + " DKK");
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR, "Fejl", "Kunne ikke beregne omkostninger: " + ex.getMessage());
        }
    }


    private void registrerDeltager() {
        if (!validerInput()) return;

        try {
            Deltager deltager = Controller.createDeltager(navnTextField.getText(),null, telefonTextField.getText());
            LocalDate ankomstDato = ankomstDatoValg.getValue();
            LocalDate afrejseDato = afrejseDatoValg.getValue();
            boolean erTaler = talerCheckBox.isSelected();
            Konference konference = konferenceComboBox.getValue();

            Tilmelding tilmelding = Controller.createTilmelding(deltager, ankomstDato, afrejseDato, erTaler, konference);

            String ledsagerNavn = ledsagerTextField.getText();
            if (!ledsagerNavn.isEmpty()) {
                Ledsager ledsager = Controller.createLedsager(ledsagerNavn, deltager);
                tilmelding.setLedsager(ledsager);

                Udflugt valgtUdflugt = udflugtListView.getSelectionModel().getSelectedItem();
                if (valgtUdflugt != null) {
                    tilmelding.addUdflugt(valgtUdflugt);
                }
            }

            showAlert(Alert.AlertType.INFORMATION,"Succes", "Deltageren er nu tilmeldt konferencen.");
            this.close();
        } catch (Exception ex) {
            showAlert(Alert.AlertType.ERROR,"Fejl", "Der opstod en fejl: " + ex.getMessage());
        }
    }


    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

