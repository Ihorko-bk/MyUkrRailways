package UI.Control;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class DatePicker extends VBox {
    private Label label;
    private javafx.scene.control.DatePicker pickerDate;
    private HBox hbHotDates;
        private HotDate hdToday, hdTomorrow, hdTheDayAfter;

    public DatePicker(String labelText, SearchButton searchButton) {
        super();
        createLabel(labelText);
        createDatePicker(searchButton);
        createHotDatesBox();
        getStyleClass().add("datePicker");
    }

    public LocalDate getDate() {
        return pickerDate.getValue();
    }

    private void createLabel(String labelText) {
        label = new Label(labelText);
        label.getStyleClass().add("simpleLabel");
        getChildren().add(label);
    }

    private void createDatePicker(SearchButton searchButton) {
        pickerDate = new javafx.scene.control.DatePicker(LocalDate.now());
        //тут воно мало одразу в кнопку кидати дату і назва була б
//        pickerDate.valueProperty().addListener(
//                (observable, oldValue, newValue) -> searchButton.setBtnText(newValue)
//        );
//        pickerDate.valueProperty().addListener(
//                observable -> System.out.println(pickerDate.getValue())
////                        searchButton.setBtnText(pickerDate.getValue())
//        );
        pickerDate.getStyleClass().add("picker");
        getChildren().add(pickerDate);
    }

    private void createHotDatesBox() {
        createHotDates();
        hbHotDates = new HBox(hdToday, hdTomorrow, hdTheDayAfter);
        hbHotDates.getStyleClass().add("hotList");
        getChildren().add(hbHotDates);
    }

    private void createHotDates() {
        hdToday = new HotDate("Today", LocalDate.now());
        hdTomorrow = new HotDate("Tomorrow", LocalDate.now().plusDays(1));
        hdTheDayAfter = new HotDate("TheDayAfter", LocalDate.now().plusDays(2));
    }

    private class HotDate extends Label {
        HotDate(String text, LocalDate date) {
            super(text);
            getStyleClass().add("hot");
            setOnMouseClicked(event -> pickerDate.setValue(date));
        }
    }
}
