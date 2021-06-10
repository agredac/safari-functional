package functional.programing.intro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class PassengerCountOrder implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getPassengers().size() - o2.getPassengers().size();
    }
}

interface CarCriterion{
    boolean test(Car c);
}



public class CarScratch {

    public static <E> void showAll(List<Car> lc) {
        for (Car c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }



    public static List<Car> getCarsByCriterion(Iterable<Car> in, CarCriterion criterion) {
        List<Car> out = new ArrayList<>();
        for (Car car : in) {
            if (criterion.test(car))
                out.add(car);
        }
        return out;
    }


    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
        );
        showAll(cars);

        showAll(getCarsByCriterion(cars,  Car.getRedCarCriterion()));
        showAll(getCarsByCriterion(cars,  Car.getGasLevelCarCriterion(6)));

     //   cars.sort(new PassengerCountOrder());
        cars.sort(Car.getGasComparator());
        showAll(cars);
    }
}
