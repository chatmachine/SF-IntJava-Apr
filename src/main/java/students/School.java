package students;

import java.util.ArrayList;
import java.util.List;

interface StudentCriterion {
  boolean test(Student s);
}

interface Silly {
  boolean daft(Student s);
}

//class SmartnessCriterion implements StudentCriterion {
//  @Override
//  public boolean test(Student s) {
//    return s.getGpa() > 3.0F;
//  }
//}
//
//class EnthusiasmCriterion implements StudentCriterion {
//  @Override
//  public boolean test(Student s) {
//    return s.getCourses().size() > 3;
//  }
//}
//
public final class School {
  public static StudentCriterion inverse(StudentCriterion crit) {
    return s -> !crit.test(s);
  }

  public static StudentCriterion and(StudentCriterion c1, StudentCriterion c2) {
    return s -> c1.test(s) && c2.test(s);
  }

  public static StudentCriterion or(StudentCriterion c1, StudentCriterion c2) {
    return s -> c1.test(s) || c2.test(s);
  }

  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("========================================");
  }
// BAD!!! Mixes selection of smart students with what to do with them!
//  public static void showAllSmart(List<Student> ls) {
//    for (Student s : ls) {
//      if (s.getGpa() > 3) {
//        System.out.println("> " + s);
//      }
//    }
//    System.out.println("========================================");
//  }

  public static List<Student> getByCriterion(Iterable<Student> ls, StudentCriterion criterion) {
    List<Student> result = new ArrayList<>();
    for (Student s : ls) {
      if (criterion.test(s)) {
        result.add(s);
      }
    }
    return result;
  }

  public static List<Student> getSmart(Iterable<Student> ls, float threshold) {
    List<Student> result = new ArrayList<>();
    for (Student s : ls) {
      if (s.getGpa() > threshold) {
        result.add(s);
      }
    }
    return result;
  }

  public static List<Student> getEnthusiastic(Iterable<Student> ls, int threshold) {
    List<Student> result = new ArrayList<>();
    for (Student s : ls) {
      if (s.getCourses().size() > threshold) {
        result.add(s);
      }
    }
    return result;
  }

//  public static List<Student> getFairlySmart(Iterable<Student> ls) {
//    List<Student> result = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGpa() > 2.6) {
//        result.add(s);
//      }
//    }
//    return result;
//  }
//
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
    showAll(getSmart(school, 3.0F));
//    showAll(getFairlySmart(school));
    showAll(getSmart(school, 2.6F));
    showAll(getEnthusiastic(school, 1));

    System.out.println("Using behavior parameter");
    showAll(getByCriterion(school, Student.getSmartnessCriterion(2.6F)));
    showAll(getByCriterion(school, Student.getEnthusiasmCriterion()));
    showAll(getByCriterion(school, s -> s.getCourses().size() > 3));

    StudentCriterion x = s -> s.getCourses().size() > 3;
//    ((Silly)(s -> s.getCourses().size() > 3)).test(school.get(0)) ;
//    ((StudentCriterion)(s -> s.getCourses().size() > 3)).test(school.get(0)) ;

    System.out.println("--------------------------------");
    StudentCriterion smart = Student.getSmartnessCriterion(3F);
    System.out.println("Smart: ");
    showAll(getByCriterion(school, smart));
    StudentCriterion notSmart = inverse(smart);
    System.out.println("Not Smart: ");
    showAll(getByCriterion(school, notSmart));

    System.out.println("--------------------------------");
    StudentCriterion enthusiastic = Student.getEnthusiasmCriterion();
    System.out.println("Enthusiastic: ");
    showAll(getByCriterion(school, enthusiastic));
    StudentCriterion notEnthusiastic = inverse(enthusiastic);
    System.out.println("Not Enthusiastic: ");
    showAll(getByCriterion(school, notEnthusiastic));
    System.out.println("Enthusiastic and not smart");
    showAll(getByCriterion(school, and(enthusiastic, notSmart)));
  }


}
