package usestream;

import builtins.student.Student;

import java.util.Arrays;
import java.util.List;

public class StudentAverages {
  public static void main(String[] args) {
    List<Student> roster = Arrays.asList(
        Student.of("Fred", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Sheila", 3.8,
            "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    roster.stream()
        .reduce(new Average(0, 0),
            (a, s) -> a.include(s.getGpa()),
            (a1, a2) -> a1.merge(a2))
    .get()
    .ifPresent(d -> System.out.println("Average grade is " + d));
    ;
  }
}
