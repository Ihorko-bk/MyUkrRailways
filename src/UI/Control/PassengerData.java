package UI.Control;

import Entity.Place;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PassengerData extends VBox {

    private PlaceOrder placeOrder;

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

    private PassengerData(PlaceOrder placeOrder) {
        super();
        this.placeOrder = placeOrder;
        createLblPassengerNumber(placeOrder.getPassengerNumber());
        createVBMain();

        getStyleClass().add("passengerData");
    }

    private void createLblPassengerNumber(String passengerNumber) {
        lblPassengerNumber = new Label(passengerNumber);
        lblPassengerNumber.getStyleClass().add("passengerNumber");
        getChildren().add(lblPassengerNumber);
    }
    public void changePassengerNumber(int number) {
        lblPassengerNumber.setText("Passenger " + number);
    }

    private void createVBMain() {
        createHBFLNamesAndCancel();
        createHBRouteCarriagePlaceInfo();
        createHBBuyingDocTypeServicesAndCost();

    // відкрий ---------------------------------------------------------------------------------------<<<<<<<<<<<<<<
//        vbMain = new VBox(hbFLNamesAndCancel, hbRouteCarriagePlaceInfo, hbBuyingDocTypeServicesAndCost);
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
        btnCancel.setOnMouseClicked(event -> {
            System.out.println("Cancel click");
        });
    }

    private void createHBRouteCarriagePlaceInfo() {
        Place place = placeOrder.getPlaceButton().getPlace();
        lblRouteInfo = new Label(place.getCarriage().getRoute().getName());
        lblCarriageInfo = new Label(place.getCarriage().getNumber() + "");
        lblPlaceInfo = new Label(place.getNumber() + "");

        hbRouteCarriagePlaceInfo = new HBox(lblRouteInfo, lblRouteInfo, lblPlaceInfo);
        hbRouteCarriagePlaceInfo.getStyleClass().add("hbRouteCarriagePlaceInfo");
    }

    private void createHBBuyingDocTypeServicesAndCost() {
        createPaneBuyingAndDocType();
        createPaneServices();

//        hbBuyingDocTypeServicesAndCost = new HBox(pBuyingAndDocType, _);    --------------------------------------------------------
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


//        pServices = new Pane(_);                ---------------------------------------------------------------------------
        pServices.getStyleClass().add("pServices");
    }

    private void createVBServices() {
        lblServices = new Label("Services");


//        vbServices = new VBox(lblServices, _);      ------------------------------------------------------------------------
        vbServices.getStyleClass().add("vbServices");
    }


    public static ArrayList<PassengerData> createPassengerDataListFromPlaceOrderList(ArrayList<PlaceOrder> placeOrders) {
        ArrayList<PassengerData> passengerDataList = new ArrayList<>();
        for (int i = 0; i < placeOrders.size(); i++) {
            passengerDataList.add(new PassengerData(placeOrders.get(i)));
        }
        return passengerDataList;
    }
}
