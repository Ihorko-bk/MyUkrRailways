package UI.View;

import UI.Control.PassengerData;
import UI.Control.PlaceOrder;
import UI.Control.SimpleButton;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EnterPassengersDataPane extends VBox {


    private ArrayList<PassengerData> passengerDataList;


    private Label lblEnterPassengerData;
    private Label lblBeSureAndBlaBla;
    private VBox vbPassengersData;
    private HBox hbTotalCostAndBuyButton;
        private VBox vbTotalCostAndBuyButton;
            private BorderPane bpTotalCost;
                private Label lblLabelTotalCost;
                private Label lblTotalCost;
            private SimpleButton btnBuy;

    public EnterPassengersDataPane(ArrayList<PlaceOrder> placeOrders) {
        createLblEnterPassengerData();
        createLblBeSureAndBlaBla();
        passengerDataList = PassengerData.createPassengerDataListFromPlaceOrderList(placeOrders, this);
        createVBPassengersData();
        createHBTotalCostAndBuyButton();

        getStyleClass().add("enterPassengerDataPane");
        getChildren().addAll(lblEnterPassengerData, lblBeSureAndBlaBla, vbPassengersData, hbTotalCostAndBuyButton);
    }

    private void createLblEnterPassengerData() {
        lblEnterPassengerData = new Label("Enter passenger data");
        lblEnterPassengerData.getStyleClass().add("lblEnterPassengerData");
    }

    private void createLblBeSureAndBlaBla() {
        lblBeSureAndBlaBla = new Label(
        "Be sure to enter the last name and first name of the passenger who will be travelling\n" +
                "First name and last name CANNOT be changed after payment of travel documents"
        );
        lblBeSureAndBlaBla.getStyleClass().add("lblBeSureAndBlaBla");
    }

    private void createVBPassengersData() {
        vbPassengersData = new VBox();
        vbPassengersData.getChildren().addAll(passengerDataList);
        vbPassengersData.getStyleClass().add("vbPassengersData");
    }


    private void createHBTotalCostAndBuyButton() {
        createVBTotalCostAndBuyButton();

        hbTotalCostAndBuyButton = new HBox(vbTotalCostAndBuyButton);
        hbTotalCostAndBuyButton.getStyleClass().add("hbTotalCostAndBuyButton");
    }
    private void createVBTotalCostAndBuyButton() {
        createBPTotalCost();
        createBtnBuy();

        vbTotalCostAndBuyButton = new VBox(bpTotalCost, btnBuy);
        vbTotalCostAndBuyButton.getStyleClass().add("vbTotalCostAndBuyButton");
    }
    private void createBPTotalCost() {
        lblLabelTotalCost = new Label("Total cost: ");
        lblLabelTotalCost.getStyleClass().add("lblLabelTotalCost");

        lblTotalCost = new Label();
        recountTotalCost();
        lblTotalCost.getStyleClass().add("lblTotalCost");

        bpTotalCost = new BorderPane();
        bpTotalCost.setLeft(lblLabelTotalCost);
        bpTotalCost.setRight(lblTotalCost);
        bpTotalCost.getStyleClass().add("bpTotalCost");
    }
    private void createBtnBuy() {
        btnBuy = new SimpleButton("Buy", event -> {

        });
        btnBuy.getStyleClass().add("btnBuy");
    }


    private void setTotalCost(double value) {
        lblTotalCost.setText(String.format("%2.2f UAH", value));
    }

    public void recountTotalCost() {
        setTotalCost(PassengerData.countTotalTicketCost(passengerDataList));
    }

    public void cancelOrder(PassengerData passengerData) {
        passengerDataList.remove(passengerData);
        vbPassengersData.getChildren().remove(passengerData);
        recountPassengersNumbers();
        recountTotalCost();
    }

    private void recountPassengersNumbers() {
        for (int i = 0; i < passengerDataList.size(); i++) {
            passengerDataList.get(i).setPassengerNumber(i+1);
        }
    }


}
