package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

class IsSmart implements StudentCriterion {
  public boolean test(Student s) {
    return s.getGpa() > 3;
  }
}

class IsEnthusiastic implements StudentCriterion {
  public boolean test(Student s) {
    return s.getCourses().size() > 2;
  }
}

public class School {
  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------");
  }

  //  public static List<Student> getSmartStudents(List<Student> ls,
//                                               double threshold) {
//    List<Student> out = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() > threshold) {
//        out.add(s);
//      }
//    }
//    return out;
//  }
// GoF design pattern "Command" -- uses "pointer to an object" as
//  a pointer to behavior (or pointer to a function)
// "higher order function"
  public static List<Student> getByCriterion(List<Student> ls,
                                             StudentCriterion crit) {
    List<Student> out = new ArrayList<>();
    for (Student s : ls) {
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
//    showAll(getSmartStudents(roster, 2.0));
//    showAll(getEnthusiasticStudents(roster, 2));
    showAll(getByCriterion(roster, new IsSmart()));
    showAll(getByCriterion(roster, new IsEnthusiastic()));
    showAll(getByCriterion(roster,
        // Java 1.1 "anonymous inner class"
        new /*class IsSmart implements*/ StudentCriterion() {
          public boolean test(Student s) {
            return s.getGpa() < 3;
          }
        }));


  }
}
