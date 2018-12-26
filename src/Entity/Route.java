package Entity;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class Route {
    // нам треба витягувати(а отже там і зберігати) сполучення з БД. бо якщо брати першу і останню
    // зупинку, то буде маршрут наш називатись з наших введених початкових та кінцевих точок - лажа.

    private int id;
    private String name;    // додав ім'я(номер) поїзда(маршруту)
    // одразу ArrayList, бо нам ітак ніколи не прийдеться міняти його інший. Аналогічно всюди.
    private ArrayList<RouteStation> routeStations = new ArrayList<>();
    private LocalDateTime date;
    private ArrayList<Carriage> carriages = new ArrayList<>();
    private ArrayList<Ticket> tickets = new ArrayList<>();

    public Route(){
    }


    // повертає сполучення маршруту (чомусь в даний момент воно мені хочеться тут мати)
//    public String getRouteFromTo() {
//        return getFirstRouteStation().toString() + getLastRouteStation().toString();
//    }
    // повертає першу станцію(зупинку) маршруту(helper)
    public RouteStation getFirstRouteStation() {
        return routeStations.get(0);
    }
    // повертає останню станцію(зупинку) маршруту(helper)
    public RouteStation getLastRouteStation() {
        return routeStations.get(routeStations.size()-1);
    }

    //метод видає кількість вільних місця залежно від типу вагону
    public int getAnountFreePlacesByCarriagesType(CarriageType carriageType) {
        int amount = 0;
        for (Carriage carriage:carriages) {
            // мені здається що enum'и порівнюються з ==. якщо ні - вйо переписуємо.
            if (carriage.getCarriageType() == carriageType) {
                amount += carriage.getAmountFreePlaces();
            }
        }
        return amount;
    }

    // метод, який повертає колекцію вагонів суто одного типу
    public ArrayList<Carriage> getCarriagesByType(CarriageType carriageType) {
        ArrayList<Carriage> sublistOfCarriages = new ArrayList<>();
        for (Carriage carriage:carriages) {
            if (carriage.getCarriageType() == carriageType) {
                // тут не буде проблем з посиланням на один і той ще об'єкт, правда?
                sublistOfCarriages.add(carriage);
            }
        }
        return sublistOfCarriages;
    }

    // метод, який розраховує тривалість поїздки.
    // ним же, методом сумування результату з датою відправлення отримуємо дату прибуття.
    // бляха, яка ж це лажа..
//    public LocalTime getRouteDuration() {
//
//    }





    // хз нащо але споглядаючи за іншими класами вирішив і тут посрати Getter'ами та Setter'ами
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<RouteStation> getRouteStations() {
        return routeStations;
    }
    public void setRouteStations(ArrayList<RouteStation> routeStations) {
        this.routeStations = routeStations;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ArrayList<Carriage> getCarriages() {
        return carriages;
    }
    public void setCarriages(ArrayList<Carriage> carriages) {
        this.carriages = carriages;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
