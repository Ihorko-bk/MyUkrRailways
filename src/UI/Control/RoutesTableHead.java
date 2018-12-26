package UI.Control;

import UI.View.RoutesTable;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class RoutesTableHead extends HBox {

    private RoutesTable routesTable;
    private ToggleGroup tgSort;

    private BorderPane bpTrainNumber;   // "route name"
        private RoutesTableHeadColumnLabel lblTrainNumber;
    private BorderPane bpFromTo;
        private Label lblFromTo;
    private BorderPane bpDate;
        private Label lblDate;
    private BorderPane bpDepartureArrival;
        private RoutesTableHeadColumnLabel lblDeparture;
        private RoutesTableHeadColumnLabel lblArrival;
    private BorderPane bpDuration;
        private RoutesTableHeadColumnLabel lblDuration;
    private BorderPane bpSeatsAvailable;
        private Label lblSeatsAvailable;

    public RoutesTableHead(RoutesTable routesTable) {
        super();
        this.routesTable = routesTable;

        tgSort = new ToggleGroup();
        createLabelsAndBPs();

        getChildren().addAll(bpTrainNumber, bpFromTo, bpDate, bpDepartureArrival, bpDuration, bpSeatsAvailable);
        getStyleClass().add("routesTableHead");
    }

    private void createLabelsAndBPs() {
        lblTrainNumber = createRoutesTableHeadColumnLabel("Train number",
                event -> routesTable.sortByTrainNumber(lblTrainNumber.sortBy()));
        bpTrainNumber = createBorderPane("bpTrainNumber", lblTrainNumber);

        lblFromTo = createSimpleLabel("From / To");
        bpFromTo = createBorderPane("bpFromTo", lblFromTo);

        lblDate = createSimpleLabel("Date");
        bpDate = createBorderPane("bpDate", lblDate);

        lblDeparture = createRoutesTableHeadColumnLabel("Departure",
                event -> routesTable.sortByDeparture(lblDeparture.sortBy()));
        lblArrival = createRoutesTableHeadColumnLabel("Arrival",
                event -> routesTable.sortByArrival(lblArrival.sortBy()));
        bpDepartureArrival = createBorderPane("bpDepartureArrival", lblDeparture, lblArrival);

        lblDuration = createRoutesTableHeadColumnLabel("Duration",
                event -> routesTable.sortByDuration(lblDuration.sortBy()));
        bpDuration = createBorderPane("bpDuration", lblDuration);

        lblSeatsAvailable = createSimpleLabel("Seats available");
        bpSeatsAvailable = createBorderPane("bpSeatsAvailable", lblSeatsAvailable);
    }

    private Label createSimpleLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().add("routesTableHeadColumnSimpleLabel");
        return label;
    }
    private RoutesTableHeadColumnLabel createRoutesTableHeadColumnLabel(String text,
                                                                        EventHandler<? super MouseEvent> event) {
        RoutesTableHeadColumnLabel label = new RoutesTableHeadColumnLabel(text, tgSort);
        label.setOnMouseClicked(event);
        return label;
    }
    private BorderPane createBorderPane(String style, Label...labels) {
        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().addAll("routesTableHeadBP", style);
        if (labels.length == 1) {
            borderPane.setLeft(labels[0]);
            return borderPane;
        } else {
            VBox vBox = new VBox(labels);
            vBox.getStyleClass().add("routesTableHeadBPVB");
            borderPane.setLeft(vBox);
            return borderPane;
        }
    }
}
