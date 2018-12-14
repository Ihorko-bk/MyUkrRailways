import Entity.CarriageType;
import UI.Control.CarriageTab;
import UI.Control.PlaceButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
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

        HBox carriageList = new HBox();
        carriageList.getStyleClass().add("carriageList");

//        Label lblcarriages = new Label("Вагони:");
        Label lblcarriages = new Label("Carriages:");
        lblcarriages.setPadding(new Insets(0, 10, 0, 0));


        carriageList.getChildren().add(lblcarriages);

        CarriageTab carriageTab1 = new CarriageTab(1, 69);
        CarriageTab carriageTab2 = new CarriageTab(2, 11);
        CarriageTab carriageTab3 = new CarriageTab(3, 5);
        carriageList.getChildren().addAll(carriageTab1, carriageTab2, carriageTab3);


        CarriageType carriageType = CarriageType.COUCHETTE;

        VBox carriageScheme = new VBox();
        carriageScheme.getStyleClass().add("carriageScheme");

//        Label lblCarriageType = new Label("Тип вагону: Плацкарт");
        Label lblCarriageType =
                new Label("Type of carriage: "
                        + carriageType.name().substring(0, 1) + carriageType.name().substring(1).toLowerCase());
        lblCarriageType.getStyleClass().add("carriageType");

        Pane floor = new Pane();
        floor.getStyleClass().add("floor");
        floor.setId(carriageType.name().toLowerCase());

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

        floor.getChildren().addAll(leftSeparators);
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

        floor.getChildren().addAll(rightSeparators);

        // -------------------------------------------------------------------------праві
        // ------------------------------------------------------------------------------перегородки

        // ------------------------------------------------------------------------------сральники
        Image imgToilet = new Image("images/toilet.png");
        ImageView firstToilet = new ImageView(imgToilet);
        firstToilet.setLayoutX(38);
        firstToilet.setLayoutY(5);

        ImageView secondToilet = new ImageView(imgToilet);
        secondToilet.setLayoutX(798);
        secondToilet.setLayoutY(5);

        floor.getChildren().addAll(firstToilet, secondToilet);
        // ------------------------------------------------------------------------------сральники

        // ------------------------------------------------------------------------------місця
        //створення місць для заповнення вагону
        ArrayList<PlaceButton> placeButtons = new ArrayList<>();
        Random random = new Random(8462846L);
        for (int i = 1; i <= carriageType.getCapasity(); i++) {
            placeButtons.add(new PlaceButton(random.nextBoolean(), i));
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
        floor.getChildren().addAll(hbLeftUpPlaces, hbLeftDownPlaces, hbRightPlaces);
        // ------------------------------------------------------------------------------місця

        carriageScheme.getChildren().addAll(lblCarriageType, floor);



        VBox vBox = new VBox();
        vBox.getChildren().addAll(carriageList, carriageScheme);
        Scene scene = new Scene(vBox);
        scene.getStylesheets().add("css/uz.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
