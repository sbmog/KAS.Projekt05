package gui.component;

import application.model.Konference;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class LabeledListViewInput extends VBox {
    private final ListView<String> listView = new ListView<>();

    public LabeledListViewInput (String labelText){
        this.setStyle("-fx-border-color: lightgrey; -fx-border-width: 1; -fx-background-color: aliceblue;");
        this.setPadding(new Insets(5));
        this.setMinWidth(200);
        Label label = new Label(labelText);
        label.setStyle("-fx-font-weight: bold;");
        this.getChildren().addAll(label,listView);
    }

    public ListView<String> getListView() {
        return listView;
    }
}