package UI.Control;

import javafx.css.PseudoClass;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CarriageTab extends Pane {     // ПЕРЕПИСАТИ! потрібно в ньому тримати місця, чи де чи шо думай блять!
    private int carriageNumber;
    private int freePlacesAmount;
    private boolean selected = false;


    public void setSelected(boolean selected) {
        this.selected = selected;
        pseudoClassStateChanged(PseudoClass.getPseudoClass("selected"), selected);
    }

    public CarriageTab(int carriageNumber, int freePlacesAmount) {
        super();
        this.carriageNumber = carriageNumber;
        this.freePlacesAmount = freePlacesAmount;

        Tooltip tooltip = new Tooltip("Вагон №" + carriageNumber + ": вільно " + freePlacesAmount);
        Tooltip.install(this, tooltip);

        getStyleClass().add("carriageTab");

        ImageView imgCarriage = new ImageView(new Image("images/carriage.png"));
        //цю хрєнь що нижче перепиши відступами в css
        getChildren().add(imgCarriage);

        Label lblCarriageNumber = new Label(carriageNumber+"");
        lblCarriageNumber.setPrefSize(45, 18);
        lblCarriageNumber.setMaxSize(45, 18);
        lblCarriageNumber.setAlignment(Pos.CENTER);
        getChildren().add(lblCarriageNumber);

        Label lblFreePlacesAmount = new Label(freePlacesAmount+"");
        lblFreePlacesAmount.setLayoutX(45);
        lblFreePlacesAmount.setLayoutY(0);
        lblFreePlacesAmount.setPrefSize(22, 10);
        lblFreePlacesAmount.setMaxSize(22, 10);
        lblFreePlacesAmount.setAlignment(Pos.TOP_CENTER);
        lblFreePlacesAmount.setStyle("-fx-background-color: #a1b0b9");
        getChildren().add(lblFreePlacesAmount);




        setCursor(Cursor.HAND);
        setOnMouseClicked(event -> {
            setSelected(!selected);
            // --------------------------------------- пиши переключання форм туть

        });

    }

}
