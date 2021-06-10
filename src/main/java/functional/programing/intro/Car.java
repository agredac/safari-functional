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


    // It's better to return the abstraction, not the concrete class
    public static CarCriterion getRedCarCriterion() {
        //  return new RedCarCriterion(); Return a new object each time is called (bad solution)
        return RED_CAR_CRITERION; //singleton pattern
    }


    private static final CarCriterion RED_CAR_CRITERION = (c) -> {
        return c.color.equals("Red");
    };

//    private static final CarCriterion RED_CAR_CRITERION =/* new  CarCriterion() {/*
//     //   @Override
//       /* public boolean test*/(/*Car*/c) ->{
//            return c.color.equals("Red");
//        }
//   /* }*/;


//    private static final CarCriterion RED_CAR_CRITERION = new  CarCriterion() {
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    };


//    private static final CarCriterion RED_CAR_CRITERION = new /*RedCarCriterion();
//
//    private static class RedCarCriterion implements*/ CarCriterion() {
//
//        @Override
//        public boolean test(Car c) {
//            return c.color.equals("Red");
//        }
//    };


    /*
    public static RedCarCriterion getRedCarCriterion() {
      //  return new RedCarCriterion(); Return a new object each time is called (bad solution)
        return RED_CAR_CRITERION; //singleton pattern
    }*/

//    public static final RedCarCriterion RED_CAR_CRITERION = new RedCarCriterion();
//
//    // It's better to return the abstraction, not the concrete class
//    public static CarCriterion getRedCarCriterion() {
//        //  return new RedCarCriterion(); Return a new object each time is called (bad solution)
//        return RED_CAR_CRITERION; //singleton pattern
//    }
//

    public static CarCriterion getGasLevelCarCriterion(int threshold) {
        return new GasLevelCarCriterion(threshold);
    }

    private static class GasLevelCarCriterion implements CarCriterion {
        private final int threshold;

        public GasLevelCarCriterion(int threshold) {
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
     * Final  3. Change to lambda
     */

    private static final Comparator<Car> gasComparator = (Car o1, Car o2) -> {
        return o1.gasLevel - o2.gasLevel;
    };


    /**
     * Final  3 Optional. Change to lambda with type parameter (Car).
     * it works because the types are getting by context "Comparator<Car>"
     */
//    private static final Comparator<Car> gasComparator = ( o1,  o2) -> {
//        return o1.gasLevel - o2.gasLevel;
//    };


    /**
     * Step  2. Change to anonymous class
     */
//    private static final Comparator<Car> gasComparator = new  Comparator<Car>(){
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.gasLevel;
//        }
//    };


    /**
     *  Step 1. Normal assign
     */
//    private static final Comparator<Car> gasComparator = new CarGasComparator();
//
//    private static class CarGasComparator implements Comparator<Car>{
//        @Override
//        public int compare(Car o1, Car o2) {
//            return o1.gasLevel - o2.gasLevel;
//        }
//    }


}
