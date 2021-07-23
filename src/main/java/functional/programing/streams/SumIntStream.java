package functional.programing.streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class SumIntStream {

    public static void main(String[] args) {

        OptionalInt optionalInt = IntStream.iterate(0, value -> value + 1)
                .limit(100)
                .reduce((a, b) -> a + b);
        //      .forEach(value -> System.out.println(" >"+value));

        optionalInt.ifPresent(res -> System.out.println("sum is: "+res));

    }
}
