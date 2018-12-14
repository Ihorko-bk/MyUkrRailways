package Entity;

import java.time.LocalTime;
import java.util.Comparator;

public class RouteStation implements Comparator {
    private int id;
    private Station station ;
    private Route route;
    private int ordinance;
    private LocalTime arrivalTime;      //з LocalDateTime на LocalTime
    private LocalTime departureTime;    //з LocalDateTime на LocalTime

    public RouteStation() {
    }

    //дописав id до конструктора щоб сортувати по ньому, так як в нас вже LocalTime
    public RouteStation(int id, Station station, Route route, LocalTime arrivalTime, LocalTime departureTime) {
        this.id = id;
        this.station = station;
        this.route = route;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    //компаратор для сортування зупинок по id(а отже по порядку). хз чи потрібно буде, але чомусь захотілось..
    @Override
    public int compare(Object o1, Object o2) {
        return Integer.compare(((RouteStation) o1).id, ((RouteStation) o2).id);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }
    public void setStation(Station station) {
        this.station = station;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return station.getName();
    }
}
