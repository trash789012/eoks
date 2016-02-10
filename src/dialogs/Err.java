package dialogs;

import javafx.scene.control.Alert;

/**
 * Created by Николай on 23.11.2015.
 */
public class Err {
    String s, context;

    public Err(String context, String s) {
        this.context = context;
        this.s = s;
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(s);
        alert.setContentText(context);

        alert.showAndWait();
    }
}
