package UI.View;

import Entity.Station;
import UI.Control.DatePicker;
import UI.Control.SearchButton;
import UI.Control.StationField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchRoutesView extends VBox {

    private VBox vbSearchParameters;
        private HBox hbStationsSelection;
            private StationField sfFrom, sfTo;
            private Pane paneWithImage;
                private ImageView imgSwapArrow;
        private HBox hbDateTimePickers;
            private DatePicker datePicker;
    //        private TimePicker timePicker;
    private SearchButton searchButton;


    public SearchRoutesView(ArrayList<Station> stations) {
        super();
        createSearchParametersBox(stations);
        createSearchButton();

    }

    private void createStationsSelectionBox(ArrayList<Station> stations) {
        createStationFields(stations);
        createImageSwapArrow();
        hbStationsSelection = new HBox(sfFrom, imgSwapArrow, sfTo);
        hbStationsSelection.getStyleClass().add("stationsSelections");
    }

    private void createStationFields(ArrayList<Station> stations) {
        sfFrom = new StationField("From", stations);
        sfTo = new StationField("To", stations);
    }

    private void createImageSwapArrow() {
        imgSwapArrow = new ImageView(new Image("images/swapArrow.png"));
        imgSwapArrow.getStyleClass().add("swapArrow");
    }

    private void createSearchParametersBox(ArrayList<Station> stations) {
        createStationsSelectionBox(stations);
        createDateTimePickersBox();
        vbSearchParameters = new VBox(hbStationsSelection, hbDateTimePickers);
        vbSearchParameters.getStyleClass().add("searchParametersBox");
        getChildren().add(vbSearchParameters);
    }

    private void createDateTimePickersBox() {
        createDatePicker();
//        createTimePicker();
        hbDateTimePickers = new HBox(datePicker);
        hbDateTimePickers.getStyleClass().add("dateTimePickerBox");

    }

    private void createDatePicker() {
        datePicker = new DatePicker("Departure date", searchButton);
    }

    private void createSearchButton() {
//        searchButton = new SearchButton(
//                LocalDate.now(),
//                event -> {
//                    // пиши тут шо робити кнопці!!
//                }
//        );
        searchButton = new SearchButton(
                "Search for trains",
                event -> {
                    // пиши тут шо робити кнопці!!
                    System.out.println("From " + sfFrom.getSelectedStation().getName() +
                            " to " + sfTo.getSelectedStation().getName() +
                            "\n when: " + datePicker.getDate());
                }
        );
        getChildren().add(searchButton);
    }
}
