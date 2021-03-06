package Entity;


import java.util.ArrayList;

public class Carriage {
    private int id;
    private Route route;
    // додав номер вагону. прошу висказатись чи того треба. в сенсі чи номером в нас може бути id?
    private int number;
    private CarriageType carriageType;
    // додав колекцію місць
    private ArrayList<Place> places;

    public Carriage() {
    }

    public Carriage(CarriageType carriageType) {
        this.carriageType = carriageType;
    }

    // напевне треба зробити конструктор для створення вагону з маршруту <маршрут>, номером <номер>,
    // типу <тип> та масивом місць. або хз. ну хоча б тимчасово.
    public Carriage(Route route, int number, CarriageType carriageType, ArrayList<Place> places) {
        this.route = route;
        this.number = number;
        this.carriageType = carriageType;
        this.places = places;
    }
    // не хотів робити через this(route, number, carriageType, null) бо то тупо передавати null
    public Carriage(Route route, int number, CarriageType carriageType) {
        this.route = route;
        this.number = number;
        this.carriageType = carriageType;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    // Getter, Setter, "Adder"(я хз нашо. може знадобиться. а як нє то викинути до сраки) для places
    public ArrayList<Place> getPlaces() {
        return places;
    }
    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }
    public void addPlace(Place place) {
        if (place==null) places = new ArrayList<>();
        this.places.add(place);
    }

    // метод, який повертає кількість вільних місць у вагоні
    public int getAmountFreePlaces() {
        int amount = 0;
        for (Place place: places) {
            if (place.isFree()) {
                amount++;
            }
        }
        return amount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public CarriageType getCarriageType() {
        return carriageType;
    }
    public void setCarriageType(CarriageType carriageType) {
        this.carriageType = carriageType;
    }
}
