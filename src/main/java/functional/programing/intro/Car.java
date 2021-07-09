package functional.programing.intro;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class Car {

    private final int gasLevel;
    private final String color;
    private final List<String> passengers;
    private final List<String> trunkContents;

    private Car(int gasLevel, String color, List<String> passengers, List<String> trunkContents) {
        this.gasLevel = gasLevel;
        this.color = color;
        this.passengers = passengers;
        this.trunkContents = trunkContents;
    }

    public static Car withGasColorPassengers(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, null);
        return self;
    }

    public static Car withGasColorPassengersAndTrunk(int gas, String color, String... passengers) {
        List<String> p = Collections.unmodifiableList(Arrays.asList(passengers));
        Car self = new Car(gas, color, p, Arrays.asList("jack", "wrench", "spare wheel"));
        return self;
    }

    public Car addGas(int g){

        return new Car(gasLevel+g,color,passengers,trunkContents);
    }

    public int getGasLevel() {
        return gasLevel;
    }

    public String getColor() {
        return color;
    }

    public List<String> getPassengers() {
        return passengers;
    }

    public List<String> getTrunkContents() {
        return trunkContents;
    }

    @Override
    public String toString() {
        return "Car{" + "gasLevel=" + gasLevel + ", color=" + color + ", passengers=" + passengers
                + (trunkContents != null ? ", trunkContents=" + trunkContents : " no trunk") + '}';
    }


    public static Predicate<Car> getColorCriterion(String... colors) {

        Set<String> colorSet = new HashSet<>(Arrays.asList(colors));

        return c -> colorSet.contains(c.color);
    }

    /**
     * 2. Second option to given context to lambda expression:  By Return type
     */
    public static Predicate<Car> getFourPassengerCriterion() {
        return c -> c.passengers.size() == 4;
    }

    // It's better to return the abstraction, not the concrete class
    public static Predicate<Car> getRedCarCriterion() {
        //  return new RedCarCriterion(); Return a new object each time is called (bad solution)
        return RED_CAR_PREDICATE; //singleton pattern
    }


    /**
     * 1. First option to given context to lambda expression:  By Assign
     */
    private static final Predicate<Car> RED_CAR_PREDICATE = c -> c.color.equals("Red");


    public static Predicate<Car> getGasLevelCarCriterion(int threshold) {
        return c -> c.gasLevel >= threshold;
    }

//    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
//        return new GasLevelCriterion(threshold);
//    }
//
//    private static class GasLevelCriterion implements Criterion<Car> {
//        private final int threshold;
//
//        public GasLevelCriterion(int threshold) {
//            this.threshold = threshold;
//        }
//
//        @Override
//        public boolean test(Car c) {
//            return c.gasLevel >= threshold;
//        }
//    }




    public static Comparator<Car> getGasComparator() {
        return gasComparator;
    }

    /**
     * Creating lambda block
     */
    private static final Comparator<Car> gasComparator = (o1, o2) -> o1.gasLevel - o2.gasLevel;


}
