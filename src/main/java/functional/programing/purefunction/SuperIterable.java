package functional.programing.purefunction;

import functional.programing.intro.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {

    private final Iterable<E> self;

    public SuperIterable(Iterable<E> self) {
        this.self = self;
    }

    public SuperIterable<E> filter(Predicate<E> pred) {

        List<E> results = new ArrayList<>();
        self.forEach(e -> {
            if (pred.test(e)) results.add(e);
        });


/*
        for (E e : self) {
            if (pred.test(e))
                results.add(e);

        }*/

        return new SuperIterable<>(results);
    }

    public void forEvery(Consumer<E> consumer) {

        for (E e : self)
            consumer.accept(e);
    }


    public <F> SuperIterable<F> map(Function<E, F> op) {

        List<F> result = new ArrayList<>();

        self.forEach(e -> result.add(op.apply(e)));

        return new SuperIterable<>(result);
    }

    @Override
    public Iterator<E> iterator() {
        return self.iterator();
    }

    public static void main(String[] args) {

        SuperIterable<String> strings = new SuperIterable<>(
                Arrays.asList("Red", "blue", "Green")
        );

        for (String s : strings) {
            System.out.println("> " + s);
        }

        System.out.println("-----------------------------");

        SuperIterable<String> upperCaseList = strings.filter(s -> Character.isUpperCase(s.charAt(0)));

        upperCaseList.forEvery(s -> System.out.println(s));
        System.out.println("-----------------------------");

        strings.forEach(s -> System.out.println(s));

        System.out.println("-----------------------------");
        strings
                .map(s -> s.toUpperCase())
                .forEach((s -> System.out.println(s)));

        System.out.println("-----------------------------");
        strings
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .map(s -> s.toUpperCase())
                .forEach((s -> System.out.println(s)));

        /*

        for (String s : upperCaseList) {
            System.out.println("> " + s);
        }
        for (String s : strings) {
            System.out.println("> " + s);
        }*/

        SuperIterable<Car> carIter = new SuperIterable<>(
                Arrays.asList(
                        Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                        Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                        Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                        Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                        Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo")
                ));

        carIter
                .filter(car -> car.getGasLevel() > 6)
                .map(car -> car.getPassengers().get(0) + "is driving " + car.getColor()
                        + "car with lots of fuel")
                .forEach(car -> System.out.println("> " + car));

        System.out.println("-----------------------------");
        carIter
                .map(car -> Car.withGasColorPassengers(car.getGasLevel()+4,
                        car.getColor(),
                        car.getPassengers().toArray(new String[]{})))
                .forEach(car -> System.out.println("> " + car));
        System.out.println("-----------------------------");
        carIter
                .map(car -> car.addGas(4))
                .forEach(car -> System.out.println("> " + car));
    }
}
