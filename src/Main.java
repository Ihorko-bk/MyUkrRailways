import Entity.Carriage;
import Entity.CarriageType;
import Entity.Place;
import UI.Control.CarriageFloor;
import UI.Control.CarriageTab;
import UI.Control.PlaceButton;
import UI.View.CarriagesPane;
import UI.View.PlaceOrdersPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        ArrayList<Place> placesForCarriage1 = new ArrayList<>();
        ArrayList<Place> placesForCarriage2 = new ArrayList<>();
        ArrayList<Place> placesForCarriage3 = new ArrayList<>();
        Random rand = new Random(8462846L);
        Carriage carriage1 = new Carriage(null, 1, CarriageType.COUCHETTE);
        Carriage carriage2 = new Carriage(null, 2, CarriageType.COUCHETTE);
        Carriage carriage3 = new Carriage(null, 3, CarriageType.COUCHETTE);
        for (int i = 1; i <=54; i++) {
            placesForCarriage1.add(new Place(i, rand.nextBoolean(), carriage1));
            placesForCarriage2.add(new Place(i, rand.nextBoolean(), carriage2));
            placesForCarriage3.add(new Place(i, rand.nextBoolean(), carriage3));
        }
        carriage1.setPlaces(placesForCarriage1);
        carriage2.setPlaces(placesForCarriage2);
        carriage3.setPlaces(placesForCarriage3);

        Carriage carriageSV = new Carriage(null, 4, CarriageType.SV);
        ArrayList<Place> placesForCarriageSV = new ArrayList<>();
        for (int i = 0; i < CarriageType.SV.getCapasity(); i++) {
            placesForCarriageSV.add(new Place(i, rand.nextBoolean(), carriageSV));
        }
        carriageSV.setPlaces(placesForCarriageSV);





        HBox carriageList = new HBox();
        carriageList.getStyleClass().add("carriageList");

        Label lblcarriages = new Label("Carriages:");
        lblcarriages.setPadding(new Insets(0, 10, 0, 0));


        carriageList.getChildren().add(lblcarriages);

        ToggleGroup toggleGroup = new ToggleGroup();
        CarriageTab carriageTab1 = new CarriageTab(1, 69);
        carriageTab1.setToggleGroup(toggleGroup);
        carriageTab1.setSelected(true);
        CarriageTab carriageTab2 = new CarriageTab(2, 11);
        carriageTab2.setToggleGroup(toggleGroup);
        CarriageTab carriageTab3 = new CarriageTab(3, 5);
        carriageTab3.setToggleGroup(toggleGroup);

        carriageList.getChildren().addAll(carriageTab1, carriageTab2, carriageTab3);


        CarriageType carriageType = CarriageType.COUCHETTE;

        VBox carriageScheme = new VBox();
        carriageScheme.getStyleClass().add("carriageScheme");

