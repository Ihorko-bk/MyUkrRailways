package UI.View;

import UI.Control.PassengerData;
import UI.Control.PlaceOrder;
import UI.Control.SimpleButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class EnterPassengerDataPane extends VBox {

    private Label lblEnterPassengerData;
    private Label lblBeSureAndBlaBla;
    private ArrayList<PassengerData> passengerDataList;
    private Label lblTotalCost;
    private SimpleButton btnBuy;

    public EnterPassengerDataPane(ArrayList<PlaceOrder> placeOrders) {

    }
}
