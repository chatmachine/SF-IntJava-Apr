package collections;

import studentscleaned.students.Student;

import java.util.List;
import java.util.stream.Collectors;

public final class StudentCollections {

  public static String gradeLetter(Student s) {
    float gpa = s.getGpa();
    if (gpa > 3.6) return "A";
    else if (gpa > 3.4) return "B";
    else if (gpa > 3.0) return "C";
    else if (gpa > 2.6) return "D";
    return "E";
  }

  public static void main(String[] args) {
    List<Student> school = List.of(
        Student.ofNameGpaCourses("Fred", 2.2F, "Math", "Physics", "Politics", "History of Art"),
        Student.ofNameGpaCourses("Jim", 2.7F, "Math"),
        Student.ofNameGpaCourses("Tony", 2.3F, "Math", "Art", "Engineering"),
        Student.ofNameGpaCourses("Alice", 3.5F, "Math"),
        Student.ofNameGpaCourses("Sarah", 3.7F, "Math", "Philosophy", "German Literature"),
        Student.ofNameGpaCourses("Sheila", 3.8F, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );
  school.stream()
      .collect(Collectors.groupingBy(s -> StudentCollections.gradeLetter(s)))
      .forEach((k, v) -> System.out.println("Grade " + k + " achieved by " + v));

  school.stream()
      .collect(Collectors.groupingBy(
          s -> StudentCollections.gradeLetter(s),
          Collectors.counting()))
      .forEach((k, v) -> System.out.println("Grade " + k + " achieved by " + v + " students"));



  }
}
