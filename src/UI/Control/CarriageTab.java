package UI.Control;

import Entity.Carriage;
import UI.View.PlaceOrdersPane;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CarriageTab extends Pane implements Toggle {     // приведи його до пуття, ато дивитись страшно!
    // ------------------------------------------------------------------------------------ ой, не питайте навіть..
    private ObjectProperty<ToggleGroup> toggleGroup;
    @Override
    public ToggleGroup getToggleGroup() {
        return toggleGroup == null ? null : toggleGroup.get();
    }
    @Override
    public void setToggleGroup(ToggleGroup value) {
        toggleGroupProperty().set(value);
    }
    @Override
    public ObjectProperty<ToggleGroup> toggleGroupProperty() {
        if (toggleGroup == null) {
            toggleGroup = new ObjectPropertyBase<ToggleGroup>() {
                private ToggleGroup old;
                private ChangeListener<Toggle> listener = (o, oV, nV) ->
                        getImpl_traversalEngine().setOverriddenFocusTraversability(nV != null ? isSelected() : null);

                @Override protected void invalidated() {
                    final ToggleGroup tg = get();
                    if (tg != null && !tg.getToggles().contains(CarriageTab.this)) {
                        if (old != null) {
                            old.getToggles().remove(CarriageTab.this);
                        }
                        tg.getToggles().add(CarriageTab.this);
                        final ParentTraversalEngine parentTraversalEngine = new ParentTraversalEngine(CarriageTab.this);
                        setImpl_traversalEngine(parentTraversalEngine);
                        // If there's no toggle selected, do not override
                        parentTraversalEngine.setOverriddenFocusTraversability(tg.getSelectedToggle() != null ? isSelected() : null);
                        tg.selectedToggleProperty().addListener(listener);
                    } else if (tg == null) {
                        old.selectedToggleProperty().removeListener(listener);
                        old.getToggles().remove(CarriageTab.this);
                        setImpl_traversalEngine(null);
                    }

                    old = tg;
                }

                @Override
                public Object getBean() {
                    return CarriageTab.this;
                }

                @Override
                public String getName() {
                    return "toggleGroup";
                }
            };
        }
        return toggleGroup;
    }

    private BooleanProperty selected;
    @Override
    public boolean isSelected() {
        return selected != null && selected.get();
    }
    @Override
    public BooleanProperty selectedProperty() {
        if (selected == null) {
            selected = new BooleanPropertyBase() {
                @Override protected void invalidated() {
                    final boolean selected = get();
                    final ToggleGroup tg = getToggleGroup();
                    // Note: these changes need to be done before selectToggle/clearSelectedToggle since
                    // those operations change properties and can execute user code, possibly modifying selected property again
                    pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), selected);
                    notifyAccessibleAttributeChanged(AccessibleAttribute.SELECTED);
                    if (tg != null) {
                        if (selected) {
                            tg.selectToggle(CarriageTab.this);
                        } else if (tg.getSelectedToggle() == CarriageTab.this) {
                            clearSelectedToggle(tg);
                        }
                    }
                }
                void clearSelectedToggle(ToggleGroup tg) {
                    if (!tg.getSelectedToggle().isSelected()) {
                        for (Toggle toggle: tg.getToggles()) {
                            if (toggle.isSelected()) {
                                return;
                            }
                        }
                    }
                    tg.selectToggle(null);
                }

                @Override
                public Object getBean() {
                    return CarriageTab.this;
                }

                @Override
                public String getName() {
                    return "selected";
                }
            };
        }
        return selected;
    }
    @Override
    public void setSelected(boolean selected) {
        selectedProperty().set(selected);
    }
    // ------------------------------------------------------------------------------------ ой, не питайте навіть..

    private int carriageNumber;
    private int freePlacesAmount;
    private CarriageFloor carriageFloor;

    private Tooltip tooltip;
    private ImageView imgCarriage;
    Label lblCarriageNumber, lblFreePlacesAmount;

    public CarriageTab(Carriage carriage, Pane paneForFloor, PlaceOrdersPane placeOrdersPane) {
        super();
        this.carriageNumber = carriageNumber;
        this.freePlacesAmount = freePlacesAmount;

        createCarriageFloor(carriage, placeOrdersPane);

        createTooltip();
        createImage();
        createLabels();

        getStyleClass().add("carriageTab");
        setOnMouseClicked(event -> {
            if (getToggleGroup() == null || !isSelected()) {
                setSelected(!isSelected());
                action();
            }
        });
    }

    private void createCarriageFloor(Carriage carriage, PlaceOrdersPane placeOrdersPane) {
        carriageFloor = new CarriageFloor(carriage, placeOrdersPane);
    }
    public CarriageFloor getCarriageFloor() {
        return carriageFloor;
    }

    private void createTooltip() {
        tooltip = new Tooltip("Carriage №" + carriageNumber + ": free " + freePlacesAmount);
        Tooltip.install(this, tooltip);
    }

    private void createImage() {
        imgCarriage = new ImageView(new Image("images/carriage.png"));
        getChildren().add(imgCarriage);
    }

    private void createLabels() {
        lblCarriageNumber = new Label(carriageNumber+"");
        lblCarriageNumber.setId("carriageNumber");

        lblFreePlacesAmount = new Label(freePlacesAmount+"");
        lblFreePlacesAmount.setId("carriageFreePlacesAmount");
        lblFreePlacesAmount.setLayoutX(45);
        lblFreePlacesAmount.setLayoutY(0);

        getChildren().addAll(lblCarriageNumber, lblFreePlacesAmount);
    }

    public CarriageTab(int carriageNumber, int freePlacesAmount) {
        super();
        this.carriageNumber = carriageNumber;
        this.freePlacesAmount = freePlacesAmount;

        tooltip = new Tooltip("Carriage №" + carriageNumber + ": free " + freePlacesAmount);
        Tooltip.install(this, tooltip);

        getStyleClass().add("carriageTab");

        imgCarriage = new ImageView(new Image("images/carriage.png"));
        //цю хрєнь що нижче перепиши відступами в css
        getChildren().add(imgCarriage);

//        lblCarriageNumber = new Label(carriageNumber+"");
//        lblCarriageNumber.setPrefSize(45, 18);
//        lblCarriageNumber.setMaxSize(45, 18);
//        lblCarriageNumber.setAlignment(Pos.CENTER);
//        getChildren().add(lblCarriageNumber);
//
//        lblFreePlacesAmount = new Label(freePlacesAmount+"");
//        lblFreePlacesAmount.setLayoutX(45);
//        lblFreePlacesAmount.setLayoutY(0);
//        lblFreePlacesAmount.setPrefSize(22, 10);
//        lblFreePlacesAmount.setMaxSize(22, 10);
//        lblFreePlacesAmount.setAlignment(Pos.TOP_CENTER);
//        lblFreePlacesAmount.setStyle("-fx-background-color: #a1b0b9");
//        getChildren().add(lblFreePlacesAmount);
        createLabels();

//        setCursor(Cursor.HAND);
        setOnMouseClicked(event -> {
            if (getToggleGroup() == null || !isSelected()) {
                setSelected(!isSelected());
                action();
            }
        });
    }

    private void action() {

        System.out.println("doing");


    }
}
