import java.util.ArrayList;

public class PlacesDemo {
    public static void main(String[] args) {
        ArrayList<Integer> places = new ArrayList<>();
        for (int i = 1; i <= 54; i++) {
            places.add(i);
        }
        ArrayList<Integer> placesLeftUp = new ArrayList<>();
        ArrayList<Integer> placesLeftDown = new ArrayList<>();
        ArrayList<Integer> placesRight = new ArrayList<>();

        for (int i = 0; i < 36; i++) {
            if (((double)i+1)%2==0) {
                placesLeftUp.add(places.get(i));
            } else {
                placesLeftDown.add(places.get(i));
            }
        }
        for (int i = 53; i >= 36; i--) {
            placesRight.add(places.get(i));
        }


        for (int i = 0; i < placesLeftUp.size(); i++) {
            System.out.print(placesLeftUp.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < placesLeftDown.size(); i++) {
            System.out.print(placesLeftDown.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < placesRight.size(); i++) {
            System.out.print(placesRight.get(i) + " ");
        }
    }
}
