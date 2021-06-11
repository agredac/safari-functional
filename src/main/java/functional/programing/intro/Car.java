package functional.programing.intro;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    /**
     * 2. Second option to given context to lambda expression:  By Return type
     *
     */
    public static Criterion<Car> getFourPassengerCriterion(){
        return c-> c.passengers.size() ==4;
    }

    // It's better to return the abstraction, not the concrete class
    public static Criterion<Car> getRedCarCriterion() {
        //  return new RedCarCriterion(); Return a new object each time is called (bad solution)
        return RED_CAR_CRITERION; //singleton pattern
    }


    /**
     * 1. First option to given context to lambda expression:  By Assign
     *
     */
    private static final Criterion<Car> RED_CAR_CRITERION = c ->  c.color.equals("Red");

    public static Criterion<Car> getGasLevelCarCriterion(int threshold) {
        return new GasLevelCriterion(threshold);
    }

    private static class GasLevelCriterion implements Criterion<Car> {
        private final int threshold;

        public GasLevelCriterion(int threshold) {
            this.threshold = threshold;
        }

        @Override
        public boolean test(Car c) {
            return c.gasLevel >= threshold;
        }
    }


    public static Comparator<Car> getGasComparator() {
        return gasComparator;
    }

    /**
     * Creating lambda block
     */
    private static final Comparator<Car> gasComparator = (o1, o2) -> o1.gasLevel - o2.gasLevel;



}
