package lambdaapis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Examples {
  public static void main(String[] args) {
    List<String> words = new ArrayList<>(List.of("hello", "goodbye", "short", "reallyverylong"));
    words.forEach(s -> System.out.println(s));
    System.out.println("------------------------");
    words.replaceAll(s -> s.toUpperCase());
    words.forEach(s -> System.out.println(s));
    System.out.println("------------------------");
    words.removeIf(s -> s.length() < 6);
    words.forEach(s -> System.out.println(s));
    System.out.println("------------------------");


    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    LocalDate yesterday = today.minusDays(1);
    Map<LocalDate, String> workCal = new HashMap<>(Map.of(
        today, "Clear desk",
        tomorrow, "Leave early"
    ));

    Map<LocalDate, String> homeCal = Map.of(
        yesterday, "Buy party food",
        tomorrow, "Party all night long!"
    );

    workCal.forEach((k,v) -> System.out.printf("On %1$tm/%0$te %1s\n", k, v ));
    System.out.println("----------------------------");

    workCal.compute(today, (ld, s) -> s + " carefully so boss doesn't complain!");
    workCal.forEach((k,v) -> System.out.printf("On %1$tm/%0$te %1s\n", k, v ));
    System.out.println("----------------------------");

    Map<LocalDate, String> combinedCal = new HashMap<>();
    workCal.forEach((k,v) -> combinedCal.put(k, v));
    homeCal.forEach((k,v) -> combinedCal.merge(k, v, (k1, v1) -> v + " and " + v1));
    combinedCal.forEach((k,v) -> System.out.printf("On %1$tm/%0$te %1s\n", k, v ));
  }
}
