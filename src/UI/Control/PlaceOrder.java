package UI.Control;

import UI.View.PlaceOrdersPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PlaceOrder extends VBox {

    private PlaceButton placeButton;
    public PlaceButton getPlaceButton() {
        return placeButton;
    }

    private Label passengerNumber;
    private Pane placeOrderBox;
    private Label lblCarriage, lblbPlace;
    private ImageView closeButton;

    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber.setText("Passenger " + passengerNumber);
    }
    public String getPassengerNumber() {
        return passengerNumber.getText();
    }

    public PlaceOrder(PlaceButton placeButton, int passengerNuber) {
        super();
        this.placeButton = placeButton;

        createLblPassengerNumber(passengerNuber);
        createPlaceOrderBox(placeButton.getPlace().getCarriage().getNumber(),
                placeButton.getPlace().getNumber());
    }

    private void createLblPassengerNumber(int passengerNuber) {
        this.passengerNumber = new Label("Passenger " + passengerNuber);
        this.passengerNumber.getStyleClass().add("passengerNumber");
        getChildren().add(this.passengerNumber);
    }

    private void createPlaceOrderBox(int carriageNumber, int placeNumber) {
        placeOrderBox = new Pane();
        placeOrderBox.getStyleClass().add("placeOrderBox");

        createLblCarriage(carriageNumber);
        createLblPlace(placeNumber);
        createCloseButton();

        placeOrderBox.getChildren().addAll(lblCarriage, lblbPlace, closeButton);
        getChildren().add(placeOrderBox);
    }

    private void createLblCarriage(int carriageNumber) {
        lblCarriage = new Label("Coach " + carriageNumber);
        lblCarriage.setLayoutX(10);
        lblCarriage.setLayoutY(12);

    }

    private void createLblPlace(int placeNumber) {
        lblbPlace = new Label("Seat "+ placeNumber);
        lblbPlace.setLayoutX(10);
        lblbPlace.setLayoutY(28);
    }

    private void createCloseButton() {
        closeButton = new ImageView(new Image("images/close.png"));
        closeButton.getStyleClass().add("placeOrderCloseButton");
        closeButton.setLayoutX(90);
        closeButton.setLayoutY(8);
        closeButton.setOnMouseClicked(event -> {
            placeButton.setSelected(false);
            ((PlaceOrdersPane) getParent().getParent()).removePlaceOrder(this);
        });
    }
}
