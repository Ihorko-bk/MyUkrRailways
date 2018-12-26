package UI.Control;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.VBox;

public     class NameField extends VBox {
    Label lblName;
    TextField tfName;
    public NameField(String name) {
        this.lblName = new Label(name);
        tfName = new TextField();
        tfName.setTextFormatter(new TextFormatter<>(change -> (
                change.getControlNewText().matches(
                        "([A-z]{0,15})|([А-яЄ-ЯҐа-їґ]{0,15})"
                ) ? change : null
        )));
        getChildren().addAll(lblName, tfName);
        getStyleClass().add("nameField");
    }
    public String getName() {
        return tfName.getText().substring(0, 1).toUpperCase() + tfName.getText().substring(1).toLowerCase();
    }
}
