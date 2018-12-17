package UI.View;

import Entity.Carriage;
import Entity.CarriageType;
import UI.Control.CarriageFloor;
import UI.Control.CarriageTab;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class CarriagesPane extends VBox {

    private ToggleGroup toggleGroup;

    private HBox carriageList;
        private Label lblcarriages;
        private ArrayList<CarriageTab> carriageTabs;
    private VBox carriageScheme;
        private Label lblCarriageType;
        private CarriageFloor carriageFloor;

    public CarriagesPane(ArrayList<Carriage> carriages, PlaceOrdersPane placeOrdersPane) {
        super();

        createCarriageScheme(carriages.get(0).getCarriageType());

        createToggleGroup();
        createCarriageList(carriages, placeOrdersPane);

        getChildren().addAll(carriageList, carriageScheme, carriageFloor);

        carriageTabs.get(0).fire();
    }

    private void createCarriageScheme(CarriageType carriageType) {
        carriageScheme = new VBox();
        createLabelCarriagesType(carriageType);
        carriageScheme.getStyleClass().add("carriageScheme");
        carriageFloor = new CarriageFloor();
    }

    private void createLabelCarriagesType(CarriageType carriageType) {
        lblCarriageType =
                new Label("Wagon type: "
                        + carriageType.name().substring(0, 1) + carriageType.name().substring(1).toLowerCase());
        lblCarriageType.getStyleClass().add("carriageType");
        carriageScheme.getChildren().add(lblCarriageType);
    }

    private void createToggleGroup() {
        toggleGroup = new ToggleGroup();
    }

    private void createCarriageList(ArrayList<Carriage> carriages, PlaceOrdersPane placeOrdersPane) {
        carriageList = new HBox();
        carriageList.getStyleClass().add("carriageList");
        createLabelCarriages();
        createCarriageTabs(carriages, placeOrdersPane);
    }

    private void createLabelCarriages() {
        lblcarriages = new Label("Coaches:");
        lblcarriages.setPadding(new Insets(0, 10, 0, 0));
        carriageList.getChildren().add(lblcarriages);
    }

    private void createCarriageTabs(ArrayList<Carriage> carriages, PlaceOrdersPane placeOrdersPane) {
        carriageTabs = new ArrayList<>();
        for (int i = 0; i < carriages.size(); i++) {
            carriageTabs.add(new CarriageTab(carriages.get(i), placeOrdersPane, toggleGroup));
        }
        carriageList.getChildren().addAll(carriageTabs);
    }

    public void changeCarriageFloor(CarriageFloor newCarriageFloor) {
        carriageScheme.getChildren().remove(carriageFloor);
        carriageFloor = newCarriageFloor;
        carriageScheme.getChildren().add(carriageFloor);
    }
}
