package UI.Control;

import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

public class PlaceButton extends Pane {

    private boolean free;
    private Label number;
    private Separator separator;

    private boolean selected;

    public PlaceButton(boolean free, int number) {
        super();
        this.free = free;
        pseudoClassStateChanged(PseudoClass.getPseudoClass("free"), free);
        if (free) createActions();
        createLabel(number);
        createSeparator(number);
        createTooltip(free);
        getStyleClass().add("place");
        getChildren().addAll(this.number, separator);

    }

    private void createActions() {
        selected = false;
        setCursor(Cursor.HAND);
        onMouseClickedProperty().setValue(event -> {
            selected = !selected;
            pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), selected);
        });
    }

    private void createLabel(int number) {
        this.number = new Label(number+"");
        this.number.setLayoutX(0);
        this.number.setLayoutY(0);
    }

    private void createSeparator(int number) {
        separator = new Separator(Orientation.HORIZONTAL);
        separator.setLayoutX(4);
        separator.setLayoutY(((double)number)%2==0 ? 5 : 20);
    }

    private void createTooltip(boolean free) {
        Tooltip tooltip = new Tooltip("PlaceButton: " + number + (free ? " free" : " busy"));
        Tooltip.install(this, tooltip);
    }

    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }
    public int getNumber() {
        return Integer.valueOf(number.getText());
    }
}