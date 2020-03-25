package superiterable;

import builtins.student.Student;

import java.util.Arrays;
import java.util.List;

public class UseSuperIterable {
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
        .forEvery(s -> System.out.println("> " + s));
  }
}
