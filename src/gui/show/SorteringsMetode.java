package gui.show;

import application.model.Deltager;
import javafx.collections.ObservableList;

public class SorteringsMetode {

    public static void sorterNavne(ObservableList<Deltager> deltagere) {
        for (int i = 0; i < deltagere.size() - 1; i++) {
            for (int j = i + 1; j < deltagere.size(); j++) {
                if (deltagere.get(i).getNavn().compareTo(deltagere.get(j).getNavn()) > 0) {
                    Deltager temp = deltagere.get(i);
                    deltagere.set(i, deltagere.get(j));
                    deltagere.set(j, temp);
                }
            }
        }
    }
}