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
    private ComboBox<Konference> konferenceComboBox;

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

        initializeFields(konference);

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
    }

    private void initializeFields(Konference konference) {
        navnTextField = new TextField();
        navnTextField.setPromptText("Indtast deltagers navn");

        telefonTextField = new TextField();
        telefonTextField.setPromptText("Indtast telefonnummer");

        ankomstDatoValg = new DatePicker();
        ankomstDatoValg.setValue(LocalDate.now());

        afrejseDatoValg = new DatePicker();
        afrejseDatoValg.setValue(LocalDate.now().plusDays(1));

        talerCheckBox = new CheckBox("Er du foredragsholder?");

        ledsagerTextField = new TextField();
        ledsagerTextField.setPromptText("Indtast ledsagers navn (valgfrit)");

        udflugtListView = new ListView<>();
        udflugtListView.getItems().addAll(Controller.getUdflugter());
        udflugtListView.setMinWidth(300);

        konferenceComboBox = new ComboBox<>();
        konferenceComboBox.getItems().addAll(Controller.getKonferencer());
        if (konference != null) {
            konferenceComboBox.setValue(konference);
        }else {
            konferenceComboBox.setPromptText("Vælg en konference");
        }
    }

    private void beregnFuldeOmkostninger() {
        Konference selectedKonference = konferenceComboBox.getValue();
        if (selectedKonference == null) {
            showAlert("Fejl", "Du skal vælge en konference først!");
            return;
        }


        String navn = navnTextField.getText();
        String telefon = telefonTextField.getText();
        LocalDate ankomstDato = ankomstDatoValg.getValue();
        LocalDate afrejseDato = afrejseDatoValg.getValue();
        boolean erTaler = talerCheckBox.isSelected();
        String ledsagerNavn = ledsagerTextField.getText();
        Udflugt valgtUdflugt = udflugtListView.getSelectionModel().getSelectedItem();


        Deltager deltager = new Deltager(navn, null, null);//Hvad gør vi med adresse? De satans parametre
        deltager.setTelefonNummer(telefon);

        Tilmelding tilmelding = selectedKonference.createTilmelding(deltager, ankomstDato, afrejseDato, erTaler);


        //Håndter ledsager
        if (!ledsagerNavn.isEmpty()) {
            Ledsager ledsager = new Ledsager(ledsagerNavn, deltager);
            deltager.setLedsager(ledsager);
        }

        //Håndter udflugt
        if (valgtUdflugt != null) {
            Ledsager ledsager = deltager.getLedsager();
            if (ledsager != null) {
                tilmelding.addUdflugt(valgtUdflugt);
            }
        }

        int totalOmkostning = Controller.getSamletPrisForDeltagelse(tilmelding);
        totalOmkostningLabel.setText("Total pris: " + totalOmkostning + " DKK");

    }

    private void registrerDeltager() {
        Konference ValgteKonference = konferenceComboBox.getValue();
        if (ValgteKonference == null) {
            showAlert("Fejl", "Du skal vælge en konference først!");
            return;
        }


        String navn = navnTextField.getText();
        String telefon = telefonTextField.getText();
        LocalDate ankomstDato = ankomstDatoValg.getValue();
        LocalDate afrejseDato = afrejseDatoValg.getValue();
        boolean erTaler = talerCheckBox.isSelected();
        String ledsagerNavn = ledsagerTextField.getText();
        Udflugt valgtUdflugt = udflugtListView.getSelectionModel().getSelectedItem();

        //opret deltager
        Deltager deltager = new Deltager(navn, null, telefon); //Konstruktor bøvl igen
        deltager.setTelefonNummer(telefon);

        //Opret tilmelding
        Tilmelding tilmelding = ValgteKonference.createTilmelding(deltager, ankomstDato, afrejseDato, erTaler);
        Controller.createTilmelding(deltager, ankomstDato, afrejseDato, erTaler, ValgteKonference);

        if (!ledsagerNavn.isEmpty()) {
            Ledsager ledsager = new Ledsager(ledsagerNavn, deltager);
            Controller.createLedsager(ledsagerNavn, deltager);
        }

        if (valgtUdflugt != null) {
            Ledsager ledsager = deltager.getLedsager();
            if (ledsager != null) {
                tilmelding.addUdflugt(valgtUdflugt);
            }
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tilmelding");
        alert.setHeaderText("Deltager er tilmeldt!");
        alert.setContentText("Deltageren er nu tilmeldt konferencen.");
        alert.showAndWait();
        this.close();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}