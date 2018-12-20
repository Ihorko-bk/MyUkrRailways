package UI.Control;

import Entity.Place;
import UI.View.EnterPassengersDataPane;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PassengerData extends VBox {

    private PlaceOrder placeOrder;
    private EnterPassengersDataPane enterPassengersDataPane;
    private double ticketCost;
    public double getTicketCost() {
        return ticketCost;
    }

    private double bedLinenCost = 30;
    private double beverageCost = 8;

    private Label lblPassengerNumber;
    private VBox vbMain;
        private HBox hbFLNamesAndCancel;
            private NameField nfLastName, nfFirstName;
            private Label btnCancel;
        private HBox hbRouteCarriagePlaceInfo;
            Label lblRouteInfo, lblCarriageInfo, lblPlaceInfo;
        private HBox hbBuyingDocTypeServicesAndCost;
            private Pane pBuyingAndDocType;
                private RadioButton rbBuy;
                private VBox vbDocType;
                    private Label lblDocType;
                    private RadioButton rbFull, rbChild, rbStudents;
                    private ToggleGroup tgDocType;
            private Pane pServices;
                private VBox vbServices;
                    private Label lblServices;
                    private CheckBox chbBedLinen, chb2Beverage, chb1Beverage;
            private BorderPane bpTicketCost;
                private Label lblTicketCost;


    private PassengerData(PlaceOrder placeOrder, EnterPassengersDataPane enterPassengersDataPane) {
        super();
        this.placeOrder = placeOrder;
        this.enterPassengersDataPane = enterPassengersDataPane;

        this.ticketCost = 0;
        // а мало б бути ось так:
//        ticketCost = placeOrder.getPlaceButton().getPlace().getCost();

        createLblPassengerNumber(placeOrder.getPassengerNumber());
        createVBMain();

        getChildren().addAll(lblPassengerNumber, vbMain);
        getStyleClass().add("passengerData");
    }

    private void createLblPassengerNumber(String passengerNumber) {
        lblPassengerNumber = new Label(passengerNumber);
        lblPassengerNumber.getStyleClass().add("passengerNumber");
    }
    public void setPassengerNumber(int number) {
        lblPassengerNumber.setText("Passenger " + number);
    }

    private void createVBMain() {
        createHBFLNamesAndCancel();
        createHBRouteCarriagePlaceInfo();
        createHBBuyingDocTypeServicesAndCost();

        vbMain = new VBox(hbFLNamesAndCancel, hbRouteCarriagePlaceInfo, hbBuyingDocTypeServicesAndCost);
        vbMain.getStyleClass().add("main");
    }
    private void createHBFLNamesAndCancel() {
        createNameFields();
        createCancelButton();
        hbFLNamesAndCancel = new HBox(nfLastName, nfFirstName, btnCancel);
    }
    private void createNameFields() {
        nfLastName = new NameField("Last name");
        nfFirstName = new NameField("First name");
    }
    private void createCancelButton() {
        btnCancel = new Label("Cancel");
        btnCancel.getStyleClass().add("btnCancel");
        // допиши сюди спливаюче вікно з підтвердженням скасування
        btnCancel.setOnMouseClicked(event -> enterPassengersDataPane.cancelOrder(this));
    }

    private void createHBRouteCarriagePlaceInfo() {
        Place place = placeOrder.getPlaceButton().getPlace();
        lblRouteInfo = new Label("Train " + place.getCarriage().getRoute().getName());
        lblCarriageInfo = new Label("Coach " + place.getCarriage().getNumber());
        lblPlaceInfo = new Label(String.format("Place %2d", place.getNumber()));

        hbRouteCarriagePlaceInfo = new HBox(lblRouteInfo, lblCarriageInfo, lblPlaceInfo);
        hbRouteCarriagePlaceInfo.getStyleClass().add("hbRouteCarriagePlaceInfo");
    }

    private void createHBBuyingDocTypeServicesAndCost() {
        createPaneBuyingAndDocType();
        createPaneServices();
        createBPTicketCost();

        hbBuyingDocTypeServicesAndCost = new HBox(pBuyingAndDocType, pServices, bpTicketCost);
        hbBuyingDocTypeServicesAndCost.getStyleClass().add("hbBuyingDocTypeServicesAndCost");
    }

    private void createPaneBuyingAndDocType() {
        createRBBuy();
        createVBDocType();

        pBuyingAndDocType = new Pane(rbBuy, vbDocType);
        pBuyingAndDocType.getStyleClass().add("pBuyingAndDocType");
    }
    private void createRBBuy() {
        rbBuy = new RadioButton("Buy");
        rbBuy.setSelected(true);
        rbBuy.setLayoutX(14);
        rbBuy.setLayoutY(15);
    }
    private void createVBDocType() {
        lblDocType = new Label("Document type");
        createRBsDocType();

        vbDocType = new VBox(lblDocType, rbFull, rbChild, rbStudents);
        vbDocType.getStyleClass().add("vbDocType");
        vbDocType.setLayoutX(171);
        vbDocType.setLayoutY(15);
    }
    private void createRBsDocType() {
        tgDocType = new ToggleGroup();

        rbFull = createDocTypeRadioButton("Full");
        rbFull.setSelected(true);
        rbChild = createDocTypeRadioButton("Child");
        rbChild.setDisable(true);
        rbStudents = createDocTypeRadioButton("Student's");
        rbStudents.setDisable(true);
    }
    private RadioButton createDocTypeRadioButton(String rbLabelText) {
        RadioButton rb = new RadioButton(rbLabelText);
        rb.setToggleGroup(tgDocType);
        return rb;
    }

    private void createPaneServices() {
        createVBServices();

        pServices = new Pane(vbServices);
        pServices.getStyleClass().add("pServices");
    }
    private void createVBServices() {
        lblServices = new Label("Services");
        createChBsServices();

        vbServices = new VBox(lblServices, chbBedLinen, chb2Beverage, chb1Beverage);
        vbServices.getStyleClass().add("vbServices");
    }
    private void createChBsServices() {
        chbBedLinen = createCheckBoxServices("Bed linen", bedLinenCost);
        chb2Beverage = createCheckBoxServices("2 Beverage", 2*beverageCost);
        chb1Beverage = createCheckBoxServices("1 Beverage", beverageCost);
    }
    private CheckBox createCheckBoxServices(String name, double costValue) {
        CheckBox cb = new CheckBox(name);
        cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) addToTicketCost(costValue);
            else addToTicketCost(-costValue);
        });
        return cb;
    }

    private void createBPTicketCost() {
        lblTicketCost = new Label();
        setTicketCost(ticketCost);

        bpTicketCost = new BorderPane();
        bpTicketCost.setTop(lblTicketCost);
        bpTicketCost.getStyleClass().add("bpTicketCost");
    }
    private void setTicketCost(double cost) {
        lblTicketCost.setText(String.format("%2.2f UAH", cost));
    }
    private void addToTicketCost(double value) {
        ticketCost += value;
        setTicketCost(ticketCost);
        enterPassengersDataPane.recountTotalCost();
    }





    // "утилітні" методи:

    // створення списку PassengerData'ів зі списку PlaceOrder'ів
    public static ArrayList<PassengerData> createPassengerDataListFromPlaceOrderList(
            ArrayList<PlaceOrder> placeOrders, EnterPassengersDataPane enterPassengersDataPane) {
        ArrayList<PassengerData> passengerDataList = new ArrayList<>();
        for (int i = 0; i < placeOrders.size(); i++) {
            passengerDataList.add(new PassengerData(placeOrders.get(i), enterPassengersDataPane));
        }
        return passengerDataList;
    }
    // підрахунок загальної вартості білетів
    public static double countTotalTicketCost(ArrayList<PassengerData> passengerDatas) {
        double totalTicketsCost = 0;
        for (PassengerData pd:passengerDatas) {
            totalTicketsCost += pd.getTicketCost();
        }
        return totalTicketsCost;
    }
}
