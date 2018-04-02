package students;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Student {
  private String name;
  private float gpa;
  private List<String> courses;

  private Student(String name, float gpa, List<String> courses) {
    if (name == null || gpa < 0 || gpa > 4) throw new IllegalArgumentException("Bad values...");
    this.name = name;
    this.gpa = gpa;
    this.courses = courses;
  }

  public static Student ofNameGpaCourses(String name, float gpa, String ... courses) {
//    return new Student(name, gpa, Arrays.asList(courses.clone()));
    return new Student(name, gpa, Arrays.asList(courses));
  }

  public String getName() {
    return name;
  }

  public float getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return Collections.unmodifiableList(courses);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", gpa=" + gpa +
        ", courses=" + courses +
        '}';
  }

  public static StudentCriterion getSmartnessCriterion(float threshold) {
    float [] vals = { threshold };
//    threshold ++;
    System.out.println("CREATING TEST!!!");
    return s -> {
      System.out.println("EXECUTING TEST!!!");
      vals[0]++;
      return s.gpa > vals[0];
    };
  }

//  static class SmartnessCriterion implements StudentCriterion {
//    @Override
//    public boolean test(Student s) {
//      return s.getGpa() > 3.0F;
//    }
//  }
//
  private static final StudentCriterion enthusiasmCriterion =
      s -> s.getCourses().size() > 3 ;

//  private static final StudentCriterion enthusiasmCriterion =
//      /*(Student*/ s/*)*/ -> /*{ return*/ s.getCourses().size() > 3/*; }*/;
//
//  private static final StudentCriterion enthusiasmCriterion =
//      (Student s) -> { return s.getCourses().size() > 3; };
//
//  private static final StudentCriterion enthusiasmCriterion = /*new StudentCriterion() {*/
////    @Override
//    /*public boolean test*/ (Student s) -> {
//      return s.getCourses().size() > 3;
//    }
//  /*}*/;
//
//  private static final StudentCriterion enthusiasmCriterion = new /*EnthusiasmCriterion();
//
//  private static class EnthusiasmCriterion implements */ StudentCriterion() {
//    @Override
//    public boolean test(Student s) {
//      return s.getCourses().size() > 3;
//    }
//  };

//  private static final StudentCriterion enthusiasmCriterion = new EnthusiasmCriterion();
//
//  private static class EnthusiasmCriterion implements StudentCriterion {
//    @Override
//    public boolean test(Student s) {
//      return s.getCourses().size() > 3;
//    }
//  }
//
  public static StudentCriterion getEnthusiasmCriterion() {
    return enthusiasmCriterion;
  }


}
