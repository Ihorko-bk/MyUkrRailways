package UI.Control;

import Entity.Place;
import UI.View.PlaceOrdersPane;
import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

public class PlaceButton extends Pane {

    private Place place;
    public Place getPlace() {
        return place;
    }

    private boolean free;
    private Label number;
    private PlaceOrdersPane placeOrdersPane;

    protected Separator separator;

    private boolean selected;
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // тимчасовий
    public PlaceButton(int number, boolean free) {
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

    // конструктор з place та формою, куди додавати / звідки видаляти замовлення
    public PlaceButton(Place place, PlaceOrdersPane placeOrdersPane) {
        super();
        this.place = place;
        this.placeOrdersPane = placeOrdersPane;
        this.free = place.isFree();
        pseudoClassStateChanged(PseudoClass.getPseudoClass("free"), free);
        if (free) createActions();
        createLabel(place.getNumber());
        createSeparator(place.getNumber());
        createTooltip(free);
        getStyleClass().add("place");
        getChildren().addAll(this.number, separator);
    }

    // тимчасовий
    public PlaceButton(Place place) {
        super();
        this.place = place;
        this.free = place.isFree();
        pseudoClassStateChanged(PseudoClass.getPseudoClass("free"), free);
        if (free) createActions();
        createLabel(place.getNumber());
        createSeparator(place.getNumber());
        createTooltip(free);
        getStyleClass().add("place");
        getChildren().addAll(this.number, separator);
    }

    private void createActions() {
        selected = false;
        setCursor(Cursor.HAND);
        onMouseClickedProperty().setValue(event -> {
            orderPlace(selected);
            selected = !selected;
            pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), selected);
        });
    }

    // отуть пишемо що буде як натиснеться на місце <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private void orderPlace(boolean selected) {

        // нехай викликає метод форми і віддається їй щоб можна було потім забрати виділення з неї
        // коли відміниться замовлення місця

        if (!selected) {
            // створи замовлення місця
            // adding PlaceOrder to PlaceOrdersPane

        } else {
            // видали замовлення місця
            // removing PlaceOrder from PlaceOrdersPane


        }


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
        Tooltip tooltip = new Tooltip("Place: " + number.getText() + (free ? " free" : " busy"));
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