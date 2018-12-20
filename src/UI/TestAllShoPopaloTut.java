package UI;

import Entity.Station;
import UI.View.SearchRoutesPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestAllShoPopaloTut extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

//        ComboBox<String> cbTest = new ComboBox<>();
//        cbTest.getItems().addAll("Hello", "Ihorko", "Elya", "Something");
//        cbTest.setPrefSize(446, 49);
//        cbTest.setMaxSize(446, 49);
//        StationField.HotStation hotStation = new StationField().new HotStation("HotStation");
//        cbTest.getStyleClass().add("hotStation");


//        StationField stationField = new StationField("From",
//                new ArrayList<Station>(){{
//                    add(new Station("Kyiv"));
//                    add(new Station("Lviv"));
//                    add(new Station("Kharkiv"));
//                    add(new Station("Odesa"));
//                    add(new Station("Dnipro Holovnyy"));
//                }});

//        DatePicker datePicker = new DatePicker("Departure date", null);


        SearchRoutesPane searchRoutesPane = new SearchRoutesPane(new ArrayList<Station>(){{
            add(new Station("Kyiv"));
            add(new Station("Lviv"));
            add(new Station("Kharkiv"));
            add(new Station("Odesa"));
            add(new Station("Dnipro Holovnyy"));
        }});
//        Pane pane = new Pane(searchRoutesPane);
//
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.valueProperty().addListener(observable -> System.out.println(datePicker.getValue()));
        Button btn = new Button("Next day");
        btn.setOnAction(event -> datePicker.setValue(datePicker.getValue().plusDays(1)));
        Button btn1 = new Button("tomorrow");
        btn1.setOnAction(event -> datePicker.setValue(LocalDate.now().plusDays(1)));
        Button btn2 = new Button("getDate");
        btn2.setOnAction(event -> System.out.println(datePicker.getValue()));
//        Pane pane = new VBox(datePicker, btn, btn1, btn2);

        ArrayList<Label> labels = new ArrayList<>();
        labels.add(new Label("First"));
        labels.add(new Label("Second"));
        labels.add(new Label("Third"));

        VBox pane = new VBox();
        pane.getChildren().addAll(labels);
        pane.setMinSize(700, 700);
        Scene scene = new Scene(pane, 1040, 400);
        scene.getStylesheets().add("css/uz.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        labels.get(0).setText("NotFirst!");


    }

}
