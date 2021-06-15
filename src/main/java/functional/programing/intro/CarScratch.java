package functional.programing.intro;

import java.time.LocalDate;
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
@FunctionalInterface
interface Criterion<E>{
    boolean test(E c);
}



public class CarScratch {

    public static <E> void showAll(List<E> lc) {
        for (E c : lc) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
    }



    public static <E> List<E> getCarsByCriterion(Iterable<E> in, Criterion<E> criterion) {
        List<E> out = new ArrayList<>();
        for (E e : in) {
            if (criterion.test(e))
                out.add(e);
        }
        return out;
    }


    public static <E> Criterion<E> negate(Criterion<E> crit){
        return x -> !crit.test(x);
    }

    public static <E> Criterion<E> and(Criterion<E> first, Criterion<E> second){

       return x-> first.test(x) && second.test(x);

    }

    public static <E> Criterion<E> or(Criterion<E> first, Criterion<E> second){

        return x-> first.test(x) || second.test(x);

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

        /**
         * 3. Third option to given context to lambda expression:  By argument
         *
         */
        showAll(getCarsByCriterion(cars, c -> c.getPassengers().size()==2));

        /**
         * using lambda expression as Return Expresion (  2. Second option)
         */
        showAll(getCarsByCriterion(cars, Car.getFourPassengerCriterion()));

        List<String> colors = Arrays.asList("LightCoral", "pink", "Orange","Gold");
        showAll(getCarsByCriterion(colors, str->str.length()>4));
        showAll(getCarsByCriterion(colors, str-> Character.isUpperCase(str.charAt(0))));

        LocalDate today = LocalDate.now();

        List<LocalDate> dates = Arrays.asList(today, today.plusDays(1), today.plusDays(7),today.minusDays(1));

        showAll(getCarsByCriterion(dates, ld->ld.isAfter(today)));



//        showAll(getCarsByCriterion(cars,  Car.getGasLevelCarCriterion(6)));
//        showAll(getCarsByCriterion(cars,  Car.getGasLevelCarCriterion(3)));
//        showAll(getCarsByCriterion(cars,  Car.getColorCriterion("Red","Black")));

        Criterion<Car> gasLevel7 =  Car.getGasLevelCarCriterion(7);
        showAll(getCarsByCriterion(cars, gasLevel7));
        Criterion<Car> notGasLevel7 = CarScratch.negate(gasLevel7);
        showAll(getCarsByCriterion(cars, notGasLevel7));


        Criterion<Car> gasLevel6 =  Car.getGasLevelCarCriterion(6);
        Criterion<Car> getByColors = Car.getColorCriterion("Red","Black");
        Criterion<Car> andExample = CarScratch.and(gasLevel6,getByColors);

        showAll(getCarsByCriterion(cars, andExample));

    }
}
