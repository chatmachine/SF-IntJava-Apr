package superiterable;

import studentscleaned.students.Student;

import java.util.List;

public final class SuperSchool {
  public static void main(String[] args) {
    List<Student> schoolList = List.of(
        Student.ofNameGpaCourses("Fred", 2.2F, "Math", "Physics", "Politics", "History of Art"),
        Student.ofNameGpaCourses("Jim", 2.7F, "Math"),
        Student.ofNameGpaCourses("Tony", 2.3F, "Math", "Art", "Engineering"),
        Student.ofNameGpaCourses("Alice", 3.5F, "Math"),
        Student.ofNameGpaCourses("Sarah", 3.7F, "Math", "Philosophy", "German Literature"),
        Student.ofNameGpaCourses("Sheila", 3.8F, "Math", "Physics", "Astrophysics", "Quantum Mechanics")
    );

    SuperIterable<Student> school = new  SuperIterable<>(schoolList);

    school
        .filter(s -> s.getGpa() > 3)
        .map(s -> s.getName())
        .forEach(s -> System.out.println(s + " is smart"));
    System.out.println("==========================");
    school
        .filter(s -> s.getGpa() > 3)
        .flatMap(s -> new SuperIterable<>(s.getCourses()))
        .forEach(s -> System.out.println("> " + s));

    System.out.println("==========================");
    System.out.println("As stream....");
    schoolList.stream()
        .filter(s -> s.getGpa() > 3)
        .flatMap(s -> s.getCourses().stream())
        .forEach(s -> System.out.println("> " + s));


    // Lab solutions:
    System.out.println("1)");
    schoolList.stream()
        .forEach(s -> System.out.println(s));

    System.out.println("2)");
    schoolList.stream()
        .filter(s -> s.getGpa() > 3)
        .forEach(s -> System.out.println(s));

    System.out.println("3)");
    schoolList.stream()
        .filter(s -> s.getGpa() > 3)
        .map(s -> s.getName())
        .forEach(s -> System.out.println(s));

    System.out.println("4)");
    schoolList.stream()
        .filter(s -> s.getGpa() > 3)
        .map(s -> s.getName() + " has grade " + s.getGpa())
        .forEach(s -> System.out.println(s));

    System.out.println("5)");
    schoolList.stream()
        .flatMap(s -> s.getCourses().stream())
        .forEach(s -> System.out.println(s));

    System.out.println("6)");
    schoolList.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .forEach(s -> System.out.println(s));

    System.out.println("7)");
    schoolList.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .sorted()
        .forEach(s -> System.out.println(s));

    System.out.println("8)");
    schoolList.stream()
        .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
        .forEach(s -> System.out.println(s));

  }
}
