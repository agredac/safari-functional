package functional.programing.streams;

import functional.programing.intro.Car;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVersion {

    public static void main(String[] args) {


        List<String> strings =
                Arrays.asList("Red", "blue", "Green");


        strings.stream().forEach(s -> System.out.println("> " + s));
        System.out.println("-----------------------------");


        Stream<String> upperCase = strings.stream().filter(s -> Character.isUpperCase(s.charAt(0)));
        upperCase.forEach(s -> System.out.println("> " + s));

        System.out.println("-----------------------------");
        strings.forEach(s -> System.out.println(s));


        System.out.println("-----------------------------");
        strings.stream()
                .filter(s -> Character.isUpperCase(s.charAt(0)))
                .map(s -> s.toUpperCase())
                .forEach((s -> System.out.println(s)));


        List<Car> carIter = Arrays.asList(
                Car.withGasColorPassengers(6, "Red", "Fred", "Jim", "Sheila"),
                Car.withGasColorPassengers(3, "Octarine", "Rincewind", "Ridcully"),
                Car.withGasColorPassengers(9, "Black", "Weatherwax", "Magrat"),
                Car.withGasColorPassengers(7, "Green", "Valentine", "Gillian", "Anne", "Dr. Mahmoud"),
                Car.withGasColorPassengers(6, "Red", "Ender", "Hyrum", "Locke", "Bonzo"));

        System.out.println("-----------------------------");
        carIter.stream()
                .filter(car -> car.getGasLevel() > 6)
                .map(car -> car.getPassengers().get(0) + "is driving " + car.getColor()
                        + "car with lots of fuel")
                .forEach(car -> System.out.println("> " + car));

        System.out.println("-----------------------------");
        carIter.stream()
                .map(car -> Car.withGasColorPassengers(car.getGasLevel() + 4,
                        car.getColor(),
                        car.getPassengers().toArray(new String[]{})))
                .forEach(car -> System.out.println("> " + car));

        System.out.println("-----------------------------");
        carIter.stream()
                .map(car -> car.addGas(4))
                .forEach(car -> System.out.println("> " + car));


        System.out.println("-------------FLAT MAP 1----------------");

        carIter.stream()
                .flatMap(car -> car.getPassengers().stream()).forEach(System.out::println);

        System.out.println("-------------FLAT MAP 2----------------");
        carIter.stream()
                .filter(car -> car.getPassengers().size() > 2)
                .flatMap(car -> car.getPassengers().stream())
                .map(s -> s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-------------FLAT MAP 3----------------");
        carIter.stream()
                .filter(car -> car.getPassengers().size() > 2)
                .flatMap(car -> car.getPassengers().stream()
                        .map(s -> s + "is riding in a"+ car.getColor()+ "Car "))
                .forEach(System.out::println);


    }
}
