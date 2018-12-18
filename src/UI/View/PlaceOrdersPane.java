package UI.View;

import UI.Control.PlaceButton;
import UI.Control.PlaceOrder;
import UI.Control.SimpleButton;
import javafx.scene.control.Label;
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
    private SimpleButton btnOrderTickets;

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

    private void createLblRouteNumber(String routeNumber) {
        lblRouteNumber = new Label(routeNumber);
    }

    private void createPlaceOrderList() {
        placeOrdersList = new HBox(routeNumberBox);
        placeOrdersList.getStyleClass().add("placeOrderList");
        getChildren().add(placeOrdersList);
    }

    public boolean addPlaceOrder(PlaceButton placeButton) {
        if (placeOrdersList.getChildren().size() != 8) {
            placeOrdersList.getChildren().add(new PlaceOrder(placeButton, ++passengerNumber));
            if (!isVisible()) setVisible(true);
            return true;
        }

        // описати тут вискакуюче вікно з помилкою типу "ніззя набирати стільки квитків на раз!"

        return false;
    }
    public void removePlaceOrder(PlaceButton placeButton) {
        for (int i = 1; i < placeOrdersList.getChildren().size(); i++) {
            if (((PlaceOrder) placeOrdersList.getChildren().get(i)).getPlaceButton() == placeButton) {
                placeOrdersList.getChildren().remove(i);
                passengerNumber--;
                checkPassengerNumbersInPlaceOrderList();
                return;
            }
        }
    }
    public void removePlaceOrder(PlaceOrder placeOrder) {
        placeOrdersList.getChildren().remove(placeOrder);
        passengerNumber--;
        checkPassengerNumbersInPlaceOrderList();
    }
    public boolean addRemoveOrder(PlaceButton placeButton, boolean addRemove) {
        if (addRemove) {
            return addPlaceOrder(placeButton);
        } else {
            removePlaceOrder(placeButton);
            return true;
        }
    }
    private void checkPassengerNumbersInPlaceOrderList() {
        if (passengerNumber == 0) {
            setVisible(false);
            return;
        }
        for (int i = 1; i < placeOrdersList.getChildren().size(); i++) {
            ((PlaceOrder) placeOrdersList.getChildren().get(i)).setPassengerNumber(i);
        }
    }

    private void createLblTotalCost() {
        lblTotalCost = new Label();
        recountTotalCost();
        lblTotalCost.setId("totalCostLabel");
        getChildren().add(lblTotalCost);
    }
    public void recountTotalCost() {
        lblTotalCost.setText("Total cost:   " + totalCost + " UAH");
    }

    private void createOrderTicketsButton() {
        btnOrderTickets = new SimpleButton("Order tickets", event -> {

            // туть пишемо перехід на форму з заповленнями білетів особистими даними

        });
        getChildren().add(btnOrderTickets);
    }
}


