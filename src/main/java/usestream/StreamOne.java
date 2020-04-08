package usestream;

import builtins.student.Student;
import superiterable.SuperIterable;

import java.util.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOne {

  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Sheila", 3.8,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );
    List<String> names = Arrays.asList("Fred", "Jim", "Sheila");

    names.stream()
        .filter(s -> s.length() > 4)
        .forEach(s -> System.out.println(s));

    System.out.println("---------------");
    Stream<Student> ss = roster.stream();
    ss.peek(s -> System.out.println("peeking... " + s))
        .filter(s -> s.getGpa() > 3)
        .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
        .forEach(s -> System.out.println(s))
    ;
    ss.close(); // only needed for OS resources like files and network

    roster.stream().forEach(s -> System.out.println(s));
    System.out.println("---------------------");

    DoubleStream.generate(() -> Math.random())
        .limit(10)
        .filter(d -> d > 0.7)
        .mapToObj(d -> "The value " + d + " is greater than 0.7")
        .forEach(d -> System.out.println(d));

    boolean allBig = DoubleStream.generate(() -> Math.random())
        .allMatch(d -> d > 0.1);
    System.out.println("All big? " + allBig);

    Optional<Student> student = roster.stream()
        .max(Comparator.comparing(s -> s.getName()));
    student.ifPresent(s -> System.out.println("Last alphabetic name is " + s));

    System.out.println("---------------------");

    OptionalInt res = IntStream.iterate(1, x -> x + 1)
        .limit(10)
        .reduce((x1, x2) -> x1 + x2);
    res.ifPresent(x -> System.out.println("Sum is " + x));
  }
}
