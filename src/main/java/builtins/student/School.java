package builtins.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class School {
  public static <E> void processAll(Iterable<E> ls, Consumer<E> op) {
    for (E s : ls) {
      op.accept(s);
    }
  }

  public static <E> List<E> getByCriterion(Iterable<E> ls,
                                             Predicate<E> crit) {
    List<E> out = new ArrayList<>();
    for (E s : ls) {
      if (crit.test(s)) {
        out.add(s);
      }
    }
    return out;
  }

  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Sheila", 3.8,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );
    processAll(getByCriterion(roster, s -> s.getGpa() < 3),
        s -> System.out.println(s.getName() + " studies badly here"));

    List<String> names = Arrays.asList("Fred", "Jim", "Sheila");

    processAll(getByCriterion(names, n -> n.length() < 6),
        n -> System.out.println(n + " is a short name."));

//    Predicate<LocalDate> pld = d -> d.isAfter(LocalDate.now());
//
//    processAll(getByCriterion(names, pld),
//        n -> System.out.println(n + " is a short name."));


  }
}
