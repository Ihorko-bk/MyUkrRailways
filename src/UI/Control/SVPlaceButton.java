package UI.Control;

import Entity.Place;
import UI.View.PlaceOrdersPane;

public class SVPlaceButton extends PlaceButton {

    public SVPlaceButton(Place place, PlaceOrdersPane placeOrdersPane) {
        super(place, placeOrdersPane);
        separator.setLayoutY(50);
        setId("sv");
    }

    // тимчасовий
    public SVPlaceButton(int number, boolean free) {
        super(number, free);
        separator.setLayoutY(50);
        setId("sv");
    }
}
