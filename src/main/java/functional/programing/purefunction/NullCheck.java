package functional.programing.purefunction;

import functional.programing.intro.Car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NullCheck {

    public static void main(String[] args) {

        Map<String, Car> owners = new HashMap<>();
        owners.put("Sheila", Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"));
        owners.put("Librarian", Car.withGasColorPassengers(6, "Red", "Jim", "Sheila"));
        owners.put("Ogg", Car.withGasColorPassengersAndTrunk(6, "Red", "Jim", "Sheila"));


        /*
         * 1. This code will fail due to the fact owner named "TEST" not exist, and getTrunkContents will return null

        String owner = "TEST";
        Car car = owners.get(owner);
        List<String> trunkItems = car.getTrunkContents();
        System.out.println(trunkItems);/*

        /**
         * 2. To void the #1 scenario , we must to validate if: 1. Car exists and 2. if the car has content
         */

        String owner = "TEST";
        Car car = owners.get(owner);
        if (car != null) {
            List<String> trunkItems = car.getTrunkContents();
            if (trunkItems != null)
                System.out.println(trunkItems);
        }

        /**
         * 3. The better approach is to use Optional, if the content no exist (is null) the code doesn't throw a null pointer exception
         */
        String owner2 = "Ogg";
        Optional<Map<String, Car>> ownersOpt = Optional.of(owners);
        ownersOpt
                .map(o -> o.get(owner2)) //Return an Optionl of Car if the owner is present in the map
                .map(c -> c.getTrunkContents()) // Return an Option on List<Sting> if the Car of the owner, has trunk contents
                .map(tc -> owner2 + " has " + tc + " in the Car") // Create a new message
                .ifPresent(m -> System.out.println(m));


        /**
         * 4. Using Optional as type of Car Object. (TrunkContet)
         */
        String owner3 = "Sheila";
        Optional<Map<String, Car>> ownersOpt2 = Optional.of(owners);
        ownersOpt2
                .map(o -> o.get(owner3)) //Return an Optionl of Car if the owner is present in the map
                .map(c -> c.getTrunkContentsOpt()
                        .map(l -> l.toString())
                        .orElse("nothing"))// Return an Option on List<Sting> if the Car of the owner, has trunk contents
                .map(tc -> owner2 + " has " + tc + " in the Car") // Create a new message
                .ifPresent(m -> System.out.println(m));


    }


}
