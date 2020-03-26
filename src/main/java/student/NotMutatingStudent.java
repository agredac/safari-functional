package student;

import java.util.Arrays;

public class NotMutatingStudent {
  public static void main(String[] args) {
    Student fred = Student.of("Fred", 3.2, "Math", "Physics");
    Student newFred  = Arrays.asList(fred)
        .stream()
        .map(s -> s.changeGrade(3.9))
        .findFirst()
        .get();

    System.out.println("Fred is " + fred);
    System.out.println("newFred is " + newFred);
    System.out.println("Fred == newFred " + (fred == newFred));
  }
}
