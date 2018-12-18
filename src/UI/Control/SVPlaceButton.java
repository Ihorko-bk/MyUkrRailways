package UI.Control;

import Entity.Place;
import UI.View.PlaceOrdersPane;

public class SVPlaceButton extends PlaceButton {

    public SVPlaceButton(Place place, PlaceOrdersPane placeOrdersPane) {
        super(place, placeOrdersPane);
        setId("sv");
        separator.setLayoutY(50);
    }

    // тимчасовий
    public SVPlaceButton(int number, boolean free) {
        super(number, free);
        setId("sv");
        separator.setLayoutY(50);
    }
}
