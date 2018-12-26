package UI.Control;

import com.sun.javafx.scene.traversal.ParentTraversalEngine;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.BooleanPropertyBase;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.value.ChangeListener;
import javafx.css.PseudoClass;
import javafx.scene.AccessibleAttribute;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class RoutesTableHeadColumnLabel extends Label implements Toggle {

    private String name;
    private boolean orderByASC = true;

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
                    if (tg != null && !tg.getToggles().contains(RoutesTableHeadColumnLabel.this)) {
                        if (old != null) {
                            old.getToggles().remove(RoutesTableHeadColumnLabel.this);
                        }
                        tg.getToggles().add(RoutesTableHeadColumnLabel.this);
                        final ParentTraversalEngine parentTraversalEngine = new ParentTraversalEngine(RoutesTableHeadColumnLabel.this);
                        setImpl_traversalEngine(parentTraversalEngine);
                        // If there's no toggle selected, do not override
                        parentTraversalEngine.setOverriddenFocusTraversability(tg.getSelectedToggle() != null ? isSelected() : null);
                        tg.selectedToggleProperty().addListener(listener);
                    } else if (tg == null) {
                        old.selectedToggleProperty().removeListener(listener);
                        old.getToggles().remove(RoutesTableHeadColumnLabel.this);
                        setImpl_traversalEngine(null);
                    }

                    old = tg;
                }

                @Override
                public Object getBean() {
                    return RoutesTableHeadColumnLabel.this;
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
                            tg.selectToggle(RoutesTableHeadColumnLabel.this);
                        } else if (tg.getSelectedToggle() == RoutesTableHeadColumnLabel.this) {
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
                    return RoutesTableHeadColumnLabel.this;
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
        if (!isSelected() && !orderByASC) orderByASC = true;
        selectedProperty().set(selected);
        setText(selected ? name+(orderByASC ? " ↓": " ↑") : name);
    }

    public boolean sortBy() {
        setSelected(true);
        orderByASC = !orderByASC;
        return !orderByASC;
    }

    public RoutesTableHeadColumnLabel(String text, ToggleGroup toggleGroup) {
        super(text);
        this.name = text;
        setToggleGroup(toggleGroup);

        getStyleClass().addAll("routesTableHeadColumnSimpleLabel", "routesTableHeadColumnLabel");
    }
}
