package functional.programing.streams;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Averager {
    private double total;
    private long count;

    public Averager() {
    }

    public void include(double d) {
        total += d;
        count++;
    }

    public void merge(Averager other) {
        total += other.total;
        count += other.count;
    }

    public double get() {

        return total / count;
    }
}

public class CollectAverage {


    public static void main(String[] args) {

        /**
         *
         * To compute average, we need
         *  1. Count
         *  2. Sum
         *
         *  and 3 Operations
         *
         *  1. Create Bucket (make average object)
         *  2. Include a new data item from the stream ( count +=1, sum +=data)
         *  3. Merge two buckets (count += other_bucket, sum += other_sum)
         */

        runSingleThread();
        runMultipleThreads();
    }

    private static void runSingleThread() {
        long start = System.nanoTime();
        Averager collect = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .limit(4_000_000_000L)
                .collect(() -> new Averager(),
                        (averager, value) -> averager.include(value),
                        (averager, averager2) -> averager.merge(averager2));
        long end = System.nanoTime();
        System.out.println("Average is: "+collect.get() +  " computation took "
                + ((end-start)/1_000_000L)+ " ms");
    }

    private static void runMultipleThreads() {
        long start = System.nanoTime();

//        Averager collect = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
//                .parallel()
//                .limit(4_000_000_000L)
       // .map(x-> Math.sin(x))
//                .collect(() -> new Averager(),
//                        (averager, value) -> averager.include(value),
//                        (averager, averager2) -> averager.merge(averager2));
//        long end = System.nanoTime();


        Averager collect = DoubleStream.generate(() -> ThreadLocalRandom.current().nextDouble(-Math.PI, Math.PI))
                .parallel()
                .limit(4_000_000_000L)
                .map(Math::sin)
                .collect(Averager::new,
                        Averager::include,
                        Averager::merge);
        long end = System.nanoTime();

        System.out.println("Average in parallel is: "+collect.get() +  " computation took "
        + ((end-start)/1_000_000L)+ " ms");
    }
}
