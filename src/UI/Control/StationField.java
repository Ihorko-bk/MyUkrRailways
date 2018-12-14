package UI.Control;

import Entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class StationField extends VBox {

    private Label label;
    private ComboBox<Station> cbStations;
    private HBox hotStationList;
        private HotStation hsKyiv, hsLviv, hsKharkiv, hsOdesa, hsDniproHolovnyy;

    public StationField(String labelText, ArrayList<Station> stations) {
        super();
        createLabel(labelText);
        createComboBox(stations);
        createHotStations(stations);
        createHotStationsList();
    }

    public Station getSelectedStation() {
        return cbStations.getValue();
    }

    private void createLabel(String labelText) {
        label = new Label(labelText);
        label.getStyleClass().add("simpleLabel");
        getChildren().add(label);
    }

    private void createComboBox(ArrayList<Station> stations) {
        cbStations = new ComboBox<>();
        cbStations.getStyleClass().add("stationSelectField");
        cbStations.getItems().addAll(stations);
        new AutoCompleteComboBoxListener<>(cbStations);
        getChildren().add(cbStations);
    }

    private void createHotStations(ArrayList<Station> stations) {
        hsKyiv = createHotStationByName(stations, "Kyiv");
        hsLviv = createHotStationByName(stations, "Lviv");
        hsKharkiv = createHotStationByName(stations, "Kharkiv");
        hsOdesa = createHotStationByName(stations, "Odesa");
        hsDniproHolovnyy = createHotStationByName(stations, "Dnipro Holovnyy");
    }

    private void createHotStationsList() {
        hotStationList = new HBox(hsKyiv, hsLviv, hsKharkiv, hsOdesa, hsDniproHolovnyy);
        hotStationList.getStyleClass().add("hotList");
        getChildren().add(hotStationList);
    }

    private HotStation createHotStationByName(ArrayList<Station> stations, String name) {
        for (Station station: stations) {
            if (station.getName().equals(name)) {
                return new HotStation(station);
            }
        }
        return null;
    }

    private class HotStation extends Label {
        HotStation(Station station) {
            super(station.getName());
            getStyleClass().add("hot");
            setOnMouseClicked(event -> cbStations.getSelectionModel().select(station));
        }
    }

    // тимчасова(надіюсь) фігня
    class AutoCompleteComboBoxListener<T> implements EventHandler<KeyEvent> {

        private ComboBox comboBox;
        private StringBuilder sb;
        private ObservableList<T> data;
        private boolean moveCaretToPos = false;
        private int caretPos;

        public AutoCompleteComboBoxListener(final ComboBox comboBox) {
            this.comboBox = comboBox;
            sb = new StringBuilder();
            data = comboBox.getItems();

            this.comboBox.setEditable(true);
            this.comboBox.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    comboBox.hide();
                }
            });
            this.comboBox.setOnKeyReleased(AutoCompleteComboBoxListener.this);
        }

        @Override
        public void handle(KeyEvent event) {

            if(event.getCode() == KeyCode.UP) {
                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                return;
            } else if(event.getCode() == KeyCode.DOWN) {
                if(!comboBox.isShowing()) {
                    comboBox.show();
                }
                caretPos = -1;
                moveCaret(comboBox.getEditor().getText().length());
                return;
            } else if(event.getCode() == KeyCode.BACK_SPACE) {
                moveCaretToPos = true;
                caretPos = comboBox.getEditor().getCaretPosition();
            } else if(event.getCode() == KeyCode.DELETE) {
                moveCaretToPos = true;
                caretPos = comboBox.getEditor().getCaretPosition();
            }

            if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT
                    || event.isControlDown() || event.getCode() == KeyCode.HOME
                    || event.getCode() == KeyCode.END || event.getCode() == KeyCode.TAB) {
                return;
            }

            ObservableList list = FXCollections.observableArrayList();
            for (int i=0; i<data.size(); i++) {
                if(data.get(i).toString().toLowerCase().startsWith(
                        AutoCompleteComboBoxListener.this.comboBox
                                .getEditor().getText().toLowerCase())) {
                    list.add(data.get(i));
                }
            }
            String t = comboBox.getEditor().getText();

            comboBox.setItems(list);
            comboBox.getEditor().setText(t);
            if(!moveCaretToPos) {
                caretPos = -1;
            }
            moveCaret(t.length());
            if(!list.isEmpty()) {
                comboBox.show();
            }
        }

        private void moveCaret(int textLength) {
            if(caretPos == -1) {
                comboBox.getEditor().positionCaret(textLength);
            } else {
                comboBox.getEditor().positionCaret(caretPos);
            }
            moveCaretToPos = false;
        }

    }
}
