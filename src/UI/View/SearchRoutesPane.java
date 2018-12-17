package UI.View;

import Entity.Station;
import UI.Control.DatePicker;
import UI.Control.SearchButton;
import UI.Control.StationField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SearchRoutesPane extends BorderPane {
    private VBox vbSearchRoutesBox;
        private VBox vbSearchParameters;
            private HBox hbStationsSelection;
                private StationField sfFrom, sfTo;
                private HBox paneWithImage;
                    private ImageView imgSwapArrow;
            private HBox hbDateTimePickers;
                private DatePicker datePicker;
        //        private TimePicker timePicker;
        private BorderPane bpWithButton;
            private SearchButton searchButton;


    public SearchRoutesPane(ArrayList<Station> stations) {
        super();
        createSearchParametersBox(stations);
        createSearchButton();
        createMainBox();
    }

    private void createStationsSelectionBox(ArrayList<Station> stations) {
        createStationFields(stations);
        createImageSwapArrow();
        hbStationsSelection = new HBox(sfFrom, paneWithImage, sfTo);
        hbStationsSelection.getStyleClass().add("stationsSelections");
    }

    private void createStationFields(ArrayList<Station> stations) {
        sfFrom = new StationField("From", stations);
        sfTo = new StationField("To", stations);
    }

    private void createImageSwapArrow() {
        imgSwapArrow = new ImageView(new Image("images/swapArrow.png"));
        imgSwapArrow.getStyleClass().add("swapArrow");
        imgSwapArrow.setOnMouseClicked(event -> swapStationsFields());

        paneWithImage = new HBox(imgSwapArrow);
        paneWithImage.getStyleClass().add("swapArrowPane");
    }

    private void swapStationsFields() {
        Station station = sfFrom.getSelectedStation();
        sfFrom.setSelectedStation(sfTo.getSelectedStation());
        sfTo.setSelectedStation(station);
    }

    private void createSearchParametersBox(ArrayList<Station> stations) {
        createStationsSelectionBox(stations);
        createDateTimePickersBox();
        vbSearchParameters = new VBox(hbStationsSelection, hbDateTimePickers);
        vbSearchParameters.getStyleClass().add("searchParametersBox");
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

        searchButton = new SearchButton(
                "Search for trains",
                event -> {
                    // пиши тут шо робити кнопці!!

                    // тіпа тест ↓ ↓ ↓
                    System.out.println("From " + sfFrom.getSelectedStation().getName() +
                            " to " + sfTo.getSelectedStation().getName() +
                            "\n when: " + datePicker.getDate());
                    // тіпа тест ↑ ↑ ↑
                }
        );
        bpWithButton = new BorderPane(searchButton);
        bpWithButton.getStyleClass().add("paneWithButton");
    }

    private void createMainBox() {
        vbSearchRoutesBox = new VBox(vbSearchParameters, bpWithButton);
        getStyleClass().add("searchRoutesBox");
        setCenter(vbSearchRoutesBox);

    }
}
