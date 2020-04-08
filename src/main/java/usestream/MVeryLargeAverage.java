package usestream;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

public class MVeryLargeAverage {
  public static void main(String[] args) {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(250_000_000L, -Math.PI, +Math.PI)
        .parallel()
//        .boxed()
        .map(x -> Math.sin(x))
        .collect(() -> new MAverage(),
            (a, d) -> a.include(d),
            (a1, a2) -> a1.merge(a2))
        .get()
        .ifPresent(d -> System.out.println("Average is " + d));
    long time = System.nanoTime() - start;
    System.out.printf("Time was %7.3f\n", time / 1_000_000_000.0);
  }
}
