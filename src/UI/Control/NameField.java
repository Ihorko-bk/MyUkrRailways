package UI.Control;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public     class NameField extends VBox {
    Label lblName;
    TextField tfName;
    public NameField(String name) {
        this.lblName = new Label(name);
        tfName = new TextField();
        getChildren().addAll(lblName, tfName);
        getStyleClass().add("nameField");
    }
    public String getName() {
        return tfName.getText();
    }
}