//        Label lblCarriageType = new Label("Тип вагону: Плацкарт");
        Label lblCarriageType =
                new Label("Type of carriage: "
                        + carriageType.name().substring(0, 1) + carriageType.name().substring(1).toLowerCase());
        lblCarriageType.getStyleClass().add("carriageType");

        Pane carriageFloor = new Pane();
        carriageFloor.getStyleClass().add("carriageFloor");
        carriageFloor.setId(carriageType.name().toLowerCase());

        // ------------------------------------------------------------------------------перегородки
        // -------------------------------------------------------------------------ліві

        ArrayList<Separator> leftSeparators = new ArrayList<>();
        Separator firstLeftSeparator = new Separator(Orientation.VERTICAL);
        int leftSeparatorsX = 33;
        firstLeftSeparator.setId("short");
        firstLeftSeparator.setLayoutX(leftSeparatorsX);
        firstLeftSeparator.setLayoutY(0);
        leftSeparatorsX += 40;
        leftSeparators.add(firstLeftSeparator);
        for (int i = 0; i < 10; i++, leftSeparatorsX+=80) {
            Separator anotherOneSeparator = new Separator(Orientation.VERTICAL);
            anotherOneSeparator.setLayoutX(leftSeparatorsX);
            anotherOneSeparator.setLayoutY(0);
            anotherOneSeparator.setId("long");
            leftSeparators.add(anotherOneSeparator);
        }
        Separator lastLeftSeparator = new Separator(Orientation.VERTICAL);
        lastLeftSeparator.setId(carriageType == CarriageType.COUCHETTE ? "long" : "short");
        lastLeftSeparator.setLayoutX(leftSeparatorsX - 40);
        lastLeftSeparator.setLayoutY(0);
        leftSeparators.add(lastLeftSeparator);

        carriageFloor.getChildren().addAll(leftSeparators);
        // -------------------------------------------------------------------------ліві
        // -------------------------------------------------------------------------праві
        ArrayList<Separator> rightSeparators = new ArrayList<>();
        Separator firstRightSeparator = new Separator(Orientation.VERTICAL);
        int rightSeparatorsX = 33;
        int rightSeparatorsY = carriageType == CarriageType.COUCHETTE ? 91 : 60;
        firstRightSeparator.setId("short");
        firstRightSeparator.setLayoutX(rightSeparatorsX);
        firstRightSeparator.setLayoutY(rightSeparatorsY);
        rightSeparators.add(firstRightSeparator);
        if (carriageType == CarriageType.COUCHETTE) {
            rightSeparatorsX += 40;
            for (int i = 0; i < 10; i++, rightSeparatorsX+=80) {
                Separator anotherOneSeparator = new Separator(Orientation.VERTICAL);
                anotherOneSeparator.setLayoutX(rightSeparatorsX);
                anotherOneSeparator.setLayoutY(rightSeparatorsY);
                anotherOneSeparator.setId("short");
                rightSeparators.add(anotherOneSeparator);
            }
        }
        Separator lastRightSeparator = new Separator(Orientation.VERTICAL);
        lastRightSeparator.setId("short");
        lastRightSeparator.setLayoutX(833);
        lastRightSeparator.setLayoutY(rightSeparatorsY);
        rightSeparators.add(lastRightSeparator);

        carriageFloor.getChildren().addAll(rightSeparators);

        // -------------------------------------------------------------------------праві
        // ------------------------------------------------------------------------------перегородки

        // ------------------------------------------------------------------------------сральники
        Image imgToilet = new Image("images/toilet.png");
        ImageView toilet1 = new ImageView(imgToilet);
        toilet1.setLayoutX(38);
        toilet1.setLayoutY(5);

        ImageView toilet2 = new ImageView(imgToilet);
        toilet2.setLayoutX(798);
        toilet2.setLayoutY(5);

        carriageFloor.getChildren().addAll(toilet1, toilet2);
        // ------------------------------------------------------------------------------сральники

        // ------------------------------------------------------------------------------місця
        //створення місць для заповнення вагону
        ArrayList<PlaceButton> placeButtons = new ArrayList<>();
        Random random = new Random(8462846L);
        for (int i = 1; i <= carriageType.getCapasity(); i++) {
            placeButtons.add(new PlaceButton(i, random.nextBoolean()));
        }
        // ---------------------------------------------------------------------------ліві верхні
        HBox hbLeftUpPlaces = new HBox();
        hbLeftUpPlaces.setLayoutX(78);
        hbLeftUpPlaces.setLayoutY(5);
        hbLeftUpPlaces.getStyleClass().add("hb_w_places");
        // ---------------------------------------------------------------------------ліві верхні
        // ---------------------------------------------------------------------------ліві нижні
        HBox hbLeftDownPlaces = new HBox();
        hbLeftDownPlaces.setLayoutX(78);
        hbLeftDownPlaces.setLayoutY(35);
        hbLeftDownPlaces.getStyleClass().add("hb_w_places");
        // ---------------------------------------------------------------------------ліві нижні
        // ---------------------------------------------------------------------------праві
        HBox hbRightPlaces = new HBox();
        hbRightPlaces.setLayoutX(78);
        hbRightPlaces.setLayoutY(95);
        hbRightPlaces.getStyleClass().add("hb_w_places");
        // ---------------------------------------------------------------------------праві
        for (int i = 0; i < 36; i++) {
            if (((double)i+1)%2==0) {
                hbLeftUpPlaces.getChildren().add(placeButtons.get(i));
            } else hbLeftDownPlaces.getChildren().add(placeButtons.get(i));
        }
        for (int i = 53; i >=36; i--) {
            hbRightPlaces.getChildren().add(placeButtons.get(i));
        }
        carriageFloor.getChildren().addAll(hbLeftUpPlaces, hbLeftDownPlaces, hbRightPlaces);
        // ------------------------------------------------------------------------------місця







