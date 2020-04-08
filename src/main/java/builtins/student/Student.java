package builtins.student;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  private Student(){}
  public static Student of(String name, double gpa, String ... courses) {
    Student self = new Student();
    self.name = name;
    self.gpa = gpa;
    self.courses = Arrays.asList(courses);
    return self;
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  public static Predicate<Student> getUnSmartCriterion(
      /*final*/ double threshold) {
//    threshold++;
    return s -> {
      return s.gpa < threshold; // closure
    };
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }
}
