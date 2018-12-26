package UI.Control;

import Entity.CarriageType;
import Entity.Route;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoutesTableRow extends HBox {

    Route route;

    private BorderPane bpTrainNumber;
        private Label lblTrainNumber;
    private BorderPane bpFromTo;
            private Label lblFrom;
            private Label lblTo;
    private BorderPane bpDate;
            private Label lblDepartureDate;
            private Label lblArrivalDate;
    private BorderPane bpDepartureArrivalTime;
            private Label lblDepartureTime;
            private Label lblArrivalTime;
    private BorderPane bpDuration;
        private Label lblDuration;
    private VBox vbSeatsAvailable;
        // туть щось ще..
        private RoutesTableSeatCell svSeats;
        private RoutesTableSeatCell comparatmentSeats;
        private RoutesTableSeatCell couchetteSeats;


    public RoutesTableRow(Route route) {
        super();
        this.route = route;

        createTrainNumber();
        createFromTo();
        createDate();
        createDepartureArrivalTime();
        createDuration();
        createSeatsAvailable();

        getChildren().addAll(bpTrainNumber, bpFromTo, bpDate, bpDepartureArrivalTime, bpDuration, _);
        getStyleClass().add("routesTableRow");
    }

    private void createTrainNumber() {
        lblTrainNumber = createLabel(route.getName(), "lblTrainNumber");
        bpTrainNumber = createBorderPane("bpTrainNumber", lblTrainNumber);
    }
    private void createFromTo() {
        lblFrom = createLabel(route.getFirstRouteStation().toString(), "lblFrom");
        lblTo = createLabel(route.getLastRouteStation().toString(), "lblTo");
        bpFromTo = createBorderPane("bpFromTo", lblFrom, lblTo);
    }
                                 // ----------------------------------------------   туть дописати!
    private void createDate() {
        lblDepartureDate = createLabel(
                "Departure ",       // туть вписати дату відправлення
                "lblDepartureDate");
        lblArrivalDate = createLabel(
                "Arrival  ",       // туть вписати дату прибуття
                "lblArrivalDate");
        bpDate = createBorderPane("bpDate", lblDepartureDate, lblArrivalDate);
    }
    private void createDepartureArrivalTime() {
        lblDepartureTime = createLabel(
                route.getFirstRouteStation().getArrivalTime().format(DateTimeFormatter.ofPattern("hh:mm aa")),
                "lblDepartureTime");
        lblArrivalTime = createLabel(
                route.getLastRouteStation().getDepartureTime().format(DateTimeFormatter.ofPattern("hh:mm aa")),
                "lblArrivalTime");
        bpDepartureArrivalTime = createBorderPane("bpDepartureArrivalTime", lblDepartureTime, lblArrivalTime);
    }
                                // ----------------------------------------------   туть дописати!
    private void createDuration() {
        lblDuration = createLabel(
                "666",      // туть потрібно підтягнути тривалість поїздки з маршруту (getRouteDuration())
                "lblDuration");
        bpDuration = createBorderPane("bpDuration", lblDuration);
    }
    private void createSeatsAvailable() {
        vbSeatsAvailable = new VBox();
        vbSeatsAvailable.getStyleClass().add("vbSeatsAvailable");

        int svFreePlaces = route.getAnountFreePlacesByCarriagesType(CarriageType.SV);
        int comparatmentFreePlaces = route.getAnountFreePlacesByCarriagesType(CarriageType.COMPARATMENT);
        int couchetteFreePlaces = route.getAnountFreePlacesByCarriagesType(CarriageType.COUCHETTE);

        if (svFreePlaces != 0) {

        }
        if (comparatmentFreePlaces != 0) {

        }
        if (couchetteFreePlaces != 0) {

        }


    }

    private Label createLabel(String text, String style) {
        Label label = new Label(text);
        label.getStyleClass().add(style);
        return label;
    }

    private BorderPane createBorderPane(String style, Label...labels) {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().addAll("routesTableCell", style);
        if (labels.length > 1) {
            VBox vBox = new VBox(labels);
            borderPane.setCenter(vBox);
        } else borderPane.setCenter(labels[0]);
        return borderPane;
    }


    public static ArrayList<RoutesTableRow> createRoutesTableRowArrayList(ArrayList<Route> routes) {
        ArrayList<RoutesTableRow> routesTableRows = new ArrayList<>();
        for (int i = 0; i < routes.size(); i++) {
            routesTableRows.add(new RoutesTableRow(routes.get(i)));
        }
        return routesTableRows;
    }

}
