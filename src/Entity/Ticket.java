package Entity;

import java.time.LocalDateTime;

public class Ticket {
    private int id;
    // пропоную перейменувати з date на purchaseDate, так як нам потім треба буде отримати дати
    // відправки та прибуття і можна буде плутатись з тим..
    private LocalDateTime purchaseDate;
    private String firstName;
    private String lastName;
    private Route route;
    private int carriageId;
    private int placeNumber;
    private RouteStation start;
    private RouteStation end;

    public Ticket() {
    }



    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public int getCarriageId() {
        return carriageId;
    }
    public void setCarriageId(int carriageId) {
        this.carriageId = carriageId;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }
    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public RouteStation getStart() {
        return start;
    }
    public void setStart(RouteStation start) {
        this.start = start;
    }

    public RouteStation getEnd() {
        return end;
    }
    public void setEnd(RouteStation end) {
        this.end = end;
    }
}
