package studentscleaned.students;

import java.util.ArrayList;
import java.util.List;

interface Criterion<E> {
  boolean test(E s);
}

public final class School {
  public static <E> Criterion<E> inverse(Criterion<E> crit) {
    return s -> !crit.test(s);
  }

  public static <E> Criterion<E> and(Criterion<E> c1, Criterion<E> c2) {
    return s -> c1.test(s) && c2.test(s);
  }

  public static <E> Criterion<E> or(Criterion<E> c1, Criterion<E> c2) {
    return s -> c1.test(s) || c2.test(s);
  }

  public static <E> void showAll(List<E> ls) {
    for (E s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("========================================");
  }

  public static <E> List<E> getByCriterion(Iterable<E> ls, Criterion<E> criterion) {
    List<E> result = new ArrayList<>();
    for (E s : ls) {
      if (criterion.test(s)) {
        result.add(s);
      }
    }
    return result;
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
    showAll(school);

    Criterion<Student> smart = Student.getSmartnessCriterion(3F);
    System.out.println("Smart: ");
    showAll(getByCriterion(school, smart));
    Criterion<Student> notSmart = inverse(smart);
    System.out.println("Not Smart: ");
    showAll(getByCriterion(school, notSmart));

    System.out.println("--------------------------------");
    Criterion<Student> enthusiastic = Student.getEnthusiasmCriterion();
    System.out.println("Enthusiastic: ");
    showAll(getByCriterion(school, enthusiastic));
    Criterion<Student> notEnthusiastic = inverse(enthusiastic);
    System.out.println("Not Enthusiastic: ");
    showAll(getByCriterion(school, notEnthusiastic));
    System.out.println("Enthusiastic and not smart");
    showAll(getByCriterion(school, and(enthusiastic, notSmart)));

    System.out.println("--------------------------------");
    List<String> ls = List.of("Fred", "Jim", "Sheila");
    showAll(getByCriterion(ls, s -> s.length() > 3));
  }
}
