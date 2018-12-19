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

public class EnterPassengerDataPane extends VBox {


    private BooleanProperty totalCostChanges;


    private Label lblEnterPassengerData;
    private Label lblBeSureAndBlaBla;
    private ArrayList<PassengerData> passengerDataList;

    private HBox hbTotalCostAndBuyButton;
        private VBox vbTotalCostAndBuyButton;
            private BorderPane bpTotalCost;
                private Label lblLabelTotalCost;
                private Label lblTotalCost;
            private SimpleButton btnBuy;

    public EnterPassengerDataPane(ArrayList<PlaceOrder> placeOrders) {



        totalCostChanges.addListener(event -> {});
    }



    private void createHBTotalCostAndBuyButton() {
        createVBTotalCostAndBuyButton();

        hbTotalCostAndBuyButton = new HBox(_);
        hbTotalCostAndBuyButton.getStyleClass().add("hbTotalCostAndBuyButton");
    }
    private void createVBTotalCostAndBuyButton() {
        createBPTotalCost();

        vbTotalCostAndBuyButton = new VBox(_);
        vbTotalCostAndBuyButton.getStyleClass().add("vbTotalCostAndBuyButton");
    }
    private void createBPTotalCost() {
        lblLabelTotalCost = new Label("Total cost: ");
        lblLabelTotalCost.getStyleClass().add("lblLabelTotalCost");

        lblTotalCost = new Label();

        bpTotalCost = new BorderPane();
        bpTotalCost.setLeft(lblLabelTotalCost);
        bpTotalCost.setRight();
        bpTotalCost.getStyleClass().add("bpTotalCost");
    }

}
