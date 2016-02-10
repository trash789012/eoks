package dialogs;

import javafx.scene.control.Alert;

/**
 * Created by Николай on 20.11.2015.
 */
public class information {
    String text;

    public information(String text, String context) {
        this.text = text;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(text);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
