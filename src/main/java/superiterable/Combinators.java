package superiterable;

import builtins.student.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Combinators {

  public static <E> Predicate<E> negate(Predicate<E> pred) {
    return e -> !pred.test(e);
  }

  public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
    return e -> first.test(e) && second.test(e);
  }

  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Sheila", 3.8,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );
    List<String> names = Arrays.asList("Fred", "Jim", "Sheila");

    SuperIterable<Student> rosterIter = new SuperIterable<>(roster);

    Predicate<Student> smart = s -> s.getGpa() > 3.5;
    Predicate<Student> enthusiastic = s -> s.getCourses().size() > 1;
    System.out.println("Smart");
    rosterIter.filter(smart).forEach(s -> System.out.println(s));

    System.out.println("Not Smart");
    rosterIter.filter(negate(smart)).forEach(s -> System.out.println(s));

    System.out.println("Not Smart and enthusiastic");
    rosterIter.filter(and(enthusiastic, negate(smart))).forEach(s -> System.out.println(s));

    System.out.println("Not Smart and enthusiastic");
    rosterIter
        .filter(enthusiastic.and(smart.negate()))
        .forEach(s -> System.out.println(s));


    Comparator<Student> gpaOrder = Comparator.comparing(s -> s.getGpa());
    Comparator<Student> revOrder = gpaOrder.reversed();
    roster.sort(revOrder);
    roster.forEach(s -> System.out.println(s));
  }
}
