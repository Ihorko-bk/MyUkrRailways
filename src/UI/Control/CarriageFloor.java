package UI.Control;

import Entity.Carriage;
import Entity.CarriageType;
import Entity.Place;
import UI.View.PlaceOrdersPane;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class CarriageFloor extends Pane {

    private Carriage carriage;

    ArrayList<Separator> leftSeparators;
    ArrayList<Separator> rightSeparators;

    ImageView toilet1, toilet2;

    ArrayList<PlaceButton> placeButtons;
    HBox hbLeftUpPlaces, hbLeftDownPlaces, hbRightPlaces;


    public CarriageFloor(Carriage carriage, PlaceOrdersPane placeOrdersPane) {
        this.carriage = carriage;

        createSeparators();
        createToilets();
        createPlaceButtons(placeOrdersPane);

        getStyleClass().add("carriageFloor");
        setId(carriage.getCarriageType().name().toLowerCase());
    }

    // тимчасовий
    public CarriageFloor(Carriage carriage) {
        this.carriage = carriage;

        createSeparators();
        createToilets();
        createPlaceButtons();

        getStyleClass().add("carriageFloor");
        setId(carriage.getCarriageType().name().toLowerCase());
    }

    private void createSeparators() {
        createLeftSeparators();
        createRightSeparators();
    }
    private void createLeftSeparators() {
        Separator firstLeftSeparator = new Separator(Orientation.VERTICAL);
        int leftSeparatorsX = 33;
        firstLeftSeparator.setId("short");
        firstLeftSeparator.setLayoutX(leftSeparatorsX);
        firstLeftSeparator.setLayoutY(0);
        leftSeparatorsX += 40;
        leftSeparators = new ArrayList<>();
        leftSeparators.add(firstLeftSeparator);
        for (int i = 0; i < 10; i++, leftSeparatorsX+=80) {
            Separator anotherOneSeparator = new Separator(Orientation.VERTICAL);
            anotherOneSeparator.setLayoutX(leftSeparatorsX);
            anotherOneSeparator.setLayoutY(0);
            anotherOneSeparator.setId("long");
            leftSeparators.add(anotherOneSeparator);
        }
        Separator lastLeftSeparator = new Separator(Orientation.VERTICAL);
        lastLeftSeparator.setId(carriage.getCarriageType() == CarriageType.COUCHETTE ? "long" : "short");
        lastLeftSeparator.setLayoutX(leftSeparatorsX - 40);
        lastLeftSeparator.setLayoutY(0);
        leftSeparators.add(lastLeftSeparator);

        getChildren().addAll(leftSeparators);
    }
    private void createRightSeparators() {
        rightSeparators = new ArrayList<>();
        Separator firstRightSeparator = new Separator(Orientation.VERTICAL);
        int rightSeparatorsX = 33;
        int rightSeparatorsY = carriage.getCarriageType() == CarriageType.COUCHETTE ? 91 : 60;
        firstRightSeparator.setId("short");
        firstRightSeparator.setLayoutX(rightSeparatorsX);
        firstRightSeparator.setLayoutY(rightSeparatorsY);
        rightSeparators.add(firstRightSeparator);
        if (carriage.getCarriageType() == CarriageType.COUCHETTE) {
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

        getChildren().addAll(rightSeparators);
    }

    private void createToilets() {
        Image imgToilet = new Image("images/toilet.png");

        toilet1 = new ImageView(imgToilet);
        toilet1.setLayoutX(38);
        toilet1.setLayoutY(5);

        toilet2 = new ImageView(imgToilet);
        toilet2.setLayoutX(798);
        toilet2.setLayoutY(5);

        getChildren().addAll(toilet1, toilet2);
    }

    private void createPlaceButtons(PlaceOrdersPane placeOrdersPane) {
        ArrayList<Place> places = carriage.getPlaces();
        if (carriage.getCarriageType() == CarriageType.SV) {
            createSVPlaceButtons(places, placeOrdersPane);
            return;
        }
        placeButtons = new ArrayList<>();
        for (int i = 0; i < places.size(); i++) {
            placeButtons.add(new PlaceButton(places.get(i), placeOrdersPane));
        }
        createLeftPlaces();
        if (carriage.getCarriageType() == CarriageType.COUCHETTE) {
            createRightPlaces();
        }
    }

    // тимчасовий
    private void createPlaceButtons() {
        ArrayList<Place> places = carriage.getPlaces();
        if (carriage.getCarriageType() == CarriageType.SV) {
            createSVPlaceButtons(places);
            return;
        }
        placeButtons = new ArrayList<>();
        for (int i = 0; i < places.size(); i++) {
            placeButtons.add(new PlaceButton(places.get(i)));
        }
        createLeftPlaces();
        if (carriage.getCarriageType() == CarriageType.COUCHETTE) {
            createRightPlaces();
        }
    }

    private HBox createHBoxWithPlaces(int layoutX, int layoutY) {
        HBox hBox = new HBox();
        hBox.setLayoutX(layoutX);
        hBox.setLayoutY(layoutY);
        hBox.getStyleClass().add("hb_w_places");
        return hBox;
    }

    private void createSVPlaceButtons(ArrayList<Place> places, PlaceOrdersPane placeOrdersPane) {
        hbLeftUpPlaces = createHBoxWithPlaces(78, 5);
        for (int i = 0; i < places.size(); i++) {
            hbLeftUpPlaces.getChildren().add(new PlaceButton(places.get(i), placeOrdersPane));
        }
        getChildren().add(hbLeftUpPlaces);
    }

    // тимчасовий
    private void createSVPlaceButtons(ArrayList<Place> places) {
        hbLeftUpPlaces = createHBoxWithPlaces(78, 5);
        for (int i = 0; i < places.size(); i++) {
            hbLeftUpPlaces.getChildren().add(new PlaceButton(places.get(i)));
        }
        getChildren().add(hbLeftUpPlaces);
    }
    private void createLeftPlaces() {
        hbLeftUpPlaces = createHBoxWithPlaces(78, 5);
        hbLeftDownPlaces = createHBoxWithPlaces(78, 35);
        for (int i = 0; i < placeButtons.size(); i++) {
            if (((double)i+1)%2==0) {
                hbLeftUpPlaces.getChildren().add(placeButtons.get(i));
            } else hbLeftDownPlaces.getChildren().add(placeButtons.get(i));
        }
        getChildren().addAll(hbLeftUpPlaces, hbLeftDownPlaces);
    }
    private void createRightPlaces() {
        hbRightPlaces = createHBoxWithPlaces(78, 95);
        for (int i = 53; i >=36; i--) {
            hbRightPlaces.getChildren().add(placeButtons.get(i));
        }
        getChildren().add(hbRightPlaces);
    }
}
