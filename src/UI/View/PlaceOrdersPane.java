package UI.View;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlaceOrdersPane extends VBox {

    private BorderPane routeNumberBox;
        private Label lblRouteNumber;

    private void createRouteNumberBox(String routeName) {
        createLblRouteNumber(routeName);
        routeNumberBox = new BorderPane(lblRouteNumber);
        routeNumberBox.setId("routeNumberBox");
    }
    private void createLblRouteNumber(String routeName) {
        lblRouteNumber = new Label(routeName);
    }

}

class OrderTicketsButton extends BorderPane {
    private Label label;

    public OrderTicketsButton(String textLabel, EventHandler<? super MouseEvent> value) {
        super();
        this.label = new Label(textLabel);
        setCenter(label);
        setId("orderTicketButton");
        setOnMouseClicked(value);
    }
}
