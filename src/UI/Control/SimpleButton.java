package UI.Control;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class SimpleButton extends BorderPane {
    private Label label;

    public SimpleButton(String textLabel, EventHandler<? super MouseEvent> value) {
        super();
        this.label = new Label(textLabel);
        setCenter(label);
        getStyleClass().add("orderTicketButton");
        setOnMouseClicked(value);
    }
}
