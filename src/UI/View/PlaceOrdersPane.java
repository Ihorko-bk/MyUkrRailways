package UI.View;

import UI.Control.PlaceButton;
import UI.Control.PlaceOrder;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PlaceOrdersPane extends VBox {

    private int passengerNumber = 0;
    private double totalCost = 0.0;

    private HBox placeOrdersList;
        private BorderPane routeNumberBox;
            private Label lblRouteNumber;
    private Label lblTotalCost;
    private OrderTicketsButton btnOrderTickets;

    public PlaceOrdersPane(String routeName) {
        super();
        createRouteNumberBox(routeName);
        createPlaceOrderList();
        createLblTotalCost();
        createOrderTicketsButton();

        getStyleClass().add("placeOrderPane");
        setVisible(false);
    }

    private void createRouteNumberBox(String routeName) {
        createLblRouteNumber(routeName);
        routeNumberBox = new BorderPane(lblRouteNumber);
        routeNumberBox.setId("routeNumberBox");
    }

    private void createLblRouteNumber(String routeName) {
        lblRouteNumber = new Label(routeName);
    }

    private void createPlaceOrderList() {
        placeOrdersList = new HBox(routeNumberBox);
        placeOrdersList.getStyleClass().add("placeOrderList");
    }

    public void addPlaceOrder(PlaceButton placeButton) {
        placeOrdersList.getChildren().add(new PlaceOrder(placeButton, passengerNumber++));
        if (!isVisible()) setVisible(true);
    }
    public void removePlaceOrder(PlaceOrder placeOrder) {
        placeOrdersList.getChildren().remove(placeOrder);
        passengerNumber--;
        checkPassengerNumbersInPlaceOrderList();
    }
    private void checkPassengerNumbersInPlaceOrderList() {
        if (passengerNumber == 0) return;
        for (int i = 1; i < placeOrdersList.getChildren().size(); i++) {
            ((PlaceOrder) placeOrdersList.getChildren().get(i)).setPassengerNumber(i);
        }
    }

    private void createLblTotalCost() {
        lblTotalCost = new Label();
        recountTotalCost();
        lblTotalCost.setId("totalCostLabel");
    }
    public void recountTotalCost() {
        lblTotalCost.setText("Total cost:   " + totalCost + " UAH");
    }

    private void createOrderTicketsButton() {
        btnOrderTickets = new OrderTicketsButton("Order tickets", event -> {

            // туть пишемо перехід на форму з заповленнями білетів особистими даними

        });
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
