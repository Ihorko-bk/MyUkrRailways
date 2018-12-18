package UI.Control;

import Entity.Place;
import UI.View.PlaceOrdersPane;
import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.geometry.Orientation;
import javafx.scene.AccessibleAttribute;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

public class PlaceButton extends Pane implements Toggle{

    private Place place;
    public Place getPlace() {
        return place;
    }

    private boolean free;
    private Label number;
    private PlaceOrdersPane placeOrdersPane;

    protected Separator separator;

    private BooleanProperty selected;
    public final void setSelected(boolean value) {
        selectedProperty().set(value);
    }
    public void changeSelected() {
        setSelected(!isSelected());
    }

    public final boolean isSelected() {
        return selected == null ? false : selected.get();
    }

    public final BooleanProperty selectedProperty() {
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
                            tg.selectToggle(PlaceButton.this);
                        } else if (tg.getSelectedToggle() == PlaceButton.this) {
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
                    return PlaceButton.this;
                }

                @Override
                public String getName() {
                    return "selected";
                }
            };
        }
        return selected;
    }

    private ObjectProperty<ToggleGroup> toggleGroup;
    public final void setToggleGroup(ToggleGroup value) {
        toggleGroupProperty().set(value);
    }

    public final ToggleGroup getToggleGroup() {
        return toggleGroup == null ? null : toggleGroup.get();
    }

    public final ObjectProperty<ToggleGroup> toggleGroupProperty() {
        if (toggleGroup == null) {
            toggleGroup = new ObjectPropertyBase<ToggleGroup>() {
                private ToggleGroup old;
                private ChangeListener<Toggle> listener = (o, oV, nV) ->
                        getImpl_traversalEngine().setOverriddenFocusTraversability(nV != null ? isSelected() : null);

                @Override protected void invalidated() {
                    final ToggleGroup tg = get();
                    if (tg != null && !tg.getToggles().contains(PlaceButton.this)) {
                        if (old != null) {
                            old.getToggles().remove(PlaceButton.this);
                        }
                        tg.getToggles().add(PlaceButton.this);
                        final ParentTraversalEngine parentTraversalEngine = new ParentTraversalEngine(PlaceButton.this);
                        setImpl_traversalEngine(parentTraversalEngine);
                        // If there's no toggle selected, do not override
                        parentTraversalEngine.setOverriddenFocusTraversability(tg.getSelectedToggle() != null ? isSelected() : null);
                        tg.selectedToggleProperty().addListener(listener);
                    } else if (tg == null) {
                        old.selectedToggleProperty().removeListener(listener);
                        old.getToggles().remove(PlaceButton.this);
                        setImpl_traversalEngine(null);
                    }

                    old = tg;
                }

                @Override
                public Object getBean() {
                    return PlaceButton.this;
                }

                @Override
                public String getName() {
                    return "toggleGroup";
                }
            };
        }
        return toggleGroup;
    }

    // тимчасовий
    public PlaceButton(int number, boolean free) {
        super();
        this.free = free;
        pseudoClassStateChanged(PseudoClass.getPseudoClass("free"), free);
        if (free) createActions();
        createLabel(number);
        createSeparator(number);
        createTooltip(free);
        getStyleClass().add("place");
        getChildren().addAll(this.number, separator);
    }

    // конструктор з place та формою, куди додавати / звідки видаляти замовлення
    public PlaceButton(Place place, PlaceOrdersPane placeOrdersPane) {
        super();
        this.place = place;
        this.placeOrdersPane = placeOrdersPane;
        this.free = place.isFree();
        pseudoClassStateChanged(PseudoClass.getPseudoClass("free"), free);
        if (free) createActions();
        createLabel(place.getNumber());
        createSeparator(place.getNumber());
        createTooltip(free);
        getStyleClass().add("place");
        getChildren().addAll(this.number, separator);
    }

    // тимчасовий
    public PlaceButton(Place place) {
        super();
        this.place = place;
        this.free = place.isFree();
        pseudoClassStateChanged(PseudoClass.getPseudoClass("free"), free);
        if (free) createActions();
        createLabel(place.getNumber());
        createSeparator(place.getNumber());
        createTooltip(free);
        getStyleClass().add("place");
        getChildren().addAll(this.number, separator);
    }

    private void createActions() {
        setSelected(false);
        setCursor(Cursor.HAND);
        onMouseClickedProperty().setValue(event -> {
            orderPlace(isSelected());
            setSelected(!isSelected());
        });
    }

    // отуть пишемо що буде як натиснеться на місце <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private void orderPlace(boolean selected) {
        if (!placeOrdersPane.addRemoveOrder(this, !selected)) changeSelected();
    }

    private void createLabel(int number) {
        this.number = new Label(number+"");
        this.number.setLayoutX(0);
        this.number.setLayoutY(0);
    }

    private void createSeparator(int number) {
        separator = new Separator(Orientation.HORIZONTAL);
        separator.setLayoutX(4);
        separator.setLayoutY(((double)number)%2==0 ? 5 : 20);
    }

    private void createTooltip(boolean free) {
        Tooltip tooltip = new Tooltip("Place: " + number.getText() + (free ? " free" : " busy"));
        Tooltip.install(this, tooltip);
    }

    public boolean isFree() {
        return free;
    }
    public void setFree(boolean free) {
        this.free = free;
    }
    public int getNumber() {
        return Integer.valueOf(number.getText());
    }


}