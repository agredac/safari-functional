package student;

import java.util.Arrays;
import java.util.List;

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

  public Student changeGrade(double gpa) {
    Student self = new Student();
    self.name = this.name;
    self.gpa = gpa;
    self.courses = this.courses;
    return self;
  }
  public static StudentCriterion getSmartCriterion() {
    return s -> {
      return s.getGpa() < 3;
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
