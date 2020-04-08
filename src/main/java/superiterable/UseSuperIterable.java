package superiterable;

import builtins.student.Student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class UseSuperIterable {
  public static <E, F> void complexProcess(List<E> data,
                                           Predicate<E> p, Function<E, F> f, Consumer<F> c) {
    //...
  }

  public static boolean isInterestingStudent(
      Student s, Predicate<Student> p) {
    return p.test(s);
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

//    for (Student s : rosterIter) {
//      System.out.println("> " + s);
//    }

    rosterIter
        .filter(s -> s.getGpa() > 3)
        .map(s -> s.getName() + " has grade " + s.getGpa())
        .forEach(s -> System.out.println("> " + s));

    rosterIter
        .flatMap(s -> new SuperIterable(s.getCourses()))
        .forEach(s -> System.out.println(s));

    Function<Student, SuperIterable<String>> extractStudentCoursePairings =
        s -> new SuperIterable(s.getCourses())
            .map(c -> s.getName() + " takes " + c);

    rosterIter
        .flatMap(extractStudentCoursePairings)
        .forEach(s -> System.out.println(s));

    rosterIter
        .filter(Student.getUnSmartCriterion(3.5))
        .forEach(s -> System.out.println(s));

    Student f = roster.get(0);

    // HOW NOT TO USE LAMBDAS!!!
//    if (((Predicate<Student>)s -> s.getGpa() > 3).test(f)) {}
//    if (isInterestingStudent(f, s -> s.getGpa() > 3)) {}

  }
}
