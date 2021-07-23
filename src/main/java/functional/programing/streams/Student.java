package functional.programing.streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Student {

    private static final NavigableMap<Integer, String> gradeLetters = new TreeMap<>();

    static {
        gradeLetters.put(90, "A");
        gradeLetters.put(80, "B");
        gradeLetters.put(70, "C");
        gradeLetters.put(60, "D");
        gradeLetters.put(50, "E");
        gradeLetters.put(0, "F");

    }

    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getLetterGrade() {

        return gradeLetters.floorEntry(score).getValue();
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ", " + score + "%, grade is " + getLetterGrade();
    }

    public static void main(String[] args) {
        List<Student> school = Arrays.asList(
                new Student("Fred", 71),
                new Student("Jim", 38),
                new Student("Sheila", 97),
                new Student("Ogg", 100),
                new Student("Magrat", 56)
        );

        school.forEach(student -> System.out.println(student));
        System.out.println("------------------------------------");
        Map<String, List<Student>> table = school.stream()
                .collect(Collectors.groupingBy(student -> student.getLetterGrade()));

        table.entrySet().stream()
                .forEach(e -> System.out.println("Students " + e.getValue() +
                        "have grade " + e.getKey()));


        System.out.println("-----------------compare manual descending-------------------");
        //compare manual descending
        Comparator<Map.Entry<String, List<Student>>> comparatorManualDes = (o1, o2) -> o2.getKey().compareTo(o1.getKey());
        table.entrySet().stream()
                .sorted(comparatorManualDes)
                .forEach(e -> System.out.println("Students " + e.getValue() +
                        "have grade " + e.getKey()));


        System.out.println("--------------compare auto descending----------------------");
        //compare auto descending
        Comparator<Map.Entry<String, List<Student>>> comparatorAuto = Map.Entry.comparingByKey();
        table.entrySet().stream()
                .sorted(comparatorAuto.reversed())
                .forEach(e -> System.out.println("Students " + e.getValue() +
                        "have grade " + e.getKey()));

        System.out.println("---------------grouping and counting---------------------");
        Map<String, Long> table2 = school.stream()
                .collect(Collectors.groupingBy(
                        s -> s.getLetterGrade(),
                        Collectors.counting()
                ));

        table2.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(s -> System.out.println(s.getValue() + " students got grade" + s.getKey()));


        /**
         *  1. ording by grade
         *  2. names of studentes  fred, jim, shila, "achive grade" $grade (letter)
         * 3. need two downstream collectors in a chain to do it
         *
         * 3.1. one of them will have to take the student and extract the name
         * 3.2 the other will concatenate string values
         *
         * Collectors.mapping
         * Collectors.join
         *
         */

        System.out.println("------------------------------------");
        Map<String, String> collect =
                school.stream()
                .collect(Collectors.groupingBy(
                                                s -> s.getLetterGrade(),
                                                Collectors.mapping(
                                                                     student -> student.getName(),
                                                                      Collectors.joining(",")
                                                                    )
                                                )
                );


        collect.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.println(e.getValue() + " > " + e.getKey()));

    }
}
