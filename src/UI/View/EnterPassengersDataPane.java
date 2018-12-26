package UI.View;

import UI.Control.PassengerData;
import UI.Control.PlaceOrder;
import UI.Control.SimpleButton;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EnterPassengersDataPane extends VBox {


    private ArrayList<PassengerData> passengerDataList;


    private Label lblEnterPassengerData;
    private Label lblBeSureAndBlaBla;
    private ScrollPane spVBPassengersData;
        private VBox vbPassengersData;
    private BorderPane bpTotalCostAndBuyButton;
        private VBox vbTotalCostAndBuyButton;
            private BorderPane bpTotalCost;
                private BorderPane bpLblLabelTotalCost;
                    private Label lblLabelTotalCost;
                private Label lblTotalCost;
            private SimpleButton btnBuy;

    public EnterPassengersDataPane(ArrayList<PlaceOrder> placeOrders) {
        createLblEnterPassengerData();
        createLblBeSureAndBlaBla();
        passengerDataList = PassengerData.createPassengerDataListFromPlaceOrderList(placeOrders, this);
        createSPVBPassengersData();
        createBPTotalCostAndBuyButton();

        getStyleClass().add("enterPassengerDataPane");
        getChildren().addAll(lblEnterPassengerData, lblBeSureAndBlaBla, spVBPassengersData, bpTotalCostAndBuyButton);
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
    private void createSPVBPassengersData() {
        createVBPassengersData();

        spVBPassengersData = new ScrollPane(vbPassengersData);
        if (passengerDataList.size() > 1) {
            spVBPassengersData.setPrefSize(1014, 450);
        } else {
            spVBPassengersData.setFitToHeight(true);
        }
        spVBPassengersData.getStyleClass().add("spVBPassengersData");
    }
    private void createVBPassengersData() {
        vbPassengersData = new VBox();
        vbPassengersData.getChildren().addAll(passengerDataList);
        vbPassengersData.getStyleClass().add("vbPassengersData");
        vbPassengersData.setSpacing(20);
    }


    private void createBPTotalCostAndBuyButton() {
        createVBTotalCostAndBuyButton();

        bpTotalCostAndBuyButton = new BorderPane();
        bpTotalCostAndBuyButton.setRight(vbTotalCostAndBuyButton);
        bpTotalCostAndBuyButton.getStyleClass().add("bpTotalCostAndBuyButton");
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

        bpLblLabelTotalCost = new BorderPane();
        bpLblLabelTotalCost.getStyleClass().add("bpLblLabelTotalCost");
        bpLblLabelTotalCost.setBottom(lblLabelTotalCost);

        lblTotalCost = new Label();
        recountTotalCost();
        lblTotalCost.getStyleClass().add("lblTotalCost");

        bpTotalCost = new BorderPane();
        bpTotalCost.setLeft(bpLblLabelTotalCost);
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
