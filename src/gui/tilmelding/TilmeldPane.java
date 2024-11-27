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
    import javafx.geometry.VPos;
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
        private final LabeledTextInput firmaNavnInput = new LabeledTextInput("Firma");
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

            Scene scene = new Scene(pane, 500, 800);
            this.setScene(scene);
            this.show();
            initializeFields(konference);

            VBox personligBox = new VBox(10,navnInput,telefonInput,adresseInput);
            personligBox.setPadding(new Insets(10));
            personligBox.setAlignment(Pos.TOP_LEFT);
            pane.add(personligBox, 0, 0);

            Label konferenceLabel = new Label("vælg konference: ");
            VBox konferenceVBox = new VBox(5,konferenceLabel,konferenceComboBox,ankomstDatoInput,afrejseDatoInput);
            konferenceVBox.setAlignment(Pos.TOP_LEFT);
            konferenceVBox.setPadding(new Insets(5));

            pane.add(konferenceVBox,0, 1);

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

            VBox foredragsholderBox = new VBox(5, ForedragsholderCheckBox);
            foredragsholderBox.setPadding(new Insets(5));
            pane.add(foredragsholderBox,0,3);

            VBox ledsagerBox = new VBox(10, ledsagerInput,udflugtListViewInput);
            pane.add(ledsagerBox, 2, 2);
            ledsagerBox.setPadding(new Insets(10));
            ledsagerBox.setAlignment(Pos.TOP_LEFT);

            udflugtListViewInput.getListView().setDisable(ledsagerInput.getInputValue().isEmpty());

            ledsagerInput.getTextField().textProperty().addListener((observable, oldValue, newValue) -> {
                udflugtListViewInput.getListView().setDisable(newValue.isEmpty());
            });

            pane.add(hotelComboBox,2,0);


            VBox hotelOptionsBox = new VBox(5, badCheckBox, wifiCheckBox, morgenmadCheckBox);
            pane.add(hotelOptionsBox, 2, 1);
            hotelOptionsBox.setPadding(new Insets(5));
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

            HBox buttonbox = new HBox(20,beregnButton,registrerButton);
            buttonbox.setAlignment(Pos.CENTER);
            pane.add(buttonbox, 0, 9, 2, 1);
        }


        private void initializeFields(Konference konference) {
            konferenceComboBox.getItems().addAll(Controller.getKonferencer());

            if (konference != null) {
                konferenceComboBox.setValue(konference);
            }

            hotelComboBox.getItems().addAll(Controller.getHoteller());
            ObservableList<Udflugt> udflugter = FXCollections.observableArrayList(Controller.getUdflugter());
            udflugtListViewInput.getListView().setItems(udflugter);

            ankomstDatoInput.getDatePicker().setValue(LocalDate.now());
            afrejseDatoInput.getDatePicker().setValue(LocalDate.now().plusDays(1));

            }



        private void beregnFuldeOmkostninger() {
            if (!validerInput(konferenceComboBox, navnInput, telefonInput, ankomstDatoInput, afrejseDatoInput,adresseInput)) {
                return;
            }
            try {
                //TempDeltager for at kunne beregne prisen uden at registerer deltageren
                Deltager midlertidligDeltager = new Deltager(navnInput.getInputValue(),adresseInput.getInputValue(),telefonInput.getInputValue());
                Konference selectedKonference = konferenceComboBox.getValue();
                Tilmelding tempTilmelding = new Tilmelding(midlertidligDeltager,ankomstDatoInput.getInputValue(),afrejseDatoInput.getInputValue(), ForedragsholderCheckBox.isSelected(), selectedKonference);

                //Tilføjer valgte udfluger for den midlertidling tilmelding.
                ObservableList<Udflugt> selectedUdflugter = udflugtListViewInput.getListView().getSelectionModel().getSelectedItems();
                for (Udflugt udflugt : selectedUdflugter) {
                    tempTilmelding.addUdflugt(udflugt);
                }

                if (!ledsagerInput.getInputValue().isEmpty()) {
                    Ledsager tempLedsager = Controller.createLedsager(ledsagerInput.getInputValue(), midlertidligDeltager);
                    tempTilmelding.setLedsager(tempLedsager);
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
                totalOmkostningDisplay.setValue("Total pris: " + totalOmkostning + " DKK");

            } catch (Exception ex) {
                ValideringsMetode.showAlert(Alert.AlertType.ERROR, "Fejl", "Kunne ikke beregne omkostninger: " + ex.getMessage());
            }
        }


        private void registrerDeltager() {
            if (!validerInput(konferenceComboBox, navnInput, telefonInput,
                    ankomstDatoInput, afrejseDatoInput,adresseInput)) {
                return;
            }
            try {
                Deltager deltager = Controller.createDeltager(navnInput.getInputValue(),adresseInput.getInputValue(),telefonInput.getInputValue());
                Konference selectedKonference = konferenceComboBox.getValue();

                nuværendeTilmelding = Controller.createTilmelding(deltager, ankomstDatoInput.getInputValue(), afrejseDatoInput.getInputValue(), ForedragsholderCheckBox.isSelected(), selectedKonference);

                ObservableList<Udflugt> selectedUdflugter = udflugtListViewInput.getListView().getSelectionModel().getSelectedItems();
                for (Udflugt udflugt : selectedUdflugter) {
                    nuværendeTilmelding.addUdflugt(udflugt);
                }

                if (!ledsagerInput.getInputValue().isEmpty()) {
                    Ledsager ledsager = Controller.createLedsager(ledsagerInput.getInputValue(), deltager);
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