//        carriageScheme.getChildren().addAll(lblCarriageType, carriageFloor);


        carriageScheme.getChildren().addAll(lblCarriageType, new CarriageFloor(carriageSV));



        VBox vBox = new VBox();
        vBox.getChildren().addAll(carriageList, carriageScheme);
//        Scene scene = new Scene(vBox);


        VBox vbTest = new VBox();

        ArrayList<Carriage> crgs1 = new ArrayList<>();
        crgs1.add(carriage1);
        crgs1.add(carriage2);
        crgs1.add(carriage3);

        PlaceOrdersPane placeOrdersPane = new PlaceOrdersPane(
                "141 Л" // витягуємо ім'я маршруту
        );
        CarriagesPane cp1 = new CarriagesPane(crgs1, placeOrdersPane);

//        Carriage carriage21 = new Carriage(null, 4, CarriageType.COMPARATMENT);
//        Carriage carriage22 = new Carriage(null, 5, CarriageType.COMPARATMENT);
//        Carriage carriage23 = new Carriage(null, 6, CarriageType.COMPARATMENT);
//        ArrayList<Place> placesForCarriage21 = new ArrayList<>();
//        ArrayList<Place> placesForCarriage22 = new ArrayList<>();
//        ArrayList<Place> placesForCarriage23 = new ArrayList<>();
//        for (int i = 1; i <=CarriageType.COMPARATMENT.getCapasity(); i++) {
//            placesForCarriage21.add(new Place(i, rand.nextBoolean(), carriage21));
//            placesForCarriage22.add(new Place(i, rand.nextBoolean(), carriage22));
//            placesForCarriage23.add(new Place(i, rand.nextBoolean(), carriage23));
//        }
//        carriage21.setPlaces(placesForCarriage21);
//        carriage22.setPlaces(placesForCarriage22);
//        carriage23.setPlaces(placesForCarriage23);
//        ArrayList<Carriage> crgs2 = new ArrayList<>();
//        crgs2.add(carriage21);
//        crgs2.add(carriage22);
//        crgs2.add(carriage23);
//        CarriagesPane cp2 = new CarriagesPane(crgs2, null);
//
//        Carriage carriage31 = new Carriage(null, 7, CarriageType.SV);
//        Carriage carriage32 = new Carriage(null, 8, CarriageType.SV);
//        Carriage carriage33 = new Carriage(null, 9, CarriageType.SV);
//        ArrayList<Place> placesForCarriage31 = new ArrayList<>();
//        ArrayList<Place> placesForCarriage32 = new ArrayList<>();
//        ArrayList<Place> placesForCarriage33 = new ArrayList<>();
//        for (int i = 1; i <=CarriageType.SV.getCapasity(); i++) {
//            placesForCarriage31.add(new Place(i, rand.nextBoolean(), carriage31));
//            placesForCarriage32.add(new Place(i, rand.nextBoolean(), carriage32));
//            placesForCarriage33.add(new Place(i, rand.nextBoolean(), carriage33));
//        }
//        carriage31.setPlaces(placesForCarriage31);
//        carriage32.setPlaces(placesForCarriage32);
//        carriage33.setPlaces(placesForCarriage33);
//        ArrayList<Carriage> crgs3 = new ArrayList<>();
//        crgs3.add(carriage31);
//        crgs3.add(carriage32);
//        crgs3.add(carriage33);
//        CarriagesPane cp3 = new CarriagesPane(crgs3, null);



        vbTest.getChildren().addAll(cp1, placeOrdersPane);
        Scene scene = new Scene(vbTest);



        scene.getStylesheets().add("css/uz.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
