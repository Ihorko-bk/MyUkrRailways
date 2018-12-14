package UI.Control;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchButton extends BorderPane {
    Label btnText;
//    public SearchButton(LocalDate date, EventHandler<? super MouseEvent> onClickEvent) {
//        this.btnText = new Label();
//        setBtnText(date);
//        setCenter(btnText);
//        getStyleClass().add("searchButton");
//        setOnMouseClicked(onClickEvent);
//    }
    public SearchButton(String text, EventHandler<? super MouseEvent> onClickEvent) {
        this.btnText = new Label(text);
        setCenter(btnText);
        getStyleClass().add("searchButton");
        setOnMouseClicked(onClickEvent);
    }
    public void setBtnText(String text) {
        btnText.setText(text);
    }
    public void setBtnText(LocalDate date) {
        btnText.setText("Search for trains " + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